package com.sandisk.jtf;
import com.sandisk.zs.*;
import com.sandisk.zs.exception.*;

public class ZSTransactionStart extends JTFCommand{
	
	public String execute(){
		try{
		ZSTransaction t = new ZSTransaction();
		t.start();
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
