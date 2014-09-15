package com.sandisk.zs.exception;

/**
 * File: ZSContainerException.java Author: zane, ymiao, candy
 * 
 * Created on July 30, 2013
 * 
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com THIS IS NOT A CONTRIBUTION
 */

/**
 * Wrap a java container exception for zs container error.
 */
public class ZSContainerException extends ZSException {
    private static final long serialVersionUID = -118292162906515838L;

    public ZSContainerException(String string) {
        super(string);
    }
}
