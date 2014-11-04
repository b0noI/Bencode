package com.bencode.serialization.serializator.referance;

import com.bencode.serialization.model.BencodeList;
import com.bencode.serialization.model.IBEncodeElement;
import com.bencode.serialization.serializator.ISerializator;
import com.bencode.serialization.serializator.primitive.IPrimitiveSerializator;
import com.sun.xml.internal.ws.encoding.soap.SerializationException;

import java.lang.reflect.Field;

class PrimitiveTypeSerializator implements ISerializator<Field> {

    private final Object instance;

    PrimitiveTypeSerializator(final Object instance) {
        this.instance = instance;
    }

    @Override
    public IBEncodeElement serialize(final Field field) {
        if (!field.getType().isPrimitive()) {
            throw new SerializationException("Field is not primitive");
        }
        final Object instance;
        try {
            instance = field.get(this.instance);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new SerializationException(e);
        }
        if (instance.getClass() == Byte.class) {
            final Byte value = (Byte)instance;
            return IPrimitiveSerializator.Type.BYTE.getSerializator().serialize(value);
        } else if (instance.getClass() == Short.class) {
            final Short value = (Short)instance;
            return IPrimitiveSerializator.Type.SHORT.getSerializator().serialize(value);
        } else if (instance.getClass() == Integer.class) {
            final Integer value = (Integer)instance;
            return IPrimitiveSerializator.Type.INTEGER.getSerializator().serialize(value);
        } else if (instance.getClass() == Long.class) {
            final Long value = (Long)instance;
            return IPrimitiveSerializator.Type.LONG.getSerializator().serialize(value);
        } else if (instance.getClass() == Float.class) {
            final Float value = (Float)instance;
            return IPrimitiveSerializator.Type.FLOAT.getSerializator().serialize(value);
        } else if (instance.getClass() == Double.class) {
            final Double value = (Double)instance;
            return IPrimitiveSerializator.Type.DOUBLE.getSerializator().serialize(value);
        } else if (instance.getClass() == Character.class) {
            final Character value = (Character)instance;
            return IPrimitiveSerializator.Type.CHAR.getSerializator().serialize(value);
        }
        throw new SerializationException("Primitive type unrecognized");
    }

}
