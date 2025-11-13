package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.AbstractContext;
import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.core.ControllerInfo;
import com.pnfsoftware.jeb.core.CoreOptions;
import com.pnfsoftware.jeb.core.ICoreContext;
import com.pnfsoftware.jeb.core.IEnginesContext;
import com.pnfsoftware.jeb.core.JebClientInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.dao.IDataProvider;
import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.util.concurrent.ThreadUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.net.Net;
import com.pnfsoftware.jeb.util.net.SecureSocketInfo;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;

@SerDisabled
public class Xa extends AbstractContext implements ICoreContext {
   private static final ILogger q = GlobalLog.getLogger(Xa.class);
   private static Xa RF;
   private Nt xK;
   private Thread Dw;
   private long Uv;
   private String oW;
   private CoreOptions gO;
   private List nf = new ArrayList();
   private dv gP = dv.q;

   public static Xa q(String var0, CoreOptions var1) throws JebException {
      if (RF == null) {
         RF = new Xa(var0, var1);
      } else if (var0 != null && !RF.Dw().equals(var0)) {
         throw new JebException(cvm.q(new byte[]{10, 1, 6, 24, 30, 0, 3, 72, 62, 101, 106, 67, 69, 73, 82, 92, 87, 64, 73, 0, 89, 85, 75}, 2, 160));
      }

      return RF;
   }

   public static Xa q() throws JebException {
      return q(null, null);
   }

   public static Xa RF() {
      return RF;
   }

   private Xa(String var1, CoreOptions var2) throws JebException {
      if (var2 == null) {
         var2 = CoreOptions.getDefault();
      }

      this.oW = var1;
      this.gO = var2;
      this.Uv = tl.RF();
      ControllerInfo var3 = var2.getControllerInfo();
      if (Licensing.isFloatingBuild() && var3 != null && var3.getAddress() != null) {
         Net var14 = new Net();
         if (var3.getProtocol() == 1) {
            try {
               X509TrustManager[] var16 = new X509TrustManager[1];
               SSLContext var18 = ej.q(var16);
               SecureSocketInfo var20 = new SecureSocketInfo(var18, var16[0], new CI(this));
               var14.setSecureSocketInfo(var20);
            } catch (Exception var13) {
               throw new JebException(var13);
            }
         }

         oM var17 = new oM(var14, var3.getInterface(), var3.getPort(), var3.getProtocol());
         this.xK = new Nt(this, var17);
         this.Dw = ThreadUtil.start("jeb-flt-connector", this.xK);
      } else {
         boolean var4 = false;
         int[] var5 = new int[1];
         long[] var6 = tl.xK();

         for (long var10 : var6) {
            bK var12 = new bK(var10);
            if (var12.q(var1, var5)) {
               this.Uv = var10;
               var4 = true;
               break;
            }
         }

         if (!var4) {
            throw new JebException(
               cvm.q(new byte[]{-23, 15, 7, 98, 73, 26, 83, 78, 1, 27, 84, 80, 21, 23, 31, 4, 29, 0, 17, 1, 68, 84, 27, 79, 82, 7, 27}, 1, 163)
            );
         }

         Licensing.setLicenseTimestamp(var5[0]);
      }

      long var15 = System.currentTimeMillis() / 1000L;
      long var19 = Licensing.getExpirationTimestamp();
      if (var15 > 0L && var19 > 0L && var15 > var19) {
         this.nf();
      }
   }

   private void nf() {
      this.q(dv.Uv);
   }

   @Override
   public void close() {
      if (this.xK != null) {
         try {
            this.Dw.interrupt();
            this.Dw.join(1000L);
            this.xK.RF();
         } catch (InterruptedException var1) {
         }
      }
   }

   public final synchronized void q(dv var1) {
      if (var1 == null) {
         if (Licensing.isDebugBuild()) {
            throw new RuntimeException();
         }

         var1 = dv.q;
      }

      this.gP = var1;
   }

   public final dv xK() {
      return this.gP;
   }

   public String Dw() {
      return this.oW;
   }

   public boolean Uv() {
      return this.xK != null;
   }

   public long oW() {
      return this.Uv;
   }

   public long gO() {
      return this.xK != null ? this.xK.q() : this.Uv;
   }

   @Override
   public CoreOptions getOptions() {
      return this.gO;
   }

   @Override
   public Version getVersion() {
      return app_ver;
   }

   public zJ q(IDataProvider var1, JebClientInformation var2) throws JebException {
      if (!DQ.q(var2)) {
         throw new JebException(cvm.q(new byte[]{0, 59, 15, 20, 1, 28, 7, 29, 27, 19, 31, 1, 68, 106, 15, 7, 98, 67, 15, 5, 12, 11, 26}, 1, 85));
      } else {
         if (var1 == null) {
            var1 = new vX(this);
         }

         zJ var3 = new zJ(this, (IDataProvider)var1, this.gO.getJebClassesLocation(), this.gO.isDevelopmentMode(), this.gO.getStandardProxyInfo());
         this.nf.add(var3);
         return var3;
      }
   }

   @Override
   public List listEnginesContexts() {
      return this.nf;
   }

   @Override
   public IEnginesContext getDefaultEnginesContexts() {
      return this.nf.isEmpty() ? null : (IEnginesContext)this.nf.get(0);
   }

   @Override
   public void closeEnginesContext(IEnginesContext var1) {
      var1.close();
      this.nf.remove(var1);
   }
}
