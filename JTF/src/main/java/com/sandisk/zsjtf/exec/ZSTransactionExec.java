package com.sandisk.zsjtf.exec;

import com.sandisk.zs.ZSTransaction;
import com.sandisk.zs.exception.ZSException;
import com.sandisk.zsjtf.JTFCommand;
import com.sandisk.zsjtf.exception.JTFException;
import com.sandisk.zsjtf.global.*;

public class ZSTransactionExec extends ZSCommandExec {

	private ZSTransaction zsTransaction;

	public ZSTransactionExec(JTFCommand jtfCommand) {
		super(jtfCommand);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String Execute() {
		// TODO Auto-generated method stub
		return null;
	}

	public String start() {
		try {
			zsTransaction.start();

			return JTFCommand.handleSuccessReturn();
		} catch (ZSException e) {
			return JTFCommand.handleServerErrorReturn(e);
		}
	}

	public String commit() {
		try {
			zsTransaction.commit();

			return JTFCommand.handleSuccessReturn();
		} catch (ZSException e) {
			return JTFCommand.handleServerErrorReturn(e);
		}
	}

	public String rollback() {
		try {
			zsTransaction.rollback();

			return JTFCommand.handleSuccessReturn();
		} catch (ZSException e) {
			return JTFCommand.handleServerErrorReturn(e);
		}
	}

	@Override
	public void setZSEntry(Object zsEntry) throws JTFException {
		// TODO Auto-generated method stub
		if (zsEntry instanceof ZSTransaction) {
			zsTransaction = (ZSTransaction) zsEntry;
		} else {
			throw new JTFException("zsEntry can not be cast to ZSTransaction");
		}
	}

}
