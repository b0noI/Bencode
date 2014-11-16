package com.bencode.common;

public enum Type {

    PRIMITIVE,
    ARRAY,
    REF;

    public static Type getType(final Class<?> type) {
        if (type.isPrimitive() || TypeHelper.typeCanBeUnboxedToPrimitive(type)) {
            return PRIMITIVE;
        } else if (type.isArray()) {
            return ARRAY;
        }
        return REF;
    }

}
