package com.bencode.model;

import java.nio.ByteBuffer;
import java.util.Arrays;


public class ByteString extends AbstractBEncodeElement implements Comparable<ByteString> {

    public  static  final byte       BYTE_STRING_SEPARATOR           = ':';

    private static  final ByteString NULL_BYTE_STRING                = ByteString.buildElement(new byte[]{-1});

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

    public static ByteString buildElement(final int value) {
        final ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        byteBuffer.putInt(value);
        return new ByteString(byteBuffer.array());
    }

    public static ByteString buildElement(final String from) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(from.length());
        for (char ch : from.toCharArray()) {
            byteBuffer.put((byte)ch);
        }
        return ByteString.buildElement(byteBuffer.array());
    }

    public static ByteString nullString() {
        return NULL_BYTE_STRING;
    }

    public byte[] getValue() {
        return value.array();
    }

    @Override
    public byte[] getElement() {
        final ByteBuffer byteBuffer = ByteBuffer.allocate(getValue().length + 5);
        byteBuffer.putInt(getValue().length);
        byteBuffer.put(BYTE_STRING_SEPARATOR);
        byteBuffer.put(getValue());
        return byteBuffer.array();
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(getElement().length);
        for (byte element: getElement()) {
            sb.append((char) element);
        }
        return sb.toString();
    }

}
