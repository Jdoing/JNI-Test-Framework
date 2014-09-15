package com.sandisk.zs;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zs.exception.ZSExceptionHandler;
import com.sandisk.zs.type.ContainerProperty;
import com.sandisk.zs.type.OpenContainerMode;
import com.sandisk.zs.type.WriteObjectMode;
import com.sandisk.zs.type.ZSBulkIterator;
import com.sandisk.zs.type.ZSMData;
import com.sandisk.zs.type.ZSObjectOp;
import com.sandisk.zs.type.ZSObjectOpType;
import com.sandisk.zs.type.ZSSnapshot;
import com.sandisk.zs.type.ZSStatistics;

/**
 * File:   ZSContainer.java
 * Author: zane, ymiao, candy
 *
 * Created on July 30, 2013
 *
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com
 * THIS IS NOT A CONTRIBUTION
 */

/**
 * Provides the interface to ZS container operations. The class supports ZS
 * container creation, open, close, delete as well as object read, write,
 * deletion and enumeration.
 * 
 */
public class ZSContainer {
    private long containerId;
    private String containerName;
    private ContainerProperty property;

    /**
     * Get container id.
     * 
     * @return container id
     */
    public long getContainerId() {
        return containerId;
    }

    /**
     * Get container name.
     * 
     * @return container name
     */
    public String getContainerName() {
        return containerName;
    }

    /**
     * Construct the ZSContainer instance with container name and default
     * property.
     * 
     * @param containerName
     *            container name
     */
    public ZSContainer(String containerName) {
        this.containerName = containerName;
        property = new ContainerProperty();
        NativeZSContainer.ZSLoadCntrPropDefaults(property);
    }

    /**
     * Construct the ZSContainer instance with container name and property.
     * 
     * @param containerName
     *            container name
     * @param property
     *            container property
     */
    public ZSContainer(String containerName, ContainerProperty property) {
        this.containerName = containerName;
        this.property = property;
    }

    /**
     * Create container.
     * 
     * @throws ZSContainerException
     */
    public void create() throws ZSContainerException {
        int resultCode = NativeZSContainer.ZSOpenContainer(containerName, 1, property); // 1 is create-container mode
        ZSExceptionHandler.handleContainer(resultCode, containerName);
        this.containerId = property.getContainerId();
    }

    /**
     * Create container with specified comparator.
     * @param cmp
     *            the compare function for key sort.
     * @throws ZSContainerException
     */
    public void create(Comparator cmp) throws ZSContainerException {
        int resultCode = NativeZSContainer.ZSOpenContainerSpecial(containerName, 1, property, cmp); // 1 is create-container mode
        ZSExceptionHandler.handleContainer(resultCode, containerName);
        this.containerId = property.getContainerId();
    }

    /**
     * Open container normally.
     * 
     * @param openMode
     *            Open container mode. Supported values are
     *            OpenContainerMode.READ_ONLY and OpenContainerMode.READ_WRITE.
     * @throws ZSContainerException
     */
    public void open(OpenContainerMode openMode) throws ZSContainerException {
        if (openMode != OpenContainerMode.READ_ONLY && openMode != OpenContainerMode.READ_WRITE) {
            throw new ZSContainerException(
                    "Error open container mode. Please use OpenContainerMode.READ_ONLY or OpenContainerMode.READ_WRITE.");
        }
        int resultCode = NativeZSContainer.ZSOpenContainer(containerName, openMode.getValue(), property);
        ZSExceptionHandler.handleContainer(resultCode, containerName);
        this.containerId = property.getContainerId();
    }

    /**
     * Open container specially with comparator which to sort key.
     * 
     * @param openMode
     *            OpenContainerMode instance for open container
     * @param cmpObj
     *            compare callback function for range query
     * @return zs container instance
     * @throws ZSContainerException
     */
    public void open(OpenContainerMode openMode, Comparator cmpObj) throws ZSContainerException {
        if (openMode != OpenContainerMode.READ_ONLY && openMode != OpenContainerMode.READ_WRITE) {
            throw new ZSContainerException(
                    "Error open container mode. Please use OpenContainerMode.READ_ONLY or OpenContainerMode.READ_WRITE.");
        }

        int resultCode = NativeZSContainer.ZSOpenContainerSpecial(containerName, openMode.getValue(), property, cmpObj);
        ZSExceptionHandler.handleContainer(resultCode, containerName);
        containerId = property.getContainerId();
    }

    /**
     * Close container.
     * 
     * @throws ZSContainerException
     */
    public void close() throws ZSContainerException {
        int resultCode = NativeZSContainer.ZSCloseContainer(containerId);
        ZSExceptionHandler.handleContainer(resultCode, containerName);
    }

    /**
     * Drop container.
     * 
     * @throws ZSContainerException
     */
    public void drop() throws ZSContainerException {
        int resultCode = NativeZSContainer.ZSDeleteContainer(containerId);
        ZSExceptionHandler.handleContainer(resultCode, containerName);
    }

    /**
     * Read a value from a container with specified key.
     * 
     * @param key
     *            The key of object
     * @return object data as byte array, return empty array if object does not
     *         exist
     * @throws ZSContainerException
     */
    public byte[] read(byte[] key) throws ZSContainerException {
        byte[] data = NativeZSContainer.ZSReadObject(key, containerId);
        return data;
    }

    /**
     * Read a value from a container with specified key. If the object is
     * expiry, will return empty byte array.
     * 
     * @param key
     *            The key of object
     * @return object data as byte array, return empty array if object do not
     *         exist
     * 
     * @throws ZSContainerException
     */
    public byte[] readWithExpiry(byte[] key) throws ZSContainerException {
        byte[] data = NativeZSContainer.ZSReadObjectExpiry(key, containerId);
        return data;
    }

    /**
     * Write to a container with default write mode. The object is written
     * whether or not it already exists.
     * 
     * @param key
     *            object key
     * @param data
     *            object value
     * @throws ZSContainerException
     */
    public void write(byte[] key, byte[] data) throws ZSContainerException {
        write(key, data, WriteObjectMode.ZS_WRITE_EXIST_OR_NOT);
    }

    /**
     * Write to a container with an expiry time. If time is out when read
     * object, the object will be removed and can't be read.
     * 
     * @param key
     *            object key
     * @param data
     *            object value
     * @throws ZSContainerException
     */
    public void writeWithExpiry(byte[] key, byte[] data, int expiry) throws ZSContainerException {
        if (key == null || data == null || key.length == 0) {
            throw new ZSContainerException("Key or value can not be null.");
        }
        int resultCode = NativeZSContainer.ZSWriteObjectExpiry(key, data, containerId,
                WriteObjectMode.ZS_WRITE_EXIST_OR_NOT.ordinal(), expiry);
        ZSExceptionHandler.handleContainer(resultCode, key);
    }

    /**
     * Write object to container with specified write mode. If throw key already
     * exist or not exist exception, will skip it.
     * 
     * @param key
     *            object key
     * @param data
     *            object value
     * @param writeobjectMode
     *            write object mode whether judge key exist
     * @return zs exit code
     * 
     *         <pre>
     * 1. return 1 normally
     * 2. may return 13 (already exist) if write mode is WriteObjectMode.ZS_WRITE_MUST_NOT_EXIST
     * 3. may return 12 (not found) if write mode is WriteObjectMode.ZS_WRITE_MUST_EXIST
     * </pre>
     * @throws ZSContainerException
     */
    public int write(byte[] key, byte[] data, WriteObjectMode writeobjectMode) throws ZSContainerException {
        if (key == null || data == null || key.length == 0) {
            throw new ZSContainerException("Key or value can not be null.");
        }
        int resultCode = NativeZSContainer.ZSWriteObject(key, data, containerId, writeobjectMode.ordinal());
        if (resultCode == 12 || resultCode == 13) {
            // used for writeobjectMode = 1 or 2
            return resultCode; // 13 key already exist, 12 key does not exist
        }
        ZSExceptionHandler.handleContainer(resultCode, key);
        return resultCode; // return 1 as normal
    }

    /**
     * Delete the object with specified key. If the key is not exist, will not
     * throw exception.
     * 
     * @param key
     *            object key
     * @return zs exit code
     * 
     *         <pre>
     * 1. return 1 normally
     * 2. may return 12 if object does not exist
     * </pre>
     * @throws ZSContainerException
     */
    public int delete(byte[] key) throws ZSContainerException {
        int resultCode = NativeZSContainer.ZSDeleteObject(key, containerId);
        if (resultCode == 12) {
            // handle the object not exist, would not throw the exception
            return resultCode;
        }
        ZSExceptionHandler.handleContainer(resultCode, key);
        // result code returned would be 1 (success)or 12(object not found)
        return resultCode;
    }

    /**
     * Flush the object with specified from cache to storage.
     * 
     * @param key
     *            object key
     * @throws ZSContainerException
     */
    public void flushToObject(byte[] key) throws ZSContainerException {
        int resultCode = NativeZSContainer.ZSFlushObject(key, containerId);
        ZSExceptionHandler.handleContainer(resultCode, key);
    }

    /**
     * Retrieve access statistics for a particular container.
     * 
     * @return zs stats instance
     * @throws ZSContainerException
     */

    public ZSStatistics getStats() throws ZSContainerException {
        ZSStatistics stats = new ZSStatistics();
        int resultCode = NativeZSContainer.ZSGetContainerStats(containerId, stats);
        ZSExceptionHandler.handleContainer(resultCode);
        return stats;
    }

    /**
     * Retrieve the properties for a particular container.
     * 
     * @return zs container properties.
     */
    public ContainerProperty getProperties() throws ZSContainerException {
        return property;
    }

    /**
     * Change the properties for a particular container. Note that only a small
     * subset of container properties can be changed dynamically.
     * 
     * @param containerProp
     *            The container properties
     * @throws ZSContainerException
     */
    public void setContainerProperties(ContainerProperty containerProp) throws ZSContainerException {
        this.property = containerProp;
        int resultCode = NativeZSContainer.ZSSetContainerProps(containerId, containerProp);
        ZSExceptionHandler.handleContainer(resultCode);
    }

    /**
     * Create a snapshot and return the snapshot instance.
     * 
     * @return zs snapshot instance
     * @throws ZSContainerException
     */
    public ZSSnapshot createSnapshot() throws ZSContainerException {
        long snapSeq =  NativeZSSnapshot.ZSCreateContainerSnapshot(containerId);
        return new ZSSnapshot(snapSeq);
    }

    /**
     * Delete snapshot.
     * @param snap
     *          snapshot which be deleted
     * @throws ZSContainerException
     */
    public void deleteSnapshot(ZSSnapshot snap) throws ZSContainerException {
        NativeZSSnapshot.ZSDeleteContainerSnapshot(containerId, snap.getSnapSeq());
    }

    /**
     * Get all snapshots in container.
     * 
     * @return snapshot instance array
     * @throws ZSContainerException
     */
    public ZSSnapshot[] getSnapshots() throws ZSContainerException {
        return NativeZSSnapshot.ZSGetContainerSnapshots(containerId);
    }

    // public int mSet(ZSMData[] datas){
    // int size = datas.length;
    // if(size == 0){
    // return 0;
    // }
    // int succ_num = NativeZSContainer.ZSMSet(containerId, datas);
    // return succ_num;
    // }

    // public int mGet(ZSMData[] datas){
    // if(datas.length == 0){
    // return 0;
    // }
    // int succ_num = NativeZSContainer.ZSMGet( containerId, datas);
    // return succ_num;
    // }

    /**
     * Write multi objects to container with specified write mode.. The object
     * key and value wrapped to ZSMData object.
     * 
     * @param datas
     *            objects to be written
     * @param writeObjectMode
     *            write object mode whether judge key exist
     * @return zs exit code
     * @throws ZSContainerException
     */
    public int multiPut(ZSMData[] datas, WriteObjectMode writeObjectMode) throws ZSContainerException {
        if (datas.length == 0) {
            return 1;
        }
        int re = NativeZSContainer.ZSMPut(containerId, datas, writeObjectMode.ordinal());
        ZSExceptionHandler.handleContainer(re);
        return re;
    }

    /**
     * Write multi objects to container with default write mode which is
     * read-write mode. The object key and value wrapped to ZSMData object.
     * 
     * @param datas
     *            objects to be written
     * @return zs exit code
     * @throws ZSContainerException
     */
    public int multiPut(ZSMData[] datas) throws ZSContainerException {
        int re = multiPut(datas, WriteObjectMode.ZS_WRITE_EXIST_OR_NOT);
        return re;
    }

    /**
     * Bulk operation
     * 
     * @param bulkOps
     *            operation array
     * @return ZS return code, return 1 normally
     * 
     *         <pre>
     * For detailed return code of each operation, please reference bulkOps[i].retCode()
     * </pre>
     */
    private int bulkOp(ZSObjectOp[] bulkOps) throws ZSContainerException {
        int re = NativeZSContainer.ZSBulkOp(containerId, (ZSObjectOp[]) bulkOps);
        return re;
    }

    /**
     * Bulk operation
     * 
     * @param bulkOps
     *            operation list
     * @return ZS return code, return 1 normally
     * 
     *         <pre>
     * For detailed return code of each operation, please reference bulkOps.get(i).retCode()
     * </pre>
     */
    public int bulkOp(List<ZSObjectOp> bulkOps) throws ZSContainerException {
        ZSObjectOp[] ops = new ZSObjectOp[bulkOps.size()];
        for (int i = 0; i < bulkOps.size(); i++) {
            ZSObjectOp op = bulkOps.get(i);
            if (op == null) {
                throw new ZSContainerException("Bulk operation object can't be null.");
            }
            if (op.getKey() == null || op.getKey().length == 0) {
                throw new ZSContainerException("Bulk operation object key can't be null or empty.");
            }
            if ((op.getOptType() == ZSObjectOpType.ADD || op.getOptType() == ZSObjectOpType.REPLACE)
                    && op.getData() == null) {
                throw new ZSContainerException(
                        "If operation type is ADD or REPLACE, bulk operation object data can't be null.");
            }
            ops[i] = bulkOps.get(i);
        }
        return this.bulkOp(ops);
    }

    /**
     * Bulk enumeration start
     * 
     * @return ZS return code, return 1 normally
     * @throws ZSContainerException
     */
    public int bulkEnumCreate() throws ZSContainerException {
        return NativeZSContainer.ZSBulkEnumCreate(containerId);
    }

    /**
     * Bulk enumeration next which fetch batch data from ZS storage
     * 
     * @return List of ZSObjectOp
     * @throws ZSContainerException
     */
    public List<ZSObjectOp> bulkEnumNext(int count) throws ZSContainerException {
        return Arrays.asList(NativeZSContainer.ZSBulkEnumNext(containerId, count));
    }

    /**
     * Bulk enumeration finish which release all resources
     * 
     * @return ZS return code, return 1 normally
     * @throws ZSContainerException
     */
    public int bulkEnumFinsh() throws ZSContainerException {
        return NativeZSContainer.ZSBulkEnumFinish(containerId);
    }

    /**
     * Iterator wrapper of Bulk enumeration
     * 
     * @param enum_count
     *            the number want to enumerate
     * @return ZSBulkIterator which implement Iterator interface
     * @throws ZSContainerException
     */
    public ZSBulkIterator iterator(int enum_count) throws ZSContainerException {
        return new ZSBulkIterator(this, enum_count);
    }

    /**
     * Force any buffered changes to container and sync storage.
     * 
     * @throws ZSContainerException
     */
    public void flushToContainer() throws ZSContainerException {
        int resultCode = NativeZSContainer.ZSFlushContainer(containerId);
        ZSExceptionHandler.handleContainer(resultCode);
    }
}
