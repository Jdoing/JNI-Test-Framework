package com.sandisk.zs.type;

/**
 * File: ZSObjectOpType.java 
 * Author: zane, ymiao, candy
 * 
 * Created on May 08, 2014
 * 
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com THIS IS NOT A CONTRIBUTION
 */

/**
 * Bulk operation mode.
 */
public enum ZSObjectOpType {
    ADD, REMOVE, REPLACE, GET;

    public static ZSObjectOpType valueOf(int value) {
        switch (value) {
        case 0:
            return ADD;
        case 1:
            return REMOVE;
        case 2:
            return REPLACE;
        case 3:
            return GET;
        default:
            return null;
        }
    }
}
