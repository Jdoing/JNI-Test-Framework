package com.netty.example.TransportObject;
import java.io.Serializable; 


public class Protocol implements Serializable{
	private static final long   serialVersionUID    = 1L; 
	private String cname;
	private int cguid;
	
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public int getCguid() {
		return cguid;
	}
	public void setCguid(int cguid) {
		this.cguid = cguid;
	}
	
	
}
