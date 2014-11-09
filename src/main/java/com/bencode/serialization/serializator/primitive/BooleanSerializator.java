package com.bencode.serialization.serializator.primitive;


import java.nio.ByteBuffer;

class BooleanSerializator extends AbstractToByteStringSerializator<Boolean> {

    private static final int BOOLEAN_SIZE_IN_BYTES = 1;

    @Override
    protected byte[] convertToBytes(Boolean instance) {
        final ByteBuffer byteBuffer = ByteBuffer.allocate(BOOLEAN_SIZE_IN_BYTES);
        if (instance) byteBuffer.put((byte)1);
        else byteBuffer.put((byte)0);
        return byteBuffer.array();
    }

}
