package com.bencode.serialization.serializator.primitive;


class CharSerializator extends AbstractToByteStringSerializator<Character> {

    private static final ShortSerializator SHORT_SERIALIZATOR = new ShortSerializator();

    @Override
    protected byte[] convertToBytes(final Character instance) {
        return SHORT_SERIALIZATOR.convertToBytes((short)(char)instance);
    }

}