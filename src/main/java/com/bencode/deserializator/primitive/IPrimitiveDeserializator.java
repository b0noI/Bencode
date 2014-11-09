package com.bencode.deserializator.primitive;

import com.bencode.serialization.model.ByteString;

import java.nio.ByteBuffer;

/**
 * Created by b0noI on 05/11/14.
 */
public interface IPrimitiveDeserializator<T> {

    public T deserializator(final ByteString from);

    public enum Type {
        BYTE    (new ByteStringToPrimitiveDeserializator<>(ByteBuffer::get)),
        SHORT   (new ByteStringToPrimitiveDeserializator<>(ByteBuffer::getShort)),
        CHAR    (new ByteStringToPrimitiveDeserializator<>(ByteBuffer::getChar)),
        INTEGER (new ByteStringToPrimitiveDeserializator<>(ByteBuffer::getInt)),
        LONG    (new ByteStringToPrimitiveDeserializator<>(ByteBuffer::getLong)),
        FLOAT   (new ByteStringToPrimitiveDeserializator<>(ByteBuffer::getFloat)),
        DOUBLE  (new ByteStringToPrimitiveDeserializator<>(ByteBuffer::getDouble)),
        BOOLEAN (new ByteStringToBooleanDeserializator());

        private final IPrimitiveDeserializator primitiveSerializator;

        Type(IPrimitiveDeserializator primitiveSerializator) {
            this.primitiveSerializator = primitiveSerializator;
        }

        public <T> IPrimitiveDeserializator<T> getDeserializator() {
            return primitiveSerializator;
        }

    }

}
