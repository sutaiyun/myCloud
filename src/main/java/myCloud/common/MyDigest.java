package myCloud.common;

import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.apache.commons.codec.binary.Base64.encodeBase64String;

/**
 * Created by sutaiyun on 2017/1/7.
 */
public enum  MyDigest {
    INSTANCE;

    private static MessageDigest getMessageDigest(String src, String algorithm) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        md.reset();
        md.update(src.getBytes("UTF-8"));
        return md;
    }

    public final static String md5(String src) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return Hex.encodeHexString(getMessageDigest(src, "MD5").digest());
    }

    public static String sha(String src) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return Hex.encodeHexString(getMessageDigest(src, "SHA").digest());
    }

    public static String sha1(String src) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return Hex.encodeHexString(getMessageDigest(src, "SHA-1").digest());
    }

    public final static String toMd5Base64(String src) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        /*
        BASE64Encoder base64en = new BASE64Encoder();
        String strMd5=base64en.encode(getMessageDigest(src, "MD5").digest());
        */
        return encodeBase64String(getMessageDigest(src, "MD5").digest());
    }
}
