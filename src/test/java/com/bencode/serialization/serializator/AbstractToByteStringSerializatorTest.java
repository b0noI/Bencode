package com.bencode.serialization.serializator;

import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractToByteStringSerializatorTest {

    @Test
    public void testSerialize() throws Exception {
        // input arguments
        final byte inputValue = '?';

        // mocks

        // expected results
        final byte[] expectedResult = new byte[]{1, ':','?'};

        // creating test instance
        final AbstractToByteStringSerializator<Byte> testInstance = new ByteSerializator();

        // execution test
        final byte[] actualResult = testInstance.serialize(inputValue);

        // result assert
        assertArrayEquals(expectedResult, actualResult);

        // mocks verify
    }

}