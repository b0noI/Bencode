package com.bencode.deserializator.converter;

import com.bencode.common.VisibilityReducedForTestPurposeOnly;
import com.bencode.model.ByteString;
import org.apache.commons.lang3.SerializationException;

import java.nio.ByteBuffer;

class ByteStringConverter implements IConverter<ByteString> {

    private static final ByteStringConverter    INSTANCE                                        = new ByteStringConverter()         ;

    private static final int                    SEPARATOR_INDEX                                 = 4                                 ;

    private static final String                 BYTE_STRING_SEPARATOR_NOT_FOUND_ERROR_MESSAGE   = "ByteString separator not found"  ;

    private ByteStringConverter(){}

    public static ByteStringConverter getInstance() {
        return INSTANCE;
    }

    @Override
    public ByteString convert(final byte[] bytes, final int position) {
        validate(bytes, position);
        final int size = getSize(bytes, position);
        final byte[] result = new byte[size];
        System.arraycopy(bytes, position + SEPARATOR_INDEX + 1, result, 0, result.length);
        return ByteString.buildElement(result);
    }

    @VisibilityReducedForTestPurposeOnly
    void validate(final byte[] bytes, final int position) {
        if (bytes.length <= position + SEPARATOR_INDEX
                || bytes[position + SEPARATOR_INDEX] != ByteString.BYTE_STRING_SEPARATOR) {
            throw new SerializationException(BYTE_STRING_SEPARATOR_NOT_FOUND_ERROR_MESSAGE);
        }
    }

    @VisibilityReducedForTestPurposeOnly
    int getSize(final byte[] bytes, final int position) {
        int size = 0;
        for (int i = position; i < position + SEPARATOR_INDEX; i++) {
            size <<= 8;
            size += bytes[i];
        }
        return size;
    }

}
