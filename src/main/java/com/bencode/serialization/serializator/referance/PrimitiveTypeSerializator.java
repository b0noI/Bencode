package com.bencode.serialization.serializator.referance;

import com.bencode.serialization.model.BencodeList;
import com.bencode.serialization.model.IBEncodeElement;
import com.bencode.serialization.serializator.ISerializator;
import com.bencode.serialization.serializator.primitive.IPrimitiveSerializator;
import com.sun.xml.internal.ws.encoding.soap.SerializationException;

import java.lang.reflect.Field;

class PrimitiveTypeSerializator {

    private final Object instance;

    PrimitiveTypeSerializator(final Object instance) {
        this.instance = instance;
    }

    public IBEncodeElement serializeElement(final Field field) throws IllegalAccessException {
        if (!field.getType().isPrimitive()) {
            throw new SerializationException("Field is not primitive");
        }
        final Object instance = field.get(this.instance);
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

    public <T>IBEncodeElement serializeArray(final Field field) throws IllegalAccessException {
        final Class componentType = field.getType().getComponentType();
        if (!componentType.isPrimitive()) {
            throw new SerializationException("Field is not primitive");
        }
        if (!field.getType().isArray()) {
            throw new SerializationException("Field is not array");
        }
        final BencodeList result = new BencodeList();
        final Object instance = field.get(this.instance);
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
