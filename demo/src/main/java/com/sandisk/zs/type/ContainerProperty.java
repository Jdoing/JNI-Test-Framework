package com.sandisk.zs.type;

import com.sandisk.zs.NativeZSContainer;
import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zs.exception.ZSExceptionHandler;

/**
 * File: ContainerProperty.java 
 * Author: zane, ymiao, candy
 * 
 * Created on July 30, 2013
 * 
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com THIS IS NOT A CONTRIBUTION
 */

/**
 * Contains the property about container.
 *
 */
public class ContainerProperty {
    /**
     * Container id
     */
    private long containerId;

    /**
     * Container name
     */
    private String containerName;

    /**
     * Size of the container in kB
     */
    private long size;
    /**
     * If true, manage flash as a circular buffer with FIFO replacement; if
     * false, manage flash using slab allocation.
     */
    private boolean fifoMode;
    /**
     * If true, persist the container across shutdowns and crashes; if false,
     * reformat the container after shutdowns or crashes.
     */
    private boolean persistent;
    /**
     * If true, container will serve as a cache;if false, objects are retained
     * until deleted.
     */
    private boolean evicting;
    /**
     * If true, use a write-thru policy in the DRAM cache for all writes to this
     * container; if false, use a writeback DRAM cache policy.
     */
    private boolean writethru;
    /**
     * Set the durability policy for the container
     * 
     * @see com.sandisk.zs.type.DurabilityLevel
     */
    private int durabilityLevel;
    /**
     * Number of containers
     */
    private long containerNumber;
    /**
     * Number of shards container uses
     */
    private int shardNumber;

    /**
     * Set the asynchronous write policy for the container
     */
    private boolean asyncWrite;

    /**
     * Only use flash.
     */
    private boolean flashOnly;

    /**
     * Only use cache.
     */
    private boolean cacheOnly;

    /**
     * Container mode flag
     */
    private int containerMode;
    

    public ContainerMode getContainerMode() {
        return ContainerMode.valueOf(containerMode);
    }

    public void setContainerMode(ContainerMode containerMode) {
        this.containerMode =containerMode.ordinal();
    }
    /**
     * @return flash only
     */
    public boolean getFlashOnly() {
        return flashOnly;
    }

    /**
     * set flash only
     * 
     * @param flushOnly
     */
    public void setFlashOnly(boolean flashOnly) {
        this.flashOnly = flashOnly;
    }

    /**
     * @return cache only
     */
    public boolean getCacheOnly() {
        return cacheOnly;
    }

    /**
     * set cache only
     * 
     * @param cacheOnly
     */
    public void setCacheOnly(boolean cacheOnly) {
        this.cacheOnly = cacheOnly;
    }

    /**
     * @return the size
     */
    public long getSize() {
        return size;
    }

    /**
     * @param size
     *            the size to set
     */
    public void setSize(long size) {
        this.size = size;
    }

    /**
     * @return the fifoMode
     */
    public boolean getFifoMode() {
        return fifoMode;
    }

    /**
     * @param fifoMode
     *            the fifoMode to set
     */
    public void setFifoMode(boolean fifoMode) {
        this.fifoMode = fifoMode;
    }

    /**
     * @return the persistent
     */
    public boolean getPersistent() {
        return persistent;
    }

    /**
     * @param persistent
     *            the persistent to set
     */
    public void setPersistent(boolean persistent) {
        this.persistent = persistent;
    }

    /**
     * @return the evicting
     */
    public boolean getEvicting() {
        return evicting;
    }

    /**
     * @param evicting
     *            the evicting to set
     */
    public void setEvicting(boolean evicting) {
        this.evicting = evicting;
    }

    /**
     * @return the writethru
     */
    public boolean getWritethru() {
        return writethru;
    }

    /**
     * @param writethru
     *            the writethru to set
     */
    public void setWritethru(boolean writethru) {
        this.writethru = writethru;
    }

    /**
     * @return the durabilityLevel
     */
    public DurabilityLevel getDurabilityLevel() {
        return DurabilityLevel.valueOf(durabilityLevel);
    }

    /**
     * @param durabilityLevel
     *            the durabilityLevel to set
     */
    public void setDurabilityLevel(DurabilityLevel durabilityLevel) {
        this.durabilityLevel = durabilityLevel.ordinal();
    }

    /**
     * There are three DurablilityLevel : 
     * ZS_DURABILITY_PERIODIC(0), ZS_DURABILITY_SW_CRASH_SAFE(1), ZS_DURABILITY_HW_CRASH_SAFE(2)
     * 
     * @param durabilityLevel
     */
    @Deprecated
    public void setDurabilityLevel(int durabilityLevel){
        if(DurabilityLevel.valueOf(durabilityLevel) != null){
            this.durabilityLevel = durabilityLevel;
        }
    }

    /**
     * @return the containerId
     */
    public long getContainerId() {
        return containerId;
    }

    /**
     * @return the containerNumber
     */
    public long getContainerNumber() {
        return containerNumber;
    }

    /**
     * @return the shardNumber
     */
    public int getShardNumber() {
        return shardNumber;
    }

    /**
     * @param shardNumber
     *            the shardNumber to set
     */
    public void setShardNumber(int shardNumber) {
        this.shardNumber = shardNumber;
    }

    /**
     * @return the asyncWrite
     */
    public boolean getAsyncWrite() {
        return asyncWrite;
    }

    /**
     * @param asyncWrite
     *            the asyncWrite to set
     */
    public void setAsyncWrite(boolean asyncWrite) {
        this.asyncWrite = asyncWrite;
    }

    /**
     * Get default property.
     * @return
     * @throws ZSContainerException
     */
    public static ContainerProperty getDefautProperty() throws ZSContainerException {
        ContainerProperty property = new ContainerProperty();
        int resultCode = NativeZSContainer.ZSLoadCntrPropDefaults(property);
        ZSExceptionHandler.handleContainer(resultCode);
        return property;
    }

    /**
     * Get container name;
     * 
     * @return containerName
     */
    public String getContainerName() {
        return containerName;
    }

    /**
     * Set container name;
     * 
     * @param containerName
     */
    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    @Override
    public String toString() {
        return this.size + "-size\n" + this.durabilityLevel + "-durabilityLevel\n" + this.fifoMode
                + "-fifo\n" + this.persistent + "-persisitent\n" + this.shardNumber
                + "-shardNumber\n" + this.evicting + "-evict\n" + this.writethru + "-writethru\n"
                + this.flashOnly + "-flashOnly\n" + this.cacheOnly + "-cacheOnly\n";
    }

}
