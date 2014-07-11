package com.sandisk.zs.type;

/**
 * File: OpenContainerMode.java 
 * Author: zane, ymiao, candy
 * 
 * Created on July 30, 2013
 * 
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com THIS IS NOT A CONTRIBUTION
 */

/**
 * Open container mode.
 */
public enum OpenContainerMode {

    READ_ONLY(2), READ_WRITE(4);

    private int value;

    private OpenContainerMode(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static OpenContainerMode valueOf(int value) {
        switch (value) {
        case 2:
            return READ_ONLY;
        case 4:
            return READ_WRITE;
        default:
            return null;
        }
    }
}
