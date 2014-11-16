package com.bencode.deserializator.converter;

import com.bencode.serialization.model.ByteString;
import org.apache.commons.lang3.SerializationException;

import java.nio.ByteBuffer;

class ByteStringConverter implements IConverter<ByteString> {

    private static final ByteStringConverter    INSTANCE                                        = new ByteStringConverter();

    private static final int                    SEPARTOR_INDEX                                  = 4;

    private static final String                 BYTE_STRING_SEPARATOR_NOT_FOUND_ERROR_MESSAGE   = "ByteString separator not found";

    private ByteStringConverter(){}

    public static ByteStringConverter getInstance() {
        return INSTANCE;
    }

    @Override
    public ByteString convert(final byte[] bytes, final int position) {
        if (bytes[position + SEPARTOR_INDEX] != ByteString.BYTE_STRING_SEPARATOR) {
            throw new SerializationException(BYTE_STRING_SEPARATOR_NOT_FOUND_ERROR_MESSAGE);
        }
        final ByteBuffer sizeBuffet = ByteBuffer.allocate(SEPARTOR_INDEX);
        for (int i = position; i < position + SEPARTOR_INDEX; i++) {
            sizeBuffet.put(bytes[i]);
        }
        sizeBuffet.position(0);
        final int size = sizeBuffet.getInt();
        final byte[] result = new byte[size];
        System.arraycopy(bytes, position + SEPARTOR_INDEX + 1, result, 0, result.length);
        return ByteString.buildElement(result);
    }

}
