package com.sandisk.zsexample;

import com.sandisk.zs.ZSClient;
import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.ZSTransaction;
import com.sandisk.zs.exception.ZSException;


/**
 * File:   ZSTransactionExample.java
 * Author: ymiao, candy
 *
 * Created on July 30, 2013
 *
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com
 * THIS IS NOT A CONTRIBUTION
 */

/**
 * Examples about zs transaction operations like start commit rollback.
 * 
 */
public class ZSTransactionExample
{
    public static void main(String[] args) throws ZSException
    {
        // generate zs prop file with default setting
        ExampleUtil.generateZSPropFile();

        // init zs by specify prop file location
        ZSClient client = ZSClient.getInstance();
        client.init(ExampleUtil.getPropFile());

        // create a container
        ZSContainer container = ExampleUtil.createHashContainer("c0");

        // start a transaction on one container
        ZSTransaction t = new ZSTransaction();
        t.start();
        container.write("key".getBytes(), "data".getBytes());
        t.rollback();
        byte[] data = container.read("key".getBytes());

        assert data.length == 0;

        // start a transaction cross containers (rollback)
        ZSContainer container2 = ExampleUtil.createBtreeContainer("c1");
        ZSTransaction t2 = new ZSTransaction();
        t2.start();
        container.write("key".getBytes(), "data".getBytes());
        container2.write("key".getBytes(), "data".getBytes());
        t2.rollback();

        data = container.read("key".getBytes());
        assert data.length == 0;

        data = container2.read("key".getBytes());
        assert data.length == 0;

        // start a transaction cross containers (commit)
        ZSTransaction t3 = new ZSTransaction();
        t3.start();
        container.write("key".getBytes(), "data".getBytes());
        container2.write("key".getBytes(), "data".getBytes());
        t3.commit();

        data = container.read("key".getBytes());
        assert "data".equals(new String(data));

        data = container2.read("key".getBytes());
        assert "data".equals(new String(data));
        
        // cleanup container if data are not useful any more
        ExampleUtil.closeContainer(container);
        ExampleUtil.closeContainer(container2);

        // shutdown zs
        client.shutdown();
    }
}
