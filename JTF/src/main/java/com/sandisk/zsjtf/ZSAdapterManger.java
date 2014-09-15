package com.sandisk.zsjtf;

import com.sandisk.zs.exception.ZSException;
import com.sandisk.zsjtf.exception.JTFException;

public interface ZSAdapterManger {

	public  ZSAdapter getZSAdapter(JTFCommand jtfCommand) throws Exception;
}
