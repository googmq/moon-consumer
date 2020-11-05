package cn.minqi.consumer.util;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import org.springframework.util.StringUtils;

/**
 * 字符串加密工具类
 *
 * @author minqi
 */
public final class StringEncoder {

    /**
     * UTF-8字符集
     */
    private static final Charset UTF8 = Charset.forName("UTF-8");
    /**
     * MD5算法
     */
    private static final String ALGORITHM_MD5 = "MD5";
    /**
     * SHA-1
     */
    private static final String ALGORITHM_SHA1 = "SHA-1";
    /**
     * SHA-256
     */
    private static final String ALGORITHM_SHA256 = "SHA-256";

    private StringEncoder() {
    }

    /**
     * 按照指定算法加密字符串
     *
     * @param algorithm 算法名称
     * @param str       源字符串
     * @return 加密字符串结果
     */
    private static String encode(String algorithm, String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(str.getBytes(UTF8));
            return toHexString(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * encode By MD5
     *
     * @param str 源字符串
     * @return 加密结果字符串
     */
    public static String encodeByMD5(String str) {
        return encode(ALGORITHM_MD5, str);
    }

    /**
     * encode By sha1
     *
     * @param str 源字符串
     * @return 加密结果字符串
     */
    public static String encodeBySHA1(String str) {
        return encode(ALGORITHM_SHA1, str);
    }

    /**
     * encode By sha256
     *
     * @param str 源字符串
     * @return 加密结果字符串
     */
    public static String encodeBySHA256(String str) {
        return encode(ALGORITHM_SHA256, str);
    }

    /**
     * 将原始字符串与KEY的每个byte进行异或操作，得到最终加密字符串
     *
     * @param encodeStr 原始字符串
     * @param key       密码字符串
     * @return 16进制转换的加密字符串
     */
    public static String encodeByXOR(String encodeStr, String key) {
        byte[] b = encodeStr.getBytes(UTF8);
        byte[] keyBytes = key.getBytes(UTF8);

        for (int i = 0, size = b.length; i < size; i++) {
            for (byte keyBytes0 : keyBytes) {
                b[i] = (byte) (b[i] ^ keyBytes0);
            }
        }

        return toHexString(b);
    }

    /**
     * 将16进制加密字符串通过再次异或转换为原始字符串
     *
     * @param decodeStr 16进制加密字符串
     * @param key       密码字符串
     * @return 解密字符串
     */
    public static String decodeByXOR(String decodeStr, String key) {
        byte[] e = toByteArray(decodeStr);
        byte[] keyBytes = key.getBytes(UTF8);

        byte[] dee = e;
        for (int i = 0, size = e.length; i < size; i++) {
            for (byte keyBytes0 : keyBytes) {
                e[i] = (byte) (dee[i] ^ keyBytes0);
            }
        }

        return new String(e, UTF8);
    }

    /**
     * 将16进制字符串转换为字节数组
     *
     * @param hexString 16进制字符串
     * @return 字节数组
     */
    public static byte[] toByteArray(String hexString) {
        if (StringUtils.isEmpty(hexString)) {
            return null;
        }
        hexString = hexString.toLowerCase();
        final byte[] byteArray = new byte[hexString.length() >> 1];
        int index = 0;
        for (int i = 0; i < hexString.length(); i++) {
            if (index > hexString.length() - 1) {
                return byteArray;
            }
            byte highDit = (byte) (Character.digit(hexString.charAt(index), 16) & 0xFF);
            byte lowDit = (byte) (Character.digit(hexString.charAt(index + 1), 16) & 0xFF);
            byteArray[i] = (byte) (highDit << 4 | lowDit);
            index += 2;
        }
        return byteArray;
    }

    /**
     * 将子节数组转换为16进制字符串
     *
     * @param byteArray 子节数组
     * @return 16进制字符串
     */
    public static String toHexString(byte[] byteArray) {
        final StringBuilder hexString = new StringBuilder("");
        if (byteArray == null || byteArray.length <= 0) {
            return null;
        }
        for (int i = 0; i < byteArray.length; i++) {
            int v = byteArray[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                hexString.append(0);
            }
            hexString.append(hv);
        }
        return hexString.toString().toLowerCase();
    }
}
