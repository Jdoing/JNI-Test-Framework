package com.sandisk.zs;

import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zs.exception.ZSExceptionHandler;
import com.sandisk.zs.type.EnumerationProperty;
import com.sandisk.zs.type.ZSEntry;

/**
 * File:   ZSEnumerator.java
 * Author: zane, ymiao, candy
 *
 * Created on July 30, 2013
 *
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com
 * THIS IS NOT A CONTRIBUTION
 */

/**
 * Provides the interface to ZS container object enumeration.
 * The class supports object enumeration initialization, iteration
 * and completion.
 * 
 */
public class ZSEnumerator
{

    private long containerId;
    private EnumerationProperty enumerationProperty;
    private ZSIterator iterator;

    /**
     * Get enumeration property.
     * @return property
     */
    public EnumerationProperty getEnumerationProperty()
    {
        return enumerationProperty;
    }

    /**
     * Set enumeration property.
     * @param enumerationProperty
     *              enumeration property
     */
    public void setEnumerationProperty(EnumerationProperty enumerationProperty)
    {
        this.enumerationProperty = enumerationProperty;
    }

    /**
     * Get the enumeration iterator which to iterate the objects.
     * @return ZSIterator
     */
    public ZSIterator getIterator()
    {
        return iterator;
    }

    public ZSEnumerator(long containerId) throws ZSContainerException
    {
        this.iterator = new ZSIterator();
        this.containerId = containerId;
    }

    public ZSEnumerator(long containerId, EnumerationProperty enumerationProperty) throws ZSContainerException
    {
        this.iterator = new ZSIterator();
        this.containerId = containerId;
        if (enumerationProperty.checkEnableStatus())
        {
            this.enumerationProperty = enumerationProperty;
        }
        else
        {
            this.enumerationProperty = null;
        }
    }

    /**
     * Start the enumerate.
     * @throws ZSContainerException
     */
    public void begin() throws ZSContainerException
    {
        int resultCode = NativeZSEnumerator.ZSEnumerateContainerObjects(
                containerId, enumerationProperty);
        ZSExceptionHandler.handleContainer(resultCode);
    }

    /**
     * Finish the enumerate.
     * @throws ZSContainerException
     */
    public void finish() throws ZSContainerException
    {
        int resultCode = NativeZSEnumerator
                .ZSFinishEnumeration();
        ZSExceptionHandler.handleContainer(resultCode);
    }

    public class ZSIterator
    {
        /**
         * Get the object wrapped ZSEntry.
         * If return null, the enumerate finished.
         * @return object wrapped ZSEntry instance
         * @throws ZSContainerException
         */
        public ZSEntry next() throws ZSContainerException
        {
            ZSEntry entry = new ZSEntry();
            int resultCode = NativeZSEnumerator.ZSNextEnumeratedObject(
                    entry);
            if (resultCode != 1)
            {
                entry = null;
            }
            return entry;
        }
    }

}