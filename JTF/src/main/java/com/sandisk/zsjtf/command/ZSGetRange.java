/*
 * File: ZSWriteObject.java
 * Author: qyu, rchen, yjiang
 *
 * SanDisk Proprietary Material, © Copyright 2014 SanDisk, all rights reserved.
 * http://www.sandisk.com
 * THIS IS NOT A CONTRIBUTION.
 */
package com.sandisk.zsjtf.command;

import java.util.Properties;

import com.sandisk.zsjtf.JTFCommand;
import com.sandisk.zsjtf.exception.JTFException;
import com.sandisk.zsjtf.exec.ZSGetRangeExec;
import com.sandisk.zs.*;
import com.sandisk.zs.exception.*;
import com.sandisk.zs.type.RangeMeta;
import com.sandisk.zs.type.ZSRangeFlags;
import com.sandisk.zsjtf.util.Log;

/**
 * ZSGetRange command class.
 *
 * @author yjiang
 *
 *         args: cguid=%u keybuf_size=%u databuf_size=%u start_key=%d end_key=%d
 *         keylen_start=%u keylen_end=%u flags=%u
 *
 *         must have: cguid return: success: OK failed: SERVER_ERROR
 *         %s|CLIENT_ERROR %s
 */
public class ZSGetRange extends JTFCommand {

	public ZSGetRange(String rawCommand) throws JTFException {
		super(rawCommand);
		// TODO Auto-generated constructor stub

		getProperties();

	}

	private static Long cguid = 0L;
	private Integer keybufSize = 0;
	private Long databufSize = 0L;
	private Integer keyLenStart = 0;
	private Integer KeyLenEnd = 0;
	private Integer startKey = 0;
	private Integer endKey = 0;
	private Long startSeq = 0L;
	private Long endSeq = 0L;
	private Integer flags = 0;

	private String ZSAdapterName = "ZSRangeAdapter";
	private String ZSCommandExecName = "ZSGetRangeExec";

	public String execute() {

		return this.zsCommandExec.Execute();
	}

	public String getZSAdapterName() {
		return ZSAdapterName;
	}

	public void setZSAdapterName(String ZSAdapterName) {
		this.ZSAdapterName = ZSAdapterName;
	}

	public int getKeybufSize() {
		return keybufSize;
	}

	public long getDatabufSize() {
		return databufSize;
	}

	public int getKeyLenStart() {
		return keyLenStart;
	}

	public int getKeyLenEnd() {
		return KeyLenEnd;
	}

	public int getStartKey() {
		return startKey;
	}

	public int getEndKey() {
		return endKey;
	}

	public long getStartSeq() {
		return startSeq;
	}

	public long getEndSeq() {
		return endSeq;
	}

	public int getFlags() {
		return flags;
	}

	public static Long getCguid() {
		return cguid;
	}

	@Override
	public String getZSCommandExecName() {
		// TODO Auto-generated method stub
		return ZSCommandExecName;
	}

	// public String execute() {
	// try {
	// Log.logDebug("Start to getProperties:");
	// getProperties();
	// Log.logDebug("getProperties finish!");
	// if ((keybufSize == 0) && (databufSize == 0) && (startKey == 0)
	// && (endKey == 0) && (startSeq == 0) && (endSeq == 0)
	// && (flags == 0)) {
	// Log.logDebug("Initialize the ZSRange without meta paremeter.");
	// ZSRange range = new ZSRange(cguid);
	// range.begin();
	// Log.logDebug("GetRange finish!");
	//
	// } else {
	// // range query part of the data
	// Log.logDebug("Ready to GetRange");
	// RangeMeta meta = new RangeMeta();
	// setMeta(meta);
	// ZSRange range2 = new ZSRange(meta, cguid);
	//
	// Log.logDebug("Start to GetRange");
	// range2.begin();
	// Log.logDebug("GetRange finish");
	// }
	//
	// return "OK";
	// } catch (JTFException e) {
	// return "CLIENT_ERROR:" + e.toString();
	// } catch (Exception e) {
	// return "SERVER_ERROR ZS_FAILURE:" + e.toString();
	// }
	// }

	// private void setMeta(RangeMeta rmeta) throws Exception {
	// if (flags == 0) {
	// flags = ZSRangeFlags.START_GT | ZSRangeFlags.END_LE;
	// }
	//
	// keybufSize = (keybufSize != 0) ? keybufSize : MAX_KEY_LEN;
	// databufSize = (databufSize != 0) ? databufSize : MAX_DATA_LEN;
	// keyLenStart = (keyLenStart != 0) ? keyLenStart : 8;
	// KeyLenEnd = (KeyLenEnd != 0) ? KeyLenEnd : 8;
	// startSeq = (startSeq != 0) ? startSeq : 0;
	// endSeq = (endSeq != 0) ? endSeq : 0;
	//
	// String formatter = String.format("%%0%dd", keyLenStart);
	// String keyStart = String.format(formatter, startKey);
	//
	// String fmt = String.format("%%0%dd", KeyLenEnd);
	// String keyEnd = String.format(fmt, endKey);
	//
	// rmeta.setFlags(flags);
	// rmeta.setStartInfo(keyStart.getBytes(), flags);
	// rmeta.setEndInfo(keyEnd.getBytes(), flags);
	// rmeta.setStartSeq(startSeq);
	// rmeta.setEndSeq(endSeq);
	// }

	private void getProperties() throws JTFException {
		Log.logDebug("Get guid");
		cguid = this.getLongProperty(CGUID, true);

		Log.logDebug("get keybufSize");
		keybufSize = getIntegerProperty(KEYBUF_SIZE, false);

		Log.logDebug("get databufSize");
		databufSize = this.getLongProperty(DATABUF_SIZE, false);

		Log.logDebug("get startKey");
		startKey = this.getIntegerProperty(START_KEY, false);

		Log.logDebug("get endKey");
		endKey = this.getIntegerProperty(END_KEY, false);

		Log.logDebug("get keyLenStart");
		keyLenStart = this.getIntegerProperty(KEYLEN_START, false);

		Log.logDebug("get KeyLenEnd");
		KeyLenEnd = this.getIntegerProperty(KEYLEN_END, false);

		 Log.logDebug("get startSeq");
		 startSeq = this.getLongProperty(START_SEQ, false);
		
		 Log.logDebug("get endSeq");
		 endSeq = this.getLongProperty(END_SEQ, false);

		Log.logDebug("Get flags");
		// flags = this.getIntegerProperty(FLAGS, false,FLAGS_DEFAULT_VALUE);
		String flagsTemp = this.getStringProperty(FLAGS, false);

		flags = getFlags(flagsTemp);
	}

	private int getFlags(String flagsTemp) throws JTFException {
		if (flagsTemp == null)
			return 0;

		String[] flagsArray = flagsTemp.split("\\|");

		for (String flag : flagsArray) {
			if (flag.equals("ZS_RANGE_BUFFER_PROVIDED")) {
				flags |= ZSRangeFlags.ZS_RANGE_BUFFER_PROVIDED;
			} else if (flag.equals("ZS_RANGE_ALLOC_IF_TOO_SMALL")) {
				flags |= ZSRangeFlags.ZS_RANGE_ALLOC_IF_TOO_SMALL;
			} else if (flag.equals("ZS_RANGE_SEQNO_LE")) {
				flags |= ZSRangeFlags.SEQNO_LE;
			} else if (flag.equals("ZS_RANGE_SEQNO_GT_LE")) {
				flags |= ZSRangeFlags.SEQNO_GT_LE;
			} else if (flag.equals("ZS_RANGE_START_GT")) {
				flags |= ZSRangeFlags.START_GT;
			} else if (flag.equals("ZS_RANGE_START_GE")) {
				flags |= ZSRangeFlags.START_GE;
			} else if (flag.equals("ZS_RANGE_START_LT")) {
				flags |= ZSRangeFlags.START_LT;
			} else if (flag.equals("ZS_RANGE_START_LE")) {
				flags |= ZSRangeFlags.START_LE;
			} else if (flag.equals("ZS_RANGE_END_GT")) {
				flags |= ZSRangeFlags.END_GT;
			} else if (flag.equals("ZS_RANGE_END_GE")) {
				flags |= ZSRangeFlags.END_GE;
			} else if (flag.equals("ZS_RANGE_END_LT")) {
				flags |= ZSRangeFlags.END_LT;
			} else if (flag.equals("ZS_RANGE_END_LE")) {
				flags |= ZSRangeFlags.END_LE;
			} else if (flag.equals("ZS_RANGE_KEYS_ONLY")) {
				flags |= ZSRangeFlags.KEYS_ONLY;
			} else if (flag.equals("ZS_RANGE_PRIMARY_KEY")) {
				flags |= ZSRangeFlags.PRIMARY_KEY;
			}
			// else if(flag.equals("ZS_RANGE_INDEX_USES_DATA")){
			// flags |=ZS_RANGE_INDEX_USES_DATA;
			// }
			// else if(flag.equals("ZS_RANGE_INPLACE_POINTERS")){
			// flags |=ZS_RANGE_INPLACE_POINTERS;
			// }
			else {
				throw new JTFException(
						"The argument of flags can't match ZSRangeFlags!");
			}
		}

		return flags;
	}

}
