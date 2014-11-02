package com.bencode.serialization.serializator.primitive;


import java.nio.ByteBuffer;

class DoubleSerializator extends AbstractToByteStringSerializator<Double> {

    private static final int DOUBLE_SIZE_IN_BYTES = 8;

    @Override
    protected byte[] convertToBytes(final Double instance) {
        final ByteBuffer byteBuffer = ByteBuffer.allocate(DOUBLE_SIZE_IN_BYTES);
        byteBuffer.putDouble(instance);
        return byteBuffer.array();
    }

}
