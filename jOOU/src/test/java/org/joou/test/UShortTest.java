package org.joou.test;

import static junit.framework.Assert.fail;
import static org.joou.Unsigned.ushort;
import static org.junit.Assert.assertEquals;

import org.joou.UShort;
import org.junit.Test;

public class UShortTest {

    @Test
    public void testAddUShortValid() throws Exception {
        assertEquals(ushort(3), ushort(1).add(ushort(2)));
    }

    @Test
    public void testAddUShortInvalid() throws Exception {
        try {
            UShort.MAX.add(ushort(1));
            fail();
        } catch (NumberFormatException e) {}

        try {
            UShort.MAX.add(ushort(UShort.MAX_VALUE));
            fail();
        } catch (NumberFormatException e) {}
    }

    @Test
    public void testAddIntValid() throws Exception {
        assertEquals(ushort(3), ushort(1).add(2));
    }

    @Test
    public void testAddIntInvalid() throws Exception {
        try {
            UShort.MAX.add(1);
            fail();
        } catch (NumberFormatException e) {}

        try {
            UShort.MIN.add(-1);
            fail();
        } catch (NumberFormatException e) {}
    }

    @Test
    public void testSubtractUShortValid() throws Exception {
        assertEquals(ushort(1), ushort(3).subtract(ushort(2)));
    }

    @Test
    public void testSubtractUShortInvalid() throws Exception {
        try {
            UShort.MIN.subtract(ushort(1));
            fail();
        } catch (NumberFormatException e) {}
    }

    @Test
    public void testSubtractIntValid() throws Exception {
        assertEquals(ushort(1), ushort(3).subtract(2));
    }

    @Test
    public void testSubtractIntInvalid() throws Exception {
        try {
            UShort.MIN.subtract(1);
            fail();
        } catch (NumberFormatException e) {}

        try {
            UShort.MAX.subtract(-1);
            fail();
        } catch (NumberFormatException e) {}
    }

}
