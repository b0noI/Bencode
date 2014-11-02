package com.bencode.serialization.serializator.referance;

import com.bencode.serialization.model.IBEncodeElement;
import com.bencode.serialization.serializator.ISerializator;
import com.bencode.serialization.serializator.primitive.IPrimitiveSerializator;
import com.sun.xml.internal.ws.encoding.soap.SerializationException;

import java.lang.reflect.Field;

class PrimitiveFieldSerializator implements ISerializator<Field> {

    private final Object instance;

    public PrimitiveFieldSerializator(final Object instance) {
        this.instance = instance;
    }

    @Override
    public IBEncodeElement serialize(final Field field) {
        if (!field.getType().isPrimitive()) {
            throw new SerializationException("Field is not primitive");
        }
        try {
            if (field.getType() == Byte.TYPE) {
                final Byte value = field.getByte(instance);
                return IPrimitiveSerializator.Type.BYTE.getSerializator().serialize(value);
            } else if (field.getType() == Short.TYPE) {
                final Short value = field.getShort(instance);
                return IPrimitiveSerializator.Type.SHORT.getSerializator().serialize(value);
            } else if (field.getType() == Integer.TYPE) {
                final Integer value = field.getInt(instance);
                return IPrimitiveSerializator.Type.INTEGER.getSerializator().serialize(value);
            } else if (field.getType() == Long.TYPE) {
                final Long value = field.getLong(instance);
                return IPrimitiveSerializator.Type.LONG.getSerializator().serialize(value);
            } else if (field.getType() == Float.TYPE) {
                final Float value = field.getFloat(instance);
                return IPrimitiveSerializator.Type.FLOAT.getSerializator().serialize(value);
            } else if (field.getType() == Double.TYPE) {
                final Double value = field.getDouble(instance);
                return IPrimitiveSerializator.Type.DOUBLE.getSerializator().serialize(value);
            }
            throw new SerializationException("Primitive type unrecognized");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new SerializationException(e);
        }
    }

}
