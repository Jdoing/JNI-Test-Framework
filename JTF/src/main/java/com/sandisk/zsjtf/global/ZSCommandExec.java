package com.sandisk.zsjtf.global;

public abstract class ZSCommandExec {

	protected ZSAdapter zsAdapter;
	
	public ZSCommandExec(ZSAdapter zsAdapter){
		
		this.zsAdapter = zsAdapter;
	}
	
	public abstract String Execute();
}
