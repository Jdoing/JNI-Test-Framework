package com.sandisk.zs;

import com.sandisk.zs.NativeObject;
import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zs.type.ZSSnapshot;

/**
 * File:   NativeZSSnapshot.java
 * Author: zane, ymiao, candy
 *
 * Created on July 30, 2013
 *
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com
 * THIS IS NOT A CONTRIBUTION
 */

/**
 * Provides the public interface to ZS snapshot operations.
 */
public class NativeZSSnapshot extends NativeObject{
	
	public static final native long ZSCreateContainerSnapshot(long containerId) throws ZSContainerException;
	
	public static final native void ZSDeleteContainerSnapshot(long containerId, long snap_seq) throws ZSContainerException;
	
	public static final native ZSSnapshot[] ZSGetContainerSnapshots(long containerId) throws ZSContainerException;
}
