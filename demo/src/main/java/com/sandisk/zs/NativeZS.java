package com.sandisk.zs;

import com.sandisk.zs.NativeObject;
import com.sandisk.zs.type.ZSState;
import com.sandisk.zs.type.ZSStatistics;

/**
 * File:   NativeZS.java
 * Author: zane, ymiao, candy
 *
 * Created on July 30, 2013
 *
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com
 * THIS IS NOT A CONTRIBUTION
 */

/**
 * Provides the public interface to ZS global operations.
 */
public class NativeZS extends NativeObject
{
    public static final native int ZSLoadProperties(String propertiesFile);

    public static final native void ZSSetProperty(String propertyName, String value);

    public static final native int ZSInit(ZSState zs_state);

    public static final native int ZSShutdown();

    public static final native String ZSGetVersion();

    public static final native String ZSGetProperty(String key, String def);

    public static final native int ZSFlushCache();

    public static final native int ZSGetStats(ZSStatistics stats);

    public static final native int ZSTransactionStart();

    public static final native int ZSTransactionCommit();

    public static final native int ZSTransactionRollback();
}
