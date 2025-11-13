package com.pnfsoftware.jeb.util.net;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;

public class SecureSocketInfo {
   SSLContext sslContext;
   X509TrustManager trustManager;
   HostnameVerifier hostnameVerifier;

   public SecureSocketInfo(SSLContext var1, X509TrustManager var2, HostnameVerifier var3) {
      this.sslContext = var1;
      this.trustManager = var2;
      this.hostnameVerifier = var3;
   }

   public SSLContext getSslContext() {
      return this.sslContext;
   }

   public X509TrustManager getTrustManager() {
      return this.trustManager;
   }

   public HostnameVerifier getHostnameVerifier() {
      return this.hostnameVerifier;
   }
}
