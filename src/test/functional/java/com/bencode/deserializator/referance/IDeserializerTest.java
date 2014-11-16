package com.bencode.deserializator.referance;

import com.bencode.deserializator.converter.IConverter;
import com.bencode.model.Dict;
import com.bencode.model.IBEncodeElement;
import com.bencode.serialization.serializator.ISerializer;
import com.bencode.serialization.serializator.referance.ReferenceSerializer;
import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.List;

import static org.testng.Assert.*;

public class IDeserializerTest {

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
        myClass.list = new LinkedList<>();
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
        ISerializer serializer = new ReferenceSerializer();
        IConverter converter = IConverter.getConverter();

        // execution test
        byte[] bytes = serializer.serialize(myClass).getElement();
        MyTestObjectWithList afterSer = IDeserializer.deserialize(bytes);

        // result assert
        assertNotNull(afterSer);

        // mocks verify
    }

    @Test
    public void testAllTypesClass() throws Exception {
        // input arguments
        final AllTypesTest testInstance = new AllTypesTest();
        testInstance.bNP = true;
        testInstance.bP = true;
        testInstance.byteNP = 12;
        testInstance.byteP = 13;
        testInstance.doubleNP = .2;
        testInstance.doubleP = .3;
        testInstance.floatNP = .4f;
        testInstance.floatP = .5f;
        testInstance.intNP = 14;
        testInstance.intP = 15;
        testInstance.longNP = 16l;
        testInstance.longP = 17l;
        testInstance.shortNP = 18;
        testInstance.shortP = 19;
        testInstance.ref = new MyTestObjectWithPrimitiveWithBoxedArray[2];
        testInstance.ref[0] = new MyTestObjectWithPrimitiveWithBoxedArray();
        testInstance.ref[1] = new MyTestObjectWithPrimitiveWithBoxedArray();
        testInstance.dd = new MyTestObjectWithPrimitiveWithBoxedArray[1][2][];
        testInstance.dd[0][0] = testInstance.ref;

        // mocks

        // expected results

        // creating test instance
        ISerializer serializer = new ReferenceSerializer();

        // execution test
        byte[] bytes = serializer.serialize(testInstance).getElement();
        AllTypesTest afterSer = IDeserializer.deserialize(bytes);

        // result assert
        assertTrue(afterSer.bNP);
        assertTrue(afterSer.bP);
        assertEquals(testInstance.byteNP, (Byte) (byte) 12);
        assertEquals(testInstance.byteP, (byte) 13);
        assertEquals(testInstance.doubleNP, .2);
        assertEquals(testInstance.doubleP, .3);
        assertEquals(testInstance.floatNP, .4f);
        assertEquals(testInstance.floatP, .5f);
        assertEquals(testInstance.intNP, (Integer)14);
        assertEquals(testInstance.intP, 15);
        assertEquals(testInstance.longNP, (Long)16l);
        assertEquals(testInstance.longP, 17l);
        assertEquals(testInstance.shortNP, (Short)(short)18);
        assertEquals(testInstance.shortP, 19);
        assertNull(testInstance.dd[0][1]);
        assertEquals(testInstance.dd[0][0][0], testInstance.ref[0]);
        assertEquals(testInstance.dd[0][0][1], testInstance.ref[1]);

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

    public static class AllTypesTest {

        public Boolean bNP;

        public boolean bP;

        public Byte byteNP;

        public byte byteP;

        public Short shortNP;

        public short shortP;

        public Integer intNP;

        public int intP;

        public Long longNP;

        public long longP;

        public Float floatNP;

        public float floatP;

        public Double doubleNP;

        public double doubleP;

        public MyTestObjectWithPrimitiveWithBoxedArray[] ref;

        public MyTestObjectWithPrimitiveWithBoxedArray[][][] dd;

    }

}