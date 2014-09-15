package com.sandisk.zsjtf.util;

import java.util.concurrent.ConcurrentHashMap;

import com.sandisk.zsjtf.exception.JTFException;

import com.sandisk.zs.ZSContainer;

public class ContainerManager
{
    private static ContainerManager instance = new ContainerManager();
    private ConcurrentHashMap<Long, ZSContainer> containerCache = new ConcurrentHashMap<Long, ZSContainer>();

    public static ContainerManager getInstance()
    {
        return instance;
    }

    public void setContainer(long containerID, ZSContainer containerObj)
    {
        containerCache.put(containerID, containerObj);
    }

    public ZSContainer getContainer(long containerID) throws JTFException
    {
        if (containerCache.containsKey(containerID)) {
            return containerCache.get(containerID);
        } else {
            throw new JTFException("Container ID " + containerID + " not found");
        }
    }

    public void removeContainer(long containerID) throws JTFException
    {
        if (containerCache.containsKey(containerID)) {
            containerCache.remove(containerID);
        } else {
            throw new JTFException("Container ID " + containerID + " not found");
        }
    }

    private ContainerManager() {}
}
