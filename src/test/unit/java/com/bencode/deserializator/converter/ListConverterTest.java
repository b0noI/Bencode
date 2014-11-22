package com.bencode.deserializator.converter;

import com.bencode.model.BencodeList;
import com.bencode.model.IBEncodeElement;
import org.testng.annotations.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

public class ListConverterTest {

    @Test
    public void testConvert() throws Exception {
        // input arguments
        final byte[] inputBytes = new byte[]{'l', 0, 1, 'e'};

        // mocks
        final IConverter<IBEncodeElement> generalConverterMock = mock(IConverter.class);
        final TwoCharactersBencodeElementInputValidator validatorMock = mock(TwoCharactersBencodeElementInputValidator.class);
        final IBEncodeElement mockListElement = mock(IBEncodeElement.class);
        when(mockListElement.getElement()).thenReturn(new byte[]{1});
        when(generalConverterMock.convert(inputBytes, 1)).thenReturn(mockListElement);
        when(generalConverterMock.convert(inputBytes, 2)).thenReturn(mockListElement);

        // expected results
        final BencodeList expectedList = new BencodeList();
        expectedList.add(mockListElement);
        expectedList.add(mockListElement);

        // creating test instance
        final ListConverter testInstance = new ListConverter(generalConverterMock, validatorMock);

        // execution test
        final BencodeList actualResult =  testInstance.convert(inputBytes);

        // result assert
        assertEquals(actualResult, expectedList);

        // mocks verify
        verify(generalConverterMock, times(1)).convert(inputBytes, 1);
        verify(generalConverterMock, times(1)).convert(inputBytes, 2);
        verifyNoMoreInteractions(generalConverterMock);
        verify(mockListElement, times(2)).getElement();
        verifyNoMoreInteractions(mockListElement);
        verify(validatorMock, times(1)).validate(inputBytes, 0);
        verifyNoMoreInteractions(validatorMock);
    }

}