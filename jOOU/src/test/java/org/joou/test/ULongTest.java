package org.joou.test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static org.joou.Unsigned.ulong;

import org.joou.ULong;
import org.junit.Test;

public class ULongTest {
    @Test
    public void testAddULongValid() throws Exception {
        assertEquals(ulong(3), ulong(1).add(ulong(2)));
    }

    @Test
    public void testAddULongInvalid() throws Exception {
        try {
            ULong.MAX.add(ulong(1));
            fail();
        } catch (NumberFormatException e) {}

        try {
            ULong.MAX.add(ulong(ULong.MAX_VALUE));
            fail();
        } catch (NumberFormatException e) {}
    }

    @Test
    public void testAddIntValid() throws Exception {
        assertEquals(ulong(3), ulong(1).add(2));
    }

    @Test
    public void testAddIntInvalid() throws Exception {
        try {
            ULong.MAX.add(1);
            fail();
        } catch (NumberFormatException e) {}

        try {
            ULong.MIN.add(-1);
            fail();
        } catch (NumberFormatException e) {}
    }

    @Test
    public void testSubtractULongValid() throws Exception {
        assertEquals(ulong(1), ulong(3).subtract(ulong(2)));
    }

    @Test
    public void testSubtractULongInvalid() throws Exception {
        try {
            ULong.MIN.subtract(ulong(1));
            fail();
        } catch (NumberFormatException e) {}
    }

    @Test
    public void testSubtractIntValid() throws Exception {
        assertEquals(ulong(1), ulong(3).subtract(2));
    }

    @Test
    public void testSubtractIntInvalid() throws Exception {
        try {
            ULong.MIN.subtract(1);
            fail();
        } catch (NumberFormatException e) {}

        try {
            ULong.MIN.subtract(ULong.MAX);
            fail();
        } catch (NumberFormatException e) {}

        try {
            ULong.MAX.subtract(-1);
            fail();
        } catch (NumberFormatException e) {}
    }

}
