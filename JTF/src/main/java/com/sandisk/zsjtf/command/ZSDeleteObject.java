/*
 * File: ZSDeleteObject.java
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

/**
 * ZSDeleteObject command class.
 *
 * @author rchen
 *
 *         args: cguid=%u key=%d key_offset=%d key_len=%u nops=%d
 *
 *         must have: cguid
 *
 *         return: success: OK failed: SERVER_ERROR %s | CLIENT_ERROR %s
 */
public class ZSDeleteObject extends JTFCommand {
	/* Command arg list start */
	private Long containerID;
	private Integer initialIntegerKey;
	private Integer initialKeyOffset;
	private Integer keyLength;
	private Integer nops;

	private String ZSCommandExecName = "ZSDeleteObjectExec";
	/* Command arg list end */
	public ZSDeleteObject(String rawCommand) throws JTFException {
		super(rawCommand);
		// TODO Auto-generated constructor stub
		getProperties();
	}

	@Override
	public String execute() {
//		try {
//			getProperties();
//			deleteObject();
//			return handleSuccessReturn();
//		} catch (ZSException e) {
//			return handleServerErrorReturn(e);
//		} catch (JTFException e) {
//			return handleClientErrorReturn(e);
//		}
		
		String result = zsCommandExec.Execute();
		return result;
	}

	private void getProperties() throws JTFException {
		containerID = getLongProperty(CGUID, true);
		initialIntegerKey = getIntegerProperty(KEY, false);
		initialKeyOffset = getIntegerProperty(KEY_OFFSET, false,
				KEY_OFFSET_DEFAULT_VALUE);
		keyLength = getIntegerProperty(KEY_LEN, false, KEY_LEN_DEFAULT_VALUE);
		nops = getIntegerProperty(NOPS, false, NOPS_DEFAULT_VALUE);
	}

//	private void deleteObject() throws JTFException, ZSContainerException {
//		ZSContainer container = ContainerManager.getInstance().getContainer(
//				containerID);
//
//		int integerKey;
//		int keyOffset;
//
//		// byte[] key = new byte[keyLength];
//
//		// initialize the key array separately to support range query(yjiang
//		// modify)
//		byte[] key;
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
//			if (container.delete(key) != 1) {
//				throw new ZSContainerException(i + " th ZSDeleteObject failed");
//			}
//		}
//	}

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

	public Integer getNops() {
		return nops;
	}

	public void setContainerID(Long containerID) {
		this.containerID = containerID;
	}
}
