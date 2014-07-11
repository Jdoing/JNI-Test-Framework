package com.sandisk.zsexample;

import java.util.Arrays;

import com.sandisk.zs.ZSClient;
import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.exception.ZSException;
import com.sandisk.zs.type.ZSObjectOp;
import com.sandisk.zs.type.ZSObjectOpType;

/**
 * File:   ZSBulkOptExample.java
 * Author: ymiao, candy
 *
 * Created on July 30, 2013
 *
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com
 * THIS IS NOT A CONTRIBUTION
 */
/**
 * Examples about zs bulk operations. 
 * 
 */
public class ZSBulkOptExample
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

        // a bulk operations
        ZSObjectOp[] ops = new ZSObjectOp[4];
        ops[0] = new ZSObjectOp(ZSObjectOpType.ADD, "key".getBytes(), "data".getBytes());
        ops[1] = new ZSObjectOp(ZSObjectOpType.REPLACE, "key".getBytes(), "data2".getBytes());
        ops[2] = new ZSObjectOp(ZSObjectOpType.GET, "key".getBytes());
        ops[3] = new ZSObjectOp(ZSObjectOpType.REMOVE, "key".getBytes());

        container.bulkOp(Arrays.asList(ops));

        assert ops[0].getRetCode() == 1;
        assert ops[1].getRetCode() == 1;
        assert ops[2].getRetCode() == 1;
        assert Arrays.equals("data2".getBytes(), ops[2].getData());
        assert ops[3].getRetCode() == 1;
        
        // close container safely
        ExampleUtil.closeContainer(container);
        // shutdown zs
        client.shutdown();
    }
}
