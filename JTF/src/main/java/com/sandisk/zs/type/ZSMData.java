package com.sandisk.zs.type;

import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zs.exception.ZSExceptionHandler;

/**
 * File: ZSMData.java 
 * Author: zane, ymiao, candy
 * 
 * Created on July 30, 2013
 * 
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com THIS IS NOT A CONTRIBUTION
 */

/**
 * Wrap the key and data for mput function.
 */
public class ZSMData {
	public byte[] key;
	public byte[] data;
	public int flag = 0;
	public int status = 1;

	public ZSMData(){
		super();
	}
	
	public int getFlags() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    //for ZSMGet
	public ZSMData(byte[] key){
		super();
		this.key = key;
	}
	
	//for ZSMSet
	public ZSMData(byte[] key, byte[] data){
		super();
		this.key = key;
		this.data = data;
	}
	
	public byte[] getKey() {
		return key;
	}

	public void setKey(byte[] key) {
		this.key = key;
	}

	public byte[] getData() throws ZSContainerException {
		if(status != 1){
			ZSExceptionHandler.handleContainer(status);
		}
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	@Override
	public String toString() {
		try {
			return "key: " + new String(key) + ", data: " + new String(getData());
		} catch (ZSContainerException e) {
			e.printStackTrace();
		}
		return null;
	}
}
