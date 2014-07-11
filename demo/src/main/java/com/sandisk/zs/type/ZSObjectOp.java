package com.sandisk.zs.type;

/**
 * File: ZSObjectOp.java 
 * Author: zane, ymiao, candy
 * 
 * Created on July 30, 2013
 * 
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com THIS IS NOT A CONTRIBUTION
 */

/**
 * Wrap the properties for bulk object operation.
 */
public class ZSObjectOp
{
    private int optType;
    private byte[] key;
    private byte[] data;
    private long current; // Optional field
    private long expiry; // Optional field
    private int flags; // Optional field
    private int retCode; // ZS return code

    /**
     * Construct of Class
     * 
     * @param optType
     *            <pre>
     * ZSObjectOpType.ADD, ZSObjectOpType.REMOVE, ZSObjectOpType.REPLACE, ZSObjectOpType.GET
     * </pre>
     * @param key
     *            The object key.
     */
    public ZSObjectOp(ZSObjectOpType optType, byte[] key)
    {
        // optType = 0, 1, 2, 3 as: ADD, REMOVE, REPLACE, GET
        this.optType = optType.ordinal();
        // encoded key
        this.key = key;
    }

    /**
     * Construct of Class
     * 
     * @param optType
     *            <pre>
     * ZSObjectOpType.ADD, ZSObjectOpType.REMOVE, ZSObjectOpType.REPLACE, ZSObjectOpType.GET
     * </pre>
     * @param key
     *            The object key
     * @param data
     *            The object data
     */
    public ZSObjectOp(ZSObjectOpType optType, byte[] key, byte[] data)
    {
        // optType = 0, 1, 2, 3 as: ADD, REMOVE, REPLACE, GET
        this.optType = optType.ordinal();
        // encoded key
        this.key = key;
        // encoded data;
        this.data = data;
    }
    
    //just for jni c code to init object operation
    private ZSObjectOp(int optType, byte[] key, byte[] data)
    {
        // optType = 0, 1, 2, 3 as: ADD, REMOVE, REPLACE, GET
        this.optType = optType;
        // encoded key
        this.key = key;
        // encoded data;
        this.data = data;
    }

    public ZSObjectOpType getOptType()
    {
        return ZSObjectOpType.valueOf(optType);
    }

    public void setOptType(ZSObjectOpType optType)
    {
        this.optType = optType.ordinal();
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

    public long getCurrent()
    {
        return current;
    }

    public void setCurrent(long current)
    {
        this.current = current;
    }

    public long getExpiry()
    {
        return expiry;
    }

    public void setExpiry(long expiry)
    {
        this.expiry = expiry;
    }

    public int getFlags()
    {
        return flags;
    }

    public void setFlags(int flags)
    {
        this.flags = flags;
    }

    public int getRetCode()
    {
        return retCode;
    }

    public void setRetCode(int retCode)
    {
        this.retCode = retCode;
    }
}
