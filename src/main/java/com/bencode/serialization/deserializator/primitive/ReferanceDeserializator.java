package com.bencode.serialization.deserializator.primitive;

import com.bencode.serialization.model.ByteString;
import com.bencode.serialization.model.Dict;
import com.bencode.serialization.model.IBEncodeElement;
import com.sun.xml.internal.ws.encoding.soap.SerializationException;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by b0noI on 08/11/14.
 */
public class ReferanceDeserializator implements IReferanceDeserializator {

    @Override
    public <T> T deserialize(final Class<T> targetClass, final Dict dict) {
        return deserialize(targetClass, dict, 1, new HashMap<>());
    }

    public <T> T deserialize(final Class<T> targetClass,
                             final Dict dict,
                             final Integer key,
                             final Map<Integer, Object> objects) {
        try {
            final T instnace = targetClass.newInstance();
            objects.put(key, instnace);
            final Dict instanceElement = (Dict)dict.getValue((byte)(int)key);
            for (Field field : targetClass.getDeclaredFields()) {
                final IBEncodeElement fieldElement = instanceElement.getValue(field.getName());
                if (field.getType().isPrimitive()) {
                    if (field.getType() == byte.class || field.getType() == Byte.class) {
                        final Byte value = (Byte)IPrimitiveDeserializator.Type.BYTE.getDeserializator().deserializator((ByteString)fieldElement);
                        field.set(instnace, value);
                    }
                    if (field.getType() == short.class || field.getType() == Short.class) {
                        final Short value = (Short)IPrimitiveDeserializator.Type.SHORT.getDeserializator().deserializator((ByteString)fieldElement);
                        field.set(instnace, value);
                    }
                    if (field.getType() == int.class || field.getType() == Integer.class) {
                        final Integer value = (Integer)IPrimitiveDeserializator.Type.INTEGER.getDeserializator().deserializator((ByteString)fieldElement);
                        field.set(instnace, value);
                    }
                    if (field.getType() == long.class || field.getType() == Long.class) {
                        final Long value = (Long)IPrimitiveDeserializator.Type.LONG.getDeserializator().deserializator((ByteString)fieldElement);
                        field.set(instnace, value);
                    }
                    if (field.getType() == float.class || field.getType() == Float.class) {
                        final Float value = (Float)IPrimitiveDeserializator.Type.FLOAT.getDeserializator().deserializator((ByteString)fieldElement);
                        field.set(instnace, value);
                    }
                    if (field.getType() == double.class || field.getType() == Double.class) {
                        final Double value = (Double)IPrimitiveDeserializator.Type.DOUBLE.getDeserializator().deserializator((ByteString)fieldElement);
                        field.set(instnace, value);
                    }
                } else {
                    final Integer referance =  (Integer)IPrimitiveDeserializator.Type.INTEGER.getDeserializator().deserializator((ByteString)fieldElement);
                    if (objects.containsKey(referance)) {
                        field.set(instnace, objects.get(referance));
                    } else {
                        field.set(instnace, deserialize(field.getType(), dict, referance, objects));
                    }
                }
            }
            return instnace;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new SerializationException(e);
        }
    }

}
