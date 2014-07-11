package com.sandisk.zs.type;

import java.util.Iterator;
import java.util.List;

import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.exception.ZSContainerException;

/**
 * File: ZSBulkIterator.java 
 * Author: zane, ymiao, candy
 * 
 * Created on July 30, 2013
 * 
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com THIS IS NOT A CONTRIBUTION
 */

/**
 * Provide a iterator to iterate the bulk enumeration.
 */
public class ZSBulkIterator implements Iterator<ZSObjectOp>
{
    private int index;
    private int enum_count;
    ZSContainer container;
    List<ZSObjectOp> list;

    public ZSBulkIterator(ZSContainer container, int enum_count) throws ZSContainerException
    {
        this.index = 0;
        this.enum_count = enum_count;
        this.container = container;
        this.container.bulkEnumCreate();
    }

    @Override
    public boolean hasNext()
    {
        if (index == 0)
        {
            try
            {
                list = container.bulkEnumNext(enum_count);
                if (list != null && list.size() > 0)
                {
                    return true;
                }
                else
                {
                    container.bulkEnumFinsh();
                }
            }
            catch (ZSContainerException e)
            {
                e.printStackTrace();
            }

        }
        else
        {
            if (index == list.size())
            {
                index = 0;
                return hasNext();
            }
            else
            {
                return true;
            }
        }
        // no data can be fetch now..
        return false;
    }

    @Override
    public ZSObjectOp next()
    {
        ZSObjectOp next = list.get(index);
        index++;
        return next;
    }

    @Override
    public void remove()
    {
        throw new RuntimeException("Remote operation is not supported");
    }

}
