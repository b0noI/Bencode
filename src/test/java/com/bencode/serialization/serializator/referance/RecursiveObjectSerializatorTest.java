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
        final byte[] expectedResult = new byte[]{100,4,58,0,0,0,0,100,22,58,67,65,83,69,95,73,78,83,69,78,83,73,84,73,86,69,95,79,82,68,69,82,4,58,0,0,0,3,4,58,104,97,115,104,4,58,0,0,0,0,22,58,115,101,114,105,97,108,80,101,114,115,105,115,116,101,110,116,70,105,101,108,100,115,4,58,0,0,0,2,16,58,115,101,114,105,97,108,86,101,114,115,105,111,110,85,73,68,8,58,-96,-16,-92,56,122,59,-77,66,5,58,118,97,108,117,101,4,58,0,0,0,1,101,4,58,0,0,0,1,100,101,4,58,0,0,0,2,100,101,4,58,0,0,0,3,100,16,58,115,101,114,105,97,108,86,101,114,115,105,111,110,85,73,68,8,58,119,3,92,125,92,80,-27,-50,101,101};

        // creating test instance

        // execution test
        final IBEncodeElement actualResult = RecursiveObjectSerializator.serialize(inputForSerialization);

        // result assert
        assertEquals(actualResult.getElement(), expectedResult);

        // mocks verify

    }

}