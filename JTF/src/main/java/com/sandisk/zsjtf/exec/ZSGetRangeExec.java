package com.sandisk.zsjtf.exec;

import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zsjtf.ZSAdapter;
import com.sandisk.zsjtf.ZSCommandExec;
import com.sandisk.zsjtf.ZSRangeAdapter;
import com.sandisk.zsjtf.exception.JTFException;
import com.sandisk.zsjtf.util.Log;

public class ZSGetRangeExec extends ZSCommandExec {

	private ZSRangeAdapter zsRangeAdapter;

	public ZSGetRangeExec(ZSAdapter zsAdapter) {
		super(zsAdapter);
	}

	@Override
	public String Execute() {
		// TODO Auto-generated method stub

		try {
			if (zsAdapter instanceof ZSRangeAdapter) {
				zsRangeAdapter = (ZSRangeAdapter) zsAdapter;
			} else {
				throw new JTFException(
						"ZSRangeAdapter do not match ZSRangeAdapter");
			}

			Log.logDebug("Start to GetRange");

			zsRangeAdapter.getRange();

			Log.logDebug("GetRange finish");

			return "OK";

		} catch (JTFException e) {
			return "CLIENT_ERROR:" + e.toString();
		} catch (ZSContainerException e) {
			return "SERVER_ERROR ZS_FAILURE:" + e.toString();
		}

	}

}
