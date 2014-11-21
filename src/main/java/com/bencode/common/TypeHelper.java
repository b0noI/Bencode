package com.bencode.common;


import java.io.Serializable;

public class TypeHelper {

    public static boolean canBeSerialized(final Object instance) {
        return instance instanceof Serializable;
    }

    public static boolean typeCanBeUnboxedToPrimitive(final Class type) {
        return type == Byte.class ||
                type == Short.class ||
                type == Integer.class ||
                type == Long.class ||
                type == Float.class  ||
                type == Double.class ||
                type == Character.class ||
                type == Boolean.class;
    }

}
