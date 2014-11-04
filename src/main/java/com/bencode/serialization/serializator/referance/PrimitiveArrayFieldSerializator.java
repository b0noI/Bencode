package com.bencode.serialization.serializator.referance;


import com.bencode.serialization.model.BencodeList;
import com.bencode.serialization.model.IBEncodeElement;
import com.bencode.serialization.serializator.ISerializator;
import com.bencode.serialization.serializator.primitive.IPrimitiveSerializator;
import com.sun.xml.internal.ws.encoding.soap.SerializationException;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

class PrimitiveArrayFieldSerializator implements ISerializator<Field>{

    private static  final String FIELD_IS_NOT_PRIMITIVE_ERROR_STRING    = "Field is not primitive"  ;

    private static  final String FIELD_IS_NOT_ARRAY_ERROR_STRING        = "Field is not array"      ;

    private         final Object instance                                                           ;

    PrimitiveArrayFieldSerializator(final Object instance) {
        this.instance = instance;
    }

    @Override
    public IBEncodeElement serialize(Field field) {
        final Class componentType = field.getType().getComponentType();
        if (!componentType.isPrimitive()) {
            throw new SerializationException(FIELD_IS_NOT_PRIMITIVE_ERROR_STRING);
        }
        if (!field.getType().isArray()) {
            throw new SerializationException(FIELD_IS_NOT_ARRAY_ERROR_STRING);
        }
        final Object instance;
        try {
            instance = field.get(this.instance);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new SerializationException(e);
        }
        return serialize(IPrimitiveSerializator.Type.findSerializator(componentType), instance);
    }

    private <T> IBEncodeElement serialize(final ISerializator<T> serializator, final Object target) {
        final BencodeList result = new BencodeList();
        final int length = Array.getLength(target);
        for (int i = 0; i < length; i++) {
            final T element = (T)Array.get(target, i);
            result.add(serializator.serialize(element));
        }
        return result;
    }

}
