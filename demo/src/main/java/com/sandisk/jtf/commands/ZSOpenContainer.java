package com.sandisk.jtf.commands;

import com.sandisk.jtf.JTFCommand;
import com.sandisk.jtf.JTFUtils;
import com.sandisk.jtf.exception.JTFException;

import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zs.type.ContainerMode;
import com.sandisk.zs.type.ContainerProperty;
import com.sandisk.zs.type.DurabilityLevel;
import com.sandisk.zs.type.OpenContainerMode;

/**
 * Created by ray on 7/9/14.
 * FDFOpenContainer
 * cname=%s
 * fifo_mode=yes|no
 * persistent=yes|no
 * evicting=yes|no
 * writethru=yes|no
 * size=%d
 * durability_level=FDF_FULL_DURABILITY|FDF_PERIODIC_DURABILITY
 * num_shards=%d
 * flags=FDF_CTNR_CREATE|FDF_CTNR_RW_MODE
 *
 * must have: cname
 *
 * return:
 * success: OK cguid=%ull
 * failed: SERVER_ERROR %s | CLIENT_ERROR %s
 */
public class ZSOpenContainer extends JTFCommand
{
    public String execute()
    {
        try {
            long containerID = openContainer();
            return "OK cguid=" + containerID;
        } catch (ZSContainerException e) {
            return "SERVER_ERROR " + e.toString();
        } catch (JTFException e) {
            return "CLIENT_ERROR " + e.toString();
        }
    }

    private long openContainer() throws JTFException, ZSContainerException
    {
        String containerName = getStringProperty("cname", true);

        Integer containerSize = getIntegerProperty("size", false);
        Integer shardNumber = getIntegerProperty("num_shards", false);

        Boolean isFIFO = getBooleanProperty("fifo", false);
        Boolean isPersistent = getBooleanProperty("persistent", false);
        Boolean isEvicting = getBooleanProperty("evicting", false);
        Boolean isWriteThru = getBooleanProperty("writethru", false);

        DurabilityLevel durabilityLevel = null;
        String dLevel = getStringProperty("durability_level", false);
        if (dLevel != null) {
            if (dLevel.equals("ZS_DURABILITY_SW_CRASH_SAFE")) {
                durabilityLevel = DurabilityLevel.ZS_DURABILITY_SW_CRASH_SAFE;
            } else if (dLevel.equals("ZS_DURABILITY_PERIODIC")) {
                durabilityLevel = DurabilityLevel.ZS_DURABILITY_PERIODIC;
            } else if (dLevel.equals("ZS_DURABILITY_HW_CRASH_SAFE")) {
                durabilityLevel = DurabilityLevel.ZS_DURABILITY_HW_CRASH_SAFE;
            }
        }

        // FIXME: ContainerMode?
        // ContainerMode containerMode = ContainerMode.HASHMODE;
  
        ContainerProperty containerProps = ContainerProperty.getDefautProperty();
        if (containerSize != null) {
            containerProps.setSize(containerSize);
        }
        if (shardNumber != null) {
            containerProps.setShardNumber(shardNumber);
        }
        if (isFIFO != null) {
            containerProps.setFifoMode(isFIFO);
        }
        if (isPersistent != null) {
            containerProps.setPersistent(isPersistent);
        }
        if (isEvicting != null) {
            containerProps.setEvicting(isEvicting);
        }
        if (isWriteThru != null) {
            containerProps.setWritethru(isWriteThru);
        }
        if (durabilityLevel != null) {
            containerProps.setDurabilityLevel(durabilityLevel);
        }

        ZSContainer container;
        long containerID;
        String openMode = getStringProperty("flags", false);
        if (openMode.equals("ZS_CTNR_CREATE")) {
            container = new ZSContainer(containerName, containerProps);
            container.create();
            containerID = container.getContainerId();
            JTFUtils.setNameIDMap(containerName, containerID);
            JTFUtils.setContainer(containerID, container);
        } else {
            containerID = JTFUtils.getNameIDMap(containerName);
            container = JTFUtils.getContainer(containerID);
            OpenContainerMode openContainerMode = null;
            if (openMode.equals("ZS_CTNR_RW_MODE")) {
                openContainerMode = OpenContainerMode.READ_WRITE;
            } else if (openMode.equals("ZS_CTNR_READ_ONLY")) {
                openContainerMode = OpenContainerMode.READ_ONLY;
            }
            container.open(openContainerMode);
        }

        return containerID;
    }
}

