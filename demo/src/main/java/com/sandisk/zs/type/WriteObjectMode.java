package com.sandisk.zs.type;

/**
 * File: WriteObjectMode.java 
 * Author: zane, ymiao, candy
 * 
 * Created on July 30, 2013
 * 
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com THIS IS NOT A CONTRIBUTION
 */

/**
 * Write object mode.
 */
public enum WriteObjectMode {
    ZS_WRITE_EXIST_OR_NOT, ZS_WRITE_MUST_NOT_EXIST, ZS_WRITE_MUST_EXIST;

    public static WriteObjectMode valueOf(int value) {
        switch (value) {
        case 0:
            return ZS_WRITE_EXIST_OR_NOT;
        case 1:
            return ZS_WRITE_MUST_NOT_EXIST;
        case 2:
            return ZS_WRITE_MUST_EXIST;
        default:
            return null;
        }
    }
}
