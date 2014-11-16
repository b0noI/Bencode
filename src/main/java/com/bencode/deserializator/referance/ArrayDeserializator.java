package com.bencode.deserializator.referance;


import com.bencode.serialization.model.BencodeList;
import com.bencode.serialization.model.IBEncodeElement;
import org.apache.commons.lang3.SerializationException;

import java.lang.reflect.Array;


public class ArrayDeserializator {

    private final RecursiveDeserializator deserializator;

    public ArrayDeserializator(RecursiveDeserializator deserializator) {
        this.deserializator = deserializator;
    }

    public <T>T deserialize(final IBEncodeElement element, final Class<?> type) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        if (!(element instanceof BencodeList)) throw new SerializationException("element is not array!");
        final BencodeList list = (BencodeList) element;
        final Object array = Array.newInstance(type, list.size());
        for (int i = 0; i < list.size(); i++) {
            Array.set(array, i, deserializator.deserialize(list.get(i), type));
        }
        return (T) array;
    }

}
