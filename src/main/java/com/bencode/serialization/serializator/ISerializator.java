package com.bencode.serialization.serializator;


import com.bencode.serialization.model.IBEncodeElement;

public interface ISerializator<T> {

    public IBEncodeElement serialize(final T instance);

}
