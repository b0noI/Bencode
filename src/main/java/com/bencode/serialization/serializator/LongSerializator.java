package com.bencode.serialization.serializator;

import java.nio.ByteBuffer;

class LongSerializator extends AbstractToByteStringSerializator<Long> {

    private static final int LONG_SIZE_IN_BYTES = 8;

    @Override
    protected byte[] convertToBytes(final Long instance) {
        final ByteBuffer byteBuffer = ByteBuffer.allocate(LONG_SIZE_IN_BYTES);
        byteBuffer.putLong(instance);
        return byteBuffer.array();
    }

}
