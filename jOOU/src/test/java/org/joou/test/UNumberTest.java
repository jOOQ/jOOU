/**
 * Copyright (c) 2011-2013, Lukas Eder, lukas.eder@gmail.com
 * All rights reserved.
 *
 * This software is licensed to you under the Apache License, Version 2.0
 * (the "License"); You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * . Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * . Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * . Neither the name "jOOU" nor the names of its contributors may be
 *   used to endorse or promote products derived from this software without
 *   specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package org.joou.test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import org.joou.UByte;
import org.joou.UInteger;
import org.joou.ULong;
import org.joou.UShort;
import org.junit.Test;

import static java.math.BigInteger.ONE;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.joou.ULong.MAX_VALUE_LONG;
import static org.joou.Unsigned.*;
import static org.junit.Assert.*;

/**
 * @author Lukas Eder
 */
public class UNumberTest {
    private static final float FLOAT_EPSILON = 2e-24f;
    private static final double DOUBLE_EPSILON = 2e-53;

    @Test
    public void testRange0() {
        testCastable((short) 0, ubyte("0"));
        testCastable((short) 0, ubyte((byte) 0));
        testCastable((short) 0, ubyte((short) 0));

        testCastable(0, ushort("0"));
        testCastable(0, ushort((short) 0));
        testCastable(0, ushort(0));

        testCastable(0L, uint("0"));
        testCastable(0L, uint(0));
        testCastable(0L, uint(0L));

        testCastable(BigInteger.ZERO, ulong("0"));
        testCastable(BigInteger.ZERO, ulong(0L));
        testCastable(BigInteger.ZERO, ulong(BigInteger.ZERO));
    }

    @Test
    public void testRange1() {
        testCastable((short) 1, ubyte("1"));
        testCastable((short) 1, ubyte((byte) 1));
        testCastable((short) 1, ubyte((short) 1));

        testCastable(1, ushort("1"));
        testCastable(1, ushort((short) 1));
        testCastable(1, ushort(1));

        testCastable(1L, uint("1"));
        testCastable(1L, uint(1));
        testCastable(1L, uint(1L));

        testCastable(BigInteger.ONE, ulong("1"));
        testCastable(BigInteger.ONE, ulong(1L));
        testCastable(BigInteger.ONE, ulong(BigInteger.ONE));
    }

    @Test
    public void testRangeSignedMaxValue() {
        testCastable(Byte.MAX_VALUE, ubyte(Byte.toString(Byte.MAX_VALUE)));
        testCastable(Byte.MAX_VALUE, ubyte(Byte.MAX_VALUE));
        testCastable(Byte.MAX_VALUE, ubyte((short) Byte.MAX_VALUE));

        testCastable(Short.MAX_VALUE, ushort(Short.toString(Short.MAX_VALUE)));
        testCastable(Short.MAX_VALUE, ushort(Short.MAX_VALUE));
        testCastable(Short.MAX_VALUE, ushort((int) Short.MAX_VALUE));

        testCastable(Integer.MAX_VALUE, uint(Integer.toString(Integer.MAX_VALUE)));
        testCastable(Integer.MAX_VALUE, uint(Integer.MAX_VALUE));
        testCastable(Integer.MAX_VALUE, uint((long) Integer.MAX_VALUE));

        testCastable(BigInteger.valueOf(Long.MAX_VALUE), ulong(Long.toString(Long.MAX_VALUE)));
        testCastable(BigInteger.valueOf(Long.MAX_VALUE), ulong(Long.MAX_VALUE));
        testCastable(BigInteger.valueOf(Long.MAX_VALUE), ulong(BigInteger.valueOf(Long.MAX_VALUE)));
    }

    @Test
    public void testRangeSignedMaxValuePlusOne() {
        testCastable((short) 0x80, ubyte(Short.toString((short) 0x80)));
        testCastable((short) 0x80, ubyte((byte) -0x80));
        testCastable((short) 0x80, ubyte((short) 0x80));

        testCastable(0x8000, ushort(Integer.toString(0x8000)));
        testCastable(0x8000, ushort((short) -0x8000));
        testCastable(0x8000, ushort(0x8000));

        testCastable(0x80000000L, uint(Long.toString(0x80000000L)));
        testCastable(0x80000000L, uint((int) -0x80000000L));
        testCastable(0x80000000L, uint(0x80000000L));

        testCastable(MAX_VALUE_LONG, ulong(MAX_VALUE_LONG.toString()));
        testCastable(MAX_VALUE_LONG, ulong(0x8000000000000000L));
        testCastable(MAX_VALUE_LONG, ulong(MAX_VALUE_LONG));
    }

    @Test
    public void testRangeSignedMaxValuePlusTwo() {
        testCastable((short) 0x81, ubyte(Short.toString((short) 0x81)));
        testCastable((short) 0x81, ubyte((byte) -0x7F));
        testCastable((short) 0x81, ubyte((short) 0x81));

        testCastable(0x8001, ushort(Integer.toString(0x8001)));
        testCastable(0x8001, ushort((short) -0x7FFF));
        testCastable(0x8001, ushort(0x8001));

        testCastable(0x80000001L, uint(Long.toString(0x80000001L)));
        testCastable(0x80000001L, uint((int) -0x7FFFFFFFL));
        testCastable(0x80000001L, uint(0x80000001L));

        testCastable(MAX_VALUE_LONG.add(ONE), ulong(MAX_VALUE_LONG.add(ONE).toString()));
        testCastable(MAX_VALUE_LONG.add(ONE), ulong(0x8000000000000001L));
        testCastable(MAX_VALUE_LONG.add(ONE), ulong(MAX_VALUE_LONG.add(ONE)));
    }

    @Test
    public void testRangeUnsignedMaxValue() {
        testCastable(UByte.MAX_VALUE, ubyte(Short.toString(UByte.MAX_VALUE)));
        testCastable(UByte.MAX_VALUE, ubyte((byte) -1));
        testCastable(UByte.MAX_VALUE, ubyte(UByte.MAX_VALUE));

        testCastable(UShort.MAX_VALUE, ushort(Integer.toString(UShort.MAX_VALUE)));
        testCastable(UShort.MAX_VALUE, ushort((short) -1));
        testCastable(UShort.MAX_VALUE, ushort(UShort.MAX_VALUE));

        testCastable(UInteger.MAX_VALUE, uint(Long.toString(UInteger.MAX_VALUE)));
        testCastable(UInteger.MAX_VALUE, uint(-1));
        testCastable(UInteger.MAX_VALUE, uint(UInteger.MAX_VALUE));

        testCastable(ULong.MAX_VALUE, ulong(ULong.MAX_VALUE.toString()));
        testCastable(ULong.MAX_VALUE, ulong(-1L));
        testCastable(ULong.MAX_VALUE, ulong(ULong.MAX_VALUE));
    }

    @Test
    public void testObjectMethods() {
        assertEquals(ubyte((byte) 0), ubyte((byte) 0));
        assertEquals(ubyte((byte) 1), ubyte((byte) 1));
        assertEquals("0", ubyte((byte) 0).toString());
        assertEquals("1", ubyte((byte) 1).toString());
        assertEquals(Short.toString(UByte.MAX_VALUE), ubyte((byte) -1).toString());

        assertEquals(ushort((short) 0), ushort((short) 0));
        assertEquals(ushort((short) 1), ushort((short) 1));
        assertEquals("0", ushort((short) 0).toString());
        assertEquals("1", ushort((short) 1).toString());
        assertEquals(Integer.toString(UShort.MAX_VALUE), ushort((short) -1).toString());

        assertEquals(uint(0), uint(0));
        assertEquals(uint(1), uint(1));
        assertEquals("0", uint(0).toString());
        assertEquals("1", uint(1).toString());
        assertEquals(Long.toString(UInteger.MAX_VALUE), uint(-1).toString());

        assertEquals(ulong(0), ulong(0));
        assertEquals(ulong(1), ulong(1));
        assertEquals("0", ulong(0).toString());
        assertEquals("1", ulong(1).toString());
        assertEquals(ULong.MAX_VALUE.toString(), ulong(-1).toString());

        assertNotEquals(ubyte(0), ubyte(1));
        assertNotEquals(ushort(0), ushort(1));
        assertNotEquals(uint(0), uint(1));
        assertNotEquals(ulong(0), ulong(1));
    }

    @Test
    public void testComparable() {
        testComparable(asList("1", "2", "3"), ubyte((byte) 1), ubyte((byte) 2), ubyte((byte) 3));
        testComparable(asList("1", "2", "3"), ubyte((byte) 3), ubyte((byte) 2), ubyte((byte) 1));
        testComparable(asList("1", "2", "3"), ubyte((byte) 3), ubyte((byte) 1), ubyte((byte) 2));
        testComparable(asList("1", "1", "2", "3"), ubyte((byte) 3), ubyte((byte) 1), ubyte((byte) 2), ubyte((byte) 1));

        testComparable(asList("1", "2", "3"), ushort(1), ushort(2), ushort(3));
        testComparable(asList("1", "2", "3"), ushort(3), ushort(2), ushort(1));
        testComparable(asList("1", "2", "3"), ushort(3), ushort(1), ushort(2));
        testComparable(asList("1", "1", "2", "3"), ushort(3), ushort(1), ushort(2), ushort(1));

        testComparable(asList("1", "2", "3"), uint(1), uint(2), uint(3));
        testComparable(asList("1", "2", "3"), uint(3), uint(2), uint(1));
        testComparable(asList("1", "2", "3"), uint(3), uint(1), uint(2));
        testComparable(asList("1", "1", "2", "3"), uint(3), uint(1), uint(2), uint(1));

        testComparable(asList("1", "2", "3"), ulong(1), ulong(2), ulong(3));
        testComparable(asList("1", "2", "3"), ulong(3), ulong(2), ulong(1));
        testComparable(asList("1", "2", "3"), ulong(3), ulong(1), ulong(2));
        testComparable(asList("1", "1", "2", "3"), ulong(3), ulong(1), ulong(2), ulong(1));
    }

    // Test utility methods

    @SuppressWarnings({"rawtypes", "unchecked"})
    private <T extends Comparable<T>> void testComparable(List<String> strings, T... numbers) {
        Arrays.sort(numbers);

        for (int i = 0; i < numbers.length; i++)
            assertThat(numbers[i].toString(), equalTo(strings.get(i)));
    }

    private void testCastable(short value, UByte u) {
        assertEquals((byte) value, u.byteValue());
        assertEquals(value, u.shortValue());
        assertEquals(value, u.intValue());
        assertEquals(value, u.longValue());
        assertEquals((double) value, u.doubleValue(), DOUBLE_EPSILON);
        assertEquals((float) value, u.floatValue(), FLOAT_EPSILON);
        assertEquals(new BigInteger(Short.toString(value)), u.toBigInteger());
    }

    private void testCastable(int value, UShort u) {
        assertEquals((byte) value, u.byteValue());
        assertEquals((short) value, u.shortValue());
        assertEquals(value, u.intValue());
        assertEquals(value, u.longValue());
        assertEquals((double) value, u.doubleValue(), DOUBLE_EPSILON);
        assertEquals((float) value, u.floatValue(), FLOAT_EPSILON);
        assertEquals(new BigInteger(Integer.toString(value)), u.toBigInteger());
    }

    private void testCastable(long value, UInteger u) {
        assertEquals((byte) value, u.byteValue());
        assertEquals((short) value, u.shortValue());
        assertEquals((int) value, u.intValue());
        assertEquals(value, u.longValue());
        assertEquals((double) value, u.doubleValue(), DOUBLE_EPSILON);
        assertEquals((float) value, u.floatValue(), FLOAT_EPSILON);
        assertEquals(new BigInteger(Long.toString(value)), u.toBigInteger());
    }

    private void testCastable(BigInteger value, ULong u) {
        assertEquals(value.byteValue(), u.byteValue());
        assertEquals(value.shortValue(), u.shortValue());
        assertEquals(value.intValue(), u.intValue());
        assertEquals(value.longValue(), u.longValue());
        assertEquals(value.doubleValue(), u.doubleValue(), DOUBLE_EPSILON);
        assertEquals(value.floatValue(), u.floatValue(), FLOAT_EPSILON);
        assertEquals(new BigInteger(value.toString()), u.toBigInteger());
    }
}
