package com.bencode.serialization.serializator;

import com.sun.xml.internal.ws.encoding.soap.SerializationException;


abstract class AbstractToByteStringSerializator<T> implements ISerializator<T> {

    private static final byte BYTE_STRING_SEPARATOR = ':';

    @Override
    final public byte[] serialize(final T instance) {
        final byte[] bytes = convertToBytes(instance);
        final byte[] result = new byte[bytes.length + 2];
        // this will work little bit more faster then ByteBuffer using
        System.arraycopy(bytes, 0, result, 2, bytes.length);
        if (bytes.length > Byte.MAX_VALUE) {
            throw new SerializationException("Instance length in bytes to long!");
        }
        result[0] = (byte)bytes.length;
        result[1] = BYTE_STRING_SEPARATOR;
        return result;
    }

    abstract protected byte[] convertToBytes(final T instance);

}
