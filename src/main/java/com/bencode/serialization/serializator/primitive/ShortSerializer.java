package com.bencode.serialization.serializator.primitive;


import java.nio.ByteBuffer;

class ShortSerializer extends AbstractToByteStringSerializer<Short> {

    private static final int SHORT_SIZE_IN_BYTES = 2;

    @Override
    protected byte[] convertToBytes(final Short instance) {
        final ByteBuffer byteBuffer = ByteBuffer.allocate(SHORT_SIZE_IN_BYTES);
        byteBuffer.putShort(instance);
        return byteBuffer.array();
    }

}
