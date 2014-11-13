package com.bencode.serialization.serializator.referance;


import com.bencode.serialization.model.BencodeList;
import com.bencode.serialization.model.IBEncodeElement;
import com.bencode.serialization.serializator.ISerializer;
import com.sun.xml.internal.ws.encoding.soap.SerializationException;

import java.lang.reflect.Array;

class ArraySerializer implements ISerializer<Object> {

    private static  final String                    FIELD_IS_NOT_ARRAY_ERROR_STRING        = "Field is not array"      ;

    private         final ISerializer<Object> serializator                                                       ;

    ArraySerializer(final ISerializer<Object> serializator) {
        this.serializator = serializator;
    }

    @Override
    public IBEncodeElement serialize(final Object array) {
        if (!array.getClass().isArray()) {
            throw new SerializationException(FIELD_IS_NOT_ARRAY_ERROR_STRING);
        }
        final BencodeList result = new BencodeList();
        final int length = Array.getLength(array);
        for (int i = 0; i < length; i++) {
            final Object element = Array.get(array, i);
            result.add(serializator.serialize(element));
        }
        return result;
    }

}
