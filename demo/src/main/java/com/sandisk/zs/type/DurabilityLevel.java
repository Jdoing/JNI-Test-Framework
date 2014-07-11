package com.sandisk.zs.type;

/**
 * File: DurabilityLevel.java 
 * Author: zane, ymiao, candy
 * 
 * Created on July 30, 2013
 * 
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com THIS IS NOT A CONTRIBUTION
 */

/**
 * Durability level for container.
 */
public enum DurabilityLevel {
    ZS_DURABILITY_PERIODIC, ZS_DURABILITY_SW_CRASH_SAFE, ZS_DURABILITY_HW_CRASH_SAFE;

    public static DurabilityLevel valueOf(int value) {
        switch (value) {
        case 0:
            return ZS_DURABILITY_PERIODIC;
        case 1:
            return ZS_DURABILITY_SW_CRASH_SAFE;
        case 2:
            return ZS_DURABILITY_HW_CRASH_SAFE;
        default:
            return null;
        }
    }
}
