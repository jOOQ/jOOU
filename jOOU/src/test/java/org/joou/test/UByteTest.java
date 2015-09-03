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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.joou.UByte;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import static org.joou.Unsigned.ubyte;

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
            assertEquals(ubyte(3), UByte.MAX_UBYTE.add(ubyte(1)));
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
            assertEquals(ubyte(3), UByte.MAX_UBYTE.add(1));
            fail();
        } catch (NumberFormatException e) {}

        try {
            assertEquals(ubyte(3), UByte.MIN_UBYTE.add(-1));
            fail();
        } catch (NumberFormatException e) {}
    }

}
