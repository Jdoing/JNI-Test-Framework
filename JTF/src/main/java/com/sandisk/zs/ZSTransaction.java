package com.sandisk.zs;

import com.sandisk.zs.exception.ZSException;
import com.sandisk.zs.exception.ZSExceptionHandler;

/**
 * File:   ZSTransaction.java
 * Author: zane, ymiao, candy
 *
 * Created on July 30, 2013
 *
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com
 * THIS IS NOT A CONTRIBUTION
 */

/**
 * Provides the interface to ZS transaction management. The class supports ZS transaction start, commit and rollback.
 */
public class ZSTransaction
{

    /**
     * Start the transaction.
     * 
     * @throws ZSException
     */
    public void start() throws ZSException
    {
        int resultCode = NativeZS.ZSTransactionStart();
        ZSExceptionHandler.handleClient(resultCode);
    }

    /**
     * Commit the transaction.
     * 
     * @throws ZSException
     */
    public void commit() throws ZSException
    {
        int resultCode = NativeZS.ZSTransactionCommit();
        ZSExceptionHandler.handleClient(resultCode);
    }

    /**
     * RollBack the transaction.
     * 
     * @throws ZSException
     */
    public void rollback() throws ZSException
    {
        int resultCode = NativeZS.ZSTransactionRollback();
        ZSExceptionHandler.handleClient(resultCode);
    }
}
