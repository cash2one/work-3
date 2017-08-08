package com.xinmei.common.test;

import com.xinmei.general.tools.EncryptUtil;
import org.apache.commons.codec.binary.Hex;
import org.junit.Assert;
import org.junit.Test;

import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by Toby on 2016/12/30.
 */
public class EncryptUtilTest {

    @Test
    public void testBuildKey() throws NoSuchAlgorithmException {
        KeyGenerator keygen = KeyGenerator.getInstance("DESede");
        SecureRandom random = new SecureRandom();
        keygen.init(random);
        Key key = keygen.generateKey();
        System.out.println(Hex.encodeHexString(key.getEncoded()));
    }

    @Test
    public void testEncrypt(){
        String key="f4e5e657257f040b89024931c4bc130b6d34d9dc26257698";
        String source="true";
        String str=EncryptUtil.encrypt(key,source);
        String result=EncryptUtil.decrypt(key,str);
        System.out.println(result);
        Assert.assertEquals("not equal",source,result);
    }

    @Test
    public void testEncryptError(){
        String source="source";
        String result=EncryptUtil.encrypt("111",source);
        Assert.assertEquals("",result);
    }

    @Test
    public void testDecryptError(){
        String result=EncryptUtil.decrypt("111","111");
        Assert.assertEquals("",result);
    }
}
