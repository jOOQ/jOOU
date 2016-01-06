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
import org.joou.UInteger;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.joou.Unsigned.uint;
import static org.junit.Assert.assertThat;

public class UIntegerTest {
    private static final int CACHE_SIZE = 256;
    private static final int NEAR_MISS_OFFSET = 4;

    @Test
    public void testValueOfLong() {
        for (long i = 1; i <= UInteger.MAX_VALUE; i <<= 1)
            assertThat(uint(i).longValue(), equalTo(i));
    }

    @Test
    public void testValueOfLongCachingShift() {
        for (long i = 1; i < CACHE_SIZE; i <<= 1) {
            UInteger a = uint(i);
            UInteger b = uint(i);
            assertThat(a, is(sameInstance(b)));
        }
    }

    @Test
    public void testValueOfLongCachingNear() {
        for (long i = CACHE_SIZE - NEAR_MISS_OFFSET; i < CACHE_SIZE; i++) {
            UInteger a = uint(i);
            UInteger b = uint(i);
            assertThat(a, is(sameInstance(b)));
        }
    }

    @Test
    public void testValueOfLongNoCachingShift() {
        for (long i = CACHE_SIZE; i <= CACHE_SIZE; i <<= 1) {
            UInteger a = uint(i);
            UInteger b = uint(i);
            assertThat(a, not(sameInstance(b)));
        }
    }

    @Test
    public void testValueOfLongNoCachingNear() {
        for (long i = CACHE_SIZE; i <= CACHE_SIZE + NEAR_MISS_OFFSET; i++) {
            UInteger a = uint(i);
            UInteger b = uint(i);
            assertThat(a, not(sameInstance(b)));
        }
    }

    @Test(expected = NumberFormatException.class)
    public void testValueOfLongInvalidCase1() {
        uint((UInteger.MIN_VALUE) - 1);
    }

    @Test(expected = NumberFormatException.class)
    public void testValueOfLongInvalidCase2() {
        uint((UInteger.MAX_VALUE) + 1);
    }

    @Test
    public void testSerializeDeserialize() throws ClassNotFoundException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        ByteArrayInputStream bais;
        ObjectInputStream ois;
        UInteger expected = uint(42);
        UInteger input = uint(42);
        Object actual;

        oos.writeObject(input);
        oos.close();
        bais = new ByteArrayInputStream(baos.toByteArray());
        ois = new ObjectInputStream(bais);
        actual = ois.readObject();

        assertThat(actual, allOf(
            instanceOf(UInteger.class),
            equalTo(expected),
            sameInstance(expected)
        ));
    }

    @Test
    public void testAddUIntegerValid() {
        assertThat(uint(1).add(uint(2)), is(uint(3)));
    }

    @Test(expected = NumberFormatException.class)
    public void testAddUIntegerInvalidCase1() {
        UInteger.MAX.add(uint(1));
    }

    @Test(expected = NumberFormatException.class)
    public void testAddUIntegerInvalidCase2() {
        UInteger.MAX.add(uint(UInteger.MAX_VALUE));
    }

    @Test
    public void testAddIntValid() throws Exception {
        assertThat(uint(1).add(2), is(uint(3)));
    }

    @Test(expected = NumberFormatException.class)
    public void testAddIntInvalidCase1() {
        UInteger.MAX.add(1);
    }

    @Test(expected = NumberFormatException.class)
    public void testAddIntInvalidCase2() {
        UInteger.MIN.add(-1);
    }

    @Test
    public void testSubtractUIntegerValid() throws Exception {
        assertThat(uint(3).subtract(uint(2)), is(uint(1)));
    }

    @Test(expected = NumberFormatException.class)
    public void testSubtractUIntegerInvalid() {
        UInteger.MIN.subtract(uint(1));
    }

    @Test
    public void testSubtractIntValid() {
        assertThat(uint(3).subtract(2), is(uint(1)));
    }

    @Test(expected = NumberFormatException.class)
    public void testSubtractIntInvalidCase1() {
        UInteger.MIN.subtract(1);
    }

    @Test(expected = NumberFormatException.class)
    public void testSubtractIntInvalidCase2() {
        UInteger.MAX.subtract(-1);
    }
}
