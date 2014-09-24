/*
 * File: ZSReadObject.java
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
import com.sandisk.zsjtf.util.Log;

/**
 * ZSReadObject command class.
 *
 * @author rchen
 *
 *         args: cguid=%u key=%d key_offset=%d key_len=%u data_offset=%d
 *         data_len=%d nops=%d check=yes|no keep_read=yes|no
 *
 *         must have: cguid
 *
 *         return: success: OK failed: SERVER_ERROR %s | CLIENT_ERROR %s
 */
public class ZSReadObject extends JTFCommand {

	/* Command arg list start */
	private Long containerID;
	/*
	 * Integer key for compatible with range query. If this arg is set, use the
	 * key; else use keyOffset.
	 */
	private Integer initialIntegerKey;
	private Integer initialKeyOffset; // Specify key start pos.
	private Integer keyLength;
	private Integer initialDataOffset; // Specify verify data start pos.
	private Integer dataLength;
	private Integer nops; // Specify read operation numbers.
	private Boolean check; // Specify if we should verify data after read ops
							// finish.
	private Boolean keepRead; // Specify if command should discontinued when
								// read error happens.
	/* Command arg list end */

//	private int readFailedCnt = 0;
//	private int verifyFailedCnt = 0;
	
	private String ZSReadObjectExecName = "ZSReadObjectExec";
	
	public ZSReadObject(String rawCommand) throws JTFException {
		super(rawCommand);
		// TODO Auto-generated constructor stub
		
		getProperties();
		
	}

	@Override
	public String execute() {
//		try {
//
//			Log.logDebug("Start execute->:");
//			getProperties();
//			readObject();
//			return handleSuccessReturn();
//		} catch (ZSException e) {
//			return handleServerErrorReturn(e);
//		} catch (JTFException e) {
//			return handleClientErrorReturn(e);
//		} catch (NullPointerException e) {
//			Log.logDebug("Pointer is null!");
//			return handleClientErrorReturn(e);
//		}
		
		String result = zsCommandExec.Execute();
		return result;
	}

	private void getProperties() throws JTFException {
		Log.logDebug("Start getProperties->:");
		containerID = getLongProperty(CGUID, true);

		initialIntegerKey = getIntegerProperty(KEY, false);

		initialKeyOffset = getIntegerProperty(KEY_OFFSET, false,
				KEY_OFFSET_DEFAULT_VALUE);

		keyLength = getIntegerProperty(KEY_LEN, false, KEY_LEN_DEFAULT_VALUE);

		initialDataOffset = getIntegerProperty(DATA_OFFSET, false,
				DATA_OFFSET_DEFAULT_VALUE);

		dataLength = getIntegerProperty(DATA_LEN, false, DATA_LEN_DEFAULT_VALUE);

		nops = getIntegerProperty(NOPS, false, NOPS_DEFAULT_VALUE);

		check = getBooleanProperty(CHECK, false, CHECK_DEFAULT_VALUE);

		keepRead = getBooleanProperty(KEEP_READ, false, KEEP_READ_DEFAULT_VALUE);
	}

//	private void readObject() throws JTFException, ZSContainerException {
//		Log.logDebug("Start readObject->:" + String.valueOf(initialKeyOffset));
//		ZSContainer container = ContainerManager.getInstance().getContainer(
//				containerID);
//
//		int integerKey;
//		int keyOffset;
//		int dataOffset;
//
//		byte[] result = new byte[0];
//		// byte[] key = new byte[keyLength];
//		// initialize the key array separately to support range query(yjiang
//		// modify)
//		byte[] key;
//		byte[] data = new byte[dataLength];
//		Log.logDebug("Start readObject->initialKeyOffset is:"
//				+ String.valueOf(initialKeyOffset));
//		for (int i = 0; i < nops; ++i) {
//			if (initialIntegerKey == null) {
//				key = new byte[keyLength];
//				keyOffset = initialKeyOffset + i;
//				ByteBuffManager.getInstance().arraycopy(key, keyOffset,
//						keyLength);
//			} else {
//				key = new byte[MAX_KEY_LEN];
//				integerKey = initialIntegerKey + i;
//				MiscUtils.decodeIntegerKey(key, integerKey, keyLength);
//			}
//
//			try {
//				result = container.read(key);
//			} catch (ZSContainerException e) {
//				if (keepRead) {
//					readFailedCnt++;
//				} else {
//					throw new ZSContainerException(i
//							+ " th ZSReadObject failed");
//				}
//			}
//
//			if (check) {
//				dataOffset = initialDataOffset + i;
//				ByteBuffManager.getInstance().arraycopy(data, dataOffset,
//						dataLength);
//				if (!Arrays.equals(data, result)) {
//					verifyFailedCnt++;
//				}
//			}
//		}
//	}

//	@Override
//	protected String handleSuccessReturn() throws ZSException {
//		if (readFailedCnt == 0 && verifyFailedCnt == 0) {
//			return "OK";
//		} else {
//			String msg = readFailedCnt + " items read failed "
//					+ verifyFailedCnt + " items check failed";
//			throw new ZSException(msg);
//		}
//	}

	@Override
	public String getZSCommandExecName() {
		// TODO Auto-generated method stub
		return ZSReadObjectExecName;
	}

	@Override
	public Object getZSEntry() throws ZSContainerException, JTFException,
			Exception {
		// TODO Auto-generated method stub
		
		ZSContainer container = ContainerManager.getInstance().getContainer(
				containerID);
		return container;
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

	public Boolean getCheck() {
		return check;
	}

	public Boolean getKeepRead() {
		return keepRead;
	}

}
