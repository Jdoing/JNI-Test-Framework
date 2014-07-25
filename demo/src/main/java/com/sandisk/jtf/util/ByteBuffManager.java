package com.sandisk.jtf.util;

import java.util.Random;

import com.sandisk.jtf.exception.JTFException;

public class ByteBuffManager
{
    private static ByteBuffManager instance = new ByteBuffManager();

    private final int buffSize = 200 * 1024 * 1024; // 200MB
    private final int maxDataSize = 8 * 1024 * 1024; // 8MB
    private final String ALPHANUMBERICS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.-";

    private byte[] byteBuff;

    public static ByteBuffManager getInstance()
    {
        return instance;
    }

    private ByteBuffManager()
    {
        byteBuff = new byte[buffSize];
        fillBuff();
    }

    /**
     * Wrapper of System.arraycopy.
     */
    public void arraycopy(byte[] dist, int offset, int length)
        throws JTFException, ArrayStoreException, NullPointerException
    {
        if (length > maxDataSize) {
            throw new JTFException("Data size beyond limitation");
        }
        try {
            /*
             * Here we only handle IndexOutOfboundsException.
             * Should not raise ArrayStoreException nor NullPointerException or there
             * is an internal error.
             *
             * From oracle javase7 doc:
             *
             *   If any of the following is true, an IndexOutOfBoundsException
             *   is thrown and the destination is not modified:
             *
             *   - The srcPos argument is negative.
             *   - The destPos argument is negative.
             *   - The length argument is negative.
             *   - srcPos+length is greater than src.length, the length of the source array.
             *   - destPos+length is greater than dest.length, the length of the destination array.
             */
            System.arraycopy(byteBuff, offset, dist, 0, length);
        } catch(IndexOutOfBoundsException e) {
            throw new JTFException(e.toString());
        }
    }

    private void fillBuff()
    {
        Random rnd = new Random();
        for (int i = 0; i < byteBuff.length; ++i) {
            byteBuff[i] = (byte)ALPHANUMBERICS.charAt(rnd.nextInt(ALPHANUMBERICS.length()));
        }
    }
}
