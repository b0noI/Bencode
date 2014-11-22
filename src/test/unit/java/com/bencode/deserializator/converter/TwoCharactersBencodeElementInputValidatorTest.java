package com.bencode.deserializator.converter;

import org.apache.commons.lang3.SerializationException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.OptionalInt;

import static org.testng.Assert.*;

public class TwoCharactersBencodeElementInputValidatorTest {

    @DataProvider(name = "array_validation_data_provider")
    private static Object[][] arraysForValidationDataProvider() {
        return new Object[][]{
                {new byte[]{'d', 'e'},          0,  false},
                {new byte[]{'d', 'e'},          1,  true},
                {new byte[]{'d', 'e'},          3,  true},
                {null,                          0,  true},
                {new byte[]{'d', 'e'},          -2, true},
                {new byte[]{'d', 0},            0,  true},
                {new byte[]{'d', 0, 0, 'e'},    0,  false},
        } ;
    }

    @DataProvider(name = "array_index_test_data_provider")
    private static Object[][] arraysForIndexTestDataProvider() {
        return new Object[][]{
                {new byte[]{'d', 'e'},          0,  OptionalInt.of(1)},
                {new byte[]{'d', 'e'},          1,  OptionalInt.empty()},
                {new byte[]{'d', 'e'},          3,  OptionalInt.empty()},
                {new byte[]{'d', 0},            0,  OptionalInt.empty()},
                {new byte[]{'d', 0, 0, 'e'},    0,  OptionalInt.of(3)},
        } ;
    }

    @Test(dataProvider = "array_validation_data_provider")
    public void testValidate(final byte[] testArray, final int position, final boolean shouldThrowException) throws Exception {
        // input arguments

        // mocks

        // expected results

        // creating test instance
        final TwoCharactersBencodeElementInputValidator testInstance = new TwoCharactersBencodeElementInputValidator((byte)'d', (byte)'e');

        // execution test
        try {
            testInstance.validate(testArray, position);
            if (shouldThrowException) fail();
        } catch (SerializationException e) {
            if (!shouldThrowException) fail();
        }

        // result assert

        // mocks verify
    }

    @Test(dataProvider = "array_index_test_data_provider")
    public void testIndexOfEndDictByte(final byte[] testArray, final int position, final OptionalInt expectedResult) throws Exception {
        // input arguments

        // mocks

        // expected results

        // creating test instance
        final TwoCharactersBencodeElementInputValidator testInstance = new TwoCharactersBencodeElementInputValidator((byte)'d', (byte)'e');

        // execution test
        final OptionalInt actualResult = testInstance.indexOfEndDictByte(testArray, position);

        // result assert
        assertEquals(actualResult, expectedResult);

        // mocks verify
    }
}