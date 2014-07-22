package com.sandisk.jtf;

import java.util.Properties;
import com.sandisk.jtf.exception.JTFException;

/**
 * Created by ray on 7/9/14.
 */
public abstract class JTFCommand
{
    private Properties args;

    public void setArgs(Properties args)
    {
        this.args = args;
    }

    private String getProperty(String key, boolean must) throws JTFException
    {
        String value = args.getProperty(key);
        if (value == null && must) {
            throw new JTFException(key + " not found");
        } else {
            return value;
        }
    }

    protected String getStringProperty(String key, boolean must) throws JTFException
    {
        return  getProperty(key, must);
    }

    protected Integer getIntegerProperty(String key, boolean must) throws JTFException
    {
        String value = getProperty(key, must);
        return value == null ? null : new Integer(value);
    }

    protected Long getLongProperty(String key, boolean must) throws JTFException
    {
        String value = getProperty(key, must);
        return value == null ? null : new Long(value);
    }

    protected Boolean getBooleanProperty(String key, boolean must) throws JTFException
    {
        String value = getProperty(key, must);
        return value == null ? null : (value.equals("yes") ? Boolean.TRUE : Boolean.FALSE);
    }

    public abstract String execute();
}

