package com.bencode.serialization.serializator.primitive;


import com.bencode.serialization.model.ByteString;
import com.bencode.serialization.serializator.ISerializator;

public interface IPrimitiveSerializator<T> extends ISerializator<T> {

    @Override
    public ByteString serialize(final T instance);

    public enum Type {
        BYTE(new ByteSerializator(), Byte.class),
        SHORT(new ShortSerializator(), Short.class),
        CHAR(new CharSerializator(), Character.class),
        INTEGER(new IntegerSerializator(), Integer.class),
        LONG(new LongSerializator(), Long.class),
        FLOAT(new FloatSerializator(), Float.class),
        DOUBLE(new DoubleSerializator(), Double.class);

        private final IPrimitiveSerializator primitiveSerializator;

        private final Class objectClass;

        Type(IPrimitiveSerializator primitiveSerializator, Class objectClass) {
            this.primitiveSerializator = primitiveSerializator;
            this.objectClass = objectClass;
        }

        public static <T> IPrimitiveSerializator<T> findSerializator(final Class<T> targetClass) {
            for (Type value: values()) {
                if (value.objectClass == targetClass) {
                    return value.getSerializator();
                }
            }
            return null;
        }

        public <T> IPrimitiveSerializator<T> getSerializator() {
            return primitiveSerializator;
        }

    }

}
