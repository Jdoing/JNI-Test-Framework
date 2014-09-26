package com.sandisk.zsjtf.util;

import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import com.sandisk.zsjtf.exception.JTFException;

public class MiscUtils
{
    private final static String zsPropFile = "/tmp/zsjtf.prop";

    public static String getZSPropFile()
    {
        return zsPropFile;
    }

    public static void genZSPropFile() throws Exception
    {
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(zsPropFile);
            Properties prop = new Properties();
            prop.setProperty("ZS_CACHE_SIZE", "0x40000000");
            prop.setProperty("ZS_FLASH_FILENAME", "/tmp/sandisk0");
            prop.setProperty("ZS_FLASH_SIZE", "4G");
            prop.setProperty("ZS_ADMIN_ENABLED", "1");
            prop.setProperty("ZS_STATS_DUMP_INTERVAL", "10");
            prop.setProperty("ZS_STATS_FILE", "/tmp/ZSstats.log");
            prop.setProperty("ZS_REFORMAT", "1");
            prop.store(fos, "Auto-generated for JNI Test Framework");
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }

    public static void decodeIntegerKey(byte[] dist, int integerKey, int keyLength)
    {
        /* Generate string formatter like "%09d", here '9' is length */
        String formatter = String.format("%%0%dd", keyLength);
        String src = String.format(formatter, integerKey);
        //System.arraycopy(src.getBytes(), 0, dist, 0, keyLength);
        //modified by yjiang for the command of GetRange when nops of WriteObject larger than 10
        System.arraycopy(src.getBytes(), 0, dist, 0, src.length());
    }

	public static String specialFormat(Object keyLength, Object integerKey) {
		String formatter = String.format("%%0%dd", keyLength);
		String result = String.format(formatter, integerKey);
		return result;
	}
    
    public static int encodeIntegerKey(byte[] key) throws JTFException
    {
        try {
            return Integer.parseInt(new String(key, "US-ASCII"));
        } catch (UnsupportedEncodingException e){
            throw new JTFException(e.toString());
        }
    }
}
