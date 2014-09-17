/*
 * File: ZSGetContainers.java
 * Author: qyu, rchen, yjiang
 *
 * SanDisk Proprietary Material, © Copyright 2014 SanDisk, all rights reserved.
 * http://www.sandisk.com
 * THIS IS NOT A CONTRIBUTION.
 */
package com.sandisk.zsjtf.command;

import com.sandisk.zsjtf.JTFCommand;
import com.sandisk.zsjtf.util.ZSServiceManager;

import com.sandisk.zs.exception.ZSContainerException;

/**
 * ZSGetContainers command class.
 *
 * @author rchen
 *
 * args: NULL
 *
 * must have: NULL
 *
 * return:
 *   success: OK n_cguid=%u\n%u %u %u... (list n cguid, seperated by one space)
 *   failed: SERVER_ERROR %s | CLIENT_ERROR %s
 */
public class ZSGetContainers extends JTFCommand
{
    private long [] containerIDs;

    @Override
    public String execute()
    {
        try {
            getContainers();
            return handleSuccessReturn();
        } catch (ZSContainerException e) {
            return handleServerErrorReturn(e);
        }
    }

    private void getContainers() throws ZSContainerException
    {
        containerIDs = ZSServiceManager.getInstance().getContainers();
    }

    @Override
    protected String handleSuccessReturn()
    {
        String ret = "OK n_cguids=" + containerIDs.length;
        for (int i = 0; i < containerIDs.length; ++i) {
            ret += ((i == 0 ? "\n" : " ") + containerIDs[i]);
        }
        return ret;
    }
}

