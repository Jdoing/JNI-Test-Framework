package com.sandisk.zsjtf.exec;

import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.exception.ZSException;
import com.sandisk.zsjtf.JTFCommand;
import com.sandisk.zsjtf.exception.JTFException;
import com.sandisk.zsjtf.global.ZSCommandExec;

public class ZSGetContainerSnapshotsExec extends ZSCommandExec {

	private ZSContainer zsContainer;

	public ZSGetContainerSnapshotsExec(JTFCommand jtfCommand) {
		super(jtfCommand);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String Execute() {
		// TODO Auto-generated method stub
		try {
			zsContainer.getSnapshots();

			return JTFCommand.handleSuccessReturn();
		} catch (ZSException e) {
			return JTFCommand.handleServerErrorReturn(e);
		}

	}

	@Override
	public void setZSEntry(Object zsEntry) throws JTFException {
		// TODO Auto-generated method stub
		if (zsEntry instanceof ZSContainer) {
			zsContainer = (ZSContainer) zsEntry;
		} else {
			throw new JTFException("ZSEntry do not match ZSContainer");
		}
	}

}
