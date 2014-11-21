package com.bencode.deserializator.converter;

import com.bencode.model.ByteString;
import com.bencode.model.IBEncodeElement;
import org.apache.commons.lang3.SerializationException;
import org.testng.annotations.Test;

import javax.sql.rowset.serial.SerialException;

import static org.testng.Assert.*;

public class ByteStringConverterTest {

    @Test
    public void testGetInstance() throws Exception {
        // input arguments

        // mocks

        // expected results

        // creating test instance
        final IConverter converter = IConverter.getConverter();

        // execution test

        // result assert
        assertNotNull(converter);

        // mocks verify

    }

    @Test
    public void testConvert() throws Exception {
        // input arguments
        final byte[] inputBytes = new byte[]{0, 0, 0, 1, ':', 1};

        // mocks

        // expected results
        final IBEncodeElement expectedResult = ByteString.buildElement(new byte[]{1});

        // creating test instance
        final ByteStringConverter converter = ByteStringConverter.getInstance();

        // execution test
        final IBEncodeElement actualresult = converter.convert(inputBytes);

        // result assert
        assertEquals(actualresult, expectedResult);

        // mocks verify
    }

    @Test
    public void testValidate() throws Exception {
        // input arguments
        final byte[] inputBytes = new byte[]{0, 0, 0, 0, ':'};
        final int inputPosition = 0;

        // mocks

        // expected results

        // creating test instance
        final ByteStringConverter converter = ByteStringConverter.getInstance();

        // execution test
        converter.validate(inputBytes, inputPosition);

        // result assert

        // mocks verify
    }

    @Test
    public void testValidateWhenInputInvalid() throws Exception {
        // input arguments
        final byte[] inputBytes = new byte[]{0, 0, 0, 0};
        final int inputPosition = 0;

        // mocks

        // expected results

        // creating test instance
        final ByteStringConverter converter = ByteStringConverter.getInstance();

        // execution test
        try {
            converter.validate(inputBytes, inputPosition);
            fail();
        } catch (SerializationException e) {}

        // result assert

        // mocks verify
    }

    @Test
    public void testGetSize() throws Exception {
        // input arguments
        final byte[] inputBytes = new byte[]{0, 0, 0, 5};
        final int inputPosition = 0;

        // mocks

        // expected results
        final int expectedResult = 5;

        // creating test instance
        final ByteStringConverter converter = ByteStringConverter.getInstance();

        // execution test
        final int actualResult = converter.getSize(inputBytes, inputPosition);

        // result assert
        assertEquals(actualResult, expectedResult);

        // mocks verify
    }

    @Test
    public void testGetSizeWhenResultMoreThen1Byte() throws Exception {
        // input arguments
        final byte[] inputBytes = new byte[]{0, 20, 16, 5};
        final int inputPosition = 0;

        // mocks

        // expected results
        final int expectedResult = 1314821;

        // creating test instance
        final ByteStringConverter converter = ByteStringConverter.getInstance();

        // execution test
        final int actualResult = converter.getSize(inputBytes, inputPosition);

        // result assert
        assertEquals(actualResult, expectedResult);

        // mocks verify
    }

}