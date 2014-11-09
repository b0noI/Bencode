package com.bencode.deserializator.primitive;

import com.bencode.serialization.model.ByteString;

import java.nio.ByteBuffer;


class ByteStringToBooleanDeserializator implements IPrimitiveDeserializator<Boolean> {

    private final ByteStringToPrimitiveDeserializator<Byte> toPrimitiveConverter = new ByteStringToPrimitiveDeserializator<>(ByteBuffer::get);

    @Override
    public Boolean deserializator(final ByteString from) {
        final byte result = toPrimitiveConverter.deserializator(from);
        return result == (byte) 1;
    }

}
