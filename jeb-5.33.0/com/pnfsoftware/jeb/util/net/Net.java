package com.pnfsoftware.jeb.util.net;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.IProgressCallback;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.Proxy;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.OkHttpClient.Builder;

public class Net implements INet {
   private static final ILogger logger = GlobalLog.getLogger(Net.class);
   private static NetProxyInfo proxyinfoStandard;
   private String userAgent;
   private int timeoutConnect;
   private int timeoutRead;
   private int timeoutWrite;
   private SecureSocketInfo ssinfo;

   public static void setGlobalProxyInformation(NetProxyInfo var0) {
      if (var0 != null && !var0.isProxy()) {
         var0 = null;
      }

      proxyinfoStandard = var0;
      logger.debug("Proxy settings: %s", var0 != null && var0.isProxy() ? var0 : "(none)");
      if (var0 != null && var0.hasCredentials()) {
         String var1 = var0.getUser();
         String var2 = var0.getPassword();
         Authenticator.setDefault(new Net$1(var1, var2));
      } else {
         Authenticator.setDefault(null);
      }
   }

   public static NetProxyInfo getGlobalProxyInformation() {
      return proxyinfoStandard;
   }

   public Net() {
      this.setUserAgent("PNF Software UP");
      this.setConnectTimeout(30000);
      this.setReadTimeout(120000);
      this.setWriteTimeout(60000);
   }

   public Net(Net var1) {
      this.setUserAgent(var1.getUserAgent());
      this.setConnectTimeout(var1.getConnectTimeout());
      this.setReadTimeout(var1.getReadTimeout());
      this.setWriteTimeout(var1.getWriteTimeout());
      this.setSecureSocketInfo(var1.getSecureSocketInfo());
   }

   @Override
   public INet duplicate() {
      return new Net(this);
   }

   public NetProxyInfo getProxyInformation() {
      return getGlobalProxyInformation();
   }

   @Override
   public void setUserAgent(String var1) {
      this.userAgent = var1;
   }

   @Override
   public String getUserAgent() {
      return this.userAgent;
   }

   @Override
   public void setConnectTimeout(int var1) {
      this.timeoutConnect = var1;
   }

   @Override
   public int getConnectTimeout() {
      return this.timeoutConnect;
   }

   @Override
   public void setReadTimeout(int var1) {
      this.timeoutRead = var1;
   }

   @Override
   public int getReadTimeout() {
      return this.timeoutRead;
   }

   @Override
   public void setWriteTimeout(int var1) {
      this.timeoutWrite = var1;
   }

   @Override
   public int getWriteTimeout() {
      return this.timeoutWrite;
   }

   @Override
   public SecureSocketInfo getSecureSocketInfo() {
      return this.ssinfo;
   }

   @Override
   public void setSecureSocketInfo(SecureSocketInfo var1) {
      this.ssinfo = var1;
   }

   public OkHttpClient buildClient(String var1) {
      return this.buildClient(var1, null);
   }

   public OkHttpClient buildClient(String var1, IProgressCallback var2) {
      return this.buildClient(var1, var2, null);
   }

   public OkHttpClient buildClient(String var1, IProgressCallback var2, int[] var3) {
      Assert.a(var3 == null || var3.length == 3);
      Builder var4 = new Builder()
         .connectTimeout(var3 != null && var3[0] >= 0 ? var3[0] : this.timeoutConnect, TimeUnit.MILLISECONDS)
         .readTimeout(var3 != null && var3[1] >= 0 ? var3[1] : this.timeoutRead, TimeUnit.MILLISECONDS)
         .writeTimeout(var3 != null && var3[2] >= 0 ? var3[2] : this.timeoutWrite, TimeUnit.MILLISECONDS);
      if (var2 != null) {
         var4.addNetworkInterceptor(new Net$2(this, var2));
      }

      NetProxyInfo var5 = this.getProxyInformation();
      if (var5 != null && var1 != null && !var5.isWhitelisted(var1)) {
         var4.proxy(var5.getProxy());
         okhttp3.Authenticator var6 = var5.getAuthenticator();
         if (var6 != null) {
            var4.proxyAuthenticator(var6);
         }
      }

      if (this.ssinfo != null) {
         SSLContext var7 = this.ssinfo.getSslContext();
         if (this.ssinfo.getTrustManager() != null) {
            var4.sslSocketFactory(var7.getSocketFactory(), this.ssinfo.getTrustManager());
         } else {
            var4.sslSocketFactory(var7.getSocketFactory());
         }

         if (this.ssinfo.getHostnameVerifier() != null) {
            var4.hostnameVerifier(this.ssinfo.getHostnameVerifier());
         }
      }

      return var4.build();
   }

   Request buildRequest(String var1, RequestBody var2) {
      okhttp3.Request.Builder var3 = new okhttp3.Request.Builder().url(var1).addHeader("User-Agent", this.userAgent);
      if (var2 != null) {
         var3.post(var2);
      }

      return var3.build();
   }

   String sendSyncRequest(OkHttpClient var1, Request var2, Map var3) throws IOException {
      Response var4 = var1.newCall(var2).execute();

      String var9;
      try {
         if (var3 != null) {
            for (String var6 : new HashSet(var3.keySet())) {
               var3.put(var6, var4.header(var6));
            }
         }

         if (!var4.isSuccessful()) {
            throw new IOException("Query failed with response code " + var4.code());
         }

         var9 = var4.body().string();
      } catch (Throwable var8) {
         if (var4 != null) {
            try {
               var4.close();
            } catch (Throwable var7) {
               var8.addSuppressed(var7);
            }
         }

         throw var8;
      }

      if (var4 != null) {
         var4.close();
      }

      return var9;
   }

   @Override
   public String post(String var1, Map var2, Map var3, Map var4) throws IOException {
      OkHttpClient var5 = this.buildClient(var1);

      String var9;
      try {
         okhttp3.FormBody.Builder var6 = new okhttp3.FormBody.Builder();
         if (var3 != null) {
            for (String var8 : var3.keySet()) {
               var6.add(var8, (String)var3.get(var8));
            }
         }

         FormBody var13 = var6.build();
         Object[] var10000 = new Object[]{var1};
         Request var14 = this.buildRequest(var1, var13);
         var9 = this.sendSyncRequest(var5, var14, var4);
      } finally {
         var5.connectionPool().evictAll();
      }

      return var9;
   }

   @Override
   public String post(String var1, Map var2, Map var3) throws IOException {
      return this.post(var1, var2, var3, null);
   }

   @Override
   public String postMultipart(String var1, Map var2, Map var3, Map var4) throws IOException {
      OkHttpClient var5 = this.buildClient(var1);

      String var18;
      try {
         okhttp3.MultipartBody.Builder var6 = new okhttp3.MultipartBody.Builder().setType(MultipartBody.FORM);
         if (var2 != null) {
            for (String var8 : var2.keySet()) {
               var18 = (String)var2.get(var8);
               var6.addFormDataPart(var8, var18);
            }
         }

         if (var3 != null) {
            for (String var15 : var3.keySet()) {
               FormFileEntry var17 = (FormFileEntry)var3.get(var15);
               var6.addFormDataPart(var15, var17.getName(), RequestBody.create(MediaType.parse("application/octet-stream"), IO.readFile(var17.getFile())));
            }
         }

         MultipartBody var14 = var6.build();
         Object[] var10000 = new Object[]{var1};
         Request var16 = this.buildRequest(var1, var14);
         var18 = this.sendSyncRequest(var5, var16, var4);
      } finally {
         var5.connectionPool().evictAll();
      }

      return var18;
   }

   @Override
   public String postMultipart(String var1, Map var2, Map var3) throws IOException {
      return this.postMultipart(var1, var2, var3, null);
   }

   @Override
   public String query(String var1, Map var2, Map var3) throws IOException {
      OkHttpClient var4 = this.buildClient(var1);

      String var12;
      try {
         if (var2 != null) {
            okhttp3.HttpUrl.Builder var5 = HttpUrl.parse(var1).newBuilder();

            for (String var7 : var2.keySet()) {
               var5.addQueryParameter(var7, (String)var2.get(var7));
            }

            var1 = var5.build().toString();
         }

         Object[] var10000 = new Object[]{var1};
         Request var11 = this.buildRequest(var1, null);
         var12 = this.sendSyncRequest(var4, var11, var3);
      } finally {
         var4.connectionPool().evictAll();
      }

      return var12;
   }

   @Override
   public String query(String var1, Map var2) throws IOException {
      return this.query(var1, var2, null);
   }

   @Override
   public String query(String var1) throws IOException {
      return this.query(var1, null, null);
   }

   @Override
   public byte[] queryBinary(String var1, Map var2, Map var3, IProgressCallback var4) throws IOException {
      OkHttpClient var5 = this.buildClient(var1, var4);
      if (var2 != null) {
         okhttp3.HttpUrl.Builder var6 = HttpUrl.parse(var1).newBuilder();

         for (String var8 : var2.keySet()) {
            var6.addQueryParameter(var8, (String)var2.get(var8));
         }

         var1 = var6.build().toString();
      }

      Object[] var10000 = new Object[]{var1};
      Request var17 = this.buildRequest(var1, null);

      byte[] var20;
      try {
         Response var18 = var5.newCall(var17).execute();

         try {
            if (var3 != null) {
               for (String var9 : new HashSet(var3.keySet())) {
                  var3.put(var9, var18.header(var9));
               }
            }

            if (!var18.isSuccessful()) {
               throw new IOException("Query failed with response code " + var18.code());
            }

            var20 = var18.body().bytes();
         } catch (Throwable var15) {
            if (var18 != null) {
               try {
                  var18.close();
               } catch (Throwable var14) {
                  var15.addSuppressed(var14);
               }
            }

            throw var15;
         }

         if (var18 != null) {
            var18.close();
         }
      } finally {
         var5.connectionPool().evictAll();
      }

      return var20;
   }

   @Override
   public byte[] queryBinary(String var1, Map var2, Map var3) throws IOException {
      return this.queryBinary(var1, var2, var3, null);
   }

   @Override
   public byte[] queryBinary(String var1, Map var2) throws IOException {
      return this.queryBinary(var1, var2, null);
   }

   @Override
   public byte[] queryBinary(String var1) throws IOException {
      return this.queryBinary(var1, null, null);
   }

   @Override
   public long downloadBinary(File var1, String var2, Map var3, Map var4, IProgressCallback var5) throws IOException {
      OkHttpClient var6 = this.buildClient(var2, var5);
      if (var3 != null) {
         okhttp3.HttpUrl.Builder var7 = HttpUrl.parse(var2).newBuilder();

         for (String var9 : var3.keySet()) {
            var7.addQueryParameter(var9, (String)var3.get(var9));
         }

         var2 = var7.build().toString();
      }

      Object[] var10000 = new Object[]{var2, var1};
      Request var28 = this.buildRequest(var2, null);

      long var11;
      try {
         Response var29 = var6.newCall(var28).execute();

         try {
            if (var4 != null) {
               for (String var10 : new HashSet(var4.keySet())) {
                  var4.put(var10, var29.header(var10));
               }
            }

            if (!var29.isSuccessful()) {
               throw new IOException("Query failed with response code " + var29.code());
            }

            try (
               InputStream var31 = var29.body().byteStream();
               FileOutputStream var32 = new FileOutputStream(var1);
            ) {
               var11 = IO.copyStream(var31, var32);
            }
         } catch (Throwable var26) {
            if (var29 != null) {
               try {
                  var29.close();
               } catch (Throwable var21) {
                  var26.addSuppressed(var21);
               }
            }

            throw var26;
         }

         if (var29 != null) {
            var29.close();
         }
      } finally {
         var6.connectionPool().evictAll();
      }

      return var11;
   }

   public static long convertHttpDateToEpoch(String var0) {
      SimpleDateFormat var1 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);

      try {
         Date var2 = var1.parse(var0);
         return var2.getTime();
      } catch (Exception var3) {
         return 0L;
      }
   }

   public static boolean isConnectedToNetwork() {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.RuntimeException: parsing failure!
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.parseGraph(DomHelper.java:211)
      //   at org.jetbrains.java.decompiler.main.rels.MethodProcessor.codeToJava(MethodProcessor.java:166)
      //
      // Bytecode:
      // 00: invokestatic java/net/NetworkInterface.getNetworkInterfaces ()Ljava/util/Enumeration;
      // 03: astore 0
      // 04: aload 0
      // 05: invokeinterface java/util/Enumeration.hasMoreElements ()Z 1
      // 0a: ifeq 2a
      // 0d: aload 0
      // 0e: invokeinterface java/util/Enumeration.nextElement ()Ljava/lang/Object; 1
      // 13: checkcast java/net/NetworkInterface
      // 16: astore 1
      // 17: aload 1
      // 18: invokevirtual java/net/NetworkInterface.isLoopback ()Z
      // 1b: ifne 27
      // 1e: aload 1
      // 1f: invokevirtual java/net/NetworkInterface.isUp ()Z
      // 22: ifeq 27
      // 25: bipush 1
      // 26: ireturn
      // 27: goto 04
      // 2a: goto 2e
      // 2d: pop
      // 2e: bipush 0
      // 2f: ireturn
   }

   // $VF: Could not verify finally blocks. A semaphore variable has been added to preserve control flow.
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public static boolean isConnectedToInternet() {
      Socket var0 = null;
      boolean var11 = false /* VF: Semaphore variable */;

      boolean var18;
      label83: {
         try {
            var11 = true;
            String var1 = "8.8.8.8";
            int var17 = 53;
            NetProxyInfo var3 = getGlobalProxyInformation();
            if (var3 != null) {
               Proxy var4 = var3.getProxy();
               if (var4 != Proxy.NO_PROXY) {
                  var1 = var3.getHostname();
                  var17 = var3.getPort();
               }
            }

            var0 = new Socket(var1, var17);
            var18 = true;
            var11 = false;
            break label83;
         } catch (Exception var15) {
            var11 = false;
         } finally {
            if (var11) {
               if (var0 != null) {
                  try {
                     var0.close();
                  } catch (IOException var12) {
                  }
               }
            }
         }

         boolean var2 = false;
         if (var0 != null) {
            try {
               var0.close();
            } catch (IOException var13) {
            }
         }

         return var2;
      }

      if (var0 != null) {
         try {
            var0.close();
         } catch (IOException var14) {
         }
      }

      return var18;
   }
}
