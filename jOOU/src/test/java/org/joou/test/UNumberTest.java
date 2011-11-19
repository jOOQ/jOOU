package org.joou.test;
import static java.math.BigInteger.ONE;
import static java.util.Arrays.asList;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.joou.ULong.MAX_VALUE_LONG;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.joou.UByte;
import org.joou.UInteger;
import org.joou.ULong;
import org.joou.UNumber;
import org.joou.UShort;
import org.junit.Test;

/**
 * Copyright (c) 2009-2011, Lukas Eder, lukas.eder@gmail.com
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

/**
 * @author Lukas Eder
 */
public class UNumberTest {

    @Test
    public void testRange0() {
        testCastable((short) 0, new UByte("0"));
        testCastable((short) 0, new UByte((byte) 0));
        testCastable((short) 0, new UByte((short) 0));

        testCastable(0, new UShort("0"));
        testCastable(0, new UShort((short) 0));
        testCastable(0, new UShort(0));

        testCastable(0L, new UInteger("0"));
        testCastable(0L, new UInteger(0));
        testCastable(0L, new UInteger(0L));

        testCastable(BigInteger.ZERO, new ULong("0"));
        testCastable(BigInteger.ZERO, new ULong(0L));
        testCastable(BigInteger.ZERO, new ULong(BigInteger.ZERO));
    }

    @Test
    public void testRange1() {
        testCastable((short) 1, new UByte("1"));
        testCastable((short) 1, new UByte((byte) 1));
        testCastable((short) 1, new UByte((short) 1));

        testCastable(1, new UShort("1"));
        testCastable(1, new UShort((short) 1));
        testCastable(1, new UShort(1));

        testCastable(1L, new UInteger("1"));
        testCastable(1L, new UInteger(1));
        testCastable(1L, new UInteger(1L));

        testCastable(BigInteger.ONE, new ULong("1"));
        testCastable(BigInteger.ONE, new ULong(1L));
        testCastable(BigInteger.ONE, new ULong(BigInteger.ONE));
    }

    @Test
    public void testRangeSignedMaxValue() {
        testCastable(Byte.MAX_VALUE, new UByte(Byte.toString(Byte.MAX_VALUE)));
        testCastable(Byte.MAX_VALUE, new UByte(Byte.MAX_VALUE));
        testCastable(Byte.MAX_VALUE, new UByte((short) Byte.MAX_VALUE));

        testCastable(Short.MAX_VALUE, new UShort(Short.toString(Short.MAX_VALUE)));
        testCastable(Short.MAX_VALUE, new UShort(Short.MAX_VALUE));
        testCastable(Short.MAX_VALUE, new UShort((int) Short.MAX_VALUE));

        testCastable(Integer.MAX_VALUE, new UInteger(Integer.toString(Integer.MAX_VALUE)));
        testCastable(Integer.MAX_VALUE, new UInteger(Integer.MAX_VALUE));
        testCastable(Integer.MAX_VALUE, new UInteger((long) Integer.MAX_VALUE));

        testCastable(BigInteger.valueOf(Long.MAX_VALUE), new ULong(Long.toString(Long.MAX_VALUE)));
        testCastable(BigInteger.valueOf(Long.MAX_VALUE), new ULong(Long.MAX_VALUE));
        testCastable(BigInteger.valueOf(Long.MAX_VALUE), new ULong(BigInteger.valueOf(Long.MAX_VALUE)));
    }

    @Test
    public void testRangeSignedMaxValuePlusOne() {
        testCastable((short) 0x80, new UByte(Short.toString((short) 0x80)));
        testCastable((short) 0x80, new UByte((byte) -0x80));
        testCastable((short) 0x80, new UByte((short) 0x80));

        testCastable(0x8000, new UShort(Integer.toString(0x8000)));
        testCastable(0x8000, new UShort((short) -0x8000));
        testCastable(0x8000, new UShort(0x8000));

        testCastable(0x80000000L, new UInteger(Long.toString(0x80000000L)));
        testCastable(0x80000000L, new UInteger((int) -0x80000000L));
        testCastable(0x80000000L, new UInteger(0x80000000L));

        testCastable(MAX_VALUE_LONG, new ULong(MAX_VALUE_LONG.toString()));
        testCastable(MAX_VALUE_LONG, new ULong(0x8000000000000000L));
        testCastable(MAX_VALUE_LONG, new ULong(MAX_VALUE_LONG));
    }

    @Test
    public void testRangeSignedMaxValuePlusTwo() {
        testCastable((short) 0x81, new UByte(Short.toString((short) 0x81)));
        testCastable((short) 0x81, new UByte((byte) -0x7F));
        testCastable((short) 0x81, new UByte((short) 0x81));

        testCastable(0x8001, new UShort(Integer.toString(0x8001)));
        testCastable(0x8001, new UShort((short) -0x7FFF));
        testCastable(0x8001, new UShort(0x8001));

        testCastable(0x80000001L, new UInteger(Long.toString(0x80000001L)));
        testCastable(0x80000001L, new UInteger((int) -0x7FFFFFFFL));
        testCastable(0x80000001L, new UInteger(0x80000001L));

        testCastable(MAX_VALUE_LONG.add(ONE), new ULong(MAX_VALUE_LONG.add(ONE).toString()));
        testCastable(MAX_VALUE_LONG.add(ONE), new ULong(0x8000000000000001L));
        testCastable(MAX_VALUE_LONG.add(ONE), new ULong(MAX_VALUE_LONG.add(ONE)));
    }

    @Test
    public void testRangeUnsignedMaxValue() {
        testCastable(UByte.MAX_VALUE, new UByte(Short.toString(UByte.MAX_VALUE)));
        testCastable(UByte.MAX_VALUE, new UByte((byte) -1));
        testCastable(UByte.MAX_VALUE, new UByte(UByte.MAX_VALUE));

        testCastable(UShort.MAX_VALUE, new UShort(Integer.toString(UShort.MAX_VALUE)));
        testCastable(UShort.MAX_VALUE, new UShort((short) -1));
        testCastable(UShort.MAX_VALUE, new UShort(UShort.MAX_VALUE));

        testCastable(UInteger.MAX_VALUE, new UInteger(Long.toString(UInteger.MAX_VALUE)));
        testCastable(UInteger.MAX_VALUE, new UInteger(-1));
        testCastable(UInteger.MAX_VALUE, new UInteger(UInteger.MAX_VALUE));

        testCastable(ULong.MAX_VALUE, new ULong(ULong.MAX_VALUE.toString()));
        testCastable(ULong.MAX_VALUE, new ULong(-1L));
        testCastable(ULong.MAX_VALUE, new ULong(ULong.MAX_VALUE));
    }

    @Test
    public void testObjectMethods() {
        assertEquals(new UByte((byte) 0), new UByte((byte) 0));
        assertEquals(new UByte((byte) 1), new UByte((byte) 1));
        assertFalse(new UByte((byte) 0).equals(new UByte((byte) 1)));
        assertEquals("0", new UByte((byte) 0).toString());
        assertEquals("1", new UByte((byte) 1).toString());
        assertEquals(Short.toString(UByte.MAX_VALUE), new UByte((byte) -1).toString());

        assertEquals(new UShort((short) 0), new UShort((short) 0));
        assertEquals(new UShort((short) 1), new UShort((short) 1));
        assertFalse(new UShort((short) 0).equals(new UShort((short) 1)));
        assertEquals("0", new UShort((short) 0).toString());
        assertEquals("1", new UShort((short) 1).toString());
        assertEquals(Integer.toString(UShort.MAX_VALUE), new UShort((short) -1).toString());

        assertEquals(new UInteger(0), new UInteger(0));
        assertEquals(new UInteger(1), new UInteger(1));
        assertFalse(new UInteger(0).equals(new UInteger(1)));
        assertEquals("0", new UInteger(0).toString());
        assertEquals("1", new UInteger(1).toString());
        assertEquals(Long.toString(UInteger.MAX_VALUE), new UInteger(-1).toString());

        assertEquals(new ULong(0), new ULong(0));
        assertEquals(new ULong(1), new ULong(1));
        assertFalse(new ULong(0).equals(new ULong(1)));
        assertEquals("0", new ULong(0).toString());
        assertEquals("1", new ULong(1).toString());
        assertEquals(ULong.MAX_VALUE.toString(), new ULong(-1).toString());
    }

    @Test
    public void testComparable() {
        testComparable(asList("1", "2", "3"), new UByte((byte) 1), new UByte((byte) 2), new UByte((byte) 3));
        testComparable(asList("1", "2", "3"), new UByte((byte) 3), new UByte((byte) 2), new UByte((byte) 1));
        testComparable(asList("1", "2", "3"), new UByte((byte) 3), new UByte((byte) 1), new UByte((byte) 2));
        testComparable(asList("1", "1", "2", "3"), new UByte((byte) 3), new UByte((byte) 1), new UByte((byte) 2), new UByte((byte) 1));

        testComparable(asList("1", "2", "3"), new UShort(1), new UShort(2), new UShort(3));
        testComparable(asList("1", "2", "3"), new UShort(3), new UShort(2), new UShort(1));
        testComparable(asList("1", "2", "3"), new UShort(3), new UShort(1), new UShort(2));
        testComparable(asList("1", "1", "2", "3"), new UShort(3), new UShort(1), new UShort(2), new UShort(1));

        testComparable(asList("1", "2", "3"), new UInteger(1), new UInteger(2), new UInteger(3));
        testComparable(asList("1", "2", "3"), new UInteger(3), new UInteger(2), new UInteger(1));
        testComparable(asList("1", "2", "3"), new UInteger(3), new UInteger(1), new UInteger(2));
        testComparable(asList("1", "1", "2", "3"), new UInteger(3), new UInteger(1), new UInteger(2), new UInteger(1));

        testComparable(asList("1", "2", "3"), new ULong(1), new ULong(2), new ULong(3));
        testComparable(asList("1", "2", "3"), new ULong(3), new ULong(2), new ULong(1));
        testComparable(asList("1", "2", "3"), new ULong(3), new ULong(1), new ULong(2));
        testComparable(asList("1", "1", "2", "3"), new ULong(3), new ULong(1), new ULong(2), new ULong(1));
    }

    // Test utility methods

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void testComparable(List<String> strings, UNumber... numbers) {
        List<UNumber> list = new ArrayList<UNumber>(asList(numbers));
        Collections.sort((List) list);

        for (int i = 0; i < numbers.length; i++) {
            assertEquals(strings.get(i), list.get(i).toString());
        }
    }

    private void testCastable(short value, UByte u) {
        assertEquals((byte) value, u.byteValue());
        assertEquals(value, u.shortValue());
        assertEquals(value, u.intValue());
        assertEquals(value, u.longValue());
        assertEquals((double) value, u.doubleValue());
        assertEquals((float) value, u.floatValue());
    }

    private void testCastable(int value, UShort u) {
        assertEquals((byte) value, u.byteValue());
        assertEquals((short) value, u.shortValue());
        assertEquals(value, u.intValue());
        assertEquals(value, u.longValue());
        assertEquals((double) value, u.doubleValue());
        assertEquals((float) value, u.floatValue());
    }

    private void testCastable(long value, UInteger u) {
        assertEquals((byte) value, u.byteValue());
        assertEquals((short) value, u.shortValue());
        assertEquals((int) value, u.intValue());
        assertEquals(value, u.longValue());
        assertEquals((double) value, u.doubleValue());
        assertEquals((float) value, u.floatValue());
    }

    private void testCastable(BigInteger value, ULong u) {
        assertEquals(value.byteValue(), u.byteValue());
        assertEquals(value.shortValue(), u.shortValue());
        assertEquals(value.intValue(), u.intValue());
        assertEquals(value.longValue(), u.longValue());
        assertEquals(value.doubleValue(), u.doubleValue());
        assertEquals(value.floatValue(), u.floatValue());
    }
}
