package top.young.tools.util;

import org.apache.commons.lang3.ArrayUtils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class EnDeCryptUtil {

    /**
     * 加密
     * @param content 需要加密的内容
     * @param keyWord 加密密钥
     * @return byte[] 加密后的字节数组
     * @throws Exception
     */
    private static byte[] encrypt(String content, String keyWord) throws NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(keyWord.getBytes());
        kgen.init(128, secureRandom);
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
        Cipher cipher = Cipher.getInstance("AES");// 创建密码器
        byte[] byteContent = content.getBytes("utf-8");
        cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
        return cipher.doFinal(byteContent); // 加密
    }
    /**
     * @param content 需要加密的内容
     * @param password 加密密钥
     * @return String 加密后的字符串
     * @throws Exception
     */
    private static String encrypttoStr(String content, String password) throws NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidKeyException {
        return parseByte2HexStr(encrypt(content, password));
    }
    /**解密
     * @param content 待解密内容
     * @param keyWord 解密密钥
     * @return byte[]
     * @throws Exception
     */
    private static byte[] decrypt(byte[] content, String keyWord) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(keyWord.getBytes());
        kgen.init(128, secureRandom);
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
        Cipher cipher = Cipher.getInstance("AES");// 创建密码器
        cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
        return cipher.doFinal(content); // 加密
    }
    /**
     * @param content 待解密内容(字符串)
     * @param keyWord 解密密钥
     * @return byte[]
     * @throws Exception
     */
    private static byte[] decrypt(String content, String keyWord) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        return decrypt(parseHexStr2Byte(content), keyWord);
    }
    /**将二进制转换成16进制
     * @param buf
     * @return String
     */
    private static String parseByte2HexStr(byte[] buf) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }
    /**将16进制转换为二进制
     * @param hexStr
     * @return byte[]
     */
    private static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return ArrayUtils.EMPTY_BYTE_ARRAY;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
    private static String Key = "eJiAyOu^2014";
    /**字符串解密**/
    public static String decString(String inputString) {
        String outputString = "";
        if (inputString == null || "".equals(inputString)) {
            return outputString;
        }
        try {
            outputString = new String(decrypt(inputString, Key));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            outputString = inputString;

        }
        return outputString;
    }
    /**字符串加密**/
    public static String encString(String inputString) {
        String outputString = "";
        if (inputString == null || "".equals(inputString)) {
            return outputString;
        }
        try {
            outputString = encrypttoStr(inputString, Key);
        } catch (Exception e) {
            System.out.println( e.getMessage());
            outputString = inputString;
        }
        return outputString;
    }
}

