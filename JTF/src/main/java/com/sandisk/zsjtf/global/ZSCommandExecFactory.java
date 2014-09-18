package com.sandisk.zsjtf.global;

import java.lang.reflect.Constructor;

import com.sandisk.zsjtf.JTFCommand;
import com.sandisk.zsjtf.exception.JTFException;

public class ZSCommandExecFactory {

	public static ZSCommandExec createZSCommandExec(JTFCommand jtfCommand)
			throws JTFException, Exception {

		if (jtfCommand == null) {
			throw new JTFException("The JTFCommand should not be null");
		}

		String ZSCommandExecName = jtfCommand.getZSCommandExecName();
		
		String className = "com.sandisk.zsjtf.exec."+ZSCommandExecName;

		Class<?> clazz = Class.forName(className);

		//Object[] args = new Object[]{zsAdapter};
		
		Constructor<?> constructor = clazz.getConstructor(ZSAdapter.class);

		ZSCommandExec zsCommandExec = (ZSCommandExec) constructor.newInstance(jtfCommand);

		Object zsEntry = jtfCommand.createZSEntry();
		
		zsCommandExec.setZSEntry(zsEntry);
		
		return zsCommandExec;
	}
}
