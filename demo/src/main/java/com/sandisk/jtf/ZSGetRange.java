package com.sandisk.jtf;

import com.sandisk.zs.*;
import com.sandisk.zs.exception.*;
public class ZSGetRange extends JTFCommand {

	public String execute() {
		try{
			if(this.args.getProperty("cguid")==null)
				return "cguid can't be empty!";
		long cguid = Long.parseLong(this.args.getProperty("cguid"));

		ZSRange range = new ZSRange(cguid);

		range.begin();
		return "success: OK";
		}
		catch(ZSContainerException e){
			return "SERVER_ERROR:"+e.toString();
		}
		catch(Exception e){
			return "CLIENT_ERROR:"+e.toString();
		}
	}
}
