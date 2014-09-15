package com.sandisk.zs.type;

/**
 * File: ZSCursor.java 
 * Author: zane, ymiao, candy
 * 
 * Created on July 30, 2013
 * 
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com THIS IS NOT A CONTRIBUTION
 */

/**
 * Provide the cursor to range query.
 */
public class ZSCursor {
    private long cursor;

    public long getCursor() {
        return this.cursor;
    }

    public void setCursor(long cursor) {
        this.cursor = cursor;
    }
}
