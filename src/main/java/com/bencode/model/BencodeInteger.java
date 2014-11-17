package com.bencode.model;


import java.nio.ByteBuffer;
import java.util.Arrays;

public class BencodeInteger extends AbstractBEncodeElement {

    private static  final char  INTEGER_START_BYTE  = 'i';

    private static  final char  INTEGER_END_BYTE    = 'e';

    private         final int   value;

    public BencodeInteger(int value) {
        this.value = value;
    }

    @Override
    public byte[] getElement() {
        final byte[] valueInBytes = convert(Math.abs(value));
        final int elementSize = valueInBytes.length + (value >= 0 ? 2 : 3);
        final ByteBuffer result = ByteBuffer.allocate(elementSize);
        result.put((byte) INTEGER_START_BYTE);
        if (value < 0) result.put((byte)'-');
        result.put(valueInBytes);
        result.put((byte) INTEGER_END_BYTE);
        return result.array();
    }

    private byte[] convert(final Integer value) {
        if (value == Integer.MIN_VALUE) return new byte[]{127, -1, -1, -1};
        final ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        byteBuffer.putInt(value);
        int zerosCount = 0;
        for (byte b : byteBuffer.array()) {
            if (b == 0) zerosCount++;
        }
        final byte result[] = new byte[4 - zerosCount];
        for (int i = zerosCount; i < 4; i++) {
            result[i - zerosCount] = byteBuffer.array()[i];
        }
        return result;
    }

}
