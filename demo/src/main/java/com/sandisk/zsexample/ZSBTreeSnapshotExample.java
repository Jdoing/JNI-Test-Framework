package com.sandisk.zsexample;

import com.sandisk.zs.ZSClient;
import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.ZSRange;
import com.sandisk.zs.exception.ZSException;
import com.sandisk.zs.type.ZSMData;
import com.sandisk.zs.type.ZSRangeFlags;
import com.sandisk.zs.type.ZSSnapshot;
import com.sandisk.zs.type.RangeData;
import com.sandisk.zs.type.RangeMeta;

/**
 * File:   ZSBTreeSnapshotExample.java
 * Author: ymiao, candy
 *
 * Created on July 30, 2013
 *
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com
 * THIS IS NOT A CONTRIBUTION
 */

/**
 * Examples about zs snapshot operations.
 * 
 */
public class ZSBTreeSnapshotExample {
    public static void main(String[] args) throws ZSException {
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

        // create snapshot
        ZSSnapshot S1 = container.createSnapshot();
        // range snapshot
        RangeMeta meta = new RangeMeta();
        meta.setStartSeq(0);
        meta.setEndSeq(S1.getSnapSeq());
        meta.setFlags(ZSRangeFlags.SEQNO_LE);
        ZSRange range = new ZSRange(meta, container.getContainerId());
        range.begin();
        RangeData[] resultDatas = range.getDatas(100);
        // verify data
        for (int i = 0; i < mput_length; i++) {
            assert ("key" + i).equals(new String(resultDatas[i].getKey()));
            assert ("value" + i).equals(new String(resultDatas[i].getData()));
        }
        range.end();

        // update key0~key4
        ZSMData[] updateDatas = new ZSMData[5];
        for (int i = 0; i < updateDatas.length; i++) {
            byte[] key = ("key" + i).getBytes();
            byte[] value = ("newValue" + i).getBytes();
            updateDatas[i] = new ZSMData(key, value);
        }
        container.multiPut(updateDatas);

        ZSSnapshot S2 = container.createSnapshot();
        // range snapshot
        RangeMeta meta2 = new RangeMeta();
        meta2.setStartSeq(S1.getSnapSeq());
        meta2.setEndSeq(S2.getSnapSeq());
        meta2.setFlags(ZSRangeFlags.SEQNO_GT_LE);
        ZSRange range2 = new ZSRange(meta2, container.getContainerId());
        range2.begin();
        RangeData[] resultDatas2 = range2.getDatas(100);
        // verify data
        assert resultDatas2.length == 5;
        for (int i = 0; i < resultDatas2.length; i++) {
            assert ("key" + i).equals(new String(resultDatas2[i].getKey()));
            assert ("newValue" + i).equals(new String(resultDatas2[i].getData()));
        }
        range2.end();

        // get all snapshots
        ZSSnapshot[] snapshots = container.getSnapshots();
        assert snapshots.length == 2;
        assert snapshots[0].getSnapSeq() == S1.getSnapSeq();
        assert snapshots[1].getSnapSeq() == S2.getSnapSeq();

        // delete snapshot
        container.deleteSnapshot(S1);
        container.deleteSnapshot(S2);
        assert container.getSnapshots().length == 0;

        // close container safely
        ExampleUtil.closeContainer(container);
        // shutdown zs
        client.shutdown();
    }
}
