package com.bencode.serialization.serializator;

import org.junit.Test;

import static org.junit.Assert.*;

public class ByteSerializatorTest {

    @Test
    public void testConvertToBytes() throws Exception {
        // input arguments
        final byte inputValue = '?';

        // mocks

        // expected results
        final byte[] expectedResult = new byte[]{'?'};

        // creating test instance
        final ByteSerializator testInstance = new ByteSerializator();

        // execution test
        final byte[] actualResult = testInstance.convertToBytes(inputValue);

        // result assert
        assertArrayEquals(expectedResult, actualResult);

        // mocks verify
    }

}