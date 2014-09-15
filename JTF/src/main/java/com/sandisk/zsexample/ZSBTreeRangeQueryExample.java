package com.sandisk.zsexample;

import com.sandisk.zs.ZSClient;
import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.ZSRange;
import com.sandisk.zs.type.ZSMData;
import com.sandisk.zs.type.ZSRangeFlags;
import com.sandisk.zs.type.RangeData;
import com.sandisk.zs.type.RangeMeta;

/**
 * File:   ZSBTreeRangeQueryExample.java
 * Author: ymiao, candy
 *
 * Created on July 30, 2013
 *
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com
 * THIS IS NOT A CONTRIBUTION
 */

/**
 * Examples about zs range query operation.
 * 
 */
public class ZSBTreeRangeQueryExample {
    public static void main(String[] args) throws Exception {
        // generate zs prop file with default setting
        ExampleUtil.generateZSPropFile();

        // init zs by specify prop file location
        ZSClient client = ZSClient.getInstance();
        client.init(ExampleUtil.getPropFile());

        // create a container
        ZSContainer container = ExampleUtil.createBtreeContainer("c0");

        // prepare data using mput
        int mput_length = 10;

        ZSMData[] datas = new ZSMData[mput_length];
        for (int i = 0; i < mput_length; i++) {
            byte[] key = ("key" + i).getBytes();
            byte[] value = ("value" + i).getBytes();
            datas[i] = new ZSMData(key, value);
        }
        container.multiPut(datas);

        // range query all data
        ZSRange range = new ZSRange(container.getContainerId());
        range.begin();
        RangeData[] resultDatas = range.getDatas(mput_length);
        assert resultDatas.length == mput_length;
        // verify data
        for (int i = 0; i < mput_length; i++) {
            assert ("key" + i).equals(new String(resultDatas[i].getKey()));
            assert ("value" + i).equals(new String(resultDatas[i].getData()));
        }
        RangeData[] result_next = range.getDatas(mput_length);
        assert result_next.length == 0;
        range.end();

        // range query part of the data
        RangeMeta meta = new RangeMeta();
        meta.setStartInfo("key2".getBytes(), ZSRangeFlags.END_LE);
        meta.setEndInfo("key5".getBytes(), ZSRangeFlags.START_GE);
        ZSRange range2 = new ZSRange(meta, container.getContainerId());
        range2.begin();
        RangeData[] resultDatas2 = range2.getDatas(mput_length);
        assert resultDatas2.length == 4;
        // verify data
        for (int i = 2; i < resultDatas2.length + 2; i++) {
            assert ("key" + i).equals(new String(resultDatas2[i - 2].getKey()));
            assert ("value" + i).equals(new String(resultDatas2[i - 2].getData()));
        }
        range2.end();

        // close container safely
        ExampleUtil.closeContainer(container);
        // shutdown zs
        client.shutdown();
    }
}
