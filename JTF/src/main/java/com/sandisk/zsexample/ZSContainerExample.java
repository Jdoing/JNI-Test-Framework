package com.sandisk.zsexample;

import java.util.Arrays;

import com.sandisk.zs.ZSClient;
import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.exception.ZSException;
import com.sandisk.zs.type.WriteObjectMode;
/**
 * File:   ZSContainerExample.java
 * Author: ymiao, candy
 *
 * Created on July 30, 2013
 *
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com
 * THIS IS NOT A CONTRIBUTION
 */

/**
 * Examples about zs container operations. 
 */
public class ZSContainerExample
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
        byte[] key = "key".getBytes();
        byte[] data = "data".getBytes();
        byte[] data2 = "data2".getBytes();
        // insert data
        int ret = container.write(key, data, WriteObjectMode.ZS_WRITE_MUST_NOT_EXIST);
        assert ret == 1; // ZS_SUCCESS

        // update data
        ret = container.write(key, data2, WriteObjectMode.ZS_WRITE_MUST_EXIST);
        assert ret == 1; // ZS_SUCCESS

        // read data
        byte[] result = container.read(key);
        assert Arrays.equals(data2, result) == true;

        // delete data
        ret = container.delete(key);
        assert ret == 1; // ZS_SUCCESS;

        // flush container data change to storage if write-back mode
        container.flushToContainer();
        // close container safely
        ExampleUtil.closeContainer(container);
        // shutdown zs
        client.shutdown();
    }
}
