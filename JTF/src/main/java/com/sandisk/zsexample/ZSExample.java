package com.sandisk.zsexample;

import java.util.List;

import com.sandisk.zs.ZSClient;
import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.exception.ZSException;
import com.sandisk.zs.type.ContainerProperty;
/**
 * File:   ZSExample.java
 * Author: ymiao, candy
 *
 * Created on July 30, 2013
 *
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com
 * THIS IS NOT A CONTRIBUTION
 */
/**
 * Examples about zs operations like init/flush/shutdown
 * 
 */
public class ZSExample
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

        // get containers ids
        long[] cguids = client.getContainers();
        assert cguids.length == 1;

        // get detailed containers properties like container name
        List<ContainerProperty> list = client.getContainersPropertiesList();
        for (int i = 0; i < list.size(); i++)
        {
            ContainerProperty cp = list.get(i);
            System.out.println(cp.getContainerName());
        }

        // flush cache if write back container exist
        client.flushCache();

        // cleanup container if data are not useful any more
        ExampleUtil.cleanContainer(container);

        // shutdown zs
        client.shutdown();
    }
}
