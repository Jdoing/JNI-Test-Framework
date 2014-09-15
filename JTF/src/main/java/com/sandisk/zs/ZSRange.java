package com.sandisk.zs;

import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zs.exception.ZSExceptionHandler;
import com.sandisk.zs.type.RangeData;
import com.sandisk.zs.type.RangeMeta;

/**
 * File:   ZSRange.java
 * Author: zane, ymiao, candy
 *
 * Created on July 30, 2013
 *
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com
 * THIS IS NOT A CONTRIBUTION
 */

/**
 * Provides the interface to ZS range queries. The class supports range object
 * creation, iteration and termination.
 * 
 */
public class ZSRange {
	private RangeMeta meta;
	private long containerId;

	public ZSRange(long containerId) {
		this.containerId = containerId;
	}

	public ZSRange(RangeMeta meta, long containerId) {
		super();
		this.meta = meta;
		this.containerId = containerId;
	}

	/**
	 * Start range container.
	 * 
	 * @throws ZSContainerException
	 */
	public void begin() throws ZSContainerException {
		int resultCode = NativeZSRange.ZSGetRange(containerId, meta);
		ZSExceptionHandler.handleContainer(resultCode);
	}

	/**
	 * Range datas which wrapped RangeData array.
	 * 
	 * @param numItems
	 *            the number want to range.
	 * @return objects ranged
	 * @throws ZSContainerException
	 */
	public RangeData[] getDatas(int numItems) throws ZSContainerException {
		if (numItems == 0) {
			return new RangeData[0];
		}
		RangeData[] datas = NativeZSRange.ZSGetNextRange(numItems,
				meta.getZSCursor());
		/*
		 * Do a sallow copy datas to avoid to be overwrite; Due to Native
		 * ZSGetRangeNext used a object pool to improvement performance;
		 */
		RangeData[] retDatas = new RangeData[datas.length];
		try {
			for (int i = 0; i < datas.length; i++) {
				retDatas[i] = (RangeData) datas[i].clone();
			}
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return retDatas;
	}

	/**
	 * Finish range query.
	 * 
	 * @throws ZSContainerException
	 */
	public void end() throws ZSContainerException {
		int resultCode = NativeZSRange.ZSGetRangeFinish(meta.getZSCursor()
				.getCursor());
		meta.getZSCursor().setCursor(0);
		ZSExceptionHandler.handleContainer(resultCode);
	}

	/**
	 * Get range meta.
	 * 
	 * @return range meta
	 */
	public RangeMeta getMeta() {
		return meta;
	}

	/**
	 * Set range meta.
	 * 
	 * @param meta
	 *            range meta contains some properties for range query, such as
	 *            "start key", "end key"
	 */
	public void setMeta(RangeMeta meta) {
		this.meta = meta;
	}

	/**
	 * Get Container id.
	 * 
	 * @return container id
	 */
	public long getContainerId() {
		return containerId;
	}

	/**
	 * Set Container id.
	 * 
	 * @param containerId
	 */
	public void setContainerId(long containerId) {
		this.containerId = containerId;
	}
}
