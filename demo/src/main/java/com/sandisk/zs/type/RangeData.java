package com.sandisk.zs.type;

/**
 * File: RangeData.java 
 * Author: zane, ymiao, candy
 * 
 * Created on July 30, 2013
 * 
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com THIS IS NOT A CONTRIBUTION
 */

/**
 * Wrap the properties for range data.
 */
public class RangeData implements Cloneable
{
    private byte[] key; // index key value
    private byte[] data; // data
    private long seqno; // sequence number for last update
    private long syndrome; // syndrome for key
    private byte[] primaryKey; // primary key value (if required)

    public RangeData()
    {
        super();
    }

    public byte[] getPrimaryKey()
    {
        return primaryKey;
    }

    public void setPrimaryKey(byte[] primaryKey)
    {
        this.primaryKey = primaryKey;
    }

    public byte[] getKey()
    {
        return key;
    }

    public void setKey(byte[] key)
    {
        this.key = key;
    }

    public byte[] getData()
    {
        return data;
    }

    public void setData(byte[] data)
    {
        this.data = data;
    }

    public long getSeqno()
    {
        return seqno;
    }

    public void setSeqno(long seqno)
    {
        this.seqno = seqno;
    }

    public long getSyndrome()
    {
        return syndrome;
    }

    public void setSyndrome(long syndrome)
    {
        this.syndrome = syndrome;
    }

    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}
