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

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static org.joou.Unsigned.ulong;

import org.joou.ULong;
import org.junit.Test;

public class ULongTest {
    @Test
    public void testStringValueRejectsOutOfRangeNumbers() {
        for (String value : new String[] {
            "18446744073709551616",
            "50000000000000000000",
            "90000000000000000000",
            "-18446744073709551616",
            "-50000000000000000000",
            "-90000000000000000000"
        }) {
            try {
                ULong.valueOf(value);
                fail("Expected NumberFormatException for " + value);
            } catch (NumberFormatException expected) {}
        }
    }

    @Test
    public void testStringValueAcceptsUpperBoundary() {
        assertEquals(ULong.MAX, ULong.valueOf("18446744073709551615"));
        assertEquals(ULong.MAX, ULong.valueOf("+18446744073709551615"));
        assertEquals(ULong.MAX, ULong.valueOf("00018446744073709551615"));
        assertEquals(ULong.MAX.subtract(1), ULong.valueOf("18446744073709551614"));
    }

    @Test
    public void testStringValueNegativeZero() {
        assertEquals(ulong(0), ulong("-0"));
        assertEquals(ulong(0), ulong("+0"));
        assertEquals(ulong(0), ulong("0"));
    }

    @Test
    public void testAddULongValid() throws Exception {
        assertEquals(ulong(3), ulong(1).add(ulong(2)));
        assertEquals(ulong("9223372036854775813"), ulong(5).subtract(Long.MIN_VALUE));
        assertEquals(ulong(5), ulong("9223372036854775813").add(Long.MIN_VALUE));
    }

    @Test
    public void testAddULongInvalid() throws Exception {
        try {
            ULong.MAX.add(ulong(1));
            fail();
        }
        catch (NumberFormatException e) {}

        try {
            ULong.MAX.add(ulong(ULong.MAX_VALUE));
            fail();
        }
        catch (NumberFormatException e) {}

        try {
            ULong.valueOf(5).add(Long.MIN_VALUE);
            fail();
        }
        catch (NumberFormatException e) {}

        try {
            ULong.MAX.subtract(Long.MIN_VALUE);
            fail();
        }
        catch (NumberFormatException e) {}
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
        assertEquals(ulong("9223372036854775803"), ulong("9223372036854775813").subtract(10L));
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

    @Test
    public void testMaxValue() throws Exception {
        ULong ulongmax = ULong.valueOf(ULong.MAX_VALUE);
        ULong longmax = ULong.valueOf(Long.MAX_VALUE);

        assertEquals(ULong.MAX_VALUE, ulongmax.toBigInteger());
        assertEquals(ULong.MAX_VALUE.toString(), ulongmax.toString());

        assertEquals(ULong.MAX_VALUE, longmax.add(longmax).add(1).toBigInteger());
        assertEquals(ULong.MAX_VALUE.toString(), longmax.add(longmax).add(1).toString());
    }
}
