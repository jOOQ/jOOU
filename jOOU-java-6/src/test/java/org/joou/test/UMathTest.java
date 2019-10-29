package org.joou.test;

import org.joou.*;
import org.junit.Assert;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.joou.Unsigned.*;

public class UMathTest {

    @Test
    public void testUByteMax() {
        assertEquals(ubyte(2), UMath.max(ubyte(1), ubyte(2)));
        assertEquals(UByte.MAX, UMath.max(ubyte(1), UByte.MAX));
        assertEquals(UByte.MAX, UMath.max(UByte.MIN, UByte.MAX));
    }

    @Test
    public void testUIntegerMax() {
        assertEquals(uint(2), UMath.max(uint(1), uint(2)));
        Assert.assertEquals(UInteger.MAX, UMath.max(uint(1), UInteger.MAX));
        assertEquals(UInteger.MAX, UMath.max(UInteger.MIN, UInteger.MAX));
    }

    @Test
    public void testULongMax() {
        assertEquals(ulong(2), UMath.max(ulong(1), ulong(2)));
        assertEquals(ULong.MAX, UMath.max(ulong(1), ULong.MAX));
        assertEquals(ULong.MAX, UMath.max(ULong.MIN, ULong.MAX));
    }

    @Test
    public void testUShotMax() {
        assertEquals(ushort(2), UMath.max(ushort(1), ushort(2)));
        assertEquals(UShort.MAX, UMath.max(ushort(1), UShort.MAX));
        assertEquals(UShort.MAX, UMath.max(UShort.MIN, UShort.MAX));
    }

    @Test
    public void testUByteMin() {
        assertEquals(ubyte(1), UMath.min(ubyte(1), ubyte(2)));
        assertEquals(UByte.MIN, UMath.min(ubyte(1), UByte.MIN));
        assertEquals(UByte.MIN, UMath.min(UByte.MIN, UByte.MAX));
    }

    @Test
    public void testUIntegerMin() {
        assertEquals(uint(1), UMath.min(uint(1), uint(2)));
        assertEquals(UInteger.MIN, UMath.min(uint(1), UInteger.MIN));
        assertEquals(UInteger.MIN, UMath.min(UInteger.MIN, UInteger.MAX));
    }

    @Test
    public void testULongMin() {
        assertEquals(ulong(1), UMath.min(ulong(1), ulong(2)));
        assertEquals(ULong.MIN, UMath.min(ulong(1), ULong.MIN));
        assertEquals(ULong.MIN, UMath.min(ULong.MIN, ULong.MAX));
    }

    @Test
    public void testUShortMin() {
        assertEquals(ushort(1), UMath.min(ushort(1), ushort(2)));
        assertEquals(UShort.MIN, UMath.min(ushort(1), UShort.MIN));
        assertEquals(UShort.MIN, UMath.min(UShort.MIN, UShort.MAX));
    }
}