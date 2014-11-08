package com.bencode.serialization.converter;

import com.bencode.serialization.model.ByteString;

class ByteStringConverter implements IConverter<ByteString> {

    private static final int SEPARTOR_INDEX = 1;

    @Override
    public ByteString convert(final byte[] bytes, final int position) {
        final byte size = bytes[position];
        final byte[] result = new byte[size];
        System.arraycopy(bytes, position + SEPARTOR_INDEX + 1, result, 0, result.length);
        return ByteString.buildElement(result);
    }

}
