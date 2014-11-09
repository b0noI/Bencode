package com.bencode.deserializator.primitive;

import com.bencode.serialization.converter.IConverter;
import com.bencode.serialization.converter.RecursiveConverter;
import com.bencode.serialization.model.Dict;
import com.bencode.serialization.model.IBEncodeElement;
import com.bencode.serialization.serializator.ISerializator;
import com.bencode.serialization.serializator.primitive.IPrimitiveSerializator;
import com.bencode.serialization.serializator.referance.ReferanceSerializer;
import org.testng.annotations.Test;

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

    @Test
    public void testDesirializationOfObject() {
        // input arguments
        final MyTestObject myClass = new MyTestObject();
        myClass.i = 12;
        myClass.y = 42;

        // mocks

        // expected results

        // creating test instance
        ISerializator serializator = new ReferanceSerializer();
        IReferanceDeserializator referanceDeserializator = new ReferanceDeserializator();
        IConverter converter = new RecursiveConverter();

        // execution test
        byte[] bytes = serializator.serialize(myClass).getElement();
        IBEncodeElement deserStage1 = converter.convert(bytes, 0);
        MyTestObject afterSer = referanceDeserializator.deserialize((Dict)deserStage1);

        // result assert
        assertTrue(afterSer.i == 12);
        assertTrue(afterSer.y == 42);

        // mocks verify
    }

    @Test
    public void testDesirializationOfObjectWithReferance() {
        // input arguments
        final MyTestObjectWithRef myClass = new MyTestObjectWithRef();
        myClass.i = 12;
        myClass.y = 42;
        myClass.ref = new MyTestObject();
        myClass.ref.i = 8;
        myClass.ref.y = 10;

        // mocks

        // expected results

        // creating test instance
        ISerializator serializator = new ReferanceSerializer();
        IReferanceDeserializator referanceDeserializator = new ReferanceDeserializator();
        IConverter converter = new RecursiveConverter();

        // execution test
        byte[] bytes = serializator.serialize(myClass).getElement();
        IBEncodeElement deserStage1 = converter.convert(bytes, 0);
        MyTestObjectWithRef afterSer = referanceDeserializator.deserialize((Dict)deserStage1);

        // result assert
        assertTrue(afterSer.i == 12);
        assertTrue(afterSer.y == 42);
        assertNotNull(afterSer.ref);
        assertTrue(afterSer.ref.i == 8);
        assertTrue(afterSer.ref.y == 10);

        // mocks verify
    }

    @Test
    public void testDesirializationOfObjectWithArray() {
        // input arguments
        final MyTestObjectWithRefArray myClass = new MyTestObjectWithRefArray();
        myClass.i = 12;
        myClass.y = 42;
        myClass.ref = new MyTestObject[2];
        myClass.ref[0] = new MyTestObject();
        myClass.ref[0].i = 8;
        myClass.ref[0].y = 10;
        myClass.ref[1] = new MyTestObject();
        myClass.ref[1].i = 80;
        myClass.ref[1].y = 100;

        // mocks

        // expected results

        // creating test instance
        ISerializator serializator = new ReferanceSerializer();
        IReferanceDeserializator referanceDeserializator = new ReferanceDeserializator();
        IConverter converter = new RecursiveConverter();

        // execution test
        byte[] bytes = serializator.serialize(myClass).getElement();
        IBEncodeElement deserStage1 = converter.convert(bytes, 0);
        MyTestObjectWithRefArray afterSer = referanceDeserializator.deserialize((Dict)deserStage1);

        // result assert
        assertTrue(afterSer.i == 12);
        assertTrue(afterSer.y == 42);
        assertNotNull(afterSer.ref);
        assertTrue(afterSer.ref[0].i == 8);
        assertTrue(afterSer.ref[0].y == 10);
        assertTrue(afterSer.ref[1].i == 80);
        assertTrue(afterSer.ref[1].y == 100);

        // mocks verify
    }

    @Test
    public void testDesirializationOfObjectWithPrimitiveArray() {
        // input arguments
        final MyTestObjectWithPrimitiveArray myClass = new MyTestObjectWithPrimitiveArray();
        myClass.i = 12;
        myClass.y = 42;
        myClass.ref = new int[2];
        myClass.ref[0] = 8;
        myClass.ref[1] = 10;

        // mocks

        // expected results

        // creating test instance
        ISerializator serializator = new ReferanceSerializer();
        IReferanceDeserializator referanceDeserializator = new ReferanceDeserializator();
        IConverter converter = new RecursiveConverter();

        // execution test
        byte[] bytes = serializator.serialize(myClass).getElement();
        IBEncodeElement deserStage1 = converter.convert(bytes, 0);
        MyTestObjectWithPrimitiveArray afterSer = referanceDeserializator.deserialize((Dict)deserStage1);

        // result assert
        assertTrue(afterSer.i == 12);
        assertTrue(afterSer.y == 42);
        assertNotNull(afterSer.ref);
        assertTrue(afterSer.ref[0] == 8);
        assertTrue(afterSer.ref[1] == 10);

        // mocks verify
    }

    @Test
    public void testDesirializationOfObjectWithPrimitiveBoxedArray() {
        // input arguments
        final MyTestObjectWithPrimitiveWithBoxedArray myClass = new MyTestObjectWithPrimitiveWithBoxedArray();
        myClass.i = 12;
        myClass.y = 42;
        myClass.ref = new Integer[2];
        myClass.ref[0] = 8;
        myClass.ref[1] = 10;

        // mocks

        // expected results

        // creating test instance
        ISerializator serializator = new ReferanceSerializer();
        IReferanceDeserializator referanceDeserializator = new ReferanceDeserializator();
        IConverter converter = new RecursiveConverter();

        // execution test
        byte[] bytes = serializator.serialize(myClass).getElement();
        IBEncodeElement deserStage1 = converter.convert(bytes, 0);
        MyTestObjectWithPrimitiveWithBoxedArray afterSer = referanceDeserializator.deserialize((Dict)deserStage1);

        // result assert
        assertTrue(afterSer.i == 12);
        assertTrue(afterSer.y == 42);
        assertNotNull(afterSer.ref);
        assertTrue(afterSer.ref[0] == 8);
        assertTrue(afterSer.ref[1] == 10);

        // mocks verify
    }

    public static class MyTestObject {
        public int i;
        public int y;
    }

    public static class MyTestObjectWithRef {
        public int i;
        public int y;
        public MyTestObject ref;
    }

    public static class MyTestObjectWithRefArray {
        public int i;
        public int y;
        public MyTestObject[] ref;
    }

    public static class MyTestObjectWithPrimitiveArray {
        public int i;
        public int y;
        public int[] ref;
    }

    public static class MyTestObjectWithPrimitiveWithBoxedArray {
        public int i;
        public int y;
        public Integer[] ref;
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