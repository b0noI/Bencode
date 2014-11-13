package com.bencode.deserializator.primitive;

import com.bencode.deserializator.referance.RecursiveDeserializator;
import com.bencode.serialization.converter.IConverter;
import com.bencode.serialization.converter.RecursiveConverter;
import com.bencode.serialization.model.Dict;
import com.bencode.serialization.model.IBEncodeElement;
import com.bencode.serialization.serializator.ISerializator;
import com.bencode.serialization.serializator.primitive.IPrimitiveSerializator;
import com.bencode.serialization.serializator.referance.ReferanceSerializer;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

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
    public void testDesirializationOfObject() throws Exception {
        // input arguments
        final MyTestObject myClass = new MyTestObject();
        myClass.i = 12;
        myClass.y = 42;

        // mocks

        // expected results

        // creating test instance
        ISerializator serializator = new ReferanceSerializer();
        IConverter converter = new RecursiveConverter();

        // execution test
        byte[] bytes = serializator.serialize(myClass).getElement();
        IBEncodeElement deserStage1 = converter.convert(bytes, 0);
        MyTestObject afterSer = RecursiveDeserializator.deserialize((Dict) deserStage1);

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
        ISerializator serializator = new ReferanceSerializer();
        IConverter converter = new RecursiveConverter();

        // execution test
        byte[] bytes = serializator.serialize(myClass).getElement();
        IBEncodeElement deserStage1 = converter.convert(bytes, 0);
        MyTestObjectWithRef afterSer = RecursiveDeserializator.deserialize((Dict)deserStage1);

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
        ISerializator serializator = new ReferanceSerializer();
        IConverter converter = new RecursiveConverter();

        // execution test
        byte[] bytes = serializator.serialize(myClass).getElement();
        IBEncodeElement deserStage1 = converter.convert(bytes, 0);
        MyTestObjectWithRefArray afterSer = RecursiveDeserializator.deserialize((Dict)deserStage1);

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
        ISerializator serializator = new ReferanceSerializer();
        IConverter converter = new RecursiveConverter();

        // execution test
        byte[] bytes = serializator.serialize(myClass).getElement();
        IBEncodeElement deserStage1 = converter.convert(bytes, 0);
        MyTestObjectWithPrimitiveArray afterSer = RecursiveDeserializator.deserialize((Dict)deserStage1);

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
        ISerializator serializator = new ReferanceSerializer();
        IConverter converter = new RecursiveConverter();

        // execution test
        final byte[] bytes = serializator.serialize(myClass).getElement();
        final IBEncodeElement deserStage1 = converter.convert(bytes, 0);
        MyTestObjectWithPrimitiveWithBoxedArray afterSer = RecursiveDeserializator.deserialize((Dict)deserStage1);

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
        ISerializator serializator = new ReferanceSerializer();
        IConverter converter = new RecursiveConverter();

        // execution test
        byte[] bytes = serializator.serialize(myClass).getElement();
        IBEncodeElement deserStage1 = converter.convert(bytes, 0);
        MyTestObjectWithBoxedPrimitives afterSer = RecursiveDeserializator.deserialize((Dict)deserStage1);

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
        ISerializator serializator = new ReferanceSerializer();
        IConverter converter = new RecursiveConverter();

        // execution test
        byte[] bytes = serializator.serialize(myClass).getElement();
        IBEncodeElement deserStage1 = converter.convert(bytes, 0);
        MyTestObjectWith2DPrimitiveArray afterSer = RecursiveDeserializator.deserialize((Dict)deserStage1);

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
        ISerializator serializator = new ReferanceSerializer();
        IConverter converter = new RecursiveConverter();

        // execution test
        byte[] bytes = serializator.serialize(myClass).getElement();
        IBEncodeElement deserStage1 = converter.convert(bytes, 0);
        MyTestObjectWith2DPrimitiveBoxedArray afterSer = RecursiveDeserializator.deserialize((Dict)deserStage1);

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
        ISerializator serializator = new ReferanceSerializer();
        IConverter converter = new RecursiveConverter();

        // execution test
        byte[] bytes = serializator.serialize(myClass).getElement();
        IBEncodeElement deserStage1 = converter.convert(bytes, 0);
        MyTestObjectWithList afterSer = RecursiveDeserializator.deserialize((Dict)deserStage1);

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