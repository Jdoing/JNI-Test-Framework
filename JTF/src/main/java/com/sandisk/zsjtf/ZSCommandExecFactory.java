package com.sandisk.zsjtf;

import java.lang.reflect.Constructor;

import com.sandisk.zsjtf.exception.JTFException;

public class ZSCommandExecFactory {

	public static ZSCommandExec createZSCommandExec(String zsCommandExecName,ZSAdapter zsAdapter)
			throws JTFException, Exception {

		if (zsAdapter == null) {
			throw new JTFException("The ZSCommandExecName should not be null");
		}

		String className = "com.sandisk.zsjtf.exec."+zsCommandExecName;

		Class clazz = Class.forName(className);

		Object[] args = new Object[]{zsAdapter};
//		Class args = ZSAdapter.class;
//		Class args = zsAdapter.getClass();
		
		Constructor constructor = clazz.getConstructor(ZSAdapter.class);

		ZSCommandExec zsCommandExec = (ZSCommandExec) constructor.newInstance(args);

		return zsCommandExec;
	}
}
