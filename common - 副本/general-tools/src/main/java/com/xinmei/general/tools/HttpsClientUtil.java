package com.xinmei.general.tools;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * https访问情况
 *
 * @Author xbzhu
 * @Mail toby.zhu@trustlife.com
 * @Date 2017年02月14日 17:56
 */
public class HttpsClientUtil {

    public static HttpClient getHttpClient() throws Exception {
        ClientConnectionManager cm = new ThreadSafeClientConnManager();
        HttpClient httpclient = new DefaultHttpClient(cm);
        SSLContext ctx = SSLContext.getInstance("SSL");
        X509TrustManager tm = new X509TrustManager() {

            public void checkClientTrusted(X509Certificate[] xcs,
                                           String string) throws CertificateException {

            }
            public void checkServerTrusted(X509Certificate[] xcs,
                                           String string) throws CertificateException {
            }
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        ctx.init(null, new TrustManager[] { tm }, null);
        SSLSocketFactory ssf = new SSLSocketFactory(ctx);
        ClientConnectionManager ccm = httpclient.getConnectionManager();
        SchemeRegistry sr = ccm.getSchemeRegistry();
        sr.register(new Scheme("https", 443, ssf));
        return httpclient;
    }
}
