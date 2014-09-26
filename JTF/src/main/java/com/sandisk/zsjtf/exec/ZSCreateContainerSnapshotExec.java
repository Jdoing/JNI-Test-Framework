package com.sandisk.zsjtf.exec;

import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.exception.ZSException;
import com.sandisk.zs.type.ZSSnapshot;
import com.sandisk.zsjtf.JTFCommand;
import com.sandisk.zsjtf.exception.JTFException;
import com.sandisk.zsjtf.global.ZSCommandExec;
import com.sandisk.zsjtf.util.ContainerManager;

public class ZSCreateContainerSnapshotExec extends ZSCommandExec {

	private ZSContainer zsContainer;
	private ZSSnapshot zsSnapshot;

	public ZSCreateContainerSnapshotExec(JTFCommand jtfCommand) {
		super(jtfCommand);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String Execute() {
		// TODO Auto-generated method stub
		try {

			zsSnapshot = zsContainer.createSnapshot();

			long snapSeq = zsSnapshot.getSnapSeq();

			return "OK snap_seq=" + snapSeq;
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
