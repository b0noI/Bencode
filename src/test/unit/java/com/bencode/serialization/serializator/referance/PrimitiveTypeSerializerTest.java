package com.bencode.serialization.serializator.referance;

import com.bencode.serialization.model.IBEncodeElement;
import com.bencode.serialization.serializator.ISerializer;
import com.bencode.serialization.serializator.primitive.IPrimitiveSerializer;
import org.apache.commons.lang3.SerializationException;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class PrimitiveTypeSerializerTest {

    @Test
    public void testSerialize() throws Exception {
        // input arguments
        final Integer inputValue = 12;

        // mocks
        final IBEncodeElement mockResult = mock(IBEncodeElement.class);
        final ISerializer mockSerializer = mock(ISerializer.class);
        when(mockSerializer.serialize(inputValue)).thenReturn(mockResult);

        // expected results
        final IBEncodeElement expectedResult = mockResult;

        // creating test instance
        final PrimitiveTypeSerializer testInstance = new PrimitiveTypeSerializer(){
            @Override
            void validateClass(Class<?> type) {
                assertEquals(type, Integer.class);
            }

            @Override
            <T> ISerializer<T> getSerializer(Class<?> type) {
                assertEquals(type, Integer.class);
                return mockSerializer;
            }
        };

        // execution test
        final IBEncodeElement actualResult = testInstance.serialize(inputValue);

        // result assert
        assertEquals(actualResult, expectedResult);

        // mocks verify
    }

    @Test
    public void testValidateClassWhenInputValid() throws Exception {
        // input arguments
        final Class inputClass = Integer.class;

        // mocks

        // expected results

        // creating test instance
        final PrimitiveTypeSerializer testInstance = new PrimitiveTypeSerializer();

        // execution test
        testInstance.validateClass(inputClass);

        // result assert

        // mocks verify
    }

    @Test
    public void testValidateClassWhenInputInvalid() throws Exception {
        // input arguments
        final Class inputClass = PrimitiveTypeSerializerTest.class;

        // mocks

        // expected results

        // creating test instance
        final PrimitiveTypeSerializer testInstance = new PrimitiveTypeSerializer();

        // execution test
        try {
            testInstance.validateClass(inputClass);
            fail();
        } catch (SerializationException e) {}

        // result assert

        // mocks verify
    }

    @Test
    public void testGetSerializer() throws Exception {
        // input arguments
        final Class inputClass = Integer.class;

        // mocks

        // expected results
        final ISerializer expectedSerializer = IPrimitiveSerializer.Type.INTEGER.getSerializer();

        // creating test instance
        final PrimitiveTypeSerializer testInstance = new PrimitiveTypeSerializer();

        // execution test
        final ISerializer serializer = testInstance.getSerializer(inputClass);

        // result assert
        assertEquals(serializer, expectedSerializer);

        // mocks verify
    }

    @Test
    public void testGetSerializerWhenTypeNotSupported() throws Exception {
        // input arguments
        final Class inputClass = PrimitiveTypeSerializerTest.class;

        // mocks

        // expected results

        // creating test instance
        final PrimitiveTypeSerializer testInstance = new PrimitiveTypeSerializer();

        // execution test
        try {
            testInstance.getSerializer(inputClass);
            fail();
        } catch (SerializationException e) {

        }

        // result assert

        // mocks verify
    }

}