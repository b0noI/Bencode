package com.bencode.model;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class BencodeIntegerTest {

    @Test
    public void testSet1() throws Exception {
        // input arguments
        final Integer inputValue = 3;

        // mocks

        // expected results
        final byte[] expectedResult = new byte[]{'i', 3, 'e'};

        // creating test instance
        final BencodeInteger testInstance = new BencodeInteger(inputValue);

        // execution test
        final byte[] actualResult = testInstance.getElement();

        // result assert
        assertEquals(actualResult, expectedResult);

        // mocks verify

    }

    @Test
    public void testSetMinus1() throws Exception {
        // input arguments
        final Integer inputValue = -3;

        // mocks

        // expected results
        final byte[] expectedResult = new byte[]{'i', '-', 3, 'e'};

        // creating test instance
        final BencodeInteger testInstance = new BencodeInteger(inputValue);

        // execution test
        final byte[] actualResult = testInstance.getElement();

        // result assert
        assertEquals(actualResult, expectedResult);

        // mocks verify

    }

    @Test
    public void testSetMaxInt() throws Exception {
        // input arguments
        final Integer inputValue = Integer.MAX_VALUE;

        // mocks

        // expected results
        final byte[] expectedResult = new byte[]{'i', 127, -1, -1, -1, 'e'};

        // creating test instance
        final BencodeInteger testInstance = new BencodeInteger(inputValue);

        // execution test
        final byte[] actualResult = testInstance.getElement();

        // result assert
        assertEquals(actualResult, expectedResult);

        // mocks verify

    }

    @Test
    public void testSetMinInt() throws Exception {
        // input arguments
        final Integer inputValue = Integer.MIN_VALUE;

        // mocks

        // expected results
        final byte[] expectedResult = new byte[]{'i', '-', 127, -1, -1, -1, 'e'};

        // creating test instance
        final BencodeInteger testInstance = new BencodeInteger(inputValue);

        // execution test
        final byte[] actualResult = testInstance.getElement();

        // result assert
        assertEquals(actualResult, expectedResult);

        // mocks verify

    }

}