/*
 * File: ZSEnumerateContainerObject.java
 * Author: qyu, rchen, yjiang
 *
 * SanDisk Proprietary Material, © Copyright 2014 SanDisk, all rights reserved.
 * http://www.sandisk.com
 * THIS IS NOT A CONTRIBUTION.
 */
package com.sandisk.zsjtf.command;

import com.sandisk.zsjtf.JTFCommand;
import com.sandisk.zsjtf.exception.JTFException;
import com.sandisk.zs.ZSEnumerator;
import com.sandisk.zs.exception.ZSContainerException;

/**
 * ZSEnumerateContainerObject command class.
 *
 * @author qyu
 *
 * args:
 *   cguid=%u
 *
 * must have: cguid
 *
 * return:
 *   success: OK
 *   failed: SERVER_ERROR %s | CLIENT_ERROR %s
 */
public class ZSEnumerateContainerObjects extends JTFCommand
{
	// Command arg list start
    private Long containerID;
    // Command arg list end
    
    private String ZSCommandExecName = "ZSEnumerateContainerObjectsExec";
    public ZSEnumerateContainerObjects(String rawCommand) throws JTFException {
		super(rawCommand);
		// TODO Auto-generated constructor stub
		 getProperties();
	}

    public String execute()
    {
//        try {
//            getProperties();
//            enumerateContainerObject();
//            return "OK";
//        } catch (ZSException e) {
//            return "SERVER_ERROR " + e.toString();
//        } catch (JTFException e) {
//            return "CLIENT_ERROR " + e.toString();
//        }
    	
    	String result = zsCommandExec.Execute();
		return result;
    }

    private void getProperties() throws JTFException
    {
        containerID = getLongProperty(CGUID, true);
    }

//    private void enumerateContainerObject() throws JTFException, ZSContainerException
//    {      
//        //bulk enumerator
//        ZSEnumerator enumerator = new ZSEnumerator(containerID);
//        enumerator.begin();
//        enumerator.finish();
//    }

	@Override
	public String getZSCommandExecName() {
		// TODO Auto-generated method stub
		return ZSCommandExecName;
	}

	@Override
	public Object getZSEntry() throws ZSContainerException, JTFException,
			Exception {
		// TODO Auto-generated method stub
		ZSEnumerator enumerator = new ZSEnumerator(containerID);
		return enumerator;
	}
}

