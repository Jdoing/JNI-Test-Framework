package com.sandisk.zs.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * File: ZSOperationCtrl.java 
 * Author: zane, ymiao, candy
 * 
 * Created on July 30, 2013
 * 
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com THIS IS NOT A CONTRIBUTION
 */

/**
 * 
 */
public class ZSOperationCtrl {
    private int concurrencyLevel = 64;
    private ReentrantLock[] locks;
    private int segmentShift;
    private int segmentMask;

    public ZSOperationCtrl(int concurrencyLevel) {
        this.concurrencyLevel = concurrencyLevel;
        // Find power-of-two sizes best matching arguments
        int sshift = 0;
        int ssize = 1;
        while (ssize < this.concurrencyLevel) {
            ++sshift;
            ssize <<= 1;
        }
        segmentShift = 32 - sshift;
        segmentMask = ssize - 1;
        // init locks
        locks = new ReentrantLock[this.concurrencyLevel];
        for (int i = 0; i < locks.length; i++) {
            locks[i] = new ReentrantLock();
        }
    }

    private int hash(int h) {
        // Spread bits to regularize both segment and index locations,
        // using variant of single-word Wang/Jenkins hash.
        h += (h << 15) ^ 0xffffcd7d;
        h ^= (h >>> 10);
        h += (h << 3);
        h ^= (h >>> 6);
        h += (h << 2) + (h << 14);
        return h ^ (h >>> 16);
    }

    public ReentrantLock getLock(int hashcode) {
        int hash = hash(hashcode);
        return locks[(hash >>> segmentShift) & segmentMask];
    }
}
