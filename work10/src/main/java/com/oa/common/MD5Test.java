package com.oa.common;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.mysql.jdbc.Buffer;

public class MD5Test   {

	public static void main(String[] args) {
		md5("123456");
	}

    public static String  md5(String content){
    	StringBuffer buffer = null;
            try {
                // 设置摘要的类型
                MessageDigest digest = MessageDigest.getInstance("MD5");
                // 如果是Sha 摘要 这个改
                // MessageDigest digest = MessageDigest.getInstance("SHA1");
                //设定 摘要的内容
                digest.update(content.getBytes());
                //获取摘要的结果
                byte[] resultBytes = digest.digest();
                 buffer = new StringBuffer();
                for (int i = 0; i < resultBytes.length; i++) {
                    byte resultByte = resultBytes[i];
                    //去除最高符号为的影响 再转化成一个十六进制的字符串
                    String s = Integer.toHexString(resultByte&0xFF);
                    //String s = Integer.toHexString(resultByte&0xFE);
                    if (s.length()==1){
                        buffer.append("0");
                    }
                    buffer.append(s);
                }

               
           
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            
            return buffer.toString();
    }
    
}
