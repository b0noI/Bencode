package com.bencode.deserializator.referance;


import com.bencode.common.BencodeHelper;
import com.bencode.model.BencodeList;
import com.bencode.model.ByteString;
import com.bencode.model.IBEncodeElement;
import org.apache.commons.lang3.SerializationException;

import java.lang.reflect.Array;


class ArrayTypeDeserializer implements IDeserializer {

    private static  final String ELEMENT_IS_NOT_ARRAY_ERROR_STRING = "element is not array!";

    private         final IDeserializer mainDeserializer;

    ArrayTypeDeserializer(final IDeserializer mainDeserializer) {
        this.mainDeserializer = mainDeserializer;
    }

    public <T>T deserialize(final IBEncodeElement element, final Class<?> type) {
        if (BencodeHelper.isNullElement(element)) return null;
        if (!(element instanceof BencodeList)) {
            throw new SerializationException(ELEMENT_IS_NOT_ARRAY_ERROR_STRING);
        }
        final BencodeList list = (BencodeList) element;
        final Object array = Array.newInstance(type, list.size());
        for (int i = 0; i < list.size(); i++) {

            Array.set(array, i, mainDeserializer.deserialize(list.get(i), type));

        }
        return (T) array;
    }

}
