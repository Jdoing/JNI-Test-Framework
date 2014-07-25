package com.sandisk.jtf.commands;

import com.sandisk.jtf.JTFCommand;
import com.sandisk.jtf.JTFUtils;
import com.sandisk.jtf.exception.JTFException;
import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.exception.ZSException;

public class ZSGetContainerSnapshots extends JTFCommand {

	private Long cguid;

	@Override
	public String execute() {
		// TODO Auto-generated method stub
		try {
			getProperties();

			ZSContainer container = JTFUtils.getContainer(cguid);
			container.getSnapshots();
			return "OK";
		} catch (ZSException e) {
			return handleServerErrorReturn(e);
		} catch (JTFException e) {
			return handleClientErrorReturn(e);
		}
	}

	private void getProperties() throws JTFException {
		cguid = this.getLongProperty(CGUID, true);
	}

}
