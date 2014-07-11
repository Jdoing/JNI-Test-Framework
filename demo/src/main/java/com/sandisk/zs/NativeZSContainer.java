package com.sandisk.zs;

import java.util.Comparator;

import com.sandisk.zs.NativeObject;
import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zs.type.ContainerProperty;
import com.sandisk.zs.type.ZSMData;
import com.sandisk.zs.type.ZSObjectOp;
import com.sandisk.zs.type.ZSStatistics;

/**
 * File:   NativeZSContainer.java
 * Author: zane, ymiao, candy
 *
 * Created on July 30, 2013
 *
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com
 * THIS IS NOT A CONTRIBUTION
 */

/**
 * Provides the public interface to ZS container operations.
 */
public class NativeZSContainer extends NativeObject
{
    static
    {
        initialize();
    }

    public static final native void initialize();

    public static final native int ZSLoadCntrPropDefaults(ContainerProperty containerState);

    public static final native int ZSOpenContainer(String cname, int flag, ContainerProperty containerState);

    public static final native int ZSOpenContainerSpecial(String cname, int flag, ContainerProperty containerState,
            Comparator cmpObj);

    public static final native int ZSCloseContainer(long containerId);

    public static final native int ZSDeleteContainer(long containerId);

    public static final native long[] ZSGetContainers() throws ZSContainerException;

    public static final native int ZSFlushContainer(long containerId);

    public static final native int ZSGetContainerProps(long containerId, ContainerProperty containerState);

    public static final native int ZSSetContainerProps(long containerId, ContainerProperty containerState);

    public static final native int ZSGetContainerStats(long containerId, ZSStatistics stats);

    public static final native int ZSWriteObject(byte[] key, byte[] data, long containerId, int flag);

    public static final native int ZSWriteObjectExpiry(byte[] key, byte[] data, long containerId, int flag, int expiry);

    public static final native byte[] ZSReadObject(byte[] key, long containerId) throws ZSContainerException;

    public static final native byte[] ZSReadObjectExpiry(byte[] key, long containerId) throws ZSContainerException;

    public static final native int ZSDeleteObject(byte[] key, long containerId);

    public static final native int ZSFlushObject(byte[] key, long containerId);

    public static final native int ZSMPut(long containerId, ZSMData[] datas, int flag);

    public static final native int ZSBulkOp(long containerId, ZSObjectOp[] ops);

    public static final native int ZSBulkEnumCreate(long containerId) throws ZSContainerException;

    public static final native ZSObjectOp[] ZSBulkEnumNext(long containerId, int count) throws ZSContainerException;

    public static final native int ZSBulkEnumFinish(long containerId) throws ZSContainerException;

}
