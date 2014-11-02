package com.bencode.serialization.serializator.primitive;


import com.bencode.serialization.model.ByteString;
import com.bencode.serialization.serializator.ISerializator;

public interface IPrimitiveSerializator<T> extends ISerializator<T> {

    @Override
    public ByteString serialize(final T instance);

    public enum Type {
        BYTE(new ByteSerializator()),
        SHORT(new ShortSerializator()),
        CHAR(new CharSerializator()),
        INTEGER(new IntegerSerializator()),
        LONG(new LongSerializator()),
        FLOAT(new FloatSerializator()),
        DOUBLE(new DoubleSerializator());

        private final IPrimitiveSerializator primitiveSerializator;

        Type(IPrimitiveSerializator primitiveSerializator) {
            this.primitiveSerializator = primitiveSerializator;
        }

        public <T> IPrimitiveSerializator<T> getSerializator() {
            return primitiveSerializator;
        }

    }

}
