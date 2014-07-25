package com.sandisk.jtf.commands;

import java.util.Arrays;

import com.sandisk.jtf.JTFCommand;
import com.sandisk.jtf.JTFUtils;
import com.sandisk.jtf.exception.JTFException;
import com.sandisk.jtf.util.ByteBuffManager;

import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zs.exception.ZSException;

/**
 * Created by ray on 7/9/14.
 * FDFReadObject
 * cguid=%u
 * key_offset=%d
 * key_len=%u
 * data_offset=%d
 * data_len=%d
 * nops=%d
 * check=yes|no
 * keep_read=yes|no
 *
 * must have: cguid
 *
 * return:
 * success: OK
 * failed: SERVER_ERROR %s | CLIENT_ERROR %s
 */
public class ZSReadObject extends JTFCommand
{
    // Command arg list start
    private Long containerID;

    private Integer initialKeyOffset; // Specify key start pos.
    private Integer keyLength;

    private Integer initialDataOffset; // Specify verify data start pos.
    private Integer dataLength;

    private Integer nops; // Specify read operation numbers.
    private Boolean check; // Specify if we should verify data after read ops finish.
    private Boolean keepRead; // Specify if command should discontinued when read error happens.
    // Command arg list end

    private int readFailedCnt = 0;
    private int verifyFailedCnt = 0;

    public String execute()
    {
        try {
            getProperties();
            readObject();
            return handleSuccessReturn();
        } catch (ZSException e) {
            return handleServerErrorReturn(e);
        } catch (JTFException e) {
            return handleClientErrorReturn(e);
        }
    }

    private void getProperties() throws JTFException
    {
        containerID = getLongProperty(CGUID, true);
        initialKeyOffset = getIntegerProperty(KEY_OFFSET, false, 0);
        keyLength = getIntegerProperty(KEY_LEN, false, 0);
        initialDataOffset = getIntegerProperty(DATA_OFFSET, false, 0);
        dataLength = getIntegerProperty(DATA_LEN, false, 0);
        nops = getIntegerProperty(NOPS, false, 0);
        check = getBooleanProperty(CHECK, false, true);
        keepRead = getBooleanProperty(KEEP_READ, false, false);
    }

    private void readObject() throws JTFException, ZSContainerException
    {
        ZSContainer container = JTFUtils.getContainer(containerID);

        int keyOffset;
        int dataOffset;

        byte[] result = new byte[0];
        byte[] key = new byte[keyLength];
        byte[] data = new byte[dataLength];

        for (int i = 0; i < nops; ++i) {
            keyOffset = initialKeyOffset + i;
            ByteBuffManager.getInstance().arraycopy(key, keyOffset, keyLength);
            try {
                result = container.read(key);
            } catch (ZSContainerException e) {
                if (keepRead) {
                    readFailedCnt++;
                } else {
                    throw e;
                }
            }
            if (check) {
                dataOffset = initialDataOffset + i;
                ByteBuffManager.getInstance().arraycopy(data, dataOffset, dataLength);
                if (!Arrays.equals(data, result)) {
                    verifyFailedCnt++;
                }
            }
        }
    }

    @Override
    protected String handleSuccessReturn() throws ZSException
    {
        if (readFailedCnt == 0 && verifyFailedCnt == 0) {
            return "OK";
        } else {
            String msg = readFailedCnt + " items read failed " + verifyFailedCnt + " items check failed";
            throw new ZSException(msg);
        }
    }
}
