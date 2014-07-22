package com.sandisk.jtf.commands;

import com.sandisk.jtf.JTFCommand;
import com.sandisk.jtf.JTFUtils;

import com.sandisk.zs.exception.ZSException;

public class ZSGetContainers extends JTFCommand
{
    public String execute()
    {
        try {
            long[] containerIDs = JTFUtils.getZSInstance().getContainers();
            String ret = "OK n_cguids=" + containerIDs.length;
            for (int i = 0; i < containerIDs.length; ++i) {
                ret += ((i == 0 ? "\n" : " ") + containerIDs[i]);
            }
            return ret;
        } catch (ZSException e) {
            return "SERVER_ERROR " + e.toString();
        }
    }
}

