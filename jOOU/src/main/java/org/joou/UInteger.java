/**
 * Copyright (c) 2011-2012, Lukas Eder, lukas.eder@gmail.com
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
package org.joou;

import java.io.ObjectStreamException;

/**
 * The <code>unsigned int</code> type
 *
 * @author Lukas Eder
 */
public final class UInteger extends UNumber implements Comparable<UInteger> {
    private static final Class<UInteger> CLASS = UInteger.class;
    private static final String CLASS_NAME = CLASS.getName();

    /**
     * System property name for the property to set the size of the
     * pre-cache.
     */
    private static final String PRECACHE_PROPERTY = CLASS_NAME + ".precacheSize";

    /**
     * Default size for the value cache.
     */
    private static final int DEFAULT_PRECACHE_SIZE = 256;

    /**
     * Generated UID
     */
    private static final long serialVersionUID = -6821055240959745390L;

    /**
     * Cached values
     */
    private static final UInteger[] VALUES = mkValues();

    /**
     * A constant holding the minimum value an <code>unsigned int</code> can
     * have, 0.
     */
    public static final long  MIN_VALUE        = 0x00000000;

    /**
     * A constant holding the maximum value an <code>unsigned int</code> can
     * have, 2<sup>32</sup>-1.
     */
    public static final long  MAX_VALUE        = 0xffffffffL;

    /**
     * The value modelling the content of this <code>unsigned int</code>
     */
    private final long        value;

    /**
     * Figure out the size of the precache.
     * @return The parsed value of the system property
     * {@link #PRECACHE_PROPERTY} or {@link #DEFAULT_PRECACHE_SIZE}
     * if the property is not set, not a number or retrieving results
     * in a {@link SecurityException}. If the parsed value is zero or
     * negative no cache will be created. If the value is larger than
     * {@link Integer#MAX_VALUE} then Integer#MAX_VALUE will be used.
     */
    private static final int getPrecacheSize()
    {
        String prop = null;
        long propParsed;

        try
        {
            prop = System.getProperty(PRECACHE_PROPERTY);
        }
        catch(SecurityException e)
        {   // security manager stopped us so use default
            // FIXME: should we log this somewhere?
            return DEFAULT_PRECACHE_SIZE;
        }
        if(prop == null)
                return DEFAULT_PRECACHE_SIZE;
        if(prop.length() <= 0)
        {   // empty value
            // FIXME: should we log this somewhere?
            return DEFAULT_PRECACHE_SIZE;
        }
        try
        {
            propParsed = Long.parseLong(prop);
        }
        catch(NumberFormatException e)
        {   // not a valid number
            // FIXME: should we log this somewhere?
            return DEFAULT_PRECACHE_SIZE;
        }
        // treat negative value as no cache...
        if(propParsed < 0)
            return 0;
        if(propParsed > Integer.MAX_VALUE)
        {   // FIXME: should we log this somewhere
            return Integer.MAX_VALUE;
        }
        return (int)propParsed;
    }

    /**
     * Generate a cached value for initial unsigned integer values.
     *
     * @return Array of cached values for UInteger
     */
    private static final UInteger[] mkValues() {
        int precacheSize = getPrecacheSize();
        UInteger[] ret;

        if(precacheSize <= 0)
            return null;
        ret = new UInteger[precacheSize];
        for (int i = 0; i < precacheSize; i++)
            ret[i] = new UInteger(i);
        return ret;
    }

    /**
     * Unchecked internal constructor. This serves two purposes: first it
     * allows {@link #UInteger(long)} to stay deprecated without warnings
     * and second constructor without unnecessary value checks.
     * @param value The value to wrap
     * @param unused Unused paramater to distinguish between this and
     *     the deprecated public constructor.
     */
    private UInteger(long value, boolean unused)
    {
        this.value = value;
    }

    /**
     * Retrieve a cached value.
     * @param value Cached value to retrieve
     * @return Cached value if one exists. Null otherwise.
     */
    private static UInteger getCached(long value)
    {
        if(VALUES != null && value < VALUES.length)
            return VALUES[(int)value];
        return null;
    }

    /**
      * Get the value of a long without checking the value.
      */
    private static UInteger valueOfUnchecked(long value)
    {
        UInteger cached;

        if((cached = getCached(value))!=null)
            return cached;
        return new UInteger(value, true);
    }

    /**
     * Create an <code>unsigned int</code>
     *
     * @throws NumberFormatException If <code>value</code> does not contain a
     *             parsable <code>unsigned int</code>.
     */
    public static UInteger valueOf(String value) throws NumberFormatException {
        return valueOfUnchecked(rangeCheck(Long.parseLong(value)));
    }

    /**
     * Create an <code>unsigned int</code> by masking it with
     * <code>0xFFFFFFFF</code> i.e. <code>(int) -1</code> becomes
     * <code>(uint) 4294967295</code>
     */
    public static UInteger valueOf(int value) {
        return valueOfUnchecked(value & MAX_VALUE);
    }

    /**
     * Create an <code>unsigned int</code>
     *
     * @throws NumberFormatException If <code>value</code> is not in the range
     *             of an <code>unsigned byte</code>
     */
    public static UInteger valueOf(long value) throws NumberFormatException {
        return valueOfUnchecked(rangeCheck(value));
    }

    /**
     * Create an <code>unsigned int</code>
     *
     * @throws NumberFormatException If <code>value</code> is not in the range
     *             of an <code>unsigned int</code>
     * @deprecated - Use {@link #valueOf(long)}, or {@link Unsigned#uint(long)}
     *             instead
     */
    @Deprecated
    public UInteger(long value) throws NumberFormatException {
        this.value = rangeCheck(value);
    }

    /**
     * Create an <code>unsigned int</code> by masking it with
     * <code>0xFFFFFFFF</code> i.e. <code>(int) -1</code> becomes
     * <code>(uint) 4294967295</code>
     *
     * @deprecated - Use {@link #valueOf(int)}, or {@link Unsigned#uint(int)}
     *             instead
     */
    @Deprecated
    public UInteger(int value) {
        this.value = value & MAX_VALUE;
    }

    /**
     * Create an <code>unsigned int</code>
     *
     * @throws NumberFormatException If <code>value</code> does not contain a
     *             parsable <code>unsigned int</code>.
     * @deprecated - Use {@link #valueOf(String)}, or
     *             {@link Unsigned#uint(String)} instead
     */
    @Deprecated
    public UInteger(String value) throws NumberFormatException {
        this.value = rangeCheck(Long.parseLong(value));
    }

    /**
     * Throw exception if value out of range (long version)
     *
     * @param value Value to check
     * @return value if it is in range
     * @throws NumberFormatException if value is out of range
     */
    private static long rangeCheck(long value) throws NumberFormatException {
        if (value < MIN_VALUE || value > MAX_VALUE) {
            throw new NumberFormatException("Value is out of range : " + value);
        }
        return value;
    }

    /**
     * Replace version read through deserialization with cached version.
     * @return cached instance of this object's value if one exists,
     *     otherwise this object
     * @throws ObjectStreamException
     */
    private Object readResolve() throws ObjectStreamException {
        UInteger cached;

        // the value read could be invalid so check it
        rangeCheck(value);
        if((cached = getCached(value))!=null)
            return cached;
        return this;
    }

    @Override
    public int intValue() {
        return (int) value;
    }

    @Override
    public long longValue() {
        return value;
    }

    @Override
    public float floatValue() {
        return value;
    }

    @Override
    public double doubleValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return Long.valueOf(value).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
	    return true;
        if (obj instanceof UInteger) {
            return value == ((UInteger) obj).value;
        }

        return false;
    }

    @Override
    public String toString() {
        return Long.valueOf(value).toString();
    }

    @Override
    public int compareTo(UInteger o) {
        return (value < o.value ? -1 : (value == o.value ? 0 : 1));
    }
}
