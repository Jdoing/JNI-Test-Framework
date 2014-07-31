package com.sandisk.jtf.commands;

import com.sandisk.jtf.JTFCommand;
import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.ZSRange;
import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zsexample.ExampleUtil;

public class ZSGetRangeFinish extends JTFCommand {

	@Override
	public String execute() {
		// TODO Auto-generated method stub

		ZSContainer container = ExampleUtil.createBtreeContainer("c0");
		ZSRange range = new ZSRange(container.getContainerId());
		try {
			range.end();
			return "OK";
		} catch (ZSContainerException e) {
			// TODO Auto-generated catch block
			return handleServerErrorReturn(e);
		} catch (Exception e) {
			return handleClientErrorReturn(e);
		}

	}

}
