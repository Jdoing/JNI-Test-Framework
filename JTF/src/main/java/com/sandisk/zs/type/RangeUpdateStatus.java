package com.sandisk.zs.type;

/**
 * File: RangeUpdateStatus.java 
 * Author: zane, ymiao, candy
 * 
 * Created on July 30, 2013
 * 
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com THIS IS NOT A CONTRIBUTION
 */

/**
 * Range update status for tombstone.
 */
public class RangeUpdateStatus
{

    private int updated_obj_num;
    private int potential_tombstone_num;

    public int getUpdated_obj_num()
    {
        return updated_obj_num;
    }
    public int getPotential_tombstone_num()
    {
        return potential_tombstone_num;
    }
}