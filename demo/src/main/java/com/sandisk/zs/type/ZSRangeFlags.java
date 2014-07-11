package com.sandisk.zs.type;

/**
 * File: ZSRangeFlags.java 
 * Author: zane, ymiao, candy
 * 
 * Created on July 30, 2013
 * 
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com THIS IS NOT A CONTRIBUTION
 */

/**
 *  Contains flags for range query.
 */
public class ZSRangeFlags {
	public static final int ZS_RANGE_BUFFER_PROVIDED = 1 << 0; // buffers for keys and data provided by application
	public static final int ZS_RANGE_ALLOC_IF_TOO_SMALL = 1 << 1; // if supplied buffers are too small,ZS will allocate
	public static final int SEQNO_LE = 1 << 5; // only return objects with seqno <= end_seq
	public static final int SEQNO_GT_LE = 1 << 6; // only return objects with start_seq < seqno <= end_seq

	public static final int START_GT = 1 << 7; // keys must be > key_start
	public static final int START_GE = 1 << 8; // keys must be >= key_start
	public static final int START_LT = 1 << 9; // keys must be < key_start
	public static final int START_LE = 1 << 10; // keys must be <= key_start

	public static final int END_GT = 1 << 11; // keys must be > key_end
	public static final int END_GE = 1 << 12; // keys must be >= key_end
	public static final int END_LT = 1 << 13; // keys must be < key_end
	public static final int END_LE = 1 << 14; // keys must be <= key_end

	public static final int KEYS_ONLY = 1 << 15; // only return keys (data is not required)

	public static final int PRIMARY_KEY = 1 << 16; // return primary keys in secondary index query
}
