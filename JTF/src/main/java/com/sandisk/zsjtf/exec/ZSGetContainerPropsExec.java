package com.sandisk.zsjtf.exec;

import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zs.type.ContainerProperty;
import com.sandisk.zs.type.DurabilityLevel;
import com.sandisk.zsjtf.JTFCommand;
import com.sandisk.zsjtf.exception.JTFException;
import com.sandisk.zsjtf.global.ZSCommandExec;

public class ZSGetContainerPropsExec extends ZSCommandExec {

	private ZSContainer zsContainer;
	private ContainerProperty containerProps;

	public ZSGetContainerPropsExec(JTFCommand jtfCommand) {
		super(jtfCommand);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String Execute() {
		// TODO Auto-generated method stub
		try {
			containerProps = zsContainer.getProperties();
			return handleSuccessReturn();
		} catch (ZSContainerException e) {
			return JTFCommand.handleServerErrorReturn(e);
		}
	}

	private String handleSuccessReturn() {
		String ret = "OK";
		ret += (" cguid=" + containerProps.getContainerId());
		ret += (" fifo_mode=" + (containerProps.getFifoMode() ? 1 : 0));
		ret += (" evicting=" + (containerProps.getEvicting() ? 1 : 0));
		ret += (" writethru=" + (containerProps.getWritethru() ? 1 : 0));
		ret += (" size=" + containerProps.getSize());
		DurabilityLevel durabilityLevel = containerProps.getDurabilityLevel();
		int dLevel = -1;
		if (durabilityLevel == DurabilityLevel.ZS_DURABILITY_PERIODIC) {
			dLevel = 0;
		} else if (durabilityLevel == DurabilityLevel.ZS_DURABILITY_SW_CRASH_SAFE) {
			dLevel = 1;
		} else if (durabilityLevel == DurabilityLevel.ZS_DURABILITY_HW_CRASH_SAFE) {
			dLevel = 2;
		}
		ret += ("\ndurability_level=" + dLevel);
		ret += ("\nnum_shards=" + containerProps.getShardNumber());
		return ret;
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
