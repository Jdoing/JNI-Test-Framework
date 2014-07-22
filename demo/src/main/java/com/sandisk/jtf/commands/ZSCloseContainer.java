package com.sandisk.jtf.commands;

import com.sandisk.jtf.JTFCommand;
import com.sandisk.jtf.JTFUtils;
import com.sandisk.jtf.exception.JTFException;

import com.sandisk.zs.exception.ZSContainerException;

/**
 * Created by ray on 7/9/14.
 */
public class ZSCloseContainer extends JTFCommand
{
    public String execute()
    {
        try {
            JTFUtils.getContainer(getLongProperty("cguid", true)).close();
            return "OK";
        } catch (ZSContainerException e) {
            return "SERVER_ERROR " + e.toString();
        } catch (JTFException e) {
            return "CLIENT_ERROR " + e.toString();
        }
    }
}

