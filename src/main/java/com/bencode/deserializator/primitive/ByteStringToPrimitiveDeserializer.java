package com.bencode.deserializator.primitive;

import com.bencode.serialization.model.ByteString;

import java.nio.ByteBuffer;
import java.util.function.Function;


class ByteStringToPrimitiveDeserializer<T> implements IPrimitiveDeserializer<T> {

    private final Function<ByteBuffer, T> toPrimitiveConverter;

    ByteStringToPrimitiveDeserializer(final Function<ByteBuffer, T> toPrimitiveConverter) {
        this.toPrimitiveConverter = toPrimitiveConverter;
    }

    @Override
    public T deserialize(final ByteString from) {
        final byte[] bytes = from.getElement();
        final int indexOfDivider = indexOf(bytes, (byte)':');
        final ByteBuffer result = ByteBuffer.allocate(bytes.length - indexOfDivider - 1);
        for (int i = indexOfDivider + 1; i < bytes.length; i++) {
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
