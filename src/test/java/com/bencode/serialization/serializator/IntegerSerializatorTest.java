package com.bencode.serialization.serializator;

import org.junit.Test;

import static org.junit.Assert.*;

public class IntegerSerializatorTest {

    @Test
    public void testConvertToBytes() throws Exception {
        // input arguments
        final int inputValue = 300_000_000;

        // mocks

        // expected results
        final byte[] expectedResult = new byte[]{17, -31, -93, 0};

        // creating test instance
        final IntegerSerializator testInstance = new IntegerSerializator();

        // execution test
        final byte[] actualResult = testInstance.convertToBytes(inputValue);

        // result assert
        assertArrayEquals(expectedResult, actualResult);

        // mocks verify
    }

}