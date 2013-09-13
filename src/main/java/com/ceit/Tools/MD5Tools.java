package com.ceit.Tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-9-10
 * Time: 下午5:08
 * To change this template use File | Settings | File Templates.
 */
public class MD5Tools {
    public final static String getMD5_Str(String str)
    {
        String md5_str="";
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        try {
            byte[] str_byte = str.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str_byte);
            byte[] md = messageDigest.digest();
            int i = md.length;
            char  ch[] = new char[i*2];
            int k = 0;
            for (int j = 0;j < i;j++)
            {
                byte byte0 = md[j];
                ch[k++] = hexDigits[byte0 >>> 4 & 0xf];
                ch[k++] = hexDigits[byte0 & 0xf];
            }
            md5_str =  new String(ch);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return md5_str;
    }
}
