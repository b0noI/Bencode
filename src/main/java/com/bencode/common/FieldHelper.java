package com.bencode.common;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


public class FieldHelper {

    public static boolean shouldBeSerialized(final Field field) {
        return !isTransient(field) && !(isStatic(field) && isFinal(field));
    }

    @VisibilityReducedForTestPurposeOnly
    static boolean isTransient(final Field field) {
        final int foundMods = field.getModifiers();
        return Modifier.isTransient(foundMods);
    }

    @VisibilityReducedForTestPurposeOnly
    static boolean isStatic(final Field field) {
        final int foundMods = field.getModifiers();
        return Modifier.isStatic(foundMods);
    }

    @VisibilityReducedForTestPurposeOnly
    static boolean isFinal(final Field field) {
        final int foundMods = field.getModifiers();
        return Modifier.isFinal(foundMods);
    }

}
