package com.bencode;

import org.testng.annotations.Test;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;


public class BencodeSerializableTest {

    @Test
    public void testProcess() throws Exception {
        // input arguments
        final TestSerializationProcess testSerializationProcess = new TestSerializationProcess();
        testSerializationProcess.a = 5;
        testSerializationProcess.b = 80;

        // mocks

        // expected results

        // creating test instance

        // execution test
        FileOutputStream fileOutputStream = new FileOutputStream("test.ben");
        ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
        out.writeObject(testSerializationProcess);

        // result assert

        // mocks verify
    }

    public static class TestSerializationProcess extends BencodeSerializable {

        public int a;

        public int b;

    }

}