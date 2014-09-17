package com.sandisk.zsjtf.global;

import com.sandisk.zsjtf.JTFCommand;

public interface ZSAdapterManger {

	public  ZSAdapter getZSAdapter(JTFCommand jtfCommand) throws Exception;
}
