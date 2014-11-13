package com.bencode.serialization.serializator.primitive;


import com.bencode.serialization.model.ByteString;
import com.bencode.serialization.serializator.ISerializer;

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

        private final IPrimitiveSerializer primitiveSerializator;

        private final Class[]                   objectClasses;

        Type(IPrimitiveSerializer primitiveSerializator, Class... objectClasses) {
            this.primitiveSerializator = primitiveSerializator;
            this.objectClasses = objectClasses;
        }

        public static <T> IPrimitiveSerializer<T> findSerializator(final Class<T> targetClass) {
            for (Type type: values()) {
                for (Class typeClass : type.objectClasses) {
                    if (typeClass == targetClass) {
                        return type.getSerializator();
                    }
                }
            }
            return null;
        }

        public <T> IPrimitiveSerializer<T> getSerializator() {
            return primitiveSerializator;
        }

    }

}
