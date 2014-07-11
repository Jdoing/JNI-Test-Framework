package com.sandisk.zs.exception;

/**
 * File: ZSException.java 
 * Author: zane, ymiao, candy
 * 
 * Created on July 30, 2013
 * 
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com THIS IS NOT A CONTRIBUTION
 */

/**
 * Wrap a java exception for zs error.
 */
public class ZSException extends Exception {

	private static final long serialVersionUID = -5408847886731734158L;

//	public ZSException(Throwable cause) {
//		super(cause);
//		super.initCause(cause);
//	}
//
//	public ZSException(String message, Throwable cause) {
//		super(message, cause);
//		super.initCause(cause);
//	}

	public ZSException(String message) {
		super(message);
	}
}
