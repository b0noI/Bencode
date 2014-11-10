package com.bencode.common;


import java.io.Serializable;

public class TypeHelper {

    public static boolean canBeSerialized(final Class type) {
        return type instanceof Serializable;
    }

    public static boolean typeCanBeUnboxedToPrimitive(final Class type) {
        return type == Byte.class ||
                type == Short.class ||
                type == Integer.class ||
                type == Long.class ||
                type == Float.class  ||
                type == Double.class;
    }

    public static Class getComponentType(final Class type) {
        if (!type.isArray()) return type;
        return getComponentType(type.getComponentType());
    }

}
