package com.bencode.serialization.serializator.primitive;

import java.nio.ByteBuffer;

class FloatSerializator extends AbstractToByteStringSerializator<Float> {

    private static final int FLOAT_SIZE_IN_BYTES = 4;

    @Override
    protected byte[] convertToBytes(Float instance) {
        final ByteBuffer byteBuffer = ByteBuffer.allocate(FLOAT_SIZE_IN_BYTES);
        byteBuffer.putFloat(instance);
        return byteBuffer.array();
    }

}
