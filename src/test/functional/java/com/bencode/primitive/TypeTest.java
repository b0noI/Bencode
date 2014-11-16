package com.bencode.primitive;

import com.bencode.deserializator.primitive.IPrimitiveDeserializer;
import com.bencode.model.Dict;
import com.bencode.model.IBEncodeElement;
import com.bencode.serializator.primitive.IPrimitiveSerializer;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TypeTest {

    @Test
    public void testByte() throws Exception {
        testSerAndDeser(IPrimitiveSerializer.Type.BYTE.getSerializer(),
                IPrimitiveDeserializer.Type.BYTE.getDeserializer(),
                (byte)':');
    }

    @Test
    public void testShort() throws Exception {
        testSerAndDeser(IPrimitiveSerializer.Type.SHORT.getSerializer(),
                IPrimitiveDeserializer.Type.SHORT.getDeserializer(),
                (short)12);
    }

    @Test
    public void testInt() throws Exception {
        testSerAndDeser(IPrimitiveSerializer.Type.INTEGER.getSerializer(),
                IPrimitiveDeserializer.Type.INTEGER.getDeserializer(),
                12999999);
    }

    @Test
    public void testChar() throws Exception {
        testSerAndDeser(IPrimitiveSerializer.Type.CHAR.getSerializer(),
                IPrimitiveDeserializer.Type.CHAR.getDeserializer(),
                'c');
    }

    @Test
    public void testLong() throws Exception {
        testSerAndDeser(IPrimitiveSerializer.Type.LONG.getSerializer(),
                IPrimitiveDeserializer.Type.LONG.getDeserializer(),
                9999999999999l);
    }

    @Test
    public void testFloat() throws Exception {
        testSerAndDeser(IPrimitiveSerializer.Type.FLOAT.getSerializer(),
                IPrimitiveDeserializer.Type.FLOAT.getDeserializer(),
                999.9999999999f);
    }

    @Test
    public void testDouble() throws Exception {
        testSerAndDeser(IPrimitiveSerializer.Type.DOUBLE.getSerializer(),
                IPrimitiveDeserializer.Type.DOUBLE.getDeserializer(),
                999.9999999999);
    }

    private<T> void testSerAndDeser(final IPrimitiveSerializer<T> primitiveSerializator, final IPrimitiveDeserializer<T> primitiveDeserializator, final T target) throws Exception {
        // input arguments

        // mocks

        // expected results
        final T expectedResult = target;

        // creating test instance

        // execution test
        final T actualResult = primitiveDeserializator.deserialize(primitiveSerializator.serialize(target));

        // result assert
        assertEquals(actualResult, expectedResult);

        // mocks verify
    }

}