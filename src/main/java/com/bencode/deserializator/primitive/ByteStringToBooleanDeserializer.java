package com.bencode.deserializator.primitive;

import com.bencode.common.ConstructorForTestPurposeOnly;
import com.bencode.model.ByteString;

import java.nio.ByteBuffer;


class ByteStringToBooleanDeserializer implements IPrimitiveDeserializer<Boolean> {

    private final ByteStringToPrimitiveDeserializer<Byte> toPrimitiveConverter;

    ByteStringToBooleanDeserializer() {
        this(new ByteStringToPrimitiveDeserializer<>(ByteBuffer::get));
    }

    @ConstructorForTestPurposeOnly
    ByteStringToBooleanDeserializer(final ByteStringToPrimitiveDeserializer<Byte> toPrimitiveConverter) {
        this.toPrimitiveConverter = toPrimitiveConverter;
    }

    @Override
    public Boolean deserialize(final ByteString from) {
        final byte result = toPrimitiveConverter.deserialize(from);
        return result == (byte) 1;
    }

}
