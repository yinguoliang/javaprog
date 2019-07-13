/*
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2002, 2013 Oracle and/or its affiliates.  All rights reserved.
 *
 */

package com.sleepycat.je.dbi;

public interface RangeConstraint {
    boolean inBounds(byte[] key);
}
