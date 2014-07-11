package com.sandisk.zs;

import java.util.ArrayList;
import java.util.List;

import com.sandisk.zs.NativeZS;
import com.sandisk.zs.NativeZSContainer;
import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zs.exception.ZSException;
import com.sandisk.zs.exception.ZSExceptionHandler;
import com.sandisk.zs.type.ContainerProperty;
import com.sandisk.zs.type.ZSState;
import com.sandisk.zs.type.ZSStatistics;

/**
 * File:   ZSClient.java
 * Author: zane, ymiao, candy
 *
 * Created on July 30, 2013
 *
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com
 * THIS IS NOT A CONTRIBUTION
 */

/**
 * Provides the client to operate zs such as init, shutdown.
 */
public class ZSClient
{

    private static ZSClient singleClient = null;

    private ZSClient()
    {
    }

    /**
     * Retrieve the ZS singleton client.
     * 
     * @return ZSClient single instance.
     */
    public static ZSClient getInstance()
    {
        if (singleClient == null)
        {
            singleClient = new ZSClient();
        }
        return singleClient;
    }

    /**
     * Initialize ZS global state. This function should be called exactly once, and must be called before any other ZS
     * function is called (except loadProperties() or setProperty()).
     * 
     * @param propertiesFile
     *            The path of properties file. If the propertiesFile is null, should call setProperty or loadProperties fucntions first to set ZS property manually.
     * @throws ZSException
     */
    public void init(String propertiesFile) throws ZSException
    {
        if (propertiesFile != null)
        {
            loadProperties(propertiesFile);
        }
        ZSState zs_state = new ZSState();
        int resultCode = NativeZS.ZSInit(zs_state);
        ZSExceptionHandler.handleClient(resultCode);
    }

    /**
     * Stop ZS and release all ZS resources. ZS will flush and sync any buffered objects. This function should be
     * called exactly once, after all per-thread contexts have been released with releasePerThreadState().
     * 
     * @throws ZSException
     */
    public void shutdown() throws ZSException
    {
        int resultCode = NativeZS.ZSShutdown();
        ZSExceptionHandler.handleClient(resultCode);
    }

    /**
     * Load property values. Read property values from the specified file. Properties not specified in the file are set
     * to built-in defaults or values set by prior calls to loadProperties() or setProperty().
     * 
     * @param propertiesFile
     *            The path of properties file
     * @throws ZSException
     */
    private void loadProperties(String propertiesFile) throws ZSException
    {
        int resultCode = NativeZS.ZSLoadProperties(propertiesFile);
        ZSExceptionHandler.handleClient(resultCode);
    }

    /**
     * Assign a value to a property.
     * 
     * @param propertyName
     *            property name
     * @param value
     *            property value
     * @throws ZSException
     */
    public void setProperty(String propertyName, String value) throws ZSException
    {
        NativeZS.ZSSetProperty(propertyName, value);
    }

    /**
     * Get property value.
     * 
     * @param propertyName
     *            property name
     * @return the property value
     */
    public String getProperty(String propertyName)
    {
        return NativeZS.ZSGetProperty(propertyName, "Default path");
    }

    /**
     * Returns the ZS version.
     * 
     * @return ZS version
     */
    public static String getVersion()
    {
        return NativeZS.ZSGetVersion();
    }

    /**
     * Get the container id array.
     * 
     * @return long array contains container id.
     * @throws ZSContainerException
     */
    public long[] getContainers() throws ZSContainerException
    {
        return NativeZSContainer.ZSGetContainers();
    }

    /**
     * Get all containers' property like container name, eviction, size, persistent etc.
     * 
     * @return List<ContainerProperty> the list of container property, a empty list if no any container.
     * @throws ZSContainerException
     */
    public List<ContainerProperty> getContainersPropertiesList() throws ZSContainerException
    {
        // get container cguid list first
        long[] cguids = NativeZSContainer.ZSGetContainers();
        List<ContainerProperty> containers = new ArrayList<ContainerProperty>();

        for (long cguid : cguids)
        {
            // get container properties one by one
            ContainerProperty property = new ContainerProperty();
            int ret = NativeZSContainer.ZSGetContainerProps(cguid, property);
            ZSExceptionHandler.handleContainer(ret);
            containers.add(property);
        }
        return containers;
    }

    /**
     * Retrieve global access statistics for this ZS instance.
     * 
     * @return the instance with zs state property
     * @throws ZSException
     */
    public ZSStatistics getStats() throws ZSException
    {
        ZSStatistics stats = new ZSStatistics();
        int resultCode = NativeZS.ZSGetStats(stats);
        ZSExceptionHandler.handleClient(resultCode);
        return stats;
    }

    /**
     * Force any buffered changes from any container to storage and sync storage.
     * 
     * @throws ZSException
     */
    public void flushCache() throws ZSException
    {
        int resultCode = NativeZS.ZSFlushCache();
        ZSExceptionHandler.handleClient(resultCode);
    }

}
