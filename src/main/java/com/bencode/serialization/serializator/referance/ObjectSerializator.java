package com.bencode.serialization.serializator.referance;

import com.bencode.common.FieldHelper;
import com.bencode.serialization.model.ByteString;
import com.bencode.serialization.model.Dict;
import com.bencode.serialization.model.IBEncodeElement;
import com.bencode.serialization.serializator.ISerializator;
import com.sun.xml.internal.ws.encoding.soap.SerializationException;

import java.lang.reflect.Field;


class ObjectSerializator implements ISerializator<Object> {

    private         final RecursiveObjectSerealizator objectSerializator                  ;

    ObjectSerializator(final RecursiveObjectSerealizator objectSerializator) {
        this.objectSerializator = objectSerializator;
    }

    @Override
    public IBEncodeElement serialize(final Object instance) {
        final Dict result = new Dict();
        final Field[] fields = instance.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (!FieldHelper.shouldBeSerialized(field)) continue;

            final ByteString key = ByteString.buildElement(field.getName().getBytes());
            final IBEncodeElement serializedField;
            try {
                serializedField = objectSerializator.serialize(field.get(instance));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new SerializationException(e);
            }
            result.putValue(key, serializedField);
        }

        serializeClassType(instance.getClass(), result);
        return result;
    }

    private void serializeClassType(final Class<?> type, final Dict dict) {
        final ByteString byteStringKey = ByteString.buildElement(CLASS_TYPE_KEY_NAME);
        final ByteString byteStringValue = ByteString.buildElement(type.getName());
        dict.putValue(byteStringKey, byteStringValue);
    }

}
