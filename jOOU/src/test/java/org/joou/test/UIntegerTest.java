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


}
