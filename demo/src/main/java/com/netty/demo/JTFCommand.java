package com.netty.demo;
import java.util.Properties;

/**
 * Created by ray on 7/9/14.
 */
public abstract class JTFCommand
{
    protected Properties args = null;

    public void setArgs(Properties args)
    {
        this.args = args;
    }

    public abstract String execute();
}
