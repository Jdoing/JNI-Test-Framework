package com.sandisk.jtf.commands;
import com.sandisk.jtf.JTFCommand;
import com.sandisk.zs.*;
import com.sandisk.zs.exception.*;
public class ZSTransactionCommit extends JTFCommand{
	public String execute(){
		try{
		ZSTransaction t = new ZSTransaction();
		t.commit();
		return "OK";
		}
		catch(ZSException e){
			return "server fail"+e.toString();
		}
		catch(Exception e){
			return "client fail"+e.toString();
		}
	}
}
