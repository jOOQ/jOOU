package org.joou.test;

import org.joou.ULong;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.joou.Unsigned.ulong;
import static org.junit.Assert.assertThat;

public class ULongTest {
    @Test
    public void testAddULongValid() throws Exception {
        assertThat(ulong(1).add(ulong(2)), equalTo(ulong(3)));
    }

    @Test(expected = NumberFormatException.class)
    public void testAddULongInvalidCase1() {
        ULong.MAX.add(ulong(1));
    }

    @Test(expected = NumberFormatException.class)
    public void testAddULongInvalidCase2() {
        ULong.MIN.add(ulong(ULong.MAX_VALUE));
    }

    @Test
    public void testAddIntValid() throws Exception {
        assertThat(ulong(1).add(2), is(ulong(3)));
    }

    @Test(expected = NumberFormatException.class)
    public void testAddIntInvalidCase1() {
        ULong.MAX.add(1);
    }

    @Test(expected = NumberFormatException.class)
    public void testAddIntInvalidCase2() {
        ULong.MIN.add(-1);
    }

    @Test
    public void testSubtractULongValid() throws Exception {
        assertThat(ulong(3).subtract(ulong(2)), is(ulong(1)));
    }

    @Test(expected = NumberFormatException.class)
    public void testSubtractULongInvalid() throws Exception {
        ULong.MIN.subtract(ulong(1));
    }

    @Test
    public void testSubtractIntValid() throws Exception {
        assertThat(ulong(3).subtract(2), is(ulong(1)));
    }

    @Test(expected = NumberFormatException.class)
    public void testSubtractIntInvalidCase1() throws Exception {
        ULong.MIN.subtract(1);
    }

    @Test(expected = NumberFormatException.class)
    public void testSubtractIntInvalidCase2() throws Exception {
        ULong.MIN.subtract(ULong.MAX);
    }

    @Test(expected = NumberFormatException.class)
    public void testSubtractIntInvalidCase3() throws Exception {
        ULong.MAX.subtract(-1);
    }
}
