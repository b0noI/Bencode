package com.bencode.serialization.serializator.primitive;


import com.bencode.serialization.model.ByteString;
import com.bencode.serialization.serializator.ISerializator;

public interface IPrimitiveSerializator<T> extends ISerializator<T> {

    @Override
    public ByteString serialize(final T instance);

    public enum Type {
        BYTE    (new ByteSerializator()     , Byte.class,       byte.class),
        SHORT   (new ShortSerializator()    , Short.class,      short.class),
        CHAR    (new CharSerializator()     , Character.class,  char.class),
        INTEGER (new IntegerSerializator()  , Integer.class,    int.class),
        LONG    (new LongSerializator()     , Long.class,       long.class),
        FLOAT   (new FloatSerializator()    , Float.class,      float.class),
        DOUBLE  (new DoubleSerializator()   , Double.class,     double.class);

        private final IPrimitiveSerializator primitiveSerializator;

        private final Class[] objectClasses;

        Type(IPrimitiveSerializator primitiveSerializator, Class... objectClasses) {
            this.primitiveSerializator = primitiveSerializator;
            this.objectClasses = objectClasses;
        }

        public static <T> IPrimitiveSerializator<T> findSerializator(final Class<T> targetClass) {
            for (Type type: values()) {
                for (Class typeClass : type.objectClasses) {
                    if (typeClass == targetClass) {
                        return type.getSerializator();
                    }
                }
            }
            return null;
        }

        public <T> IPrimitiveSerializator<T> getSerializator() {
            return primitiveSerializator;
        }

    }

}
