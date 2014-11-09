package com.bencode.common;


public class TypeHelper {

    public static boolean typeCanBeUnboxedToPrimitive(final Class type) {
        return type == Byte.class ||
                type == Short.class ||
                type == Integer.class ||
                type == Long.class ||
                type == Float.class  ||
                type == Double.class;
    }

}
