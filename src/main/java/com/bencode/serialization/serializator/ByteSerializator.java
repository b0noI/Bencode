package com.bencode.serialization.serializator;


class ByteSerializator extends AbstractToByteStringSerializator<Byte> {

    @Override
    final protected byte[] convertToBytes(final Byte instance) {
        return new byte[]{instance};
    }

}
