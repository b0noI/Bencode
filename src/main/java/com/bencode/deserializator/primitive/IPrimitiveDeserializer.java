package com.bencode.deserializator.primitive;

import com.bencode.model.ByteString;

import java.nio.ByteBuffer;
import java.util.Optional;

/**
 * Created by b0noI on 05/11/14.
 */
public interface IPrimitiveDeserializer<T> {

    public T deserialize(final ByteString from);

    public enum Type {
        BYTE    (new ByteStringToPrimitiveDeserializer<>(ByteBuffer::get),        Byte.class,         byte.class),
        SHORT   (new ByteStringToPrimitiveDeserializer<>(ByteBuffer::getShort),   Short.class,        short.class),
        CHAR    (new ByteStringToPrimitiveDeserializer<>(ByteBuffer::getChar),    Character.class,    char.class),
        INTEGER (new ByteStringToPrimitiveDeserializer<>(ByteBuffer::getInt),     Integer.class,      int.class),
        LONG    (new ByteStringToPrimitiveDeserializer<>(ByteBuffer::getLong),    Long.class,         long.class),
        FLOAT   (new ByteStringToPrimitiveDeserializer<>(ByteBuffer::getFloat),   Float.class,        float.class),
        DOUBLE  (new ByteStringToPrimitiveDeserializer<>(ByteBuffer::getDouble),  Double.class,       double.class),
        BOOLEAN (new ByteStringToBooleanDeserializer(),                           Boolean.class,      boolean.class);

        private final IPrimitiveDeserializer primitiveSerializer;

        private final Class<?>[] objectClasses;

        Type(final IPrimitiveDeserializer primitiveSerializer, final Class<?>... objectClasses) {
            this.primitiveSerializer = primitiveSerializer;
            this.objectClasses = objectClasses;
        }

        public static <T> Optional<IPrimitiveDeserializer<T>> findDeserializer(final Class<?> targetClass) {
            for (Type type: values()) {
                for (Class typeClass : type.objectClasses) {
                    if (typeClass == targetClass) {
                        return Optional.of(type.getDeserializer());
                    }
                }
            }
            return Optional.empty();
        }

        public <T> IPrimitiveDeserializer<T> getDeserializer() {
            return primitiveSerializer;
        }

    }

}
