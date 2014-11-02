package com.bencode.serialization.serializator;


public interface ISerializator {

    public <T>byte[] serialize(final T instance);

}
