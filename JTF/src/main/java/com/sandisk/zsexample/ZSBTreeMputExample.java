package com.sandisk.zsexample;

import com.sandisk.zs.ZSClient;
import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.exception.ZSException;
import com.sandisk.zs.type.ZSMData;
import com.sandisk.zs.type.WriteObjectMode;

/**
 * File:   ZSBTreeMputExample.java
 * Author: ymiao, candy
 *
 * Created on July 30, 2013
 *
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com
 * THIS IS NOT A CONTRIBUTION
 */

/**
 * Examples about container mput operation.
 * 
 */
public class ZSBTreeMputExample {
    public static void main(String[] args) throws ZSException {
        // generate zs prop file with default setting
        ExampleUtil.generateZSPropFile();

        // init zs by specify prop file location
        ZSClient client = ZSClient.getInstance();
        client.init(ExampleUtil.getPropFile());

        // create a container
        ZSContainer container = ExampleUtil.createBtreeContainer("c0");

        // generate datas array to mput
        int mput_length = 10;

        ZSMData[] datas = new ZSMData[mput_length];
        for (int i = 0; i < mput_length; i++) {
            byte[] key = ("key" + i).getBytes();
            byte[] value = ("value" + i).getBytes();
            datas[i] = new ZSMData(key, value);
        }

        // default multiPut mode is ZS_WRITE_EXIST_OR_NOT
        container.multiPut(datas);

        // verify the object
        for (int i = 0; i < mput_length; i++) {
            byte[] value = container.read(("key" + i).getBytes());
            assert ("value" + i).equals(new String(value));
        }

        // update data
        ZSMData[] datas_for_update = new ZSMData[mput_length];
        for (int i = 0; i < mput_length; i++) {
            byte[] key = ("key" + i).getBytes();
            byte[] value = ("newValue" + i).getBytes();
            datas_for_update[i] = new ZSMData(key, value);
        }

        container.multiPut(datas_for_update, WriteObjectMode.ZS_WRITE_MUST_EXIST);
        // verify the object
        for (int i = 0; i < mput_length; i++) {
            byte[] value = container.read(("key" + i).getBytes());
            assert ("newValue" + i).equals(new String(value));
        }
        
        // close container safely
        ExampleUtil.closeContainer(container);
        // shutdown zs
        client.shutdown();
    }
}
