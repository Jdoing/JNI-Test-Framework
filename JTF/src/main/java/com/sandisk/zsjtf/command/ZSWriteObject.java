/*
 * File: ZSWriteObject.java
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
import com.sandisk.zsjtf.util.MiscUtils;
import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zs.exception.ZSException;
import com.sandisk.zs.type.WriteObjectMode;

/**
 * ZSWriteObject command class.
 *
 * @author rchen
 *
 *         args: cguid=%u key=%d key_offset=%d key_len=%u data_offset=%d
 *         data_len=%d nops=%d flags=ZS_WRITE_MUST_NOT_EXIST|ZS_WRITE_MUST_EXIST
 *
 *         must have: cguid
 *
 *         return: success: OK failed: SERVER_ERROR %s|CLIENT_ERROR %s
 */
public class ZSWriteObject extends JTFCommand {
	/* Command arg list start */
	private Long containerID;
	private Integer initialIntegerKey;
	private Integer initialKeyOffset;
	private Integer keyLength;
	private Integer initialDataOffset;
	private Integer dataLength;
	private Integer nops;
	private String flags;
	/* Command arg list end */

	private WriteObjectMode writeObjectMode;

	private String ZSWriteObjectExecName = "ZSWriteObjectExec";

	public ZSWriteObject(String rawCommand) throws JTFException {
		super(rawCommand);
		// TODO Auto-generated constructor stub

		getProperties();
		setWriteObjectMode();

	}

	@Override
	public String execute() {
		// try {
		// getProperties();
		// getWriteObjectMode();
		// writeObject();
		// return handleSuccessReturn();
		// } catch (ZSException e) {
		// return handleServerErrorReturn(e);
		// } catch (JTFException e) {
		// return handleClientErrorReturn(e);
		// }

		String result = zsCommandExec.Execute();
		return result;
	}

	private void getProperties() throws JTFException {
		containerID = getLongProperty(CGUID, true);
		initialIntegerKey = getIntegerProperty(KEY, false);
		initialKeyOffset = getIntegerProperty(KEY_OFFSET, false,
				KEY_OFFSET_DEFAULT_VALUE);
		keyLength = getIntegerProperty(KEY_LEN, false, KEY_LEN_DEFAULT_VALUE);
		initialDataOffset = getIntegerProperty(DATA_OFFSET, false,
				DATA_OFFSET_DEFAULT_VALUE);
		dataLength = getIntegerProperty(DATA_LEN, false,
				DATA_OFFSET_DEFAULT_VALUE);
		nops = getIntegerProperty(NOPS, false, NOPS_DEFAULT_VALUE);
		flags = getStringProperty(FLAGS, false, ZS_WRITE_DEFAULT_VALUE);

		/*
		 * Here for compatible with data verify rule of ZSGetNextRange, if key
		 * arg is set, dataOffset should be equal with key.
		 */
		if (initialIntegerKey != null
				&& !initialIntegerKey.equals(initialDataOffset)) {
			throw new JTFException("key and data_offset not equal");
		}

		if (initialKeyOffset < 0 || keyLength < 0 || keyLength > MAX_KEY_LEN) {
			throw new JTFException("CLIENT_ERROR, related key value is wrong!");
		}

		if (initialDataOffset < 0 || dataLength < 0
				|| dataLength > MAX_DATA_LEN) {
			throw new JTFException("CLIENT_ERROR, related data value is wrong!");
		}
	}

	//
	// private void writeObject() throws JTFException, ZSContainerException
	// {
	// ZSContainer container =
	// ContainerManager.getInstance().getContainer(containerID);
	//
	// int integerKey;
	// int keyOffset;
	// int dataOffset;
	//
	// //byte[] key = new byte[keyLength];
	// byte[] key;
	// byte[] data = new byte[dataLength];
	//
	// for (int i = 0; i < nops; ++i) {
	// if (initialIntegerKey == null) {
	// key = new byte[keyLength];
	// keyOffset = initialKeyOffset + i;
	// /* Decode key by offset and length */
	// ByteBuffManager.getInstance().arraycopy(key, keyOffset, keyLength);
	// } else {
	// key = new byte[MAX_KEY_LEN];
	// integerKey = initialIntegerKey + i;
	// /* Decode key by integer key and length*/
	// MiscUtils.decodeIntegerKey(key, integerKey, keyLength);
	// }
	//
	// dataOffset = initialDataOffset + i;
	// ByteBuffManager.getInstance().arraycopy(data, dataOffset, dataLength);
	//
	// if (container.write(key, data, writeObjectMode) != 1) {
	// throw new ZSContainerException(i + " th ZSWriteObject failed");
	// }
	// }
	// }

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

	@Override
	public String getZSCommandExecName() {
		// TODO Auto-generated method stub
		return ZSWriteObjectExecName;
	}

	@Override
	public Object getZSEntry() throws ZSContainerException, JTFException,
			Exception {
		// TODO Auto-generated method stub
		ZSContainer container = ContainerManager.getInstance().getContainer(
				containerID);
		return container;
	}

	public Long getContainerID() {
		return containerID;
	}

	public Integer getInitialIntegerKey() {
		return initialIntegerKey;
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

	public Integer getNops() {
		return nops;
	}

	public String getFlags() {
		return flags;
	}

	public String getZSWriteObjectExecName() {
		return ZSWriteObjectExecName;
	}

	public WriteObjectMode getWriteObjectMode() {
		// TODO Auto-generated method stub
		return writeObjectMode;
	}
}
