package com.bencode.serialization.serializator.referance;


import com.bencode.common.FieldHelper;
import com.bencode.common.TypeHelper;
import com.bencode.serialization.model.BencodeList;
import com.bencode.serialization.model.ByteString;
import com.bencode.serialization.model.Dict;
import com.bencode.serialization.model.IBEncodeElement;
import com.bencode.serialization.serializator.ISerializator;
import com.bencode.serialization.serializator.primitive.IPrimitiveSerializator;
import com.sun.xml.internal.ws.encoding.soap.SerializationException;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class RecursiveObjectSerealizator implements ISerializator<Object> {

    private       Integer                        currentId                      = 0;

    private final Map<ObjectKey, Integer>        serializedObjectsIds           = new HashMap<>();

    private final Map<Integer, IBEncodeElement>  serializedObjects              = new HashMap<>();

    private final PrimitiveTypeSerializator      primitiveTypeSerializator      = new PrimitiveTypeSerializator();

    private final ArraySerializator              arraySerializator              = new ArraySerializator(this);

    private final ObjectSerializator             objectSerializator             = new ObjectSerializator(this);

    @Override
    public IBEncodeElement serialize(final Object instance) {

        if (instance == null) return ByteString.nullString();

        if (!TypeHelper.canBeSerialized(instance.getClass())) {
            throw new SerializationException("Non-serializable type");
        }

        if (instance.getClass().isPrimitive() || TypeHelper.typeCanBeUnboxedToPrimitive(instance.getClass())) {
            return primitiveTypeSerializator.serialize(instance);
        } else if (instance.getClass().isArray()) {
            return arraySerializator.serialize(instance);
        } else {
            final Optional<IBEncodeElement> bEncodeElementFromCache = getSerializedObjectFromMap(instance);
        if (bEncodeElementFromCache.isPresent()) {
                return bEncodeElementFromCache.get();
            }
        }

        final ObjectKey objectKey = new ObjectKey(instance);
        final int       currentId = getCurrentId();

        serializedObjectsIds.put(objectKey, currentId);
        serializedObjects.put(currentId, objectSerializator.serialize(instance));
        return serializedObjects.get(currentId);
    }

    public IBEncodeElement getSerializedElement() {
        final Dict result = new Dict();
        serializedObjects.keySet().forEach(key -> {
            final ByteString keyString = ByteString.buildElement(new byte[]{(byte) (int) key});
            final IBEncodeElement value = serializedObjects.get(key);
            result.putValue(keyString, value);
        });
        return result;
    }

    private Optional<IBEncodeElement> getSerializedObjectFromMap(final Object instance) {
        final ObjectKey fieldObjectKey = new ObjectKey(instance);
        if (serializedObjectsIds.containsKey(fieldObjectKey)) {
            final int objetId = serializedObjectsIds.get(fieldObjectKey);
            return Optional.of(IPrimitiveSerializator.Type.INTEGER.getSerializator().serialize(objetId));
        }
        return Optional.empty();
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
