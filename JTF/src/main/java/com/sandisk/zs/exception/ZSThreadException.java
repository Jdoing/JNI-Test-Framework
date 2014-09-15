package com.sandisk.zs.exception;

/**
 * File: ZSThreadException.java 
 * Author: zane, ymiao, candy
 * 
 * Created on July 30, 2013
 * 
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com THIS IS NOT A CONTRIBUTION
 */

/**
 * Wrap a java thread exception for zs thread error.
 */

public class ZSThreadException extends ZSException {
	private static final long serialVersionUID = 7850708146858474586L;
	public ZSThreadException(String message) {
		super(message);
	}
}
