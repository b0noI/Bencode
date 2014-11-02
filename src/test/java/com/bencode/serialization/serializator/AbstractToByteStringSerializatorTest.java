package com.bencode.serialization.serializator;


import org.testng.annotations.Test;

import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;

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
        final byte[] actualResult = testInstance.serialize(inputValue).getElement();

        // result assert
        assertArrayEquals(expectedResult, actualResult);

        // mocks verify
    }

}