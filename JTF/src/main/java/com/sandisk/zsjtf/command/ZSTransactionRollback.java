/*
 * File: ZSWriteObject.java
 * Author: qyu, rchen, yjiang
 *
 * SanDisk Proprietary Material, © Copyright 2014 SanDisk, all rights reserved.
 * http://www.sandisk.com
 * THIS IS NOT A CONTRIBUTION.
 */
package com.sandisk.zsjtf.command;

import com.sandisk.zs.*;
import com.sandisk.zs.exception.*;
import com.sandisk.zsjtf.JTFCommand;
import com.sandisk.zsjtf.exception.JTFException;
import com.sandisk.zsjtf.exec.ZSTransactionExec;

/**
 * ZSTransactionRollback command class.
 *
 * @author yjiang
 *
 *         args:
 *
 *         must have:
 *
 *         return: success: OK failed: SERVER_ERROR %s|CLIENT_ERROR %s
 */

public class ZSTransactionRollback extends JTFCommand {
	private String ZSCommandExecName = "ZSTransactionExec";

	public ZSTransactionRollback(String rawCommand) {
		super(rawCommand);
		// TODO Auto-generated constructor stub
	}

	public String execute() {
		// try{
		// ZSTransaction t = new ZSTransaction();
		// t.rollback();
		// return "OK";
		// }
		// catch(ZSException e){
		// return "server fail"+e.toString();
		// }
		// catch(Exception e){
		// return "client fail"+e.toString();
		// }

		ZSTransactionExec zsTransactionExec;

		if (zsCommandExec instanceof ZSTransactionExec) {
			zsTransactionExec = (ZSTransactionExec) zsCommandExec;
		} else {
			return "CLIENT_ERROR: zsCommandExec can not be cast to ZSTransactionExec";
		}

		String result = zsTransactionExec.rollback();
		return result;
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
		ZSTransaction zsTransaction = new ZSTransaction();
		return zsTransaction;
	}
}
