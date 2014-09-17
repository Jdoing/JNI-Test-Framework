package com.sandisk.zsjtf.exec;

import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zsjtf.adapter.ZSContainerAdapter;
import com.sandisk.zsjtf.exception.JTFException;
import com.sandisk.zsjtf.global.ZSAdapter;
import com.sandisk.zsjtf.global.ZSCommandExec;
import com.sandisk.zsjtf.util.Log;

public class ZSOpenContainerExec extends ZSCommandExec {

	private ZSContainerAdapter zsContainerAdapter;
	
	public ZSOpenContainerExec(ZSAdapter zsAdapter) {
		super(zsAdapter);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String Execute() {
		// TODO Auto-generated method stub
		
		try {
			if (zsAdapter instanceof ZSContainerAdapter) {
				zsContainerAdapter = (ZSContainerAdapter) zsAdapter;
			} else {
				throw new JTFException(
						"ZSAdapter do not match ZSContainerAdapter");
			}

			Log.logDebug("Start to OpenContainer");

			zsContainerAdapter.open();

			Log.logDebug("OpenContainer finish");

			return "OK";

		} catch (JTFException e) {
			return "CLIENT_ERROR:" + e.toString();
		} catch (ZSContainerException e) {
			return "SERVER_ERROR ZS_FAILURE:" + e.toString();
		}

	}

}
