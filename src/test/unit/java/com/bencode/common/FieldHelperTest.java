package com.bencode.common;

import com.bencode.serializator.primitive.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Field;

import static org.testng.Assert.*;

public class FieldHelperTest {

    @DataProvider(name = "fields_test_data_provider")
    private static Object[][] fieldsDataProvider() {
        return new Object[][]{
                {"staticField",                 true },
                {"transientField",              false},
                {"finalField",                  true },
                {"staticFinalField",            false},
                {"staticTransientField",        false},
                {"transientFinalField",         false},
                {"transientStaticFinalField",   false},
        } ;
    }

    @DataProvider(name = "transient_fields_test_data_provider")
    private static Object[][] transientFieldsDataProvider() {
        return new Object[][]{
                {"staticField",                 false},
                {"transientField",              true },
                {"finalField",                  false},
                {"staticFinalField",            false},
                {"staticTransientField",        true },
                {"transientFinalField",         true },
                {"transientStaticFinalField",   true },
        } ;
    }

    @DataProvider(name = "static_fields_test_data_provider")
    private static Object[][] staticFieldsDataProvider() {
        return new Object[][]{
                {"staticField",                 true },
                {"transientField",              false},
                {"finalField",                  false},
                {"staticFinalField",            true },
                {"staticTransientField",        true },
                {"transientFinalField",         false},
                {"transientStaticFinalField",   true },
        } ;
    }

    @DataProvider(name = "final_fields_test_data_provider")
    private static Object[][] finalFieldsDataProvider() {
        return new Object[][]{
                {"staticField",                 false},
                {"transientField",              false},
                {"finalField",                  true },
                {"staticFinalField",            true },
                {"staticTransientField",        false},
                {"transientFinalField",         true },
                {"transientStaticFinalField",   true },
        } ;
    }

    @Test(dataProvider = "fields_test_data_provider")
    public void testShouldBeSerialized(final String fieldName, final boolean expectedResult) throws Exception {
        // input arguments
        final Field field = TestClass.class.getDeclaredField(fieldName);

        // mocks

        // expected results

        // creating test instance

        // execution test
        final boolean actualResult = FieldHelper.shouldBeSerialized(field);

        // result assert
        assertEquals(actualResult, expectedResult);

        // mocks verify
    }

    @Test(dataProvider = "transient_fields_test_data_provider")
    public void testIsTransient(final String fieldName, final boolean expectedResult) throws Exception {
        // input arguments
        final Field field = TestClass.class.getDeclaredField(fieldName);

        // mocks

        // expected results

        // creating test instance

        // execution test
        final boolean actualResult = FieldHelper.isTransient(field);

        // result assert
        assertEquals(actualResult, expectedResult);

        // mocks verify
    }

    @Test(dataProvider = "static_fields_test_data_provider")
    public void testIsStatic(final String fieldName, final boolean expectedResult) throws Exception {
        // input arguments
        final Field field = TestClass.class.getDeclaredField(fieldName);

        // mocks

        // expected results

        // creating test instance

        // execution test
        final boolean actualResult = FieldHelper.isStatic(field);

        // result assert
        assertEquals(actualResult, expectedResult);

        // mocks verify
    }

    @Test(dataProvider = "final_fields_test_data_provider")
    public void testIsFinal(final String fieldName, final boolean expectedResult) throws Exception {
        // input arguments
        final Field field = TestClass.class.getDeclaredField(fieldName);

        // mocks

        // expected results

        // creating test instance

        // execution test
        final boolean actualResult = FieldHelper.isFinal(field);

        // result assert
        assertEquals(actualResult, expectedResult);

        // mocks verify
    }

    private static class TestClass {

        transient int transientField;

        static int staticField;

        final int finalField = 2;

        static final int staticFinalField = 2;

        static transient int staticTransientField;

        transient final int transientFinalField = 2;

        static transient final int transientStaticFinalField = 2;

    }

}