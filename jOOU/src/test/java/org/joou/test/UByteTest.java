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

import java.io.*;
import org.joou.UByte;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.joou.Unsigned.ubyte;
import static org.junit.Assert.assertThat;

public class UByteTest {
    @Test
    public void testValueOfByte() {
        for (int i = Byte.MIN_VALUE; i <= Byte.MAX_VALUE; i++)
            assertThat(ubyte((byte) i).intValue(), equalTo(i & 0xFF));
    }

    @Test
    public void testValueOfByteCaching() {
        for (int i = Byte.MIN_VALUE; i <= Byte.MAX_VALUE; i++) {
            UByte a = ubyte((byte) i);
            UByte b = ubyte((byte) i);
            assertThat(a, is(sameInstance(b)));
        }
    }

    @Test
    public void testValueOfShort() {
        for (int i = UByte.MIN_VALUE; i <= UByte.MAX_VALUE; i++)
            assertThat(ubyte((short) i).intValue(), equalTo(i & 0xFF));
    }

    @Test(expected = NumberFormatException.class)
    public void testValueOfShortInvalidCase1() {
        ubyte((short) ((UByte.MIN_VALUE) - 1));
    }

    @Test(expected = NumberFormatException.class)
    public void testValueOfShortInvalidCase2() {
        ubyte((short) ((UByte.MAX_VALUE) + 1));
    }

    @Test
    public void testValueOfInt() {
        for (int i = UByte.MIN_VALUE; i <= UByte.MAX_VALUE; i++)
            assertThat(ubyte(i).intValue(), equalTo(i & 0xFF));
    }

    @Test(expected = NumberFormatException.class)
    public void testValueOfIntInvalid1() {
        ubyte((UByte.MIN_VALUE) - 1);
    }

    @Test(expected = NumberFormatException.class)
    public void testValueOfIntInvalid2() {
        ubyte((UByte.MAX_VALUE) + 1);
    }

    @Test
    public void testValueOfLong() {
        for (int i = UByte.MIN_VALUE; i <= UByte.MAX_VALUE; i++)
            assertThat(ubyte(i).intValue(), equalTo(i & 0xFF));
    }

    @Test(expected = NumberFormatException.class)
    public void testValueOfLongInvalidCase1() {
        ubyte((long) (UByte.MIN_VALUE) - 1);
    }

    @Test(expected = NumberFormatException.class)
    public void testValueOfLongInvalidCase2() {
        ubyte((long) (UByte.MAX_VALUE) + 1);
    }

    @Test
    public void testSerializeDeserialize() throws ClassNotFoundException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        ByteArrayInputStream bais;
        ObjectInputStream ois;
        UByte expected = ubyte(42);
        UByte input = ubyte(42);
        Object actual;

        oos.writeObject(input);
        oos.close();
        bais = new ByteArrayInputStream(baos.toByteArray());
        ois = new ObjectInputStream(bais);
        actual = ois.readObject();

        assertThat(actual, allOf(
            instanceOf(UByte.class),
            equalTo(expected),
            sameInstance(expected)
        ));
    }

    @Test
    public void testAddUByteValid() throws Exception {
        assertThat(ubyte(1).add(ubyte(2)), is(ubyte(3)));
    }

    @Test(expected = NumberFormatException.class)
    public void testAddUByteInvalidCase1() {
        UByte.MAX.add(ubyte(1));
    }

    @Test(expected = NumberFormatException.class)
    public void testAddUByteInvalidCase2() {
        UByte.MAX.add(ubyte(UByte.MAX_VALUE));
    }

    @Test
    public void testAddIntValid() throws Exception {
        assertThat(ubyte(1).add(2), is(ubyte(3)));
    }

    @Test(expected = NumberFormatException.class)
    public void testAddIntInvalidCase1() {
        UByte.MAX.add(1);
    }

    @Test(expected = NumberFormatException.class)
    public void testAddIntInvalidCase2() {
        UByte.MIN.add(-1);
    }

    @Test
    public void testSubtractUByteValid() throws Exception {
        assertThat(ubyte(3).subtract(ubyte(2)), is(ubyte(1)));
    }

    @Test(expected = NumberFormatException.class)
    public void testSubtractUByteInvalid() throws Exception {
        UByte.MIN.subtract(ubyte(1));
    }

    @Test
    public void testSubtractIntValid() throws Exception {
        assertThat(ubyte(3).subtract(2), is(ubyte(1)));
    }

    @Test(expected = NumberFormatException.class)
    public void testSubtractIntInvalidCase1() {
        UByte.MIN.subtract(1);
    }

    @Test(expected = NumberFormatException.class)
    public void testSubtractIntInvalidCase2() {
        UByte.MAX.subtract(-1);
    }
}
