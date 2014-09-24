/*
 * File: ZSFlushContainer.java
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

/**
 * ZSFlushContainer command class.
 *
 * @author qyu
 *
 *         args: cguid=%u
 *
 *         must have: cguid
 *
 *         return: success: OK failed: SERVER_ERROR %s | CLIENT_ERROR %s
 */
public class ZSFlushContainer extends JTFCommand {

	// Command arg list start
	private Long containerID;

	// Command arg list end
	
	private String ZSCommandExecName = "ZSFlushContainerExec";
	
	public ZSFlushContainer(String rawCommand) throws JTFException {
		super(rawCommand);
		// TODO Auto-generated constructor stub
		getProperties();
	}

	public String execute() {
//		try {
//			getProperties();
//			flushContainer();
//			return "OK";
//		} catch (ZSException e) {
//			return "SERVER_ERROR " + e.toString();
//		} catch (JTFException e) {
//			return "CLIENT_ERROR " + e.toString();
//		}
		
		String result = zsCommandExec.Execute();
		return result;
	}

	private void getProperties() throws JTFException {
		containerID = getLongProperty(CGUID, true);
	}

//	private void flushContainer() throws JTFException, ZSException {
//		ZSContainer container = ContainerManager.getInstance().getContainer(
//				containerID);

		// exception handled in ZSContainer
//		container.flushToContainer();
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
}
