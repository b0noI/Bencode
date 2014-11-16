package com.bencode.serializator.primitive;

import java.nio.ByteBuffer;

class FloatSerializer extends AbstractToByteStringSerializer<Float> {

    private static final int FLOAT_SIZE_IN_BYTES = 4;

    @Override
    protected byte[] convertToBytes(final Float instance) {
        final ByteBuffer byteBuffer = ByteBuffer.allocate(FLOAT_SIZE_IN_BYTES);
        byteBuffer.putFloat(instance);
        return byteBuffer.array();
    }

}
