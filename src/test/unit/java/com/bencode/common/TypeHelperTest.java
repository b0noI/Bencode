package com.bencode.common;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.Serializable;

import static org.testng.Assert.*;

public class TypeHelperTest {

    @DataProvider(name = "unboxeble_types_data_provider")
    private static Object[][] unboxebleTypesDataProvider() {
        return new Object[][]{
                {Byte.class,             true },
                {Short.class,            true },
                {Integer.class,          true },
                {Long.class,             true },
                {Float.class,            true },
                {Double.class,           true },
                {Character.class,        true },
                {Boolean.class,          true },
                {Object.class,           false},
                {String.class,           false},
        } ;
    }

    @Test
    public void testCanBeSerialized() throws Exception {
        // input arguments
        final Serializable serializable = new Serializable() {};

        // mocks

        // expected results
        final boolean expectedResult = true;

        // creating test instance

        // execution test
        final boolean actualResult = TypeHelper.canBeSerialized(serializable);

        // result assert
        assertEquals(actualResult, expectedResult);

        // mocks verify
    }

    @Test
    public void testCanBeSerializedWhenNotSerializeble() throws Exception {
        // input arguments
        final Object instance = new Object();

        // mocks

        // expected results
        final boolean expectedResult = false;

        // creating test instance

        // execution test
        final boolean actualResult = TypeHelper.canBeSerialized(instance);

        // result assert
        assertEquals(actualResult, expectedResult);

        // mocks verify
    }

    @Test(dataProvider = "unboxeble_types_data_provider")
    public void testTypeCanBeUnboxedToPrimitive(final Class type, final boolean expectedResult) throws Exception {
        // input arguments

        // mocks

        // expected results

        // creating test instance

        // execution test
        final boolean actualResult = TypeHelper.typeCanBeUnboxedToPrimitive(type);

        // result assert
        assertEquals(expectedResult, actualResult);

        // mocks verify
    }

}