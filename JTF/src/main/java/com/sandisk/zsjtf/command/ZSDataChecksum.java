package com.sandisk.zsjtf.command;

import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zsjtf.JTFCommand;
import com.sandisk.zsjtf.exception.JTFException;

public class ZSDataChecksum extends JTFCommand {

	public ZSDataChecksum(String rawCommand) throws JTFException {
		super(rawCommand);
		// TODO Auto-generated constructor stub
		getProperties();
	}
	
	private void getProperties() throws JTFException {
		
		
		
	}
	

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getZSCommandExecName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getZSEntry() throws ZSContainerException, JTFException,
			Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
