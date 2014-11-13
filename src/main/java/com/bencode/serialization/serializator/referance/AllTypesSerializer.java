package com.bencode.serialization.serializator.referance;


import com.bencode.common.TypeHelper;
import com.bencode.serialization.model.ByteString;
import com.bencode.serialization.model.Dict;
import com.bencode.serialization.model.IBEncodeElement;
import com.bencode.serialization.serializator.ISerializer;
import com.bencode.serialization.serializator.primitive.IPrimitiveSerializer;
import com.sun.xml.internal.ws.encoding.soap.SerializationException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class AllTypesSerializer implements ISerializer<Object> {

    private       Integer                        currentId                      = 0;

    private final Map<ObjectKey, Integer>        serializedObjectsIds           = new HashMap<>();

    private final Map<Integer, IBEncodeElement>  serializedObjects              = new HashMap<>();

    private final PrimitiveTypeSerializer primitiveTypeSerializator      = new PrimitiveTypeSerializer();

    private final ArraySerializer arraySerializator              = new ArraySerializer(this);

    private final NonPrimitiveObjectSerializer objectSerializator             = new NonPrimitiveObjectSerializer(this);

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
        return IPrimitiveSerializer.Type.INTEGER.getSerializer().serialize(currentId);
    }

    public IBEncodeElement getSerializedElement() {
        final Dict result = new Dict();
        serializedObjects.keySet().forEach(key -> {
            final ByteString keyString = ByteString.buildElement(key);
            final IBEncodeElement value = serializedObjects.get(key);
            result.putValue(keyString, value);
        });
        return result;
    }

    private Optional<IBEncodeElement> getSerializedObjectFromMap(final Object instance) {
        final ObjectKey fieldObjectKey = new ObjectKey(instance);
        if (serializedObjectsIds.containsKey(fieldObjectKey)) {
            final int objetId = serializedObjectsIds.get(fieldObjectKey);
            return Optional.of(IPrimitiveSerializer.Type.INTEGER.getSerializer().serialize(objetId));
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
