package com.bencode.serialization.serializator.primitive;

import com.bencode.serialization.model.ByteString;
import com.bencode.serialization.model.IBEncodeElement;
import com.bencode.serialization.serializator.ISerializator;


abstract class AbstractToByteStringSerializator<T> implements IPrimitiveSerializator<T> {

    @Override
    final public ByteString serialize(final T instance) {
        return ByteString.buildElement(convertToBytes(instance));
    }

    abstract protected byte[] convertToBytes(final T instance);

}
