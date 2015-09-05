package org.joou.test;

import org.joou.UShort;
import org.junit.Test;

import static junit.framework.Assert.fail;
import static org.joou.Unsigned.ushort;
import static org.junit.Assert.assertEquals;

public class UShortTest {

    @Test
    public void testAddUShortValid() throws Exception {
        assertEquals(ushort(3), ushort(1).add(ushort(2)));
    }

    @Test
    public void testAddUShortInvalid() throws Exception {
        try {
            UShort.MAX_USHORT.add(ushort(1));
            fail();
        } catch (NumberFormatException e) {}

        try {
            UShort.MAX_USHORT.add(ushort(UShort.MAX_VALUE));
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
            UShort.MAX_USHORT.add(1);
            fail();
        } catch (NumberFormatException e) {}

        try {
            UShort.MIN_USHORT.add(-1);
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
            UShort.MIN_USHORT.subtract(ushort(1));
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
            UShort.MIN_USHORT.subtract(1);
            fail();
        } catch (NumberFormatException e) {}

        try {
            UShort.MAX_USHORT.subtract(-1);
            fail();
        } catch (NumberFormatException e) {}
    }

}
