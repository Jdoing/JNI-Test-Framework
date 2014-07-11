package com.sandisk.zs;

import com.sandisk.zs.NativeObject;
import com.sandisk.zs.type.EnumerationProperty;
import com.sandisk.zs.type.ZSEntry;

/**
 * File:   NativeZSEnumerator.java
 * Author: zane, ymiao, candy
 *
 * Created on July 30, 2013
 *
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com
 * THIS IS NOT A CONTRIBUTION
 */

/**
 * Provides the public interface to ZS container enumeration operations.
 */
public class NativeZSEnumerator extends NativeObject {

	public static  final native int ZSEnumerateContainerObjects(long containerId, EnumerationProperty enumerationProperty);
	
	public static  final native int ZSNextEnumeratedObject(ZSEntry entry);
	
	public static  final native int ZSFinishEnumeration();

}
