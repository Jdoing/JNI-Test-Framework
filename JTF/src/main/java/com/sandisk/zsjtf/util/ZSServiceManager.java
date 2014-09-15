package com.sandisk.zsjtf.util;

import com.sandisk.zsjtf.util.MiscUtils;

import com.sandisk.zs.ZSClient;
import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zs.exception.ZSException;

public class ZSServiceManager
{
    private static ZSServiceManager instance = new ZSServiceManager();
    private ZSClient zsClientInstance = ZSClient.getInstance();

    public static ZSServiceManager getInstance()
    {
        return instance;
    }

    public void initZSService() throws Exception
    {
        MiscUtils.genZSPropFile();
        zsClientInstance.init(MiscUtils.getZSPropFile());
    }

    public void exitZSService() throws ZSException
    {
        cleanContainers();
        zsClientInstance.shutdown();
    }

    public long[] getContainers() throws ZSContainerException
    {
        return zsClientInstance.getContainers();
    }

    public void cleanContainers() throws ZSException
    {
        // TODO: iterate containerCache, close and drop each container.
    }

    private ZSServiceManager() {}
}
