package com.bencode.serializator.primitive;


class ByteSerializer extends AbstractToByteStringSerializer<Byte> {

    @Override
    final protected byte[] convertToBytes(final Byte instance) {
        return new byte[]{instance};
    }

}
