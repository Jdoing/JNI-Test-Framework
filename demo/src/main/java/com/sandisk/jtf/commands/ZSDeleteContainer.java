package com.sandisk.jtf.commands;

import com.sandisk.jtf.JTFCommand;
import com.sandisk.jtf.JTFUtils;
import com.sandisk.jtf.exception.JTFException;

import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.exception.ZSContainerException;

public class ZSDeleteContainer extends JTFCommand
{
    public String execute()
    {
        try {
            deleteContainer();
            return "OK";
        } catch (ZSContainerException e) {
            return "SERVER_ERROR " + e.toString();
        } catch (JTFException e) {
            return "CLIENT_ERROR " + e.toString();
        }
    }

    private void deleteContainer() throws JTFException, ZSContainerException
    {
        Long containerID = getLongProperty("cguid", true);
        ZSContainer container = JTFUtils.getContainer(containerID);
        container.drop();
        JTFUtils.removeNameIDMap(container.getContainerName());
        JTFUtils.removeContainer(containerID);
    }
}

