package com.bencode.serialization.serializator.referance;

import com.bencode.common.TypeHelper;
import com.bencode.common.VisibilityReducedForTestPurposeOnly;
import com.bencode.serialization.model.IBEncodeElement;
import com.bencode.serialization.serializator.ISerializer;
import com.bencode.serialization.serializator.primitive.IPrimitiveSerializer;
import com.sun.xml.internal.ws.encoding.soap.SerializationException;

import java.util.Optional;

class PrimitiveTypeSerializer implements ISerializer<Object> {

    private static  final String FIELD_IS_NOT_PRIMITIVE_ERROR_STRING            = "Field is not primitive";

    private static  final String PRIMITIVE_TYPE_S_NOT_SUPPORTED_ERROR_STRING    = "Primitive type %s not supported";

    @Override
    public IBEncodeElement serialize(final Object instance) {
        validateClass(instance.getClass());
        final ISerializer serializer = getSerializer(instance.getClass());
        return serializer.serialize(instance);
    }

    @VisibilityReducedForTestPurposeOnly
    void validateClass(final Class<?> type) {
        if (!type.isPrimitive() && !TypeHelper.typeCanBeUnboxedToPrimitive(type)) {
            throw new SerializationException(FIELD_IS_NOT_PRIMITIVE_ERROR_STRING);
        }
    }

    @VisibilityReducedForTestPurposeOnly
    <T>ISerializer<T> getSerializer(final Class<?> type) {
        final Optional<IPrimitiveSerializer<T>> optionalSerializer = IPrimitiveSerializer.Type.findSerializer(type);
        if (!optionalSerializer.isPresent()) throw new SerializationException(PRIMITIVE_TYPE_S_NOT_SUPPORTED_ERROR_STRING);
        return optionalSerializer.get();
    }

}
