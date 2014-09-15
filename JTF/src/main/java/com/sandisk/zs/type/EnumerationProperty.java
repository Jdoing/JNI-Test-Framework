package com.sandisk.zs.type;

/**
 * File: EnumerationProperty.java 
 * Author: zane, ymiao, candy
 * 
 * Created on July 30, 2013
 * 
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com THIS IS NOT A CONTRIBUTION
 */

/**
 * Properties for enumeration.
 */
public class EnumerationProperty {
    private byte[] startKey;
    private boolean keysOnly;
    private long startSeq;
    private long endSeq;
    private boolean enableStartKey;
    private boolean enableKeysOnly;
    private boolean enableStartSeq;
    private boolean enableEndSeq;

    /**
     * Get enumeration start key.
     * 
     * @return
     */
    public byte[] getStartKey() {
        return startKey;
    }

    /**
     * Set enumeration start key.
     * 
     * @param startKey
     */
    public void setStartKey(byte[] startKey) {
        this.startKey = startKey;
        this.enableStartKey = true;
    }

    /**
     * Whether return keys only.
     * 
     * @return
     */
    public boolean isKeysOnly() {
        return keysOnly;
    }

    /**
     * Set the flag whether return keys only.
     * 
     * @param keysOnly
     */
    public void setKeysOnly(boolean keysOnly) {
        this.keysOnly = keysOnly;
        this.enableKeysOnly = true;
    }

    /**
     * Get start sequence number.
     * 
     * @return
     */
    public long getStartSeq() {
        return startSeq;
    }

    /**
     * Set start sequence number.
     * 
     * @param startSeq
     */
    public void setStartSeq(long startSeq) {
        this.startSeq = startSeq;
        this.enableStartSeq = true;
    }

    /**
     * Get end sequence number.
     * 
     * @return
     */
    public long getEndSeq() {
        return endSeq;
    }

    /**
     * Set end sequence number.
     * 
     * @param endSeq
     */
    public void setEndSeq(long endSeq) {
        this.endSeq = endSeq;
        this.enableEndSeq = true;
    }

    /**
     * Check the enumeration property whether assigned.
     * @return
     */
    public boolean checkEnableStatus() {
        return this.enableEndSeq || this.enableKeysOnly || this.enableStartKey
                || this.enableStartSeq;
    }

}
