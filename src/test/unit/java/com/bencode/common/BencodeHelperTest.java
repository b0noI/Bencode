package com.bencode.common;

import com.bencode.model.BencodeInteger;
import com.bencode.model.ByteString;
import com.bencode.model.IBEncodeElement;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class BencodeHelperTest {

    @Test
    public void testIsNullElementWhenNullByteString() throws Exception {
        // input arguments
        final IBEncodeElement nullElement = ByteString.nullString();

        // mocks

        // expected results
        final boolean expectedResult = true;

        // creating test instance

        // execution test
        final boolean actualResult = BencodeHelper.isNullElement(nullElement);

        // result assert
        assertEquals(actualResult, expectedResult);

        // mocks verify
    }

    @Test
    public void testIsNullElementWhenNotNullByteString() throws Exception {
        // input arguments
        final IBEncodeElement nullElement = ByteString.buildElement(1);

        // mocks

        // expected results
        final boolean expectedResult = false;

        // creating test instance

        // execution test
        final boolean actualResult = BencodeHelper.isNullElement(nullElement);

        // result assert
        assertEquals(actualResult, expectedResult);

        // mocks verify
    }

    @Test
    public void testIsNullElementWhenNotOtherType() throws Exception {
        // input arguments
        final IBEncodeElement nullElement = new BencodeInteger(12);

        // mocks

        // expected results
        final boolean expectedResult = false;

        // creating test instance

        // execution test
        final boolean actualResult = BencodeHelper.isNullElement(nullElement);

        // result assert
        assertEquals(actualResult, expectedResult);

        // mocks verify
    }

    @Test
    public void testIsNullElementWhenNull() throws Exception {
        // input arguments
        final IBEncodeElement nullElement = null;

        // mocks

        // expected results
        final boolean expectedResult = false;

        // creating test instance

        // execution test
        final boolean actualResult = BencodeHelper.isNullElement(nullElement);

        // result assert
        assertEquals(actualResult, expectedResult);

        // mocks verify
    }

}