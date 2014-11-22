package com.bencode.deserializator.primitive;

import com.bencode.model.ByteString;
import org.testng.annotations.Test;

import java.nio.ByteBuffer;
import java.util.function.Function;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class ByteStringToPrimitiveDeserializerTest {

    @Test
    public void testDeserialize() throws Exception {
        // input arguments
        final ByteString inputByteString = ByteString.nullString();

        // mocks
        final Function<ByteString, ?> converterMock = mock(Function.class);
        when(converterMock.apply(any())).thenReturn(1);

        // expected results
        final Integer expectedResult = 1;

        // creating test instance
        final ByteStringToPrimitiveDeserializer<Integer> testInstance = new ByteStringToPrimitiveDeserializer(converterMock);

        // execution test
        final Integer actualResult = testInstance.deserialize(inputByteString);

        // result assert
        assertEquals(actualResult, expectedResult);

        // mocks verify
    }

}