package com.bencode.serialization.serializator.primitive;


class ByteSerializator extends AbstractToByteStringSerializator<Byte> {

    @Override
    final protected byte[] convertToBytes(final Byte instance) {
        return new byte[]{instance};
    }

}
