package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.AbstractClientContext;
import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.AssetManager;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.events.ControllerNotification;
import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.util.concurrent.ThreadUtil;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.security.KeyStore;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

@SerDisabled
public class Ws extends AbstractClientContext {
   private static final ILogger sY = GlobalLog.getLogger(Ws.class);
   static String pC = "jebflt-keystore.jks";
   static String A = "nopass";
   int kS = 100;
   protected String wS;
   protected Integer UT;
   protected Integer E;

   public Ws() {
   }

   public Ws(String var1, Integer var2, Integer var3) {
      this.wS = var1;
      this.UT = var2;
      this.E = var3;
   }

   @Override
   public String getControllerInterface() {
      return this.wS != null ? this.wS : super.getControllerInterface();
   }

   @Override
   public int getControllerPort() {
      return this.UT != null ? this.UT : super.getControllerPort();
   }

   @Override
   public int getControllerProtocol() {
      return this.E != null ? this.E : super.getControllerProtocol();
   }

   @Override
   public void initialize(String[] var1) {
      super.initialize(var1);
   }

   @Override
   public void start() throws JebException {
      super.start();
      sY.info(S.L("Mode: Controller for floating clients"));
      if (rQ.pC().A()) {
         sY.info(S.L("The controller is already running"));
         terminate();
      }

      String var1 = this.getControllerInterface();
      int var2 = this.getControllerPort();
      int var3 = this.getControllerProtocol();
      rQ.pC().pC(var2);
      this.pC(var1, var2, var3);
      super.stop();
   }

   private boolean pC(String var1, int var2, int var3) {
      Sv var4 = new Sv();
      ThreadUtil.start("jeb-flt-controller-client-processor", var4);
      InetAddress var5 = null;
      if (var1 != null && !var1.isEmpty()) {
         try {
            var5 = InetAddress.getByName(var1);
         } catch (UnknownHostException var13) {
            sY.info(S.L("Invalid interface: %s"), var1);
            return false;
         }
      }

      ServerSocket var6;
      if (var3 == 1) {
         sY.info(S.L("Starting controller server (HTTPS)"));

         try {
            SSLContext var7 = pC();
            SSLServerSocketFactory var8 = var7.getServerSocketFactory();
            var6 = var8.createServerSocket(var2, this.kS, var5);
         } catch (Exception var12) {
            sY.error(S.L("Cannot listen on %s/%d"), var5, var2);
            sY.catching(var12);
            return false;
         }
      } else {
         sY.info(S.L("Starting controller server (HTTP)"));

         try {
            var6 = new ServerSocket(var2, this.kS, var5);
         } catch (IOException var11) {
            sY.error(S.L("Cannot listen on %s/%d"), var5, var2);
            sY.catching(var11);
            return false;
         }
      }

      sY.info(S.L("Listening on %s..."), var6.getLocalSocketAddress());
      K var16 = new K();
      var16.pC = 1000L * this.getStartTimestamp();
      var16.A = this.getControllerMessage();
      var16.kS = ((GA)this.getCoreContext()).UT();

      while (true) {
         Socket var17;
         while (true) {
            try {
               var17 = var6.accept();
               var17.setKeepAlive(true);
               var17.setSoTimeout(30000);
               break;
            } catch (IOException var14) {
               sY.info(S.L("Cannot accept incoming client connection"));
            } catch (Exception var15) {
               sY.info(Strings.ff(S.L("Unsupported exception, quit listening: %s"), var15));

               try {
                  var6.close();
               } catch (IOException var10) {
               }

               return false;
            }
         }

         ThreadUtil.start("jeb-flt-client-processor", new DH(var17, var4, var16));
      }
   }

   public static SSLContext pC() throws Exception {
      return pC(null);
   }

   public static SSLContext pC(X509TrustManager[] var0) throws Exception {
      return pC("TLSv1.3", AssetManager.getAsset(pC), A, var0);
   }

   private static SSLContext pC(String var0, InputStream var1, String var2, X509TrustManager[] var3) throws Exception {
      KeyStore var4 = KeyStore.getInstance("JKS");
      var4.load(var1, var2.toCharArray());
      KeyManagerFactory var5 = KeyManagerFactory.getInstance("SunX509");
      var5.init(var4, var2.toCharArray());
      KeyManager[] var6 = var5.getKeyManagers();
      TrustManagerFactory var7 = TrustManagerFactory.getInstance("SunX509");
      var7.init(var4);
      TrustManager[] var8 = var7.getTrustManagers();
      if (var8.length >= 1 && var8[0] instanceof X509TrustManager && var3 != null && var3.length >= 1) {
         var3[0] = (X509TrustManager)var8[0];
      }

      SSLContext var9 = SSLContext.getInstance(var0);
      var9.init(var6, var8, null);
      return var9;
   }

   @Override
   public boolean displayEula(String var1) {
      System.out.println(var1);
      System.out.format(S.L("Write \"%s\" to agree, then press enter") + ": ", "yes");
      String var2 = this.A();
      return var2 != null && var2.trim().equalsIgnoreCase("yes");
   }

   @Override
   public String retrieveLicenseKey(String var1) {
      System.out
         .println(
            Strings.ff(
               S.L("Hello %s. Set up your JEB controller by generating a license key. This one-time operation will only take a few seconds."),
               Licensing.user_name
            )
         );
      System.out
         .println(Strings.ff(S.L("Please visit %s, and use the following \"license data\" blob to generate a key"), "https://www.pnfsoftware.com/genlk"));

      try {
         File var2 = new File(".jeb_license_data");
         IO.writeFile(var2, var1);
         System.out.format(S.L("For your reference, the license data blob was dumped to: %s\n"), var2.getAbsolutePath());
      } catch (IOException var3) {
      }

      System.out.println(Strings.ff(S.L("License data: %s"), var1));
      System.out.print(Strings.ff("%s: ", S.L("Input your license key")));
      return this.A();
   }

   @Override
   public void onUpdatedSoftware(String var1, Version var2) {
   }

   @Override
   public void notifySupportExpired() {
   }

   @Override
   public void displayDemoInformation(String var1) {
   }

   @Override
   public boolean setupController() {
      throw new RuntimeException();
   }

   @Override
   public void notifyFloatingClient(ControllerNotification var1) {
      throw new RuntimeException();
   }

   private String A() {
      BufferedReader var1 = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset()));

      try {
         return var1.readLine();
      } catch (IOException var2) {
         return null;
      }
   }

   @Override
   public boolean checkUpdate() {
      sY.warn(S.L("Run JEB with the %s argument to update your JEB controler"), "--check-update");
      return false;
   }

   @Override
   public IUnit open(String var1) throws IOException {
      throw new RuntimeException("The controller is not meant to analyze files");
   }
}
