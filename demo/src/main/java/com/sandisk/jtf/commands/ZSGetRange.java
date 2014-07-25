package com.sandisk.jtf.commands;

import com.sandisk.jtf.JTFCommand;
import com.sandisk.jtf.exception.JTFException;
import com.sandisk.zs.*;
import com.sandisk.zs.exception.*;
import com.sandisk.zs.type.RangeMeta;
import com.sandisk.zs.type.ZSRangeFlags;

public class ZSGetRange extends JTFCommand {

	private Long cguid;
	private Integer keybufSize;
	private Long databufSize;
	private byte[] startKey;
	private byte[] endKey;
	private Long startSeq;
	private Long endSeq;
	private Integer flags;

	public String execute() {
		try {
			getProperties();
			if ((keybufSize == null) && (databufSize == null)
					&& (startKey == null) && (endKey == null)
					&& (startSeq == null) && (endSeq == null)
					&& (flags == null)) {
				ZSRange range = new ZSRange(cguid);
				range.begin();
			} else {
				// range query part of the data
				RangeMeta meta = new RangeMeta();
				meta.setEndSeq(endSeq);
				meta.setStartSeq(startSeq);
				meta.setFlags(flags);
				meta.setStartInfo(startKey, ZSRangeFlags.END_LE);
				meta.setEndInfo(endKey, ZSRangeFlags.START_GE);
				ZSRange range2 = new ZSRange(meta, cguid);
				range2.begin();
			}
			return "OK";
		} catch (ZSContainerException e) {
			return "SERVER_ERROR:" + e.toString();
		} catch (Exception e) {
			return "CLIENT_ERROR:" + e.toString();
		}
	}

	private void getProperties() throws JTFException {
		cguid = this.getLongProperty("cguid", true);
		keybufSize = this.getIntegerProperty("keybuf_size", false);
		databufSize = this.getLongProperty("databuf_size", false);
		startKey = this.getStringProperty("start_key", false).getBytes();
		endKey = this.getStringProperty("end_key", false).getBytes();
		startSeq = this.getLongProperty("keylen_start", false);
		endSeq = this.getLongProperty("keylen_end", false);
		flags = this.getIntegerProperty("flags,", false);

	}
}
