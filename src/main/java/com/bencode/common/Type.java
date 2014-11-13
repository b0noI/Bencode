package com.bencode.common;

public enum Type {

    PRIMITIVE,
    ARRAY,
    REF;

    public static Type getType(final Object instance) {
        if (instance.getClass().isPrimitive() || TypeHelper.typeCanBeUnboxedToPrimitive(instance.getClass())) {
            return PRIMITIVE;
        } else if (instance.getClass().isArray()) {
            return ARRAY;
        }
        return REF;
    }

}
