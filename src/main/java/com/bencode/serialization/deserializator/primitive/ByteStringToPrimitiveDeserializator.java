package com.bencode.serialization.deserializator.primitive;

import com.bencode.serialization.model.ByteString;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.function.Function;


class ByteStringToPrimitiveDeserializator<T> implements IPrimitiveDeserializator<T> {

    private final Function<ByteBuffer, T> toPrimitiveConverter;

    ByteStringToPrimitiveDeserializator(final Function<ByteBuffer, T> toPrimitiveConverter) {
        this.toPrimitiveConverter = toPrimitiveConverter;
    }

    @Override
    public T deserializator(final ByteString from) {
        final byte[] bytes = from.getElement();
        final int indexOfDevider = indexOf(bytes, (byte)':');
        final ByteBuffer result = ByteBuffer.allocate(bytes.length - indexOfDevider - 1);
        for (int i = indexOfDevider + 1; i < bytes.length; i++) {
            result.put(bytes[i]);
        }

        result.position(0);

        return toPrimitiveConverter.apply(result);
    }

    private int indexOf(final byte[] bytes, final byte target) {
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] == target) {
                return i;
            }
        }
        return -1;
    }

}
