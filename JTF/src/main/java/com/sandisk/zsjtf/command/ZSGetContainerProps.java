/*
 * File: ZSGetContainerProps.java
 * Author: qyu, rchen, yjiang
 *
 * SanDisk Proprietary Material, © Copyright 2014 SanDisk, all rights reserved.
 * http://www.sandisk.com
 * THIS IS NOT A CONTRIBUTION.
 */
package com.sandisk.zsjtf.command;

import com.sandisk.zsjtf.JTFCommand;
import com.sandisk.zsjtf.exception.JTFException;
import com.sandisk.zsjtf.util.ContainerManager;
import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.exception.ZSContainerException;

/**
 * ZSGetContainerProps command class.
 *
 * @author rchen
 *
 * args:
 *   cguid=%u
 *
 * must have: cguid
 *
 * return:
 *   success: OK
 *     cguid=%u
 *     fifo_mode=0|1
 *     evicting=0|1
 *     writethru=0|1
 *     size=%u
 *     durability_level=0|1
 *     num_shards=%u
 *   failed: SERVER_ERROR %s | CLIENT_ERROR %s
 */
public class ZSGetContainerProps extends JTFCommand
{
 	/* Command arg list start */
    private Long containerID;
    /* Command arg list end */

    private ZSContainer zsContainer;

    private String ZSCommandExecName = "ZSGetContainerPropsExec";
    public ZSGetContainerProps(String rawCommand) throws JTFException {
 		super(rawCommand);
 		// TODO Auto-generated constructor stub
 		getProperties();
 	}

    @Override
    public String execute()
    {
//        try {
//            getProperties();
//            getContainerProps();
//            return handleSuccessReturn();
//        } catch (ZSContainerException e) {
//            return handleServerErrorReturn(e);
//        } catch (JTFException e) {
//            return handleClientErrorReturn(e);
//        }
    	
    	String result = zsCommandExec.Execute();
		return result;
    }

    private void getProperties() throws JTFException
    {
        containerID = getLongProperty(CGUID, true);
    }

//    private void getContainerProps() throws JTFException, ZSContainerException
//    {
//        containerProps = ContainerManager.getInstance().getContainer(containerID).getProperties();
//    }

//    @Override
//    protected String handleSuccessReturn()
//    {
//        String ret = "OK";
//        ret += ("\ncguid=" + containerProps.getContainerId());
//        ret += ("\nfifo_mode=" + (containerProps.getFifoMode() ? 1 : 0));
//        ret += ("\nevicting=" + (containerProps.getEvicting() ? 1 : 0));
//        ret += ("\nwritethru=" + (containerProps.getWritethru() ? 1 : 0));
//        ret += ("\nsize=" + containerProps.getSize());
//        DurabilityLevel durabilityLevel = containerProps.getDurabilityLevel();
//        int dLevel = -1;
//        if (durabilityLevel == DurabilityLevel.ZS_DURABILITY_PERIODIC) {
//            dLevel = 0;
//        } else if (durabilityLevel == DurabilityLevel.ZS_DURABILITY_SW_CRASH_SAFE) {
//            dLevel = 1;
//        } else if (durabilityLevel == DurabilityLevel.ZS_DURABILITY_HW_CRASH_SAFE) {
//            dLevel = 2;
//        }
//        ret += ("\ndurability_level=" + dLevel);
//        ret += ("\nnum_shards=" + containerProps.getShardNumber());
//        return ret;
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
		
		zsContainer = ContainerManager.getInstance().getContainer(containerID);
		return zsContainer;
	}
}
