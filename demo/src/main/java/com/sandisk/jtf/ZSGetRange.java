package com.sandisk.jtf;

import com.sandisk.zs.*;

public class ZSGetRange extends JTFCommand {

	public String execute() {
		long cguid = Long.parseLong(this.args.getProperty("cguid"));

		ZSRange range = new ZSRange(cguid);

		int ret = range.getRange();

		if (ret == 0) {
		} else {
		}

		return null;
	}
}
