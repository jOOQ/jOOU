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
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import static org.joou.Unsigned.uint;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.joou.UInteger;
import org.junit.Test;

public class UIntegerTest {
    private static final int CACHE_SIZE=256;
    private static final int NEAR_MISS_OFFSET=4;

    @Test
    public void testValueOfLong() {
    	for(long l=01; l<=UInteger.MAX_VALUE; l<<=1)
            assertEquals(l , uint(l).longValue());
    }

    @Test
    public void testValueOfLongCachingShift() {
    	for(long l=01; l<CACHE_SIZE; l<<=1)
	{
            UInteger a = uint(l);
            UInteger b = uint(l);
            assertTrue(a == b);
        }
    }

    @Test
    public void testValueOfLongCachingNear() {
    	for(long l=CACHE_SIZE-NEAR_MISS_OFFSET; l<CACHE_SIZE; l++)
	{
            UInteger a = uint(l);
            UInteger b = uint(l);
            assertTrue(a == b);
        }
    }

    @Test
    public void testValueOfLongNoCachingShift() {
    	for(long l=CACHE_SIZE; l<=CACHE_SIZE; l<<=1)
	{
            UInteger a = uint(l);
            UInteger b = uint(l);
            assertFalse(a == b);
        }
    }

    @Test
    public void testValueOfLongNoCachingNear() {
    	for(long l=CACHE_SIZE; l<=CACHE_SIZE+NEAR_MISS_OFFSET; l++)
	{
            UInteger a = uint(l);
            UInteger b = uint(l);
            assertFalse(a == b);
        }
    }

    @Test
    public void testValueOfLongInvalid() {
        try {
            uint((UInteger.MIN_VALUE) - 1);
            fail();
        }
        catch (NumberFormatException e) {}
        try {
            uint((UInteger.MAX_VALUE) + 1);
            fail();
        }
        catch (NumberFormatException e) {}
    }

    @Test
    public void testSerializeDeserialize() throws ClassNotFoundException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        ByteArrayInputStream bais;
        ObjectInputStream ois;
        UInteger expected = uint(42);
        UInteger input = uint(42);
        UInteger actual;
        Object o;

        oos.writeObject(input);
        oos.close();
        bais = new ByteArrayInputStream(baos.toByteArray());
        ois = new ObjectInputStream(bais);
        o = ois.readObject();
        if (!(o instanceof UInteger))
            fail();
        actual = (UInteger) o;
        assertEquals(expected, actual); // same value
        assertTrue(expected == actual); // identical objects
    }

    @Test
    public void testAddUIntegerValid() throws Exception {
        assertEquals(uint(3), uint(1).add(uint(2)));
    }

    @Test
    public void testAddUIntegerInvalid() throws Exception {
        try {
            UInteger.MAX.add(uint(1));
            fail();
        } catch (NumberFormatException e) {}

        try {
            UInteger.MAX.add(uint(UInteger.MAX_VALUE));
            fail();
        } catch (NumberFormatException e) {}
    }

    @Test
    public void testAddIntValid() throws Exception {
        assertEquals(uint(3), uint(1).add(2));
    }

    @Test
    public void testAddIntInvalid() throws Exception {
        try {
            UInteger.MAX.add(1);
            fail();
        } catch (NumberFormatException e) {}

        try {
            UInteger.MIN.add(-1);
            fail();
        } catch (NumberFormatException e) {}
    }

    @Test
    public void testSubtractUIntegerValid() throws Exception {
        assertEquals(uint(1), uint(3).subtract(uint(2)));
    }

    @Test
    public void testSubtractUIntegerInvalid() throws Exception {
        try {
            UInteger.MIN.subtract(uint(1));
            fail();
        } catch (NumberFormatException e) {}
    }

    @Test
    public void testSubtractIntValid() throws Exception {
        assertEquals(uint(1), uint(3).subtract(2));
    }

    @Test
    public void testSubtractIntInvalid() throws Exception {
        try {
            UInteger.MIN.subtract(1);
            fail();
        } catch (NumberFormatException e) {}

        try {
            UInteger.MAX.subtract(-1);
            fail();
        } catch (NumberFormatException e) {}
    }


}
