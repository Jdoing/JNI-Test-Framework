package com.sandisk.zsjtf.global;

import com.sandisk.zsjtf.JTFCommand;
import com.sandisk.zsjtf.exception.JTFException;

public abstract class ZSCommandExec {

	protected JTFCommand jtfCommand;
	
	public ZSCommandExec(JTFCommand jtfCommand){
		
		this.jtfCommand = jtfCommand;
	}
	
	public abstract String Execute();
	
	public abstract void setZSEntry(Object zsEntry) throws JTFException;
}
