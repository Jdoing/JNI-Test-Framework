package com.sandisk.zsjtf.global;

import java.lang.reflect.Constructor;

import com.sandisk.zsjtf.exception.JTFException;

public class ZSCommandExecFactory {

	public static ZSCommandExec createZSCommandExec(String ZSCommandExecName,ZSAdapter zsAdapter)
			throws JTFException, Exception {

		if (zsAdapter == null || ZSCommandExecName == null) {
			throw new JTFException("The ZSCommandExecName or ZSAdapter should not be null");
		}

		String className = "com.sandisk.zsjtf.exec."+ZSCommandExecName;

		Class<?> clazz = Class.forName(className);

		//Object[] args = new Object[]{zsAdapter};
		
		Constructor<?> constructor = clazz.getConstructor(ZSAdapter.class);

		ZSCommandExec zsCommandExec = (ZSCommandExec) constructor.newInstance(zsAdapter);

		return zsCommandExec;
	}
}
