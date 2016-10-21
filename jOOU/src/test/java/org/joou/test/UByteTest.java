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
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import static org.joou.Unsigned.ubyte;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.joou.UByte;
import org.junit.Test;

public class UByteTest {

    @Test
    public void testValueOfByte() {
        for (int i = Byte.MIN_VALUE; i <= Byte.MAX_VALUE; i++)
            assertEquals(i & 0xFF, ubyte((byte) i).intValue());
    }

    @Test
    public void testValueOfByteCaching() {
        for (int i = Byte.MIN_VALUE; i <= Byte.MAX_VALUE; i++) {
            UByte a = ubyte((byte) i);
            UByte b = ubyte((byte) i);
            assertTrue(a == b);
        }
    }

    @Test
    public void testValueOfShort() {
        for (int i = UByte.MIN_VALUE; i <= UByte.MAX_VALUE; i++)
            assertEquals(i & 0xFF, ubyte((short) i).intValue());
    }

    @Test
    public void testValueOfShortInvalid() {
        try {
            ubyte((short) ((UByte.MIN_VALUE) - 1));
            fail();
        }
        catch (NumberFormatException e) {}
        try {
            ubyte((short) ((UByte.MAX_VALUE) + 1));
            fail();
        }
        catch (NumberFormatException e) {}
    }

    @Test
    public void testValueOfInt() {
        for (int i = UByte.MIN_VALUE; i <= UByte.MAX_VALUE; i++)
            assertEquals(i & 0xFF, ubyte(i).intValue());
    }

    @Test
    public void testValueOfIntInvalid() {
        try {
            ubyte((UByte.MIN_VALUE) - 1);
            fail();
        }
        catch (NumberFormatException e) {}
        try {
            ubyte((UByte.MAX_VALUE) + 1);
            fail();
        }
        catch (NumberFormatException e) {}
    }

    @Test
    public void testValueOfLong() {
        for (int i = UByte.MIN_VALUE; i <= UByte.MAX_VALUE; i++)
            assertEquals(i & 0xFF, ubyte((long) i).intValue());
    }

    @Test
    public void testValueOfLongInvalid() {
        try {
            ubyte((long) (UByte.MIN_VALUE) - 1);
            fail();
        }
        catch (NumberFormatException e) {}
        try {
            ubyte((long) (UByte.MAX_VALUE) + 1);
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
        UByte expected = ubyte(42);
        UByte input = ubyte(42);
        UByte actual;
        Object o;

        oos.writeObject(input);
        oos.close();
        bais = new ByteArrayInputStream(baos.toByteArray());
        ois = new ObjectInputStream(bais);
        o = ois.readObject();
        if (!(o instanceof UByte))
            fail();
        actual = (UByte) o;
        assertEquals(expected, actual); // same value
        assertTrue(expected == actual); // identical objects
    }

    @Test
    public void testAddUByteValid() throws Exception {
        assertEquals(ubyte(3), ubyte(1).add(ubyte(2)));
    }

    @Test
    public void testAddUByteInvalid() throws Exception {
        try {
            UByte.MAX.add(ubyte(1));
            fail();
        } catch (NumberFormatException e) {}

        try {
            UByte.MAX.add(ubyte(UByte.MAX_VALUE));
            fail();
        } catch (NumberFormatException e) {}
    }

    @Test
    public void testAddIntValid() throws Exception {
        assertEquals(ubyte(3), ubyte(1).add(2));
    }

    @Test
    public void testAddIntInvalid() throws Exception {
        try {
            UByte.MAX.add(1);
            fail();
        } catch (NumberFormatException e) {}

        try {
            UByte.MIN.add(-1);
            fail();
        } catch (NumberFormatException e) {}
    }

    @Test
    public void testSubtractUByteValid() throws Exception {
        assertEquals(ubyte(1), ubyte(3).subtract(ubyte(2)));
    }

    @Test
    public void testSubtractUByteInvalid() throws Exception {
        try {
            UByte.MIN.subtract(ubyte(1));
            fail();
        } catch (NumberFormatException e) {}
    }

    @Test
    public void testSubtractIntValid() throws Exception {
        assertEquals(ubyte(1), ubyte(3).subtract(2));
    }

    @Test
    public void testSubtractIntInvalid() throws Exception {
        try {
            UByte.MIN.subtract(1);
            fail();
        } catch (NumberFormatException e) {}

        try {
            UByte.MAX.subtract(-1);
            fail();
        } catch (NumberFormatException e) {}
    }

}
