package com.bencode.serialization.deserializator.primitive;

import com.bencode.serialization.model.ByteString;
import com.bencode.serialization.serializator.primitive.IPrimitiveSerializator;
import org.testng.annotations.Test;

import java.nio.ByteBuffer;

import static org.testng.Assert.*;

public class TypeTest {

    @Test
    public void testByte() throws Exception {
        testSerAndDeser(IPrimitiveSerializator.Type.BYTE.getSerializator(),
                IPrimitiveDeserializator.Type.BYTE.getDeserializator(),
                (byte)':');
    }

    @Test
    public void testShort() throws Exception {
        testSerAndDeser(IPrimitiveSerializator.Type.SHORT.getSerializator(),
                IPrimitiveDeserializator.Type.SHORT.getDeserializator(),
                (short)12);
    }

    @Test
    public void testInt() throws Exception {
        testSerAndDeser(IPrimitiveSerializator.Type.INTEGER.getSerializator(),
                IPrimitiveDeserializator.Type.INTEGER.getDeserializator(),
                12999999);
    }

    @Test
    public void testChar() throws Exception {
        testSerAndDeser(IPrimitiveSerializator.Type.CHAR.getSerializator(),
                IPrimitiveDeserializator.Type.CHAR.getDeserializator(),
                'c');
    }

    @Test
    public void testLong() throws Exception {
        testSerAndDeser(IPrimitiveSerializator.Type.LONG.getSerializator(),
                IPrimitiveDeserializator.Type.LONG.getDeserializator(),
                9999999999999l);
    }

    @Test
    public void testFloat() throws Exception {
        testSerAndDeser(IPrimitiveSerializator.Type.FLOAT.getSerializator(),
                IPrimitiveDeserializator.Type.FLOAT.getDeserializator(),
                999.9999999999f);
    }

    @Test
    public void testDouble() throws Exception {
        testSerAndDeser(IPrimitiveSerializator.Type.DOUBLE.getSerializator(),
                IPrimitiveDeserializator.Type.DOUBLE.getDeserializator(),
                999.9999999999);
    }

    private<T> void testSerAndDeser(final IPrimitiveSerializator<T> primitiveSerializator, final IPrimitiveDeserializator<T> primitiveDeserializator, final T target) throws Exception {
        // input arguments

        // mocks

        // expected results
        final T expectedResult = target;

        // creating test instance

        // execution test
        final T actualResult = primitiveDeserializator.deserializator(primitiveSerializator.serialize(target));

        // result assert
        assertEquals(actualResult, expectedResult);

        // mocks verify
    }

}