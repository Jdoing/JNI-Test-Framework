package com.sandisk.jtf;

import java.util.Properties;

import com.sandisk.jtf.exception.JTFException;

import com.sandisk.zs.exception.ZSException;

/**
 * Created by ray on 7/9/14.
 */
public abstract class JTFCommand
{
    // Command key listed in alphabetical order
    protected static final String CGUID = "cguid";
    protected static final String CHECK = "check";
    protected static final String DATA_LEN = "data_len";
    protected static final String DATA_OFFSET = "data_offset";
    protected static final String KEEP_READ = "keep_read";
    protected static final String KEY_LEN = "key_len";
    protected static final String KEY_OFFSET = "key_offset";
    protected static final String NOPS = "nops";

    // Default property value listed in alphabetical order
    protected static final int KEY_OFFSET_DEFAULT_VALUE = 0;

    private Properties args;

    public abstract String execute();

    public void setArgs(Properties args)
    {
        this.args = args;
    }

    protected String handleSuccessReturn() throws JTFException, ZSException
    {
        return "OK";
    }

    protected String handleServerErrorReturn(Exception e)
    {
        return "SERVER_ERROR " + e.toString();
    }

    protected String handleClientErrorReturn(Exception e)
    {
        return "CLIENT_ERROR " + e.toString();
    }

    protected String getStringProperty(String key, boolean must) throws JTFException
    {
        return  getProperty(key, must);
    }

    protected String getStringProperty(String key, boolean must, String defaultValue) throws JTFException
    {
        String value = getProperty(key, must);
        return value == null ? defaultValue : value;
    }

    protected Integer getIntegerProperty(String key, boolean must) throws JTFException
    {
        String value = getProperty(key, must);
        try {
            return value == null ? null : new Integer(value);
        } catch (NumberFormatException e) {
            throw new JTFException(e.toString());
        }
    }

    protected Integer getIntegerProperty(String key, boolean must, Integer defaultValue) throws JTFException
    {
        String value = getProperty(key, must);
        try {
            return value == null ? defaultValue : new Integer(value);
        } catch(NumberFormatException e) {
            throw new JTFException(e.toString());
        }
    }

    protected Long getLongProperty(String key, boolean must) throws JTFException
    {
        String value = getProperty(key, must);
        try {
            return value == null ? null : new Long(value);
        } catch (NumberFormatException e) {
            throw new JTFException(e.toString());
        }
    }

    protected Long getLongProperty(String key, boolean must, Long defalutValue) throws JTFException
    {
        String value = getProperty(key, must);
        try {
            return value == null ? defalutValue : new Long(value);
        } catch (NumberFormatException e) {
            throw new JTFException(e.toString());
        }
    }

    protected Boolean getBooleanProperty(String key, boolean must) throws JTFException
    {
        String value = getProperty(key, must);
        if (!value.equals("yes") && !value.equals("no")) {
            throw new JTFException(key + "neither \'yes\' nor \'no\'");
        }
        return value == null ? null : (value.equals("yes") ? Boolean.TRUE : Boolean.FALSE);
    }

    protected Boolean getBooleanProperty(String key, boolean must, Boolean defalutValue) throws JTFException
    {
        String value = getProperty(key, must);
        if (!value.equals("yes") && !value.equals("no")) {
            throw new JTFException(key + "neither \'yes\' nor \'no\'");
        }
        return value == null ? defalutValue : (value.equals("yes") ? Boolean.TRUE : Boolean.FALSE);
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
}

