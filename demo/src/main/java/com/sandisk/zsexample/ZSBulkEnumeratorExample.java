package com.sandisk.zsexample;

import java.util.Iterator;
import java.util.List;

import com.sandisk.zs.ZSClient;
import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.exception.ZSException;
import com.sandisk.zs.type.ZSObjectOp;

/**
 * File:   ZSBulkEnumeratorExample.java
 * Author: ymiao, candy
 *
 * Created on July 30, 2013
 *
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com
 * THIS IS NOT A CONTRIBUTION
 */

/**
 * Examples about zs enumerator operation.
 * 
 */
public class ZSBulkEnumeratorExample
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

        for (int i = 0; i < 10; i++)
        {
            container.write(("key" + i).getBytes(), ("data" + i).getBytes());
        }

        int enumerator_count = 3;
        int count = 0;
        // bulk enumerator example
        container.bulkEnumCreate();
        List<ZSObjectOp> list = null;
        while ((list = container.bulkEnumNext(enumerator_count)).size() > 0)
        {
            for (int i = 0; i < list.size(); i++)
            {
                list.get(i).getKey();
                list.get(i).getData();
                assert list.get(i).getRetCode() == 1; // ZS_SUCCESS
                count++;
            }
        }
        container.bulkEnumFinsh();
        assert count == 10;
        // bulk enumerator by iterator
        Iterator<ZSObjectOp> it = container.iterator(enumerator_count);
        count = 0;
        while (it.hasNext())
        {
            ZSObjectOp op = it.next();
            op.getKey();
            op.getData(); // need decode binary format to java object
            count++;
            assert op.getRetCode() == 1; // ZS_SUCCESS
        }
        assert count == 10;

        // close container
        ExampleUtil.closeContainer(container);
        // shutdown zs
        client.shutdown();
    }
}
