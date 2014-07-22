package com.sandisk.jtf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import com.sandisk.jtf.exception.JTFException;

import com.sandisk.zs.ZSClient;
import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.exception.ZSException;

/**
 * Created by ray on 7/9/14.
 */
public class JTFUtils
{
    private final static String zsPropFile = "/tmp/zs-jtf.prop";
    private static ZSClient zsInstance;
    private static ConcurrentHashMap<String, Long> nameIDMap = new ConcurrentHashMap<String, Long>();
    private static ConcurrentHashMap<Long, ZSContainer> containerCache = new ConcurrentHashMap<Long, ZSContainer>();

    public static String getZSPropFile()
    {
        return zsPropFile;
    }

    public static ZSClient getZSInstance()
    {
        return zsInstance;
    }

    public static void setNameIDMap(String containerName, long containerID)
    {
        nameIDMap.put(containerName, containerID);
    }

    public static long getNameIDMap(String containerName) throws JTFException
    {
        if (nameIDMap.containsKey(containerName)) {
            return nameIDMap.get(containerName);
        } else {
            throw new JTFException("Container Name " + containerName + "not found");
        }
    }

    public static void removeNameIDMap(String containerName) throws JTFException
    {
        if (nameIDMap.containsKey(containerName)) {
            nameIDMap.remove(containerName);
        } else {
            throw new JTFException("Container Name " + containerName + "not found");
        }
    }

    public static void setContainer(long containerID, ZSContainer containerObj)
    {
        containerCache.put(containerID, containerObj);
    }

    public static ZSContainer getContainer(long containerID) throws JTFException
    {
        if (containerCache.containsKey(containerID)) {
            return containerCache.get(containerID);
        } else {
            throw new JTFException("Container ID " + containerID + " not found");
        }
    }

    public static void removeContainer(long containerID) throws JTFException
    {
        if (containerCache.containsKey(containerID)) {
            containerCache.remove(containerID);
        } else {
            throw new JTFException("Container ID " + containerID + " not found");
        }
    }

    public static void genZSPropFile()
    {
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(zsPropFile);
            Properties prop = new Properties();
            prop.setProperty("ZS_CACHE_SIZE", "0x40000000");
            prop.setProperty("ZS_FLASH_FILENAME", "/tmp/sandisk0");
            prop.setProperty("ZS_FLASH_SIZE", "4G");
            prop.setProperty("ZS_ADMIN_ENABLED", "1");
            prop.setProperty("ZS_STATS_DUMP_INTERVAL", "10");
            prop.setProperty("ZS_STATS_FILE", "/tmp/ZSstats.log");
            prop.setProperty("ZS_REFORMAT", "1");
            prop.store(fos, "Auto-generated for ZS Example");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void initZSService() throws ZSException
    {
        genZSPropFile();
        zsInstance = ZSClient.getInstance();
        zsInstance.init(zsPropFile);
    }

    public static void cleanContainers() throws ZSException
    {
        // TODO: iterate containerCache, close and drop each container.
    }

    public static void exitZSService() throws ZSException
    {
        cleanContainers();
        zsInstance.shutdown();
    }
}

