package com.sandisk.zsjtf.exec;

import com.sandisk.zs.ZSEnumerator;
import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zsjtf.JTFCommand;
import com.sandisk.zsjtf.exception.JTFException;
import com.sandisk.zsjtf.global.ZSCommandExec;

public class ZSEnumerateContainerObjectsExec extends ZSCommandExec {

	private ZSEnumerator zsEnumerator;

	public ZSEnumerateContainerObjectsExec(JTFCommand jtfCommand) {
		super(jtfCommand);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String Execute() {
		// TODO Auto-generated method stub

		try {
			zsEnumerator.begin();
			
			zsEnumerator.finish();
			
			return JTFCommand.handleSuccessReturn();
		} catch (ZSContainerException e) {
			// TODO Auto-generated catch block
			return JTFCommand.handleServerErrorReturn(e);
		}
	}

	@Override
	public void setZSEntry(Object zsEntry) throws JTFException {
		// TODO Auto-generated method stub
		if (zsEntry instanceof ZSEnumerator) {
			zsEnumerator = (ZSEnumerator) zsEntry;
		} else {
			throw new JTFException("ZSEntry do not match ZSContainer");
		}
	}

}
