package com.bencode.serialization.serializator;


public interface ISerializator<T> {

    public byte[] serialize(final T instance);

}
