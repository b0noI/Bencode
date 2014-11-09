package com.bencode.serialization.serializator.referance;


import com.bencode.serialization.model.IBEncodeElement;
import com.bencode.serialization.serializator.ISerializator;

public class ReferanceSerializer implements ISerializator<Object> {

    @Override
    public IBEncodeElement serialize(final Object instance) {
        return RecursiveObjectSerealizator.serialize(instance);
    }

}
