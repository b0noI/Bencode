package com.bencode.serialization.serializator.primitive;

import java.nio.ByteBuffer;

class LongSerializer extends AbstractToByteStringSerializer<Long> {

    private static final int LONG_SIZE_IN_BYTES = 8;

    @Override
    protected byte[] convertToBytes(final Long instance) {
        final ByteBuffer byteBuffer = ByteBuffer.allocate(LONG_SIZE_IN_BYTES);
        byteBuffer.putLong(instance);
        return byteBuffer.array();
    }

}
