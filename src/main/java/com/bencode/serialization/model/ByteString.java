package com.bencode.serialization.model;

public class ByteString implements IBEncodeElement{

    private static  final byte      BYTE_STRING_SEPARATOR           = ':';

    private static  final String    ILLEGAL_ARGUMENT_ERROR_STRING   = "Instance length in bytes to long!";

    private         final byte[]    value;

    private ByteString(byte[] value) {
        this.value = value;
    }

    public static ByteString buildElement(final byte[] value) {
        if (value.length > Byte.MAX_VALUE) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT_ERROR_STRING);
        }
        return new ByteString(value);
    }

    public byte[] getValue() {
        return value;
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

}
