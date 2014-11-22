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



}