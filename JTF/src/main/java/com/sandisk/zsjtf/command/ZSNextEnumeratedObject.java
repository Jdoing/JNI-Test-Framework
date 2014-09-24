/*
 * File: ZSNextEnumeratedObject.java
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
 * ZSNextEnumeratedObject command class.
 *
 * @author qyu
 *
 *         args: cguid=%u
 *
 *         must have: cguid
 *
 *         return: success: OK failed: SERVER_ERROR %s | CLIENT_ERROR %s
 */
public class ZSNextEnumeratedObject extends JTFCommand {
	// Command arg list start
	private Long containerID;
	// Command arg list end

	private String ZSCommandExecName = "ZSNextEnumeratedObjectExec";

	public ZSNextEnumeratedObject(String rawCommand) throws JTFException {
		super(rawCommand);
		// TODO Auto-generated constructor stub

		getProperties();
	}

	public String execute() {
//		try {
//			getProperties();
//			String ret = "OK";
//			int count = nextEnumeratedObject();
//			ret += ("\nenumerate " + count + "objects");
//			return ret;
//		} catch (ZSContainerException e) {
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

//	private int nextEnumeratedObject() throws JTFException,
//			ZSContainerException {
//		ZSContainer container = ContainerManager.getInstance().getContainer(
//				containerID);
//
//		// do enuerate
//		int enumerator_count = 0;
//		int success_count = 0;
//		List<ZSObjectOp> list = null;
//		while ((list = container.bulkEnumNext(enumerator_count)).size() > 0) {
//			for (int i = 0; i < list.size(); i++) {
//				list.get(i).getKey();
//				list.get(i).getData();
//				assert list.get(i).getRetCode() == 1; // ZS_SUCCESS
//				success_count++;
//			}
//		}
//
//		return success_count;
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
