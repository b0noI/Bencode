package com.bencode.serialization.serializator.referance;

import com.bencode.common.TypeHelper;
import com.bencode.serialization.model.IBEncodeElement;
import com.bencode.serialization.serializator.ISerializer;
import com.bencode.serialization.serializator.primitive.IPrimitiveSerializer;
import com.sun.xml.internal.ws.encoding.soap.SerializationException;

class PrimitiveTypeSerializer implements ISerializer<Object> {

    private static  final String FIELD_IS_NOT_PRIMITIVE_ERROR_STRING = "Field is not primitive";

    @Override
    public IBEncodeElement serialize(final Object instance) {
        if (!instance.getClass().isPrimitive() && !TypeHelper.typeCanBeUnboxedToPrimitive(instance.getClass())) {
            throw new SerializationException(FIELD_IS_NOT_PRIMITIVE_ERROR_STRING);
        }
        return serialize(IPrimitiveSerializer.Type.findSerializator(instance.getClass()), instance);
    }

    private <T> IBEncodeElement serialize(final ISerializer<T> serializator, final Object target) {
        return serializator.serialize((T)target);
    }

}
