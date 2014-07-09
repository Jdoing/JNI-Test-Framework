package com.netty.example.TransportObject;
import java.util.*;

public class Pro {
	
	private String name;
	
	private HashMap<String,String> args=new HashMap<String,String>();
	
	private String ret;
	
	private String suc;
	
	private String fai;

	public Pro(String name) {
//		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HashMap<String, String> getArgs() {
		return args;
	}

	public void setArgs(HashMap<String, String> args) {
		this.args = args;
	}

	public String getRet() {
		return ret;
	}

	public void setRet(String ret) {
		this.ret = ret;
	}

	public String getSuc() {
		return suc;
	}

	public void setSuc(String suc) {
		this.suc = suc;
	}

	public String getFai() {
		return fai;
	}

	public void setFai(String fai) {
		this.fai = fai;
	}
	
	
}
