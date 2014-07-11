package com.sandisk.zs;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class JNICallbackHelper {

    private static final ThreadLocal<MessageDigest> localMD5Digest = new ThreadLocal<MessageDigest>()
    {
        @Override
        protected MessageDigest initialValue()
        {
            return newMessageDigest("MD5");
        }

        @Override
        public MessageDigest get()
        {
            MessageDigest digest = super.get();
            digest.reset();
            return digest;
        }
    };

    private static MessageDigest newMessageDigest(String algorithm)
    {
        try
        {
            return MessageDigest.getInstance(algorithm);
        }
        catch (NoSuchAlgorithmException nsae)
        {
            throw new RuntimeException("the requested digest algorithm (" + algorithm + ") is not available", nsae);
        }
    }

    private static BigDecimal composeBigDecimal(ByteBuffer bytes)
    {
        bytes = bytes.duplicate();
        int scale = bytes.getInt();
        byte[] bibytes = new byte[bytes.remaining()];
        bytes.get(bibytes);

        BigInteger bi = new BigInteger(bibytes);
        return new BigDecimal(bi,scale);
    }

    public static int compareBigDecimal(byte[] a,byte[] b) {
        return composeBigDecimal(ByteBuffer.wrap(a)).compareTo(composeBigDecimal(ByteBuffer.wrap(b)));
    }

    public static byte[] hash(byte[] a) {
        MessageDigest messageDigest = localMD5Digest.get();
        ByteBuffer block = ByteBuffer.wrap(a);
        messageDigest.update(block.array(), block.arrayOffset() + block.position(), block.remaining());
        return messageDigest.digest();
    }
}
