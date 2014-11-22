package com.bencode.deserializator.primitive;

import com.bencode.model.ByteString;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

public class ByteStringToBooleanDeserializerTest {

    @Test
    public void testDeserialize() throws Exception {
        // input arguments
        final ByteString inputByteString = ByteString.nullString();

        // mocks
        final ByteStringToPrimitiveDeserializer byteStringToPrimitiveDeserializer = mock(ByteStringToPrimitiveDeserializer.class);
        when(byteStringToPrimitiveDeserializer.deserialize(inputByteString)).thenReturn((byte)1);

        // expected results
        final boolean expectedResult = true;

        // creating test instance
        final ByteStringToBooleanDeserializer testInstance = new ByteStringToBooleanDeserializer(byteStringToPrimitiveDeserializer);

        // execution test
        final boolean actualResult = testInstance.deserialize(inputByteString);

        // result assert
        assertEquals(actualResult, expectedResult);

        // mocks verify
        verify(byteStringToPrimitiveDeserializer, times(1)).deserialize(inputByteString);
        verifyNoMoreInteractions(byteStringToPrimitiveDeserializer);
    }

}