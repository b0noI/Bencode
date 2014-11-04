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
        final PrimitiveTypeFieldSerializator primitiveFieldSerializator = new PrimitiveTypeFieldSerializator(instance);
        final Field[] fields = instance.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            int foundMods = field.getModifiers();
            int transcientMod = Modifier.TRANSIENT;
            if ((foundMods & transcientMod) == transcientMod) {
                continue;
            }
            final ByteString key = ByteString.buildElement(field.getName().getBytes());
            if (field.getType().isArray()) {
                final Class componentType = field.getType().getComponentType();
                if (componentType.isPrimitive()) {
                    result.putValue(key, new PrimitiveArrayFieldSerializator(instance).serialize(field));
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
                    result.putValue(key, tmpResult);
                }
            }
            if (field.getType().isPrimitive()) {
                result.putValue(key, primitiveFieldSerializator.serialize(field));
            } else {
                final Object fieldObj = field.get(instance);
                final ObjectKey fieldObjectKey = new ObjectKey(fieldObj);
                final int objectId = serializedObjectsIds.containsKey(fieldObjectKey) ?
                        serializedObjectsIds.get(fieldObjectKey) :
                        serializeRecursive(fieldObj);
                result.putValue(key, IPrimitiveSerializator.Type.INTEGER.getSerializator().serialize(objectId));
            }
        }
        serializedObjects.put(currentId, result);
        return currentId;
    }

    private int getCurrentId() {
        return currentId++;
    }

    private static class ObjectKey {

        private final Object intsnace;

        private ObjectKey(Object intsnace) {
            this.intsnace = intsnace;
        }

        @Override
        public boolean equals(Object o) {
            return o == this;
        }

        @Override
        public int hashCode() {
            return System.identityHashCode(intsnace);
        }

    }

}
