package com.bencode.serializator.referance;


import com.bencode.model.BencodeList;
import com.bencode.model.IBEncodeElement;
import org.apache.commons.lang3.SerializationException;

import java.lang.reflect.Array;

class ArrayTypeSerializer implements ISerializer<Object> {

    private static  final String                    FIELD_IS_NOT_ARRAY_ERROR_STRING        = "Field is not array"   ;

    private         final ISerializer<Object>       objectsSerializer                                               ;

    ArrayTypeSerializer(final ISerializer<Object> objectsSerializer) {
        this.objectsSerializer = objectsSerializer;
    }

    @Override
    public IBEncodeElement serialize(final Object array) {
        validateClass(array.getClass());
        final BencodeList result = new BencodeList();
        final int length = Array.getLength(array);
        for (int i = 0; i < length; i++) {
            final Object element = Array.get(array, i);
            result.add(objectsSerializer.serialize(element));
        }
        return result;
    }

    private void validateClass(final Class<?> type) {
        if (!type.isArray()) {
            throw new SerializationException(FIELD_IS_NOT_ARRAY_ERROR_STRING);
        }
    }

}
