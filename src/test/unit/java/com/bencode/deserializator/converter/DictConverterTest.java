package com.bencode.deserializator.converter;

import com.bencode.model.ByteString;
import com.bencode.model.Dict;
import com.bencode.model.IBEncodeElement;
import com.bencode.serializator.primitive.*;
import org.apache.commons.lang3.SerializationException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Optional;
import java.util.OptionalInt;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.testng.Assert.*;

public class DictConverterTest {

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

    @Test
    public void testConvert() throws Exception {
        // input arguments
        final byte[] inputBytes = new byte[]{'d', '0', '1', 'e'};

        // mocks
        final IConverter<IBEncodeElement> generalConverterMock = mock(IConverter.class);
        final IConverter<ByteString> byteStringConverter = mock(ByteStringConverter.class);
        final ByteString mockKey = mock(ByteString.class);
        final ByteString mockValue = mock(ByteString.class);
        when(mockKey.getElement()).thenReturn(new byte[]{1});
        when(mockValue.getElement()).thenReturn(new byte[]{1});
        when(byteStringConverter.convert(inputBytes, 1)).thenReturn(mockKey);
        when(generalConverterMock.convert(inputBytes, 2)).thenReturn(mockValue);

        // expected results

        // creating test instance
        final DictConverter testInstance = new DictConverter(generalConverterMock, byteStringConverter);

        // execution test
        final Dict dict = testInstance.convert(inputBytes);

        // result assert
        assertTrue(dict.keySet().contains(mockKey));
        assertEquals(dict.getValue(mockKey), mockValue);

        // mocks verify
        verify(byteStringConverter, times(1)).convert(inputBytes, 1);
        verifyNoMoreInteractions(byteStringConverter);
        verify(generalConverterMock, times(1)).convert(inputBytes, 2);
        verifyNoMoreInteractions(generalConverterMock);
        verify(mockKey, times(1)).getElement();
        verifyNoMoreInteractions(mockKey);
        verify(mockValue, times(1)).getElement();
        verifyNoMoreInteractions(mockValue);
    }

    @Test(dataProvider = "array_validation_data_provider")
    public void testValidate(final byte[] testArray, final int position, final boolean shouldThrowException) throws Exception {
        // input arguments

        // mocks

        // expected results

        // creating test instance

        // execution test
        try {
            DictConverter.validate(testArray, position);
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

        // execution test
        final OptionalInt actualResult = DictConverter.indexOfEndDictByte(testArray, position);

        // result assert
        assertEquals(actualResult, expectedResult);

        // mocks verify
    }

}