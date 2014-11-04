package com.bencode.serialization.serializator.referance;


import com.bencode.serialization.model.BencodeList;
import com.bencode.serialization.model.ByteString;
import com.bencode.serialization.model.Dict;
import com.bencode.serialization.model.IBEncodeElement;
import com.bencode.serialization.serializator.primitive.IPrimitiveSerializator;
import com.sun.xml.internal.ws.encoding.soap.SerializationException;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

class RecursiveObjectSerializator {

    private       Integer                       currentId               = 0;

    private final Map<ObjectKey, Integer>       serializedObjectsIds    = new HashMap<>();

    private final Map<Integer, IBEncodeElement> serializedObjects       = new HashMap<>();

    public static IBEncodeElement serialize(final Object instance) {
        final RecursiveObjectSerializator referanceFiledSerializator = new RecursiveObjectSerializator();
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

        final ObjectKey objectKey = new ObjectKey(instance);
        final int       currentId = getCurrentId();
        serializedObjectsIds.put(objectKey, currentId);

        final Dict result = new Dict();
        final Field[] fields = instance.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);

            if (!shouldBeSerialized(field)) continue;

            final ByteString key = ByteString.buildElement(field.getName().getBytes());
            final IBEncodeElement serializedField = serializeField(field, instance);

        }
        serializedObjects.put(currentId, result);
        return currentId;
    }

    private IBEncodeElement serializeField(final Field field, final Object instance) throws IllegalAccessException {
        if (field.getType().isArray()) {
            final Class componentType = field.getType().getComponentType();
            if (componentType.isPrimitive()) {
                return new PrimitiveArrayFieldSerializator(instance).serialize(field);
            } else {
                final Object[] values = (Object[])field.get(instance);
                final BencodeList tmpResult = new BencodeList();
                for (Object element : values) {
                    final ObjectKey fieldObjectKey = new ObjectKey(element);
                    final int objectId = serializedObjectsIds.containsKey(fieldObjectKey) ?
                            serializedObjectsIds.get(fieldObjectKey) :
                            serializeRecursive(element);
                    tmpResult.add(IPrimitiveSerializator.Type.INTEGER.getSerializator().serialize(objectId));
                }
                return tmpResult;
            }
        }
        final PrimitiveTypeFieldSerializator primitiveFieldSerializator = new PrimitiveTypeFieldSerializator(instance);
        if (field.getType().isPrimitive()) {
            return primitiveFieldSerializator.serialize(field);
        } else {
            final Object fieldObj = field.get(instance);
            final ObjectKey fieldObjectKey = new ObjectKey(fieldObj);
            final int objectId = serializedObjectsIds.containsKey(fieldObjectKey) ?
                    serializedObjectsIds.get(fieldObjectKey) :
                    serializeRecursive(fieldObj);
            return IPrimitiveSerializator.Type.INTEGER.getSerializator().serialize(objectId);
        }
    }

    private boolean shouldBeSerialized(final Field field) {
        final int foundMods = field.getModifiers();
        final int transcientMod = Modifier.TRANSIENT;
        if ((foundMods & transcientMod) == transcientMod) {
            return false;
        }
        return true;
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
