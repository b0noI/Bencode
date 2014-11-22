package com.bencode.deserializator.converter;

import com.bencode.model.BencodeList;
import com.bencode.model.ByteString;
import com.bencode.model.Dict;
import com.bencode.model.IBEncodeElement;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

public class RecursiveConverterTest {

    @Test
    public void testGetInstance() throws Exception {
        // input arguments

        // mocks

        // expected results

        // creating test instance

        // execution test
        final IConverter actualResult = RecursiveConverter.getInstance();

        // result assert
        assertNotNull(actualResult);

        // mocks verify
    }

    @Test
    public void testConvertWhenFirstByteDictByte() throws Exception {
        // input arguments
        final byte[] inputBytes = new byte[]{'d', 0};

        // mocks
        final DictConverter dictConverterMock = mock(DictConverter.class);
        final Dict elementMock = mock(Dict.class);
        when(dictConverterMock.convert(inputBytes, 0)).thenReturn(elementMock);

        // expected results
        final IBEncodeElement expectedResult = elementMock;

        // creating test instance
        final RecursiveConverter recursiveConverter = new RecursiveConverter(dictConverterMock, null, null, null);

        // execution test
        final IBEncodeElement actualResult = recursiveConverter.convert(inputBytes);

        // result assert
        assertEquals(actualResult, expectedResult);

        // mocks verify
        verify(dictConverterMock, times(1)).convert(inputBytes, 0);
        verifyNoMoreInteractions(dictConverterMock);
    }

    @Test
    public void testConvertWhenFirstByteListByte() throws Exception {
        // input arguments
        final byte[] inputBytes = new byte[]{'l', 0};

        // mocks
        final ListConverter listConverterMock = mock(ListConverter.class);
        final BencodeList elementMock = mock(BencodeList.class);
        when(listConverterMock.convert(inputBytes, 0)).thenReturn(elementMock);

        // expected results
        final IBEncodeElement expectedResult = elementMock;

        // creating test instance
        final RecursiveConverter recursiveConverter = new RecursiveConverter(null, listConverterMock, null, null);

        // execution test
        final IBEncodeElement actualResult = recursiveConverter.convert(inputBytes);

        // result assert
        assertEquals(actualResult, expectedResult);

        // mocks verify
        verify(listConverterMock, times(1)).convert(inputBytes, 0);
        verifyNoMoreInteractions(listConverterMock);
    }

    @Test
    public void testConvertWhenFirstByteIntegerByte() throws Exception {
        // input arguments
        final byte[] inputBytes = new byte[]{'i', 0};

        // mocks
        final IntegerConverter integerConverterMock = mock(IntegerConverter.class);
        final IBEncodeElement elementMock = mock(IBEncodeElement.class);
        when(integerConverterMock.convert(inputBytes, 0)).thenReturn(elementMock);

        // expected results
        final IBEncodeElement expectedResult = elementMock;

        // creating test instance
        final RecursiveConverter recursiveConverter = new RecursiveConverter(null, null, null, integerConverterMock);

        // execution test
        final IBEncodeElement actualResult = recursiveConverter.convert(inputBytes);

        // result assert
        assertEquals(actualResult, expectedResult);

        // mocks verify
        verify(integerConverterMock, times(1)).convert(inputBytes, 0);
        verifyNoMoreInteractions(integerConverterMock);
    }

    @Test
    public void testConvertWhenByteString() throws Exception {
        // input arguments
        final byte[] inputBytes = new byte[]{1, ':', 0};

        // mocks
        final ByteStringConverter byteStringConverterMock = mock(ByteStringConverter.class);
        final ByteString elementMock = mock(ByteString.class);
        when(byteStringConverterMock.convert(inputBytes, 0)).thenReturn(elementMock);

        // expected results
        final IBEncodeElement expectedResult = elementMock;

        // creating test instance
        final RecursiveConverter recursiveConverter = new RecursiveConverter(null, null, byteStringConverterMock, null);

        // execution test
        final IBEncodeElement actualResult = recursiveConverter.convert(inputBytes);

        // result assert
        assertEquals(actualResult, expectedResult);

        // mocks verify
        verify(byteStringConverterMock, times(1)).convert(inputBytes, 0);
        verifyNoMoreInteractions(byteStringConverterMock);
    }

}