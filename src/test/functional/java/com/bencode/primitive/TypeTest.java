package com.bencode.primitive;

import com.bencode.deserializator.primitive.IPrimitiveDeserializer;
import com.bencode.deserializator.referance.IDeserializer;
import com.bencode.serialization.converter.IConverter;
import com.bencode.serialization.model.Dict;
import com.bencode.serialization.model.IBEncodeElement;
import com.bencode.serialization.serializator.ISerializer;
import com.bencode.serialization.serializator.primitive.IPrimitiveSerializer;
import com.bencode.serialization.serializator.referance.ReferenceSerializer;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void testDesirializationOfObject() throws Exception {
        // input arguments
        final MyTestObject myClass = new MyTestObject();
        myClass.i = 12;
        myClass.y = 42;

        // mocks

        // expected results

        // creating test instance
        ISerializer serializator = new ReferenceSerializer();
        IConverter converter = IConverter.getConverter();

        // execution test
        byte[] bytes = serializator.serialize(myClass).getElement();
        IBEncodeElement deserStage1 = converter.convert(bytes, 0);
        MyTestObject afterSer = IDeserializer.deserialize((Dict) deserStage1);

        // result assert
        assertTrue(afterSer.i == 12);
        assertTrue(afterSer.y == 42);

        // mocks verify
    }

    @Test
    public void testDesirializationOfObjectWithReferance() throws Exception {
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
        ISerializer serializator = new ReferenceSerializer();
        IConverter converter = IConverter.getConverter();

        // execution test
        byte[] bytes = serializator.serialize(myClass).getElement();
        IBEncodeElement deserStage1 = converter.convert(bytes, 0);
        MyTestObjectWithRef afterSer = IDeserializer.deserialize((Dict) deserStage1);

        // result assert
        assertTrue(afterSer.i == 12);
        assertTrue(afterSer.y == 42);
        assertNotNull(afterSer.ref);
        assertTrue(afterSer.ref.i == 8);
        assertTrue(afterSer.ref.y == 10);

        // mocks verify
    }

    @Test
    public void testDesirializationOfObjectWithArray() throws Exception {
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
        ISerializer serializator = new ReferenceSerializer();
        IConverter converter = IConverter.getConverter();

        // execution test
        byte[] bytes = serializator.serialize(myClass).getElement();
        IBEncodeElement deserStage1 = converter.convert(bytes, 0);
        MyTestObjectWithRefArray afterSer = IDeserializer.deserialize((Dict) deserStage1);

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
    public void testDesirializationOfObjectWithPrimitiveArray() throws Exception {
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
        ISerializer serializator = new ReferenceSerializer();
        IConverter converter = IConverter.getConverter();

        // execution test
        byte[] bytes = serializator.serialize(myClass).getElement();
        IBEncodeElement deserStage1 = converter.convert(bytes, 0);
        MyTestObjectWithPrimitiveArray afterSer = IDeserializer.deserialize((Dict) deserStage1);

        // result assert
        assertTrue(afterSer.i == 12);
        assertTrue(afterSer.y == 42);
        assertNotNull(afterSer.ref);
        assertTrue(afterSer.ref[0] == 8);
        assertTrue(afterSer.ref[1] == 10);

        // mocks verify
    }

    @Test
    public void testDesirializationOfObjectWithPrimitiveBoxedArray() throws Exception {
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
        ISerializer serializator = new ReferenceSerializer();
        IConverter converter = IConverter.getConverter();

        // execution test
        final byte[] bytes = serializator.serialize(myClass).getElement();
        final IBEncodeElement deserStage1 = converter.convert(bytes, 0);
        MyTestObjectWithPrimitiveWithBoxedArray afterSer = IDeserializer.deserialize((Dict) deserStage1);

        // result assert
        assertTrue(afterSer.i == 12);
        assertTrue(afterSer.y == 42);
        assertNotNull(afterSer.ref);
        assertTrue(afterSer.ref[0] == 8);
        assertTrue(afterSer.ref[1] == 10);

        // mocks verify
    }

    @Test
    public void testDesirializationOfObjectWithBoxedPrimitives() throws Exception {
        // input arguments
        final MyTestObjectWithBoxedPrimitives myClass = new MyTestObjectWithBoxedPrimitives();
        myClass.i = 12;
        myClass.y = 42;

        // mocks

        // expected results

        // creating test instance
        ISerializer serializator = new ReferenceSerializer();
        IConverter converter = IConverter.getConverter();

        // execution test
        byte[] bytes = serializator.serialize(myClass).getElement();
        IBEncodeElement deserStage1 = converter.convert(bytes, 0);
        MyTestObjectWithBoxedPrimitives afterSer = IDeserializer.deserialize((Dict) deserStage1);

        // result assert
        assertTrue(afterSer.i == 12);
        assertTrue(afterSer.y == 42);

        // mocks verify
    }

    @Test
    public void testDesirializationOfObjectWith2DPrimitiveArrays() throws Exception {
        // input arguments
        final MyTestObjectWith2DPrimitiveArray myClass = new MyTestObjectWith2DPrimitiveArray();
        myClass.ref = new int[2][2];
        myClass.ref[0][0] = 0;
        myClass.ref[0][1] = 1;
        myClass.ref[1][0] = 2;
        myClass.ref[1][1] = 3;

        // mocks

        // expected results

        // creating test instance
        ISerializer serializator = new ReferenceSerializer();
        IConverter converter = IConverter.getConverter();

        // execution test
        byte[] bytes = serializator.serialize(myClass).getElement();
        IBEncodeElement deserStage1 = converter.convert(bytes, 0);
        MyTestObjectWith2DPrimitiveArray afterSer = IDeserializer.deserialize((Dict) deserStage1);

        // result assert
        assertTrue(afterSer.ref[0][0] == 0);
        assertTrue(afterSer.ref[0][1] == 1);
        assertTrue(afterSer.ref[1][0] == 2);
        assertTrue(afterSer.ref[1][1] == 3);

        // mocks verify
    }

    @Test
    public void testDesirializationOfObjectWith2DPrimitiveBoxedArrays() throws Exception {
        // input arguments
        final MyTestObjectWith2DPrimitiveBoxedArray myClass = new MyTestObjectWith2DPrimitiveBoxedArray();
        myClass.ref = new Integer[2][2];
        myClass.ref[0][0] = 0;
        myClass.ref[0][1] = 1;
        myClass.ref[1][0] = 2;
        myClass.ref[1][1] = 3;

        // mocks

        // expected results

        // creating test instance
        ISerializer serializator = new ReferenceSerializer();
        IConverter converter = IConverter.getConverter();

        // execution test
        byte[] bytes = serializator.serialize(myClass).getElement();
        IBEncodeElement deserStage1 = converter.convert(bytes, 0);
        MyTestObjectWith2DPrimitiveBoxedArray afterSer = IDeserializer.deserialize((Dict) deserStage1);

        // result assert
        assertTrue(afterSer.ref[0][0] == 0);
        assertTrue(afterSer.ref[0][1] == 1);
        assertTrue(afterSer.ref[1][0] == 2);
        assertTrue(afterSer.ref[1][1] == 3);

        // mocks verify
    }

    @Test
    public void testDesirializationOfObjectWithList() throws Exception {
        // input arguments
        final MyTestObjectWithList myClass = new MyTestObjectWithList();
        myClass.list = new ArrayList<>();
        MyTestObject testObject = new MyTestObject();
        testObject.i = 42;
        testObject.y = 56;
        myClass.list.add(testObject);
        myClass.list.add(testObject);
        MyTestObject testObject2 = new MyTestObject();
        testObject.i = 422;
        testObject.y = 566;
        myClass.list.add(testObject2);

        // mocks

        // expected results

        // creating test instance
        ISerializer serializator = new ReferenceSerializer();
        IConverter converter = IConverter.getConverter();

        // execution test
        byte[] bytes = serializator.serialize(myClass).getElement();
        IBEncodeElement deserStage1 = converter.convert(bytes, 0);
        MyTestObjectWithList afterSer = IDeserializer.deserialize((Dict) deserStage1);

        // result assert
        assertNotNull(afterSer);

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

    public static class MyTestObjectWith2DPrimitiveArray {
        public int[][] ref;
    }

    public static class MyTestObjectWith2DPrimitiveBoxedArray {
        public Integer[][] ref;
    }

    public static class MyTestObjectWithPrimitiveWithBoxedArray {
        public int i;
        public int y;
        public Integer[] ref;
    }

    public static class MyTestObjectWithBoxedPrimitives {
        public Integer i;
        public Integer y;
    }

    public static class MyTestObjectWithList {
        public List<MyTestObject> list;
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