package com.sandisk.zsjtf.exec;

import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zs.type.OpenContainerMode;
import com.sandisk.zsjtf.JTFCommand;
import com.sandisk.zsjtf.command.ZSOpenContainer;
import com.sandisk.zsjtf.exception.JTFException;
import com.sandisk.zsjtf.global.ZSCommandExec;
import com.sandisk.zsjtf.util.Log;
import com.sandisk.zsjtf.util.NameIDMapper;
import com.sandisk.zsjtf.util.ContainerManager;

public class ZSOpenContainerExec extends ZSCommandExec {

	private ZSOpenContainer zsOpenContainer;
	private ZSContainer zsContainer;

	public ZSOpenContainerExec(JTFCommand jtfCommand)
			throws ZSContainerException, JTFException {
		super(jtfCommand);
		// TODO Auto-generated constructor stub

		if (jtfCommand instanceof ZSOpenContainer) {

			zsOpenContainer = (ZSOpenContainer) jtfCommand;
		} else {
			throw new JTFException(
					"Fail when cast JTFCommand to ZSOpenContainer");
		}

	}

	public void setZSEntry(Object zsEntry) throws JTFException {

		if (zsEntry instanceof ZSContainer) {
			zsContainer = (ZSContainer) zsEntry;
		} else {
			throw new JTFException("ZSEntry do not match ZSContainer");
		}
	}

	@Override
	public String Execute() {
		// TODO Auto-generated method stub

		try {
			OpenContainerMode openContainerMode = zsOpenContainer
					.getOpenContainerMode();
			Log.logDebug("Start to OpenContainer");

			long containerID;
			
			if (openContainerMode == null) {
				zsContainer.create();

				containerID = zsContainer.getContainerId();
				
				String containerName = zsContainer.getContainerName();
				
				NameIDMapper.getInstance().setNameIDMap(containerName, containerID);
				ContainerManager.getInstance().setContainer(containerID, zsContainer);
				
			} else {
				zsContainer.open(openContainerMode);
				containerID = zsContainer.getContainerId();
			}

			Log.logDebug("OpenContainer finish");

			return JTFCommand.handleSuccessReturn() + " cguid=" + containerID;
		} catch (ZSContainerException e) {
			return JTFCommand.handleServerErrorReturn(e);
		}
	}
}