package com.bencode.common;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Objects;

import static org.testng.Assert.*;

public class TypeTest {

    @DataProvider(name = "types_data_provider")
    private static Object[][] typesDataProvider() {
        return new Object[][]{
                {int.class,                 Type.PRIMITIVE  },
                {Integer.class,             Type.PRIMITIVE  },
                {Object.class,              Type.REF        },
                {new int[0].getClass(),     Type.ARRAY      },
        } ;
    }

    @Test(dataProvider = "types_data_provider")
    public void testGetType(final Class type, final Type expectedType) throws Exception {
        // input arguments

        // mocks

        // expected results

        // creating test instance

        // execution test
        final Type actualResult = Type.getType(type);

        // result assert
        assertEquals(actualResult, expectedType);

        // mocks verify

    }

}