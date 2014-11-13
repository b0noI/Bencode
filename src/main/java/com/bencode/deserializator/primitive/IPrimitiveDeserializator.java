package com.bencode.deserializator.primitive;

import com.bencode.serialization.model.ByteString;

import java.nio.ByteBuffer;

/**
 * Created by b0noI on 05/11/14.
 */
public interface IPrimitiveDeserializator<T> {

    public T deserializator(final ByteString from);

    public enum Type {
        BYTE    (new ByteStringToPrimitiveDeserializator<>(ByteBuffer::get),        Byte.class,         byte.class),
        SHORT   (new ByteStringToPrimitiveDeserializator<>(ByteBuffer::getShort),   Short.class,        short.class),
        CHAR    (new ByteStringToPrimitiveDeserializator<>(ByteBuffer::getChar),    Character.class,    char.class),
        INTEGER (new ByteStringToPrimitiveDeserializator<>(ByteBuffer::getInt),     Integer.class,      int.class),
        LONG    (new ByteStringToPrimitiveDeserializator<>(ByteBuffer::getLong),    Long.class,         long.class),
        FLOAT   (new ByteStringToPrimitiveDeserializator<>(ByteBuffer::getFloat),   Float.class,        float.class),
        DOUBLE  (new ByteStringToPrimitiveDeserializator<>(ByteBuffer::getDouble),  Double.class,       double.class),
        BOOLEAN (new ByteStringToBooleanDeserializator(),                           Boolean.class,      boolean.class);

        private final IPrimitiveDeserializator primitiveSerializator;

        private final Class<?>[] objectClasses;

        Type(final IPrimitiveDeserializator primitiveSerializator, final Class<?>... objectClasses) {
            this.primitiveSerializator = primitiveSerializator;
            this.objectClasses = objectClasses;
        }

        public static <T> IPrimitiveDeserializator<T> findDeserializator(final Class<T> targetClass) {
            for (Type type: values()) {
                for (Class typeClass : type.objectClasses) {
                    if (typeClass == targetClass) {
                        return type.getDeserializator();
                    }
                }
            }
            return null;
        }

        public <T> IPrimitiveDeserializator<T> getDeserializator() {
            return primitiveSerializator;
        }

    }

}
