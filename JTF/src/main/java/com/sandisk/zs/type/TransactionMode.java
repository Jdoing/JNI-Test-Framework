package com.sandisk.zs.type;

/**
 * File: TransactionMode.java Author: zane, ymiao, candy
 * 
 * Created on July 30, 2013
 * 
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved. 
 * http://www.sandisk.com THIS IS NOT A CONTRIBUTION
 */
public enum TransactionMode
{
    BTREE(1), HASH(2);
    private int value;

    private TransactionMode(int value)
    {
        this.value = value;
    }

    public static TransactionMode valueOf(int value)
    {
        switch (value)
        {
        case 1:
            return BTREE;
        case 2:
            return HASH;
        default:
            return null;
        }
    }

    public int getValue()
    {
        return value;
    }
}
