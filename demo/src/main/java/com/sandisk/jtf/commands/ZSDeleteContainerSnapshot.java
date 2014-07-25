package com.sandisk.jtf.commands;

import com.sandisk.jtf.JTFCommand;
import com.sandisk.jtf.JTFUtils;
import com.sandisk.jtf.exception.JTFException;
import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.exception.ZSException;
import com.sandisk.zs.type.ZSSnapshot;

public class ZSDeleteContainerSnapshot extends JTFCommand {

	private Long cguid;
	private Integer snapSeq;

	@Override
	public String execute() {
		// TODO Auto-generated method stub

		try {
			getProperties();
			ZSContainer container = JTFUtils.getContainer(cguid);
			ZSSnapshot[] snapshots = container.getSnapshots();
			container.deleteSnapshot(snapshots[snapSeq]);
			return "OK";
		} catch (ZSException e) {
			return handleServerErrorReturn(e);
		} catch (JTFException e) {
			return handleClientErrorReturn(e);
		}
	}

	private void getProperties() throws JTFException {
		cguid = this.getLongProperty(CGUID, true);
		snapSeq = this.getIntegerProperty("snap_seq", true);
	}
}
