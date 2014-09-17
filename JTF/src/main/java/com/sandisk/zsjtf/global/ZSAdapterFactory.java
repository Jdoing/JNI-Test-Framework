package com.sandisk.zsjtf.global;

import com.sandisk.zsjtf.JTFCommand;
import com.sandisk.zsjtf.command.*;
import com.sandisk.zsjtf.exception.JTFException;
import com.sandisk.zs.*;
import com.sandisk.zs.exception.*;
import com.sandisk.zs.type.*;
import com.sandisk.zsjtf.util.Log;

public class ZSAdapterFactory implements ZSAdapterManger {

	public ZSAdapter getZSAdapter(JTFCommand jtfCommand) throws Exception {
		try {
			
			if (jtfCommand == null) {
				throw new JTFException("The JTFCommand should not be null");
			}

			String ZSAdapterName = jtfCommand.getZSAdapterName();

			String className = "com.sandisk.zsjtf" + ZSAdapterName;

			ZSAdapter zsAdapter = (ZSAdapter) Class.forName(className)
					.newInstance();
			zsAdapter.CreateZSObject(jtfCommand);
			
			return zsAdapter;
			
		} catch (JTFException e) {
			e.printStackTrace();
			return null;
		}
	}

}
