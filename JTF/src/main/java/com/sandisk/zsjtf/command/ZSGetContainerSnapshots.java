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
import com.sandisk.zsjtf.util.ContainerManager;
import com.sandisk.zsjtf.exception.JTFException;
import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zs.exception.ZSException;

/**
 * ZSGetContainerSnapshots command class.
 *
 * @author yjiang
 *
 * args:
 *   cguid=%u
 *
 * must have: cguid
 *
 * return:
 *   success: OK
 *   failed: SERVER_ERROR %s|CLIENT_ERROR %s
 */
public class ZSGetContainerSnapshots extends JTFCommand {

	private String ZSCommandExecName = "ZSGetContainerSnapshotsExec";
	
	public ZSGetContainerSnapshots(String rawCommand) throws JTFException {
		super(rawCommand);
		// TODO Auto-generated constructor stub
		getProperties();
	}

	private Long containerID;

	@Override
	public String execute() {
		// TODO Auto-generated method stub
//		try {
//			getProperties();
//
//			ZSContainer container = ContainerManager.getInstance()
//					.getContainer(cguid);
//			container.getSnapshots();
//			return "OK";
//		} catch (ZSException e) {
//			return handleServerErrorReturn(e);
//		} catch (JTFException e) {
//			return handleClientErrorReturn(e);
//		}
		String result = zsCommandExec.Execute();
		return result;
		
	}

	private void getProperties() throws JTFException {
		containerID = this.getLongProperty(CGUID, true);
	}

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

}
