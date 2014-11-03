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

    public <T>IBEncodeElement serializeElement(final T instance) {
        if (!instance.getClass().isPrimitive()) {
            throw new SerializationException("Field is not primitive");
        }
        if (instance.getClass() == Byte.TYPE) {
            final Byte value = (Byte)instance;
            return IPrimitiveSerializator.Type.BYTE.getSerializator().serialize(value);
        } else if (instance.getClass() == Short.TYPE) {
            final Short value = (Short)instance;
            return IPrimitiveSerializator.Type.SHORT.getSerializator().serialize(value);
        } else if (instance.getClass() == Integer.TYPE) {
            final Integer value = (Integer)instance;
            return IPrimitiveSerializator.Type.INTEGER.getSerializator().serialize(value);
        } else if (instance.getClass() == Long.TYPE) {
            final Long value = (Long)instance;
            return IPrimitiveSerializator.Type.LONG.getSerializator().serialize(value);
        } else if (instance.getClass() == Float.TYPE) {
            final Float value = (Float)instance;
            return IPrimitiveSerializator.Type.FLOAT.getSerializator().serialize(value);
        } else if (instance.getClass() == Double.TYPE) {
            final Double value = (Double)instance;
            return IPrimitiveSerializator.Type.DOUBLE.getSerializator().serialize(value);
        } else if (instance.getClass() == Character.TYPE) {
            final Character value = (Character)instance;
            return IPrimitiveSerializator.Type.CHAR.getSerializator().serialize(value);
        }
        throw new SerializationException("Primitive type unrecognized");
    }

    public <T>IBEncodeElement serializeArray(final T instance) {
        final Class componentType = instance.getClass().getComponentType();
        if (!componentType.isPrimitive()) {
            throw new SerializationException("Field is not primitive");
        }
        if (!instance.getClass().isArray()) {
            throw new SerializationException("Field is not array");
        }
        final BencodeList result = new BencodeList();
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
