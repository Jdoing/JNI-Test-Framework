package com.sandisk.jtf.commands;

import com.sandisk.jtf.JTFCommand;
import com.sandisk.jtf.JTFUtils;
import com.sandisk.jtf.exception.JTFException;

import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zs.type.ContainerProperty;
import com.sandisk.zs.type.DurabilityLevel;

/**
 * ZSSetContainerProps.
 * cguid=%u
 * fifo_mode=yes|no
 * persistent=yes|no
 * evicting=yes|no
 * writethru=yes|no
 * size=%d
 * durability_level=FDF_FULL_DURABILITY|FDF_PERIODIC_DURABILITY
 * num_shards=%d
 *
 * must have: cguid
 *
 * return:
 * success: OK
 * failed: SERVER_ERROR %s | CLIENT_ERROR %s
 */
public class ZSSetContainerProps extends JTFCommand
{
    public String execute()
    {
        try {
            setContainerProps();
            return "OK";
        } catch (ZSContainerException e) {
            return "SERVER_ERROR " + e.toString();
        } catch (JTFException e) {
            return "CLIENT_ERROR " + e.toString();
        }
    }

    private void setContainerProps() throws JTFException, ZSContainerException
    {
        Long containerID = getLongProperty("cguid", true);

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

        ZSContainer container = JTFUtils.getContainer(containerID);
        ContainerProperty containerProps = container.getProperties();

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

        container.setContainerProperties(containerProps);
    }
}

