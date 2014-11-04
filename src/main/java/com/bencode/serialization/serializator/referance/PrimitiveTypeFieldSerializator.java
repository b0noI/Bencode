package com.bencode.serialization.serializator.referance;

import com.bencode.serialization.model.IBEncodeElement;
import com.bencode.serialization.serializator.ISerializator;
import com.bencode.serialization.serializator.primitive.IPrimitiveSerializator;
import com.sun.xml.internal.ws.encoding.soap.SerializationException;

import java.lang.reflect.Field;

class PrimitiveTypeFieldSerializator implements ISerializator<Field> {

    private static  final String FIELD_IS_NOT_PRIMITIVE_ERROR_STRING = "Field is not primitive";

    private         final Object instance;

    PrimitiveTypeFieldSerializator(final Object instance) {
        this.instance = instance;
    }

    @Override
    public IBEncodeElement serialize(final Field field) {
        if (!field.getType().isPrimitive()) {
            throw new SerializationException(FIELD_IS_NOT_PRIMITIVE_ERROR_STRING);
        }
        final Object instance;
        try {
            instance = field.get(this.instance);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new SerializationException(e);
        }
        return serialize(IPrimitiveSerializator.Type.findSerializator(instance.getClass()), instance);
    }

    private <T> IBEncodeElement serialize(final ISerializator<T> serializator, final Object target) {
        return serializator.serialize((T)target);
    }

}
