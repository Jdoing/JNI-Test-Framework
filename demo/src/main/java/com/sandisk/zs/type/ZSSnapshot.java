package com.sandisk.zs.type;

/**
 * File:   ZSSnapshot.java
 * Author: zane, ymiao, candy
 *
 * Created on July 30, 2013
 *
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com
 * THIS IS NOT A CONTRIBUTION
 */

/**
 * Provides the interface to ZS snapshots. The class supports snapshot creation,
 * deletion and sequence numbering.
 * 
 */
public class ZSSnapshot {

    private long snapSeq;

    private long timestamp;

    public long getSnapSeq() {
        return snapSeq;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public ZSSnapshot(long snapSeq) {
        this.snapSeq = snapSeq;
    }

    public ZSSnapshot(long timestamp, long snapSeq) {
        this.snapSeq = snapSeq;
        this.timestamp = timestamp;
    }

    public String toString() {
        return "snapshot timestamp :" + timestamp + "  snapSeq :" + snapSeq;
    }

}
