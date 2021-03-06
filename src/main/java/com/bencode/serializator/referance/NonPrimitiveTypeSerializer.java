package com.bencode.serializator.referance;

import com.bencode.common.FieldHelper;
import com.bencode.common.TypeHelper;
import com.bencode.model.ByteString;
import com.bencode.model.Dict;
import com.bencode.model.IBEncodeElement;
import org.apache.commons.lang3.SerializationException;

import java.lang.reflect.Field;


class NonPrimitiveTypeSerializer implements ISerializer<Object> {

    private static  final String                TYPE_IS_PRIMITIVE_ERROR_STRING = "Type is primitive";

    private         final ISerializer<Object>   objectSerializer                                    ;

    NonPrimitiveTypeSerializer(final ISerializer<Object> objectSerializer) {
        this.objectSerializer = objectSerializer;
    }

    @Override
    public IBEncodeElement serialize(final Object instance) {
        validateClass(instance.getClass());
        final Dict result = new Dict();
        final Field[] fields = instance.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (!FieldHelper.shouldBeSerialized(field)) continue;

            final ByteString key = ByteString.buildElement(field.getName().getBytes());
            final IBEncodeElement serializedField = serializeField(instance, field);

            result.putValue(key, serializedField);
        }

        serializeClassType(instance.getClass(), result);
        return result;
    }

    private IBEncodeElement serializeField(final Object instance, final Field field) {
        try {
            return objectSerializer.serialize(field.get(instance));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new SerializationException(e);
        }
    }

    private void serializeClassType(final Class<?> type, final Dict dict) {
        final ByteString byteStringKey = ByteString.buildElement(CLASS_TYPE_KEY_NAME);
        final ByteString byteStringValue = ByteString.buildElement(type.getName());
        dict.putValue(byteStringKey, byteStringValue);
    }

    private void validateClass(final Class<?> type) {
        if (type.isPrimitive() || TypeHelper.typeCanBeUnboxedToPrimitive(type)) {
            throw new SerializationException(TYPE_IS_PRIMITIVE_ERROR_STRING);
        }
    }

}
