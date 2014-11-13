package com.bencode.serialization.serializator.primitive;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;

public class SerializersTest {

    @DataProvider(name = "serializators_provider")
    // format: <test_instance(extends AbstractToByteStringSerializer), input value, expected result>
    private static Object[][] serializatorProvider() {
        return new Object[][]{
                {new ShortSerializer()  , (short)3000                     , new byte[]{11, -72}},
                {new ByteSerializer()   , (byte)'?'                       , new byte[]{'?'}},
                {new IntegerSerializer(), 300_000_000                     , new byte[]{17, -31, -93, 0}},
                {new LongSerializer()   , 300_000_000_000_000_000l        , new byte[]{4, 41, -48, 105, 24, -98, 0, 0}},
                {new CharSerializer()   , 'l'                             , new byte[]{0, 108}},
                {new FloatSerializer()  , .99f                            , new byte[]{63, 125, 112, -92}},
                {new DoubleSerializer() , .99                             , new byte[]{63, -17, -82, 20, 122, -31, 71, -82}},
                {new BooleanSerializer(), true                            , new byte[]{1}},
        } ;
    }

    @Test(dataProvider = "serializators_provider")
    public <T>void testSerializators(final AbstractToByteStringSerializer<T> testInstance, final T inputValue, final byte[] expectedResul) throws Exception {
        // input arguments

        // mocks

        // expected results

        // creating test instance

        // execution test
        final byte[] actualResult = testInstance.convertToBytes(inputValue);

        // result assert
        assertArrayEquals(expectedResul, actualResult);

        // mocks verify
    }

}
