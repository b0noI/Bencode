package com.bencode.common;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


public class FieldHelper {

    public static boolean shouldBeSerialized(final Field field) {
        return !isTransient(field) && !(isStatic(field) && isFinal(field));
    }

    private static boolean isTransient(final Field field) {
        final int foundMods = field.getModifiers();
        return Modifier.isTransient(foundMods);
    }

    private static boolean isStatic(final Field field) {
        final int foundMods = field.getModifiers();
        return Modifier.isStatic(foundMods);
    }

    private static boolean isFinal(final Field field) {
        final int foundMods = field.getModifiers();
        return Modifier.isFinal(foundMods);
    }

}
