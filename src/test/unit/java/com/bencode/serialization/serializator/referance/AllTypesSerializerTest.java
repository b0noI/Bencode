package com.bencode.serialization.serializator.referance;

import com.bencode.deserializator.referance.IDeserializer;
import com.bencode.deserializator.converter.IConverter;
import com.bencode.model.Dict;
import com.bencode.model.IBEncodeElement;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AllTypesSerializerTest {

    @Test
    public void testSerialize() throws Exception {
        // input arguments
        final String inputForSerialization = "test123";

        // mocks

        // expected results

        // creating test instance
        final ReferenceSerializer referenceSerializer = new ReferenceSerializer();
        IConverter converter = IConverter.getConverter();

        // execution test

        final IBEncodeElement actualResult = referenceSerializer.serialize(inputForSerialization);
        IBEncodeElement deserStage1 = converter.convert(actualResult.getElement(), 0);
        final String afterSer = IDeserializer.deserialize((Dict) deserStage1);

        // result assert
        assertEquals(inputForSerialization, afterSer);

        // mocks verify

    }

}