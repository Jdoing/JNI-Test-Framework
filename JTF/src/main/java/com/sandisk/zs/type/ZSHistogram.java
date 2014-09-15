package com.sandisk.zs.type;

/**
 * File: ZSHistogram.java 
 * Author: zane, ymiao, candy
 * 
 * Created on July 30, 2013
 * 
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com THIS IS NOT A CONTRIBUTION
 */

/**
 * ZS histogram properties.
 */
public class ZSHistogram {
	/**
	 * Number of events.
	 */
	private long n;
	/**
	 * Minimum value.
	 */
	private long min;
	/**
	 * Maximum value.
	 */
	private long max;
	/**
	 * Average value.
	 */
	private double avg;
	/**
	 * Geometric mean
	 */
	private double geo;
	/**
	 * Standard deviation.
	 */
	private double std;
	/**
	 * Counts of the Base-2 logarithm of the event values.
	 */
	private long[] counts;
	public long getN() {
		return n;
	}
	public ZSHistogram() {
		super();
	}
	public long getMin() {
		return min;
	}
	public long getMax() {
		return max;
	}
	public double getAvg() {
		return avg;
	}
	public double getGeo() {
		return geo;
	}
	public double getStd() {
		return std;
	}
	public long[] getCounts() {
		return counts;
	}
	
}
