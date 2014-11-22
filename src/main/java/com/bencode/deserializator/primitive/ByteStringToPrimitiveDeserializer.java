package com.bencode.deserializator.primitive;

import com.bencode.model.ByteString;
import org.apache.commons.lang3.ArrayUtils;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.function.Function;


class ByteStringToPrimitiveDeserializer<T> implements IPrimitiveDeserializer<T> {

    private final Function<ByteBuffer, T> toPrimitiveConverter;

    ByteStringToPrimitiveDeserializer(final Function<ByteBuffer, T> toPrimitiveConverter) {
        this.toPrimitiveConverter = toPrimitiveConverter;
    }

    @Override
    public T deserialize(final ByteString from) {
        final byte[] bytes = from.getElement();
        final int indexOfDivider = ArrayUtils.indexOf(bytes, ByteString.BYTE_STRING_SEPARATOR);
        final ByteBuffer result = ByteBuffer.allocate(bytes.length - indexOfDivider - 1);
        for (int i = indexOfDivider + 1; i < bytes.length; i++) {
            result.put(bytes[i]);
        }

        result.position(0);

        return toPrimitiveConverter.apply(result);
    }

}
