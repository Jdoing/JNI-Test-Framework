package com.sandisk.zsjtf.global;

import java.lang.reflect.Constructor;

import com.sandisk.zsjtf.JTFCommand;
import com.sandisk.zsjtf.exception.JTFException;
import com.sandisk.zsjtf.util.Log;

public class ZSCommandExecFactory {

	public static ZSCommandExec createZSCommandExec(JTFCommand jtfCommand)
			throws JTFException, Exception {

		if (jtfCommand == null) {
			throw new JTFException("The JTFCommand should not be null");
		}

		String ZSCommandExecName = jtfCommand.getZSCommandExecName();
		Log.logDebug("The ZSCommandExecName is: "+ZSCommandExecName);
		
		if(ZSCommandExecName == null){
			throw new JTFException("ZSCommandExecName must be not null");
		}
		
		String className = "com.sandisk.zsjtf.exec."+ZSCommandExecName;

		Class<?> clazz = Class.forName(className);

		//Object[] args = new Object[]{zsAdapter};
		
		Constructor<?> constructor = clazz.getConstructor(JTFCommand.class);

		ZSCommandExec zsCommandExec = (ZSCommandExec) constructor.newInstance(jtfCommand);

		return zsCommandExec;
	}
}
