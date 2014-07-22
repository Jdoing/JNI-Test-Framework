package com.sandisk.jtf.commands;

import com.sandisk.jtf.JTFCommand;
import com.sandisk.jtf.JTFUtils;
import com.sandisk.jtf.exception.JTFException;

import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zs.type.ContainerProperty;
import com.sandisk.zs.type.DurabilityLevel;

/**
 * FDFGetContainerProps cguid=%u
 * must have: cguid
 *
 * return:
 * success:
 * OK
 * cguid=%u
 * fifo_mode=0|1
 * evicting=0|1
 * writethru=0|1
 * size=%u
 * durability_level=0|1
 * num_shards=%u
 *
 * failed: SERVER_ERROR %s | CLIENT_ERROR %s
 */
public class ZSGetContainerProps extends JTFCommand
{
    public String execute()
    {
        try {
            return assembleRetStr(JTFUtils.getContainer(getLongProperty("cguid", true)).getProperties());
        } catch (ZSContainerException e) {
            return "SERVER_ERROR " + e.toString();
        } catch (JTFException e) {
            return "CLIENT_ERROR " + e.toString();
        }
    }

    private String assembleRetStr(ContainerProperty containerProps)
    {
        String ret = "OK";
        ret += ("\ncguid=" + containerProps.getContainerId());
        ret += ("\nfifo_mode=" + (containerProps.getFifoMode() ? 1 : 0));
        ret += ("\nevicting=" + (containerProps.getEvicting() ? 1 : 0));
        ret += ("\nwritethru=" + (containerProps.getWritethru() ? 1 : 0));
        ret += ("\nsize=" + containerProps.getSize());
        DurabilityLevel durabilityLevel = containerProps.getDurabilityLevel();
        int dLevel = -1;
        if (durabilityLevel == DurabilityLevel.ZS_DURABILITY_PERIODIC) {
            dLevel = 0;
        } else if (durabilityLevel == DurabilityLevel.ZS_DURABILITY_SW_CRASH_SAFE) {
            dLevel = 1;
        } else if (durabilityLevel == DurabilityLevel.ZS_DURABILITY_HW_CRASH_SAFE) {
            dLevel = 2;
        }
        ret += ("\ndurability_level=" + dLevel);
        ret += ("\nnum_shards=" + containerProps.getShardNumber());
        return ret;
    }
}

