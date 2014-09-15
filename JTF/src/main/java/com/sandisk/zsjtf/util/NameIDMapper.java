package com.sandisk.zsjtf.util;

import java.util.concurrent.ConcurrentHashMap;

import com.sandisk.zsjtf.exception.JTFException;

import com.sandisk.zs.ZSContainer;

public class NameIDMapper
{
    private static NameIDMapper instance = new NameIDMapper();

    private ConcurrentHashMap<String, Long> nameIDMap = new ConcurrentHashMap<String, Long>();

    public static NameIDMapper getInstance()
    {
        return instance;
    }

    public void setNameIDMap(String containerName, long containerID)
    {
        nameIDMap.put(containerName, containerID);
    }

    public long getNameIDMap(String containerName) throws JTFException
    {
        if (nameIDMap.containsKey(containerName)) {
            return nameIDMap.get(containerName);
        } else {
            throw new JTFException("Container Name " + containerName + "not found");
        }
    }

    public void removeNameIDMap(String containerName) throws JTFException
    {
        if (nameIDMap.containsKey(containerName)) {
            nameIDMap.remove(containerName);
        } else {
            throw new JTFException("Container Name " + containerName + "not found");
        }
    }

    private NameIDMapper() {}
}
