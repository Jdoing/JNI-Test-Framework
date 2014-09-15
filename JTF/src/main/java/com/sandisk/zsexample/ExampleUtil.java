package com.sandisk.zsexample;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zs.type.ContainerMode;
import com.sandisk.zs.type.ContainerProperty;
import com.sandisk.zs.type.DurabilityLevel;

/**
 * File:   ExampleUtil.java
 * Author: ymiao, candy
 *
 * Created on July 30, 2013
 *
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com
 * THIS IS NOT A CONTRIBUTION
 */

/**
 *  Provide some functions for examples, such as init ZS, create container.
 */
public class ExampleUtil
{
    private final static String propFile = "/tmp/ZS_example.prop";

    public static void generateZSPropFile()
    {
        // general configuration
        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream(propFile);
            Properties prop = new Properties();
            prop.setProperty("ZS_CACHE_SIZE", "0x40000000");
            prop.setProperty("ZS_FLASH_FILENAME", "/tmp/sandisk0");
            prop.setProperty("ZS_FLASH_SIZE", "4G");
            prop.setProperty("ZS_ADMIN_ENABLED", "1");
            prop.setProperty("ZS_STATS_DUMP_INTERVAL", "10");
            prop.setProperty("ZS_STATS_FILE", "/tmp/ZSstats.log");
            prop.setProperty("SDF_REFORMAT", "1");
            prop.store(fos, "Auto-genertated for ZS Example");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (fos != null)
            {
                try
                {
                    fos.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }

    }

    public static String getPropFile()
    {
        return propFile;
    }

    public static ZSContainer createBtreeContainer(String containerName)
    {
        ZSContainer container = null;
        try
        {
            boolean isFIFO = true;
            boolean isPersistent = true;
            boolean isEvicting = false;
            boolean isWriteThru = true;
            int containerSize = 0;

            ContainerProperty cp = new ContainerProperty();
            cp.setSize(containerSize);
            cp.setFifoMode(isFIFO);
            cp.setEvicting(isEvicting);
            cp.setPersistent(isPersistent);
            cp.setDurabilityLevel(DurabilityLevel.ZS_DURABILITY_SW_CRASH_SAFE);
            cp.setShardNumber(1);
            cp.setWritethru(isWriteThru);
            cp.setContainerMode(ContainerMode.BTREEMODE);
            container = new ZSContainer(containerName, cp);
            container.create();
        }
        catch (ZSContainerException e)
        {
            e.printStackTrace();
        }
        return container;
    }

    public static ZSContainer createHashContainer(String containerName)
    {
        ZSContainer container = null;
        try
        {
            boolean isFIFO = true;
            boolean isPersistent = true;
            boolean isEvicting = false;
            boolean isWriteThru = true;
            int containerSize = 0;

            ContainerProperty cp = new ContainerProperty();
            cp.setSize(containerSize);
            cp.setFifoMode(isFIFO);
            cp.setEvicting(isEvicting);
            cp.setPersistent(isPersistent);
            cp.setDurabilityLevel(DurabilityLevel.ZS_DURABILITY_SW_CRASH_SAFE);
            cp.setShardNumber(1);
            cp.setWritethru(isWriteThru);
            cp.setContainerMode(ContainerMode.HASHMODE);
            container = new ZSContainer(containerName, cp);
            container.create();
        }
        catch (ZSContainerException e)
        {
            e.printStackTrace();
        }
        return container;
    }
    
    public static void cleanContainer(ZSContainer c)
    {
        if (c == null)
            return;
        try
        {
            c.close();
            c.drop();
        }
        catch (ZSContainerException e)
        {
            // do nothing
        }
    }
    
    public static void closeContainer(ZSContainer c)
    {
        if (c == null)
            return;
        try
        {
            c.close();
        }
        catch (ZSContainerException e)
        {
            // do nothing
        }
    }
}
