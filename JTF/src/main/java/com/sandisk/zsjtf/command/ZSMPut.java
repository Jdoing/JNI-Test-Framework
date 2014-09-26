/*
 * File: ZSMPut.java
 * Author: qyu, rchen, yjiang
 *
 * SanDisk Proprietary Material, © Copyright 2014 SanDisk, all rights reserved.
 * http://www.sandisk.com
 * THIS IS NOT A CONTRIBUTION.
 */
package com.sandisk.zsjtf.command;

import com.sandisk.zsjtf.JTFCommand;
import com.sandisk.zsjtf.exception.JTFException;
import com.sandisk.zsjtf.util.ByteBuffManager;
import com.sandisk.zsjtf.util.ContainerManager;
import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zs.type.WriteObjectMode;
import com.sandisk.zs.type.ZSMData;

/**
 * ZSMPut command class.
 *
 * @author qyu
 *
 *         args: cguid=%u key_offset=%d key_len=%u data_offset=%d data_len=%d
 *         flags=FDF_WRITE_MUST_NOT_EXIST | FDF_WRITE_MUST_EXIST num_objs=%d
 * 
 *         must have: cguid
 *
 *         return: success: OK failed: SERVER_ERROR %s | CLIENT_ERROR %s
 */
public class ZSMPut extends JTFCommand {
	private String ZSCommandExecName = "ZSMPutExec";

	// Command arg list start
	private Long containerID;

	private Integer initialKeyOffset = 0;
	private Integer keyLength = 0;

	private Integer initialDataOffset = 0;
	private Integer dataLength = 0;

	private String flags;

	private Integer num_objs;
	// Command arg list end

	private WriteObjectMode writeObjectMode = null;

	public ZSMPut(String rawCommand) throws JTFException {
		super(rawCommand);
		// TODO Auto-generated constructor stub
		getProperties();
		setWriteObjectMode();
	}

	public String execute() {
		// try {
		// getProperties();
		// getWriteObjectMode();
		// mPut();
		// return "OK";
		// } catch (ZSContainerException e) {
		// return "SERVER_ERROR " + e.toString();
		// } catch (JTFException e) {
		// return "CLIENT_ERROR " + e.toString();
		// }
		String result = zsCommandExec.Execute();
		return result;

	}

	
	//ZSMPut data_offset=0 key_len=15 flags=ZS_WRITE_MUST_NOT_EXIST cguid=5 data_len=10 num_objs=10000 key_offset=0
	private void getProperties() throws JTFException {
		containerID = getLongProperty(CGUID, true);
		initialKeyOffset = getIntegerProperty(KEY_OFFSET, false);
		keyLength = getIntegerProperty(KEY_LEN, false);
		initialDataOffset = getIntegerProperty(DATA_OFFSET, false);
		dataLength = getIntegerProperty(DATA_LEN, false);
		flags = getStringProperty(FLAGS, false, "ZS_WRITE_EXIST_OR_NOT");
		num_objs = getIntegerProperty(NUM_OBJS, false, 0);
	}

	private void setWriteObjectMode() throws JTFException {
		if (flags.equals("ZS_WRITE_MUST_NOT_EXIST")) {
			writeObjectMode = WriteObjectMode.ZS_WRITE_MUST_NOT_EXIST;
		} else if (flags.equals("ZS_WRITE_MUST_EXIST")) {
			writeObjectMode = WriteObjectMode.ZS_WRITE_MUST_EXIST;
		} else if (flags.equals("ZS_WRITE_EXIST_OR_NOT")) {
			writeObjectMode = WriteObjectMode.ZS_WRITE_EXIST_OR_NOT;
		} else {
			throw new JTFException("write object mode not recognized");
		}
	}

	// private void mPut() throws JTFException, ZSContainerException {
	// ZSContainer container = ContainerManager.getInstance().getContainer(
	// containerID);
	//
	// int keyOffset;
	// int dataOffset;
	//
	// // int objs_written; //how many objs are written.
	//
	// ZSMData[] datas = new ZSMData[num_objs];
	// byte[] key = new byte[keyLength];
	// byte[] data = new byte[dataLength];
	//
	// for (int i = 0; i < num_objs; i++) {
	// keyOffset = initialKeyOffset + i;
	// ByteBuffManager.getInstance().arraycopy(key, keyOffset, keyLength);
	//
	// dataOffset = initialDataOffset + i;
	// ByteBuffManager.getInstance().arraycopy(data, dataOffset,
	// dataLength);
	//
	// datas[i] = new ZSMData(key, data);
	// }
	//
	// // exception handled by ZSContainer
	// container.multiPut(datas, writeObjectMode); // default multiPut mode is
	// // ZS_WRITE_EXIST_OR_NOT
	//
	// // no need to return objs_written
	// }

	@Override
	public String getZSCommandExecName() {
		// TODO Auto-generated method stub
		return ZSCommandExecName;
	}

	@Override
	public Object getZSEntry() throws ZSContainerException, JTFException,
			Exception {
		// TODO Auto-generated method stub
		ZSContainer container = ContainerManager.getInstance().getContainer(
				containerID);
		return container;
	}

	public Integer getInitialKeyOffset() {
		return initialKeyOffset;
	}

	public Integer getKeyLength() {
		return keyLength;
	}

	public Integer getInitialDataOffset() {
		return initialDataOffset;
	}

	public Integer getDataLength() {
		return dataLength;
	}

	public String getFlags() {
		return flags;
	}

	public Integer getNum_objs() {
		return num_objs;
	}

	public WriteObjectMode getWriteObjectMode() {
		return writeObjectMode;
	}
}
