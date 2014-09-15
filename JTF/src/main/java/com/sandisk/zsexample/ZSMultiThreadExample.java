package com.sandisk.zsexample;

import java.util.Iterator;

import com.sandisk.zs.ZSClient;
import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zs.exception.ZSException;
import com.sandisk.zs.type.ZSObjectOp;
import com.sandisk.zs.type.WriteObjectMode;

/**
 * File:   ZSMultiThreadExample.java
 * Author: ymiao, candy
 *
 * Created on July 30, 2013
 *
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com
 * THIS IS NOT A CONTRIBUTION
 */

/**
 * Examples about multi thread to write or enumerate container.
 * 
 */
public class ZSMultiThreadExample
{
    public static ZSContainer container;

    static class WriteWorker extends Thread
    {
        public void run()
        {
            for (int i = 0; i < 1000; i++)
            {
                try
                {
                    container.write(("key" + i).getBytes(), ("data" + i).getBytes()
                            , WriteObjectMode.ZS_WRITE_MUST_NOT_EXIST);
                }
                catch (ZSContainerException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    static class EnumeratorWorker extends Thread
    {
        public void run()
        {
            int times = 0;
            while (times < 10) // enumerate container several times
            {
                Iterator<ZSObjectOp> it;
                int count = 0;
                try
                {
                    it = container.iterator(10);
                    while (it.hasNext())
                    {
                        ZSObjectOp op = it.next();
                        assert op.getRetCode() == 1; // ZS_SUCCESS
                        op.getKey();
                        op.getData();
                        count++;
                    }

                    System.out.println("Total enumerated data #: " + count);
                }
                catch (ZSContainerException e)
                {
                    e.printStackTrace();
                }
                times++;
            }

        }
    }

    public static void main(String[] args) throws ZSException, InterruptedException
    {
        // generate zs prop file with default setting
        ExampleUtil.generateZSPropFile();

        // init zs by specify prop file location
        ZSClient client = ZSClient.getInstance();
        client.init(ExampleUtil.getPropFile());

        // create a container
        container = ExampleUtil.createHashContainer("c0");
        WriteWorker worker1 = new WriteWorker();
        EnumeratorWorker worker2 = new EnumeratorWorker();
        worker1.start();
        worker2.start();

        worker1.join();
        worker2.join();

        // close container safely
        ExampleUtil.closeContainer(container);
        // shutdown zs
        client.shutdown();
    }
}
