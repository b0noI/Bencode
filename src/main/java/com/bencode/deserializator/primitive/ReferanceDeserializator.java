package com.bencode.deserializator.primitive;

import com.bencode.common.FieldHelper;
import com.bencode.common.TypeHelper;
import com.bencode.serialization.model.BencodeList;
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

    private final ReferanceArrayFieldDeserializator REFERANCE_ARRAY_FIELD_DESERIALIZATOR = new ReferanceArrayFieldDeserializator(this);

    private static final PrimitiveArrayFieldDeserializator PRIMITIVE_ARRAY_FIELD_DESERIALIZATOR = new PrimitiveArrayFieldDeserializator();

    @Override
    public <T> T deserialize(final Dict dict) {
        return deserialize(dict, 0, new HashMap<>());
    }

    @Override
    public <T> T deserialize(final Dict dict,
                             final Integer key,
                             final Map<Integer, Object> objects) {
        if (key == -1) return null;
        final Dict instanceElement = (Dict)dict.getValue((byte)(int)key);
        if (instanceElement == null) {
            return null;
        }
        final String typeKey = "$CLASS_TYPE";
        final IBEncodeElement bElement = instanceElement.getValue(typeKey);
        if (bElement == null) return null;
        final ByteString classNameValue = (ByteString)bElement;
        final StringBuilder sb = new StringBuilder(classNameValue.getValue().length);
        for (byte element : classNameValue.getValue()) {
            sb.append((char)element);
        }
        final Class<T> targetClass;
        try {
            targetClass = (Class<T>)Class.forName(sb.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SerializationException(e);
        }
        try {
            final T instnace = targetClass.newInstance();
            objects.put(key, instnace);
            for (Field field : targetClass.getDeclaredFields()) {
                if (!FieldHelper.shouldBeSerialized(field)) continue;
                field.setAccessible(true);
                final IBEncodeElement fieldElement = instanceElement.getValue(field.getName());
                if (field.getType().isPrimitive() || TypeHelper.typeCanBeUnboxedToPrimitive(field.getType())) {
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
                    if (field.getType() == boolean.class || field.getType() == Boolean.class) {
                        final Boolean value = (Boolean)IPrimitiveDeserializator.Type.BOOLEAN.getDeserializator().deserializator((ByteString)fieldElement);
                        field.set(instnace, value);
                    }
                } else if (!field.getType().isArray()) {
                    final Integer referance =  (Integer)IPrimitiveDeserializator.Type.INTEGER.getDeserializator().deserializator((ByteString)fieldElement);
                    if (objects.containsKey(referance)) {
                        field.set(instnace, objects.get(referance));
                    } else {
                        field.set(instnace, deserialize(dict, referance, objects));
                    }
                } else if (field.getType().isArray()) {
                    final Class<?> componentType = TypeHelper.getComponentType(field.getType());
                    if (!componentType.isPrimitive() && !TypeHelper.typeCanBeUnboxedToPrimitive(componentType)) {
                        field.set(instnace, REFERANCE_ARRAY_FIELD_DESERIALIZATOR.deserialize(field.getType().getComponentType(), (BencodeList)fieldElement, dict, objects));
                    } else {
                        field.set(instnace, PRIMITIVE_ARRAY_FIELD_DESERIALIZATOR.deserialize(field.getType().getComponentType(), (BencodeList)fieldElement));
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
