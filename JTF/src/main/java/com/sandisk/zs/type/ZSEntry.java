package com.sandisk.zs.type;

/**
 * File: ZSEntry.java 
 * Author: zane, ymiao, candy
 * 
 * Created on July 30, 2013
 * 
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com THIS IS NOT A CONTRIBUTION
 */

/**
 * Wrap the key and data.
 */
public class ZSEntry {

	private byte[] key;
	private byte[] data;
	
	public byte[] getKey() {
		return key;
	}

	public byte[] getData() {
		return data;
	}

	
	
//	public String toString(){
//		return "key="+(new String(key))+" : data="+ (data!=null?new String(data):data);
//	}
}
