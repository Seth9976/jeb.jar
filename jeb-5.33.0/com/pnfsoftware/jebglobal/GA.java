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
public class GA extends AbstractContext implements ICoreContext {
   private static final ILogger pC = GlobalLog.getLogger(GA.class);
   private static GA A;
   private cq kS;
   private Thread wS;
   private long UT;
   private String E;
   private CoreOptions sY;
   private List ys = new ArrayList();
   private Jq ld = Jq.pC;

   public static GA pC(String var0, CoreOptions var1) throws JebException {
      if (A == null) {
         A = new GA(var0, var1);
      } else if (var0 != null && !A.wS().equals(var0)) {
         throw new JebException(ckx.pC(new byte[]{-123, 39, 24, 23, 13, 5, 13, 68, 106, 15, 7, 98, 76, 5, 10, 6, 11, 29, 22, 69, 75, 14, 28}, 1, 204));
      }

      return A;
   }

   public static GA pC() throws JebException {
      return pC(null, null);
   }

   public static GA A() {
      return A;
   }

   private GA(String var1, CoreOptions var2) throws JebException {
      if (var2 == null) {
         var2 = CoreOptions.getDefault();
      }

      this.E = var1;
      this.sY = var2;
      this.UT = vJ.A();
      ControllerInfo var3 = var2.getControllerInfo();
      if (Licensing.isFloatingBuild() && var3 != null && var3.getAddress() != null) {
         Net var14 = new Net();
         if (var3.getProtocol() == 1) {
            try {
               X509TrustManager[] var16 = new X509TrustManager[1];
               SSLContext var18 = Ws.pC(var16);
               SecureSocketInfo var20 = new SecureSocketInfo(var18, var16[0], new ao(this));
               var14.setSecureSocketInfo(var20);
            } catch (Exception var13) {
               throw new JebException(var13);
            }
         }

         bO var17 = new bO(var14, var3.getInterface(), var3.getPort(), var3.getProtocol(), var3.getClientMessage());
         this.kS = new cq(this, var17);
         this.wS = ThreadUtil.start("jeb-flt-connector", this.kS);
      } else {
         boolean var4 = false;
         int[] var5 = new int[1];
         long[] var6 = vJ.kS();

         for (long var10 : var6) {
            NW var12 = new NW(var10);
            if (var12.pC(var1, var5)) {
               this.UT = var10;
               var4 = true;
               break;
            }
         }

         if (!var4) {
            throw new JebException(
               ckx.pC(new byte[]{122, 15, 7, 98, 73, 26, 83, 78, 1, 27, 84, 80, 21, 23, 31, 4, 29, 0, 17, 1, 68, 84, 27, 79, 82, 7, 27}, 1, 48)
            );
         }

         Licensing.setLicenseTimestamp(var5[0]);
      }

      long var15 = System.currentTimeMillis() / 1000L;
      long var19 = Licensing.getExpirationTimestamp();
      if (var15 > 0L && var19 > 0L && var15 > var19) {
         this.sY();
      }
   }

   private void sY() {
      this.pC(Jq.UT);
   }

   @Override
   public void close() {
      if (this.kS != null) {
         try {
            this.wS.interrupt();
            this.wS.join(1000L);
            this.kS.A();
         } catch (InterruptedException var1) {
         }
      }
   }

   public final synchronized void pC(Jq var1) {
      if (var1 == null) {
         if (Licensing.isDebugBuild()) {
            throw new RuntimeException();
         }

         var1 = Jq.pC;
      }

      this.ld = var1;
   }

   public final Jq kS() {
      return this.ld;
   }

   public String wS() {
      return this.E;
   }

   public long UT() {
      return this.UT;
   }

   public long E() {
      return this.kS != null ? this.kS.pC() : this.UT;
   }

   @Override
   public Object getValue(int var1, Object... var2) {
      return var1 == 100 ? this.E() : null;
   }

   @Override
   public CoreOptions getOptions() {
      return this.sY;
   }

   @Override
   public Version getVersion() {
      return app_ver;
   }

   public Nj pC(IDataProvider var1, JebClientInformation var2) throws JebException {
      if (!Yi.pC(var2)) {
         throw new JebException(ckx.pC(new byte[]{-8, 59, 15, 20, 1, 28, 7, 29, 27, 19, 31, 1, 68, 106, 15, 7, 98, 67, 15, 5, 12, 11, 26}, 1, 173));
      } else {
         if (var1 == null) {
            var1 = new Mo(this);
         }

         Nj var3 = new Nj(this, (IDataProvider)var1, this.sY.getJebClassesLocation(), this.sY.isDevelopmentMode(), this.sY.getStandardProxyInfo());
         this.ys.add(var3);
         return var3;
      }
   }

   @Override
   public List listEnginesContexts() {
      return this.ys;
   }

   @Override
   public IEnginesContext getDefaultEnginesContexts() {
      return this.ys.isEmpty() ? null : (IEnginesContext)this.ys.get(0);
   }

   @Override
   public void closeEnginesContext(IEnginesContext var1) {
      var1.close();
      this.ys.remove(var1);
   }
}
