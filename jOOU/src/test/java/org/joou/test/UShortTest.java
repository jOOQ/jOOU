package org.joou.test;

import org.joou.UShort;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.joou.Unsigned.ushort;
import static org.junit.Assert.assertThat;

public class UShortTest {
    @Test
    public void testAddUShortValid() throws Exception {
        assertThat(ushort(1).add(ushort(2)), is(ushort(3)));
    }

    @Test(expected = NumberFormatException.class)
    public void testAddUShortInvalidCase1() {
        UShort.MAX.add(ushort(1));
    }

    @Test(expected = NumberFormatException.class)
    public void testAddUShortInvalidCase2() {
        UShort.MAX.add(ushort(UShort.MAX_VALUE));
    }

    @Test
    public void testAddIntValid() throws Exception {
        assertThat(ushort(1).add(ushort(2)), is(ushort(3)));
    }

    @Test(expected = NumberFormatException.class)
    public void testAddIntInvalidCase1() {
        UShort.MAX.add(1);
    }

    @Test(expected = NumberFormatException.class)
    public void testAddIntInvalidCase2() {
        UShort.MIN.add(-1);
    }

    @Test
    public void testSubtractUShortValid() throws Exception {
        assertThat(ushort(3).subtract(ushort(2)), is(ushort(1)));
    }

    @Test(expected = NumberFormatException.class)
    public void testSubtractUShortInvalid() {
        UShort.MIN.subtract(ushort(1));
    }

    @Test
    public void testSubtractIntValid() {
        assertThat(ushort(3).subtract(2), is(ushort(1)));
    }

    @Test(expected = NumberFormatException.class)
    public void testSubtractIntInvalidCase1() {
        UShort.MIN.subtract(1);
    }

    @Test(expected = NumberFormatException.class)
    public void testSubtractIntInvalidCase2() {
        UShort.MAX.subtract(-1);
    }
}
