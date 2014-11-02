package com.bencode.serialization.serializator;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShortSerializatorTest {

    @Test
    public void testConvertToBytes() throws Exception {
        // input arguments
        final short inputValue = 3000;

        // mocks

        // expected results
        final byte[] expectedResult = new byte[]{11, -72};

        // creating test instance
        final ShortSerializator testInstance = new ShortSerializator();

        // execution test
        final byte[] actualResult = testInstance.convertToBytes(inputValue);

        // result assert
        assertArrayEquals(expectedResult, actualResult);

        // mocks verify
    }

}