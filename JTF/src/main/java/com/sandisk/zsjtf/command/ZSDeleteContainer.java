/*
 * File: ZSDeleteContainer.java
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
import com.sandisk.zsjtf.util.NameIDMapper;
import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zs.exception.ZSException;

/**
 * ZSDeleteContainer command class.
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
 *   failed: SERVER_ERROR %s | CLIENT_ERROR %s
 */
public class ZSDeleteContainer extends JTFCommand
{
    public ZSDeleteContainer(String rawCommand) throws JTFException {
		super(rawCommand);
		// TODO Auto-generated constructor stub
		getProperties();
	}

	/* Command arg list start */
    private Long containerID;
    /* Command arg list end */

    @Override
    public String execute()
    {
        try {
            getProperties();
            deleteContainer();
            return handleSuccessReturn();
        } catch (ZSException e) {
            return handleServerErrorReturn(e);
        } catch (JTFException e) {
            return handleClientErrorReturn(e);
        }
    }

    private void getProperties() throws JTFException
    {
        containerID = getLongProperty(CGUID, true);
    }

    private void deleteContainer() throws JTFException, ZSContainerException
    {
        ZSContainer container = ContainerManager.getInstance().getContainer(containerID);
        container.drop();
        NameIDMapper.getInstance().removeNameIDMap(container.getContainerName());
        ContainerManager.getInstance().removeContainer(containerID);
    }
}
