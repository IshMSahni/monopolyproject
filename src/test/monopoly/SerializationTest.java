package test.monopoly;

import monopoly.Serialization;
import org.junit.Assert;
import org.junit.Test;

import java.io.Serializable;

public class SerializationTest {

    static class TestObject implements Serializable {
        public int i;
        public String s;
        public TestObject(int i, String s) {
            this.i = i;
            this.s = s;
        }

        @Override
        public boolean equals(Object o) {
            return s.equals(((TestObject) o).s) && i == ((TestObject) o).i;
        }

    }

    @Test
    public void write_base64() {
        TestObject testObject = new TestObject(1, "test");
        String serialized = Serialization.write_base64(testObject);
        Assert.assertEquals("rO0ABXNyACp0ZXN0Lm1vbm9wb2x5LlNlcmlhbGl6YXRpb25UZXN0JFRlc3RPYmplY3QSnZDMzQwS7wIAAkkAAWlMAAFzdAASTGphdmEvbGFuZy9TdHJpbmc7eHAAAAABdAAEdGVzdA==", serialized);
    }

    @Test
    public void read_base64() {
        TestObject testObject = new TestObject(1, "test");
        TestObject from_base64 = (TestObject) Serialization.read_base64("rO0ABXNyACp0ZXN0Lm1vbm9wb2x5LlNlcmlhbGl6YXRpb25UZXN0JFRlc3RPYmplY3QSnZDMzQwS7wIAAkkAAWlMAAFzdAASTGphdmEvbGFuZy9TdHJpbmc7eHAAAAABdAAEdGVzdA==");
        Assert.assertTrue(testObject.equals(from_base64));
    }



}
