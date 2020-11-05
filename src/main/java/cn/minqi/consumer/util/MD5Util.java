package cn.minqi.consumer.util;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @author minqi
 */
public class MD5Util {

    public static String getMd5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            String md5 = new BigInteger(1, md.digest()).toString(16);
            return fillMd5(md5);
        } catch (Exception e) {
            throw new RuntimeException("MD5 encryption error:" + e.getMessage(), e);
        }
    }

    public static String fillMd5(String md5) {
        return md5.length() == 32 ? md5 : fillMd5("0" + md5);
    }
}