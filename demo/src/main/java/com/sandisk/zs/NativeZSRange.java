package com.sandisk.zs;

import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zs.NativeObject;
import com.sandisk.zs.type.ZSCursor;
import com.sandisk.zs.type.RangeData;
import com.sandisk.zs.type.RangeMeta;
import com.sandisk.zs.type.RangeUpdateStatus;

/**
 * File:   NativeZSRange.java
 * Author: zane, ymiao, candy
 *
 * Created on July 30, 2013
 *
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com
 * THIS IS NOT A CONTRIBUTION
 */

/**
 * Provides the public interface to ZS object range operations.
 */
public class NativeZSRange extends NativeObject
{
    static{
        initialize();
    }

    public static final native void initialize();

    public static final native int ZSGetRange(long containerId, RangeMeta meta);

    public static final native RangeData[] ZSGetNextRange(int numItems, ZSCursor zscursor) throws ZSContainerException;

    public static final native int ZSGetRangeFinish(long cursor);

    public static final native int ZSMUpdate(long containerId, byte[] rowKey,
            long timestamp, int localdeltime, RangeUpdateStatus status, byte[] startKey,
            byte[] endKey) throws ZSContainerException;
}
