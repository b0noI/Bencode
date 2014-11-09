package com.bencode.serialization.serializator.referance;


import com.bencode.common.FieldHelper;
import com.bencode.common.TypeHelper;
import com.bencode.serialization.model.BencodeList;
import com.bencode.serialization.model.ByteString;
import com.bencode.serialization.model.Dict;
import com.bencode.serialization.model.IBEncodeElement;
import com.bencode.serialization.serializator.primitive.IPrimitiveSerializator;
import com.sun.xml.internal.ws.encoding.soap.SerializationException;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

class RecursiveObjectSerealizator {

    private       Integer                       currentId               = 0;

    private final Map<ObjectKey, Integer>       serializedObjectsIds    = new HashMap<>();

    private final Map<Integer, IBEncodeElement> serializedObjects       = new HashMap<>();

    public static IBEncodeElement serialize(final Object instance) {
        final RecursiveObjectSerealizator referanceFiledSerializator = new RecursiveObjectSerealizator();
        try {
            referanceFiledSerializator.serializeRecursive(instance);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new SerializationException(e);
        }
        final Dict result = new Dict();
        referanceFiledSerializator.serializedObjects.keySet().forEach(key -> {
            final ByteString serializedKey = IPrimitiveSerializator.Type.INTEGER.getSerializator().serialize(key);
            final IBEncodeElement serializedValue = referanceFiledSerializator.serializedObjects.get(key);
            result.putValue(serializedKey, serializedValue);
        });
        return result;
    }

    private int serializeRecursive(final Object instance) throws IllegalAccessException {

        if (instance == null) return -1;

        final ObjectKey objectKey = new ObjectKey(instance);
        final int       currentId = getCurrentId();

        serializedObjectsIds.put(objectKey, currentId);

        final Dict result = new Dict();
        final Field[] fields = instance.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);

            if (!FieldHelper.shouldBeSerialized(field)) continue;

            final ByteString key = ByteString.buildElement(field.getName().getBytes());
            final IBEncodeElement serializedField = serializeField(field, instance);
            result.putValue(key, serializedField);

        }

        final String typeKey = "$CLASS_TYPE";
        ByteBuffer byteBuffer = ByteBuffer.allocate(typeKey.length());
        for (char ch : typeKey.toCharArray()) {
            byteBuffer.put((byte)ch);
        }
        final ByteString byteStringKey = ByteString.buildElement(byteBuffer.array());

        final String className = instance.getClass().getName();
        ByteBuffer byteBufferForCLassName = ByteBuffer.allocate(className.length());
        for (char ch : className.toCharArray()) {
            byteBufferForCLassName.put((byte)ch);
        }
        final ByteString byteStringValue = ByteString.buildElement(byteBufferForCLassName.array());
        result.putValue(byteStringKey, byteStringValue);

        serializedObjects.put(currentId, result);
        return currentId;
    }

    private IBEncodeElement serializeField(final Field field, final Object instance) throws IllegalAccessException {
        if (field.getType().isArray()) {
            return serializeArrayField(field, instance);
        } else {
            return serializeNonArrayField(field, instance);
        }
    }

    private IBEncodeElement serializeNonArrayField(final Field field, final Object instance) throws IllegalAccessException {
        if (field.getType().isPrimitive() || typeCanBeUnboxedToPrimitive(field.getType())) {
            final PrimitiveTypeFieldSerializator primitiveFieldSerializator = new PrimitiveTypeFieldSerializator(instance);
            return primitiveFieldSerializator.serialize(field);
        } else {
            final Object fieldObj = field.get(instance);
            return serializeReferance(fieldObj);
        }
    }

    private boolean typeCanBeUnboxedToPrimitive(final Class type) {
        return type == Byte.class ||
                type == Short.class ||
                type == Integer.class ||
                type == Long.class ||
                type == Float.class  ||
                type == Double.class;
    }

    private IBEncodeElement serializeArrayField(final Field field, final Object instance) throws IllegalAccessException {
        final Class componentType = TypeHelper.getComponentType(field.getType());
        if (componentType.isPrimitive() || typeCanBeUnboxedToPrimitive(componentType)) {
            return new PrimitiveArrayFieldSerializator(instance).serialize(field);
        } else {
            final Object[] values = (Object[])field.get(instance);
            final BencodeList tmpResult = new BencodeList();
            for (Object element : values) {
                tmpResult.add(serializeReferance(element));
            }
            return tmpResult;
        }
    }

    private IBEncodeElement serializeReferance(final Object target) throws IllegalAccessException {
        final ObjectKey fieldObjectKey = new ObjectKey(target);
        final int objectId = serializedObjectsIds.containsKey(fieldObjectKey) ?
                serializedObjectsIds.get(fieldObjectKey) :
                serializeRecursive(target);
        return IPrimitiveSerializator.Type.INTEGER.getSerializator().serialize(objectId);
    }

    private int getCurrentId() {
        return currentId++;
    }

    private static class ObjectKey {

        private final Object instance;

        private ObjectKey(Object instance) {
            this.instance = instance;
        }

        @Override
        public boolean equals(Object o) {
            return o == this;
        }

        @Override
        public int hashCode() {
            return System.identityHashCode(instance);
        }

    }

}
