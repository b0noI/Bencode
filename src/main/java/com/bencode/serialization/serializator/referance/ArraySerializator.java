package com.bencode.serialization.serializator.referance;


import com.bencode.common.TypeHelper;
import com.bencode.serialization.model.BencodeList;
import com.bencode.serialization.model.IBEncodeElement;
import com.bencode.serialization.serializator.ISerializator;
import com.bencode.serialization.serializator.primitive.IPrimitiveSerializator;
import com.sun.xml.internal.ws.encoding.soap.SerializationException;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

class ArraySerializator implements ISerializator<Object>{

    private static  final String                    FIELD_IS_NOT_ARRAY_ERROR_STRING        = "Field is not array"      ;

    private         final ISerializator<Object>     serializator                                                       ;

    ArraySerializator(final ISerializator<Object> serializator) {
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
