package com.bencode.deserializator.referance;


import com.bencode.serialization.model.BencodeList;
import com.bencode.serialization.model.IBEncodeElement;
import org.apache.commons.lang3.SerializationException;

import java.lang.reflect.Array;


class ArrayDeserializer implements IDeserializer {

    private final IDeserializer mainDeserializer;

    ArrayDeserializer(final IDeserializer mainDeserializer) {
        this.mainDeserializer = mainDeserializer;
    }

    public <T>T deserialize(final IBEncodeElement element, final Class<?> type) {
        if (!(element instanceof BencodeList)) throw new SerializationException("element is not array!");
        final BencodeList list = (BencodeList) element;
        final Object array = Array.newInstance(type, list.size());
        for (int i = 0; i < list.size(); i++) {

            Array.set(array, i, mainDeserializer.deserialize(list.get(i), type));

        }
        return (T) array;
    }

}
