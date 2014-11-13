package com.bencode.serialization.serializator.primitive;


import java.nio.ByteBuffer;

class IntegerSerializer extends AbstractToByteStringSerializer<Integer> {

    private static final int INT_SIZE_IN_BYTES = 4;

    @Override
    protected byte[] convertToBytes(final Integer instance) {
        final ByteBuffer byteBuffer = ByteBuffer.allocate(INT_SIZE_IN_BYTES);
        byteBuffer.putInt(instance);
        return byteBuffer.array();
    }

}
