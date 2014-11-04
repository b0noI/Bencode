package com.bencode.serialization.serializator.referance;


import com.bencode.serialization.model.BencodeList;
import com.bencode.serialization.model.IBEncodeElement;
import com.bencode.serialization.serializator.ISerializator;
import com.bencode.serialization.serializator.primitive.IPrimitiveSerializator;
import com.sun.xml.internal.ws.encoding.soap.SerializationException;

import java.lang.reflect.Field;

class PrimitiveArrayFieldSerializator implements ISerializator<Field>{

    private final Object instance;

    PrimitiveArrayFieldSerializator(final Object instance) {
        this.instance = instance;
    }

    @Override
    public IBEncodeElement serialize(Field field) {
        final Class componentType = field.getType().getComponentType();
        if (!componentType.isPrimitive()) {
            throw new SerializationException("Field is not primitive");
        }
        if (!field.getType().isArray()) {
            throw new SerializationException("Field is not array");
        }
        final BencodeList result = new BencodeList();
        final Object instance;
        try {
            instance = field.get(this.instance);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new SerializationException(e);
        }
        if (componentType == Byte.TYPE) {
            final byte[] values = (byte[])instance;
            for (byte element : values) {
                result.add(IPrimitiveSerializator.Type.BYTE.getSerializator().serialize(element));
            }
            return result;
        } else if (componentType == Short.TYPE) {
            final short[] values = (short[])instance;
            for (short element : values) {
                result.add(IPrimitiveSerializator.Type.SHORT.getSerializator().serialize(element));
            }
            return result;
        } else if (componentType == Integer.TYPE) {
            final int[] values = (int[])instance;
            for (int element : values) {
                result.add(IPrimitiveSerializator.Type.INTEGER.getSerializator().serialize(element));
            }
            return result;
        } else if (componentType == Long.TYPE) {
            final long[] values = (long[])instance;
            for (long element : values) {
                result.add(IPrimitiveSerializator.Type.LONG.getSerializator().serialize(element));
            }
            return result;
        } else if (componentType == Float.TYPE) {
            final float[] values = (float[])instance;
            for (float element : values) {
                result.add(IPrimitiveSerializator.Type.FLOAT.getSerializator().serialize(element));
            }
            return result;
        } else if (componentType == Double.TYPE) {
            final double[] values = (double[])instance;
            for (double element : values) {
                result.add(IPrimitiveSerializator.Type.DOUBLE.getSerializator().serialize(element));
            }
            return result;
        } else if (componentType == Character.TYPE) {
            final char[] values = (char[])instance;
            for (char element : values) {
                result.add(IPrimitiveSerializator.Type.CHAR.getSerializator().serialize(element));
            }
            return result;
        }
        throw new SerializationException("Primitive type unrecognized");
    }

}
