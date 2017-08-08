package com.xinmei.general.tools;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * Created by Toby on 2016/12/30.
 * 加密解密工具类，目前只支持3des算法
 */
public class EncryptUtil {

    public final static String KEY="f4e5e657257f040b89024931c4bc130b6d34d9dc26257698";

    public final static String RSA_PRIVATE_KEY="MIIEwAIBADANBgkqhkiG9w0BAQEFAASCBKowggSmAgEAAoIBAQC0mHmZTaPkRFl4K9nLwxOlqZmTUJ4shJcbHTAeZGsHvHhsa" +
            "/J7Fo0vokDtgCj87hpTsUH9lxv5pZe3S/hf2hFD04MsEaqOuv5+tDDv7xb6+svAwPj2mReL7Mpw6fC1sek5yfJaxfVzKbQ59ecgbauTgmi4f+zyCW4ZqD3jQeMjQ4mFkC3DTI" +
            "qPPJH0iEueN2gn/lOKTWTzncx1R0c5PestRlLfvh7RYEPB7DaI884vSn7J6DAOTi5ryOHX6lf7v6K59X6AGXk+ToGQ32UulqglJZm1UZlMfmf8aGb+XZj6moRNChIwzZ4Al4Uxg" +
            "JLP3P5kISHUmPc5jjZbPUV5VHyhAgMBAAECggEBAJ1Ar5Fn46/ePDHBrTIaOT9lQM7fwfttsM3xSncMZY4k9I8kJ846QtIZbIPhts8W3WHG1XllXPyyzjrTMgD0t1fiEzQKZgNG" +
            "nFfju0ERZWdBfq3m2mKPx8sVcumDKxBrTHP/fsK/4QxYYhCeKJU6Kc6WHW2aJs5Sm8ZHkywRsSCANgF5kdeOd83TU7phUJe/bYk+61/HW8rNPIbG2CBzWq6nU7Vpz5Ng4GhpZbJT" +
            "xzylY/G01e1yJxFiXzsU7fD/jOqlwxfLaZAjitlJudxIDF6GrhdlKxXfOV7zIWyESkDtEiIcTXYPnOrVO8qa5K31E7qTPmxgarRV2iUIJ3LkkgECgYEA2aPcg3mkv0aYEIoRs/5z3B" +
            "z+2dd4m2sFwPubnj47G91tv6SxhUvUEXsqhRepkgeNQzorb/m+evikDOGfqPalsbQajFrJSPreFPlEDaR0Z/ruRgRNrr2j59Y6SqNCHD6yTkWVnQm6rqUt9msbnhrUHba3a0pil65ps" +
            "mQTEpw687ECgYEA1G0hMLYN+Imc/Ss9y3NxFoYXuV7Ew9qBGOgJ7PAoN99ReyfAoPaRFk3/eWwUXl9vWjtnxL6ZfwTjfplytBbneVf0olLBJlVXqnNtyR3wpm9XWG/2T/uKLVPD03NQtw" +
            "5T2Bz6GTbXj/tkgO6Kkeu/WA+wSGjS1Q5pE++wlIUaA/ECgYEAr/F1UmBjnDJAupYzy5Q4K8jRCmDT4qj5ZNLDMoFSnas7jwNgc8cicONmM2Q2tJQgUinTddmrcJRTYwp/XUPQHcHg+Hho" +
            "YvRT1xbQOkspw+mq+LV/63Tp7YFVbINg7Z6N3RMJ6YM1BP0KFDzwfbdopYDR9Q2UeNUKI6oddR0R13ECgYEA0j3cDpjwxN52WP9WHql38MHwcw//kJQPF3Z4dF/QflPxEgHrHaRLPpcd1bAJjMn" +
            "JrLWE4219AAD3o5H9u427tRY/UfSzo9vtcw7hRp7yUdd6zFWro8eiH3B7LSlBZpuwkgFD6naj7KXz98OftAJuk5qbb18H48RqbUfaLXR6phECgYEArS03pCt0Kr1HFXfRaenjyK8+NpQ+tA8rqwk" +
            "35S/PuuZ6T8+Z7UcYV5dPD5R047F1EJNzRvbwuJYwT5r+S3S3mgoXUUmQmO/4oxEWcQXgpQ/LfV8hdansTP5q+NFZ2z3F3lNwlXyvHxwCM2JXqJXMdhD0UxH4s14TtXq+3RlN3U8=";

    private final static String Algorithm="DESede";

    private final static String CHARSET_ENCODE="UTF-8";


    /**
     * 签名算法
     */
    public static final String SIGN_ALGORITHMS = "SHA256WithRSA";


    private static Logger logger= LoggerFactory.getLogger(EncryptUtil.class);


    /**
     * 加密字符串
     * @param key 密钥
     * @param source 待加密字符串
     * @return 如果密钥格式不对会返回空字符串
     */
    public static String encrypt(String key,String source) {
        try {
            byte[] keyByte= Hex.decodeHex(key.toCharArray());
            SecretKey secretKey = new SecretKeySpec(keyByte, Algorithm);
            Cipher c1  = Cipher.getInstance(Algorithm);
            c1.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] buffer=c1.doFinal(source.getBytes("utf-8"));
            return String.valueOf(Hex.encodeHex(buffer));
        } catch (Exception e) {
            logger.error("encrypt error",e);
        }
        return "";
    }

    /**
     * 解密字符串
     * @param key 密钥
     * @param source 加密字符串
     * @return 如果密钥格式不对，或者加密后的字符串不对，会返回空字符串
     */
    public static String decrypt(String key,String source) {
        try {
            byte[] keyByte= Hex.decodeHex(key.toCharArray());
            SecretKey secretKey = new SecretKeySpec(keyByte, Algorithm);
            Cipher c1  = Cipher.getInstance(Algorithm);
            c1.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] buffer=c1.doFinal(Hex.decodeHex(source.toCharArray()));
            return new String(buffer,"utf-8");
        } catch (Exception e) {
            logger.error("decrypt error", e);
        }
        return "";
    }

    public static String signBySHA256WithRSA(String privateKey,String content){
        try{
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec( Base64Util.decode(privateKey) );
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PrivateKey priKey = keyf.generatePrivate(priPKCS8);
            java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
            signature.initSign(priKey);
            signature.update( content.getBytes(CHARSET_ENCODE));
            byte[] signed = signature.sign();
            return Base64Util.encode(signed).replaceAll("\\+", "\\-").replaceAll("/", "_");
        }catch (Exception e){
            logger.error("com.xinmei.general.tools.EncryptUtil sign error",e);
        }
        return null;
    }
}
