package com.bencode.serializator.referance;


import com.bencode.common.Type;
import com.bencode.common.TypeHelper;
import com.bencode.model.ByteString;
import com.bencode.model.Dict;
import com.bencode.model.IBEncodeElement;
import com.bencode.serializator.ISerializer;
import com.bencode.serializator.primitive.IPrimitiveSerializer;
import org.apache.commons.lang3.SerializationException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class AllTypesSerializer implements ISerializer<Object> {

    private static  final    String                         NON_SERIALIZABLE_TYPE_ERROR_STRING  = "Non-serializable type";

    private                  Integer                        nextId                              = 0;

    private         final    Map<ObjectKey, Integer>        serializedObjectsIds                = new HashMap<>();

    private         final Map<Integer, IBEncodeElement>     serializedObjects                   = new HashMap<>();

    private         final PrimitiveTypeSerializer           primitiveTypeSerializer             = new PrimitiveTypeSerializer();

    private         final ArrayTypeSerializer               arrayTypeSerializer                 = new ArrayTypeSerializer(this);

    private         final NonPrimitiveTypeSerializer        objectSerializer                    = new NonPrimitiveTypeSerializer(this);

    @Override
    public IBEncodeElement serialize(final Object instance) {

        if (instance == null) return ByteString.nullString();

        if (!TypeHelper.canBeSerialized(instance.getClass())) {
            throw new SerializationException(NON_SERIALIZABLE_TYPE_ERROR_STRING);
        }

        final Type objectType = Type.getType(instance.getClass());
        switch (objectType) {
            case ARRAY:
                return arrayTypeSerializer.serialize(instance);
            case PRIMITIVE:
                return primitiveTypeSerializer.serialize(instance);
            case REF:
            default:
                final Optional<IBEncodeElement> bEncodeElementFromCache = getSerializedObjectFromMap(instance);
                if (bEncodeElementFromCache.isPresent()) {
                    return bEncodeElementFromCache.get();
                }
                final ObjectKey objectKey = new ObjectKey(instance);
                final int       currentId = getNextId();

                serializedObjectsIds.put(objectKey, currentId);
                serializedObjects.put(currentId, objectSerializer.serialize(instance));
                return IPrimitiveSerializer.Type.INTEGER.getSerializer().serialize(currentId);
        }
    }

    public IBEncodeElement getResultOfSerialization() {
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
            final int objectId = serializedObjectsIds.get(fieldObjectKey);
            return Optional.of(IPrimitiveSerializer.Type.INTEGER.getSerializer().serialize(objectId));
        }
        return Optional.empty();
    }

    private int getNextId() {
        return nextId++;
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
