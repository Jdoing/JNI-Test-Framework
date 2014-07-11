package com.sandisk.zs;

/**
 * File:   NativeObject.java
 * Author: zane, ymiao, candy
 *
 * Created on July 30, 2013
 *
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com
 * THIS IS NOT A CONTRIBUTION
 */

/**
 * Provides the public interface to load the ZS JNI.
 */
public class NativeObject {
    private static String ZS_JNI_LIB_NAME = "zsjni";
    static {
        try {
            if (System.getenv("ZS_JNI_LIB_NAME") != null) {
                // check system ENV variable if specify the ZS_JNI_LIB_NAME
                ZS_JNI_LIB_NAME = System.getenv("ZS_JNI_LIB_NAME");
            } else if (System.getProperty("ZS_JNI_LIB_NAME") != null) {
                // check JVM args if specify the ZS_JNI_LIB_NAME
                ZS_JNI_LIB_NAME = System.getProperty("ZS_JNI_LIB_NAME");
            }
            System.loadLibrary(ZS_JNI_LIB_NAME);
        } catch (Exception e) {
            // do nothing for exception
        }
    }
}
