package com.bencode.serialization.serializator;

import com.bencode.serialization.model.ByteString;
import com.bencode.serialization.model.IBEncodeElement;


abstract class AbstractToByteStringSerializator<T> implements ISerializator<T> {

    @Override
    final public IBEncodeElement serialize(final T instance) {
        return ByteString.buildElement(convertToBytes(instance));
    }

    abstract protected byte[] convertToBytes(final T instance);

}
