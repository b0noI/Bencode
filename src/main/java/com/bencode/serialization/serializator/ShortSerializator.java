package com.bencode.serialization.serializator;


import java.nio.ByteBuffer;

class ShortSerializator extends AbstractToByteStringSerializator<Short> {

    private static final int SHORT_SIZE_IN_BYTES = 2;

    @Override
    protected byte[] convertToBytes(final Short instance) {
        final ByteBuffer byteBuffer = ByteBuffer.allocate(SHORT_SIZE_IN_BYTES);
        byteBuffer.putShort(instance);
        return byteBuffer.array();
    }

}
