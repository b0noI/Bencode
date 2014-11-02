package com.bencode.serialization.serializator;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;

public class SerializatorsTest {

    @DataProvider(name = "serializators_provider")
    // format: <test_instance(extends AbstractToByteStringSerializator), input value, expected result>
    private static Object[][] serializatorProvider() {
        return new Object[][]{
                {new ShortSerializator()  , (short)3000                     , new byte[]{11, -72}},
                {new ByteSerializator()   , (byte)'?'                       , new byte[]{'?'}},
                {new IntegerSerializator(), 300_000_000                     , new byte[]{17, -31, -93, 0}},
                {new LongSerializator()   , 300_000_000_000_000_000l        , new byte[]{4, 41, -48, 105, 24, -98, 0, 0}},
                {new CharSerializator()   , 'l'                             , new byte[]{0, 108}},
        } ;
    }

    @Test(dataProvider = "serializators_provider")
    public <T>void testSerializators(final AbstractToByteStringSerializator<T> testInstance, final T inputValue, final byte[] expectedResul) throws Exception {
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
