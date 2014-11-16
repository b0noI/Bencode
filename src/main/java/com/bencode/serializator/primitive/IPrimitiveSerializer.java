package com.bencode.serializator.primitive;


import com.bencode.model.ByteString;
import com.bencode.serializator.ISerializer;

import java.util.Optional;

public interface IPrimitiveSerializer<T> extends ISerializer<T> {

    @Override
    public ByteString serialize(final T instance);

    public enum Type {
        BYTE    (new ByteSerializer()     , Byte.class,       byte.class),
        SHORT   (new ShortSerializer()    , Short.class,      short.class),
        CHAR    (new CharSerializer()     , Character.class,  char.class),
        INTEGER (new IntegerSerializer()  , Integer.class,    int.class),
        LONG    (new LongSerializer()     , Long.class,       long.class),
        FLOAT   (new FloatSerializer()    , Float.class,      float.class),
        DOUBLE  (new DoubleSerializer()   , Double.class,     double.class),
        BOOLEAN (new BooleanSerializer()  , Boolean.class,    boolean.class);

        private final IPrimitiveSerializer primitiveSerializer;

        private final Class[]               objectClasses;

        Type(IPrimitiveSerializer primitiveSerializer, Class... objectClasses) {
            this.primitiveSerializer = primitiveSerializer;
            this.objectClasses = objectClasses;
        }

        public static <T> Optional<IPrimitiveSerializer<T>> findSerializer(final Class<?> targetClass) {
            for (Type type: values()) {
                for (Class typeClass : type.objectClasses) {
                    if (typeClass == targetClass) {
                        return Optional.of(type.getSerializer());
                    }
                }
            }
            return Optional.empty();
        }

        public <T> IPrimitiveSerializer<T> getSerializer() {
            return primitiveSerializer;
        }

    }

}
