package com.bencode.serializator.referance;

import com.bencode.model.IBEncodeElement;
import junit.framework.TestCase;

public class ReferenceSerializerTest extends TestCase {

    public void testSerialize() throws Exception {
        // input arguments
        final TestClass inputForSerialization = new TestClass();

        // mocks

        // expected results

        // creating test instance
        final ReferenceSerializer testInstance = new ReferenceSerializer();

        // execution test
        final IBEncodeElement actualResult = testInstance.serialize(inputForSerialization);

        // result assert
        assertTrue(actualResult != null);

        // mocks verify
    }

    private static class TestClass {

        private short a = 2;

        private int test24b = 22;

    }

}