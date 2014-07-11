package com.sandisk.zs.type;

/**
 * File: RangeMeta.java 
 * Author: zane, ymiao, candy
 * 
 * Created on July 30, 2013
 * 
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com THIS IS NOT A CONTRIBUTION
 */

/**
 * Provide meta data for range query.
 */
public class RangeMeta {
    private int flags = ZSRangeFlags.ZS_RANGE_BUFFER_PROVIDED | ZSRangeFlags.ZS_RANGE_ALLOC_IF_TOO_SMALL; // flags controlling type of range query (see above)
    private int keybufSize; // size of application provided key buffers (if applicable)
    private long databufSize; // size of application provided data buffers (if applicable),
    private byte[] startKey = null; // start key
    private byte[] endKey = null; // end key
    private long startSeq; // starting sequence number (if applicable)
    private long endSeq; // ending sequence number (if applicable)
    private boolean enableStartSeq;
    private boolean enableEndSeq;
    private ZSCursor zscursor = new ZSCursor();

    public ZSCursor getZSCursor() {
        return this.zscursor;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flag) {
        this.flags |= flag;
    }

    public int getKeybufSize() {
        return keybufSize;
    }

    public long getDatabufSize() {
        return databufSize;
    }

    public byte[] getStartKey() {
        return startKey;
    }

    public void setStartInfo(byte[] startKey, int flag) throws Exception {
        if ((startKey == null && flag == 0) || (startKey != null && flag != 0)) {
            this.startKey = startKey;
            this.flags |= flag;
        } else {
            throw new Exception("The startKey and flag is not match!");
        }
    }

    public byte[] getEndKey() {
        return endKey;
    }

    public void setEndInfo(byte[] endKey, int flag) throws Exception {
        if ((endKey == null && flag == 0) || (endKey != null && flag != 0)) {
            this.flags |= flag;
            this.endKey = endKey;
        } else {
            throw new Exception("The endKey and flag is not match!");
        }
    }

    public long getStartSeq() {
        return startSeq;
    }

    public void setStartSeq(long start_seq) {
        this.startSeq = start_seq;
        enableStartSeq = true;
    }

    public long getEndSeq() {
        return endSeq;
    }

    public void setEndSeq(long end_seq) {
        this.endSeq = end_seq;
        enableEndSeq = true;
    }

}
