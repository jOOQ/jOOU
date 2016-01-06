/**
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

import static junit.framework.Assert.fail;
import static org.joou.Unsigned.ushort;
import static org.junit.Assert.assertEquals;

import org.joou.UShort;
import org.junit.Test;

public class UShortTest {

    @Test
    public void testAddUShortValid() throws Exception {
        assertEquals(ushort(3), ushort(1).add(ushort(2)));
    }

    @Test
    public void testAddUShortInvalid() throws Exception {
        try {
            UShort.MAX.add(ushort(1));
            fail();
        } catch (NumberFormatException e) {}

        try {
            UShort.MAX.add(ushort(UShort.MAX_VALUE));
            fail();
        } catch (NumberFormatException e) {}
    }

    @Test
    public void testAddIntValid() throws Exception {
        assertEquals(ushort(3), ushort(1).add(2));
    }

    @Test
    public void testAddIntInvalid() throws Exception {
        try {
            UShort.MAX.add(1);
            fail();
        } catch (NumberFormatException e) {}

        try {
            UShort.MIN.add(-1);
            fail();
        } catch (NumberFormatException e) {}
    }

    @Test
    public void testSubtractUShortValid() throws Exception {
        assertEquals(ushort(1), ushort(3).subtract(ushort(2)));
    }

    @Test
    public void testSubtractUShortInvalid() throws Exception {
        try {
            UShort.MIN.subtract(ushort(1));
            fail();
        } catch (NumberFormatException e) {}
    }

    @Test
    public void testSubtractIntValid() throws Exception {
        assertEquals(ushort(1), ushort(3).subtract(2));
    }

    @Test
    public void testSubtractIntInvalid() throws Exception {
        try {
            UShort.MIN.subtract(1);
            fail();
        } catch (NumberFormatException e) {}

        try {
            UShort.MAX.subtract(-1);
            fail();
        } catch (NumberFormatException e) {}
    }

}
