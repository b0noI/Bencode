package com.bencode.serialization.model;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class ByteString implements IBEncodeElement, Comparable<ByteString> {

    private static  final byte       BYTE_STRING_SEPARATOR           = ':';

    private static  final String     ILLEGAL_ARGUMENT_ERROR_STRING   = "Instance length in bytes to long!";

    private         final ByteBuffer value;

    private ByteString(byte[] value) {
        this.value = ByteBuffer.wrap(value);
    }

    public static ByteString buildElement(final byte[] value) {
        if (value.length > Byte.MAX_VALUE) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT_ERROR_STRING);
        }
        return new ByteString(value);
    }

    public byte[] getValue() {
        return value.array();
    }

    @Override
    public byte[] getElement() {
        final byte[] result = new byte[getValue().length + 2];
        // this will work little bit more faster then ByteBuffer using
        System.arraycopy(getValue(), 0, result, 2, getValue().length);

        result[0] = (byte)getValue().length;
        result[1] = BYTE_STRING_SEPARATOR;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ByteString that = (ByteString) o;

        if (!Arrays.equals(value.array(), that.value.array())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return value != null ? Arrays.hashCode(value.array()) : 0;
    }

    @Override
    public int compareTo(final ByteString that) {
        return this.value.compareTo(that.value);
    }

}