package com.bencode.serialization.serializator.primitive;


class CharSerializer extends AbstractToByteStringSerializer<Character> {

    private static final ShortSerializer SHORT_SERIALIZATOR = new ShortSerializer();

    @Override
    protected byte[] convertToBytes(final Character instance) {
        return SHORT_SERIALIZATOR.convertToBytes((short)(char)instance);
    }

}
