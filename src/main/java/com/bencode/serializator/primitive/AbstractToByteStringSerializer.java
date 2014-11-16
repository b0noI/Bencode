package com.bencode.serializator.primitive;

import com.bencode.model.ByteString;


abstract class AbstractToByteStringSerializer<T> implements IPrimitiveSerializer<T> {

    @Override
    final public ByteString serialize(final T instance) {
        return ByteString.buildElement(convertToBytes(instance));
    }

    abstract protected byte[] convertToBytes(final T instance);

}
