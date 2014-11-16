package com.bencode.deserializator.primitive;

import com.bencode.model.ByteString;

import java.nio.ByteBuffer;


class ByteStringToBooleanDeserializer implements IPrimitiveDeserializer<Boolean> {

    private final ByteStringToPrimitiveDeserializer<Byte> toPrimitiveConverter = new ByteStringToPrimitiveDeserializer<>(ByteBuffer::get);

    @Override
    public Boolean deserialize(final ByteString from) {
        final byte result = toPrimitiveConverter.deserialize(from);
        return result == (byte) 1;
    }

}
