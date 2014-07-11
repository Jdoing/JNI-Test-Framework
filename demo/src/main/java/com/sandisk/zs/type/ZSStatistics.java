package com.sandisk.zs.type;

import java.util.LinkedHashMap;

/**
 * File: ZSStatistics.java 
 * Author: zane, ymiao, candy
 * 
 * Created on July 30, 2013
 * 
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com THIS IS NOT A CONTRIBUTION
 */

/**
 * Containers zs statistics from sdk.
 */
public class ZSStatistics {
	private ZSStatisticsDescribe describe = new ZSStatisticsDescribe();
	
	private long numObjs;
	private long usedSpace;
	
	/**
	 * ZS access types.
	 */
	private long[] accesses;
	/**
	 * Flash activities.
	 */
	private long[] flashStats;
	/**
	 * Cache activities.
	 */
	private long[] cacheStats;
	/**
	 * Container activities.
	 */
	private long[] cntrStats;
	/**
	 * Base-2 logarithmic histogram of key sizes in bytes.
	 */
	private ZSHistogram keySizeHisto = new ZSHistogram();
	/**
	 * Base-2 logarithmic histogram of data sizes in bytes
	 */
	private ZSHistogram dataSizeHisto = new ZSHistogram();
	/**
	 * Base-2 logarithmic histogram of access latencies in microseconds
	 */
	private ZSHistogram[] accessTimeHisto;

//	public LinkedHashMap<String, Long> getNumObjs(){
//		LinkedHashMap<String, Long> map = new LinkedHashMap<String, Long>();
//		map.put("num_objs", numObjs);
//		return map;
//	}
//	
//	public LinkedHashMap<String, Long> getUsedSpace(){
//		LinkedHashMap<String, Long> map = new LinkedHashMap<String, Long>();
//		map.put("used_space", usedSpace);
//		return map;
//	}
	
	public LinkedHashMap<String, Long> getAccesses() {
		return getStats(accesses, describe.accessDescArray);
	}

	public LinkedHashMap<String, Long> getFlashStats() {
		return getStats(flashStats, describe.flashStateDescArray);
	}

	public LinkedHashMap<String, Long> getContainerStats() {
		return getStats(cntrStats, describe.cntrDescArray);
	}

	public LinkedHashMap<String, Long> getCacheStats() {
		return getStats(cacheStats, describe.cacheStateDescArray);
	}

	private LinkedHashMap<String, Long> getStats(long[] stats, String[] stateDescArray) {
		LinkedHashMap<String, Long> map = new LinkedHashMap<String, Long>();
		for(int i =0; i < stateDescArray.length; i++){
			map.put(stateDescArray[i], stats[i]);
		}
		return map;
	}
	
	public ZSHistogram getKeySizeHisto() {
		return keySizeHisto;
	}

	public ZSHistogram getDataSizeHisto() {
		return dataSizeHisto;
	}

	public ZSHistogram[] getAccessTimeHisto() {
		return accessTimeHisto;
	}
	
}
