/*
 * File: ZSFlushObject.java
 * Author: qyu, rchen, yjiang
 *
 * SanDisk Proprietary Material, © Copyright 2014 SanDisk, all rights reserved.
 * http://www.sandisk.com
 * THIS IS NOT A CONTRIBUTION.
 */
package com.sandisk.zsjtf.command;

import com.sandisk.zsjtf.JTFCommand;
import com.sandisk.zsjtf.util.ByteBuffManager;
import com.sandisk.zsjtf.util.ContainerManager;
import com.sandisk.zsjtf.exception.JTFException;
import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zs.exception.ZSException;

/**
 * ZSFlushObject command class.
 *
 * @author qyu
 *
 * args:
 *   cguid=%u
 *   key_offset=%d
 *   key_len=%u
 *   nops=%d
 *
 * must have: cguid
 *
 * return:
 *   success: OK
 *   failed: SERVER_ERROR %s | CLIENT_ERROR %s
 */
public class ZSFlushObject extends JTFCommand
{

	// Command arg list start
    private Long containerID;

    private Integer initialKeyOffset;
    private Integer keyLength;

    private Integer nops;
    // Command arg list end
    
    private String ZSCommandExecName =;
    public ZSFlushObject(String rawCommand) throws JTFException {
		super(rawCommand);
		// TODO Auto-generated constructor stub
		
		getProperties();
	}

    public String execute()
    {
        try {
            getProperties();
            flushObject();
            return "OK";
        } catch (ZSContainerException e) {
            return "SERVER_ERROR " + e.toString();
        } catch (JTFException e) {
            return "CLIENT_ERROR " + e.toString();
        }
    }

    private void getProperties() throws JTFException
    {
        containerID = getLongProperty(CGUID, true);
        initialKeyOffset = getIntegerProperty(KEY_OFFSET, false, KEY_OFFSET_DEFAULT_VALUE);
        keyLength = getIntegerProperty(KEY_LEN, false, KEY_LEN_DEFAULT_VALUE);
        nops = getIntegerProperty(NOPS, false, NOPS_DEFAULT_VALUE);
    }

    private void flushObject() throws JTFException, ZSContainerException
    {
        ZSContainer container = ContainerManager.getInstance().getContainer(containerID);
        
        int keyOffset;

        byte[] key = new byte[keyLength];

        //not finished yet
        for (int i = 0; i < nops; ++i) {
            keyOffset = initialKeyOffset + i;
            ByteBuffManager.getInstance().arraycopy(key, keyOffset, keyLength);
            
            //exception handled in ZSContainer
            container.flushToObject(key);
        }
    }

	@Override
	public String getZSCommandExecName() {
		// TODO Auto-generated method stub
		return ZSCommandExecName;
	}

	@Override
	public Object getZSEntry() throws ZSContainerException, JTFException,
			Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
