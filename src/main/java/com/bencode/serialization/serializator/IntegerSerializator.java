package com.bencode.serialization.serializator;


import java.nio.ByteBuffer;

class IntegerSerializator extends AbstractToByteStringSerializator<Integer> {

    private static final int INT_SIZE_IN_BYTES = 4;

    @Override
    protected byte[] convertToBytes(final Integer instance) {
        final ByteBuffer byteBuffer = ByteBuffer.allocate(INT_SIZE_IN_BYTES);
        byteBuffer.putInt(instance);
        return byteBuffer.array();
    }

}
