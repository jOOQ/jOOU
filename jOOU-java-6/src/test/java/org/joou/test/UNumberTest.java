/*
 * Copyright (c) 2011-2016, Data Geekery GmbH (http://www.datageekery.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.joou.test;

import static java.math.BigInteger.ONE;
import static java.util.Arrays.asList;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.joou.ULong.MAX_VALUE_LONG;
import static org.joou.Unsigned.ubyte;
import static org.joou.Unsigned.uint;
import static org.joou.Unsigned.ulong;
import static org.joou.Unsigned.ushort;

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
 * @author Lukas Eder
 */
public class UNumberTest {

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
        assertFalse(ubyte((byte) 0).equals(ubyte((byte) 1)));
        assertEquals("0", ubyte((byte) 0).toString());
        assertEquals("1", ubyte((byte) 1).toString());
        assertEquals(Short.toString(UByte.MAX_VALUE), ubyte((byte) -1).toString());

        assertEquals(ushort((short) 0), ushort((short) 0));
        assertEquals(ushort((short) 1), ushort((short) 1));
        assertFalse(ushort((short) 0).equals(ushort((short) 1)));
        assertEquals("0", ushort((short) 0).toString());
        assertEquals("1", ushort((short) 1).toString());
        assertEquals(Integer.toString(UShort.MAX_VALUE), ushort((short) -1).toString());

        assertEquals(uint(0), uint(0));
        assertEquals(uint(1), uint(1));
        assertFalse(uint(0).equals(uint(1)));
        assertEquals("0", uint(0).toString());
        assertEquals("1", uint(1).toString());
        assertEquals(Long.toString(UInteger.MAX_VALUE), uint(-1).toString());

        assertEquals(ulong(0), ulong(0));
        assertEquals(ulong(1), ulong(1));
        assertFalse(ulong(0).equals(ulong(1)));
        assertEquals("0", ulong(0).toString());
        assertEquals("1", ulong(1).toString());
        assertEquals(ULong.MAX_VALUE.toString(), ulong(-1).toString());
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
        assertEquals(new BigInteger("" + value), u.toBigInteger());
    }

    private void testCastable(int value, UShort u) {
        assertEquals((byte) value, u.byteValue());
        assertEquals((short) value, u.shortValue());
        assertEquals(value, u.intValue());
        assertEquals(value, u.longValue());
        assertEquals((double) value, u.doubleValue());
        assertEquals((float) value, u.floatValue());
        assertEquals(new BigInteger("" + value), u.toBigInteger());
    }

    private void testCastable(long value, UInteger u) {
        assertEquals((byte) value, u.byteValue());
        assertEquals((short) value, u.shortValue());
        assertEquals((int) value, u.intValue());
        assertEquals(value, u.longValue());
        assertEquals((double) value, u.doubleValue());
        assertEquals((float) value, u.floatValue());
        assertEquals(new BigInteger("" + value), u.toBigInteger());
    }

    private void testCastable(BigInteger value, ULong u) {
        assertEquals(value.byteValue(), u.byteValue());
        assertEquals(value.shortValue(), u.shortValue());
        assertEquals(value.intValue(), u.intValue());
        assertEquals(value.longValue(), u.longValue());
        assertEquals(value.doubleValue(), u.doubleValue());
        assertEquals(value.floatValue(), u.floatValue());
        assertEquals(new BigInteger("" + value), u.toBigInteger());
    }
}
