package com.bencode.serialization.serializator.referance;

import com.bencode.serialization.model.IBEncodeElement;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class RecursiveObjectSerializatorTest {

    @Test
    public void testSerialize() throws Exception {
        // input arguments
        final String inputForSerialization = "test123";

        // mocks

        // expected results

        // creating test instance

        // execution test
        final IBEncodeElement actualResult = RecursiveObjectSerializator.serialize(inputForSerialization);

        // result assert
        assertTrue(actualResult != null);

        // mocks verify

    }

}