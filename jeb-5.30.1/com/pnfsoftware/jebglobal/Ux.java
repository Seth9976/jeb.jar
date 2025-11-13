package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.units.code.debug.DebuggerThreadStatus;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Ux extends fC implements LC {
   private static final ILogger lm = GlobalLog.getLogger(Ux.class);
   private static final byte[] zz = Strings.encodeUTF8("JDWP-Handshake");
   private String JY;
   private int HF;
   private boolean LK;
   private Version io;
   private int qa;
   private gK Hk;
   private OutputStream Me;
   private List PV = new ArrayList();
   private eh oQ;
   private AtomicInteger xW = new AtomicInteger(1);
   private Object KT = new Object();
   private Object Gf = new Object();
   private Map Ef = new ConcurrentHashMap();
   qD RF;
   Oo xK;
   Wk Dw;
   Q Uv;
   pX oW;
   Tf gO;
   Kk nf;
   private m cC = m.RF;
   Map gP = Collections.synchronizedMap(new HashMap());
   long za = 0L;

   public Ux(String var1, int var2, boolean var3) {
      this.JY = var1;
      this.HF = var2;
      this.LK = var3;
   }

   @Override
   public void q(int var1) {
      this.qa = var1;
   }

   @Override
   public int gO() {
      return this.qa;
   }

   @Override
   public boolean xK() throws zy {
      this.RF = new qD(4, 4, 8, 8, 8);

      try {
         this.q = new Socket(this.JY, this.HF);
         this.Me = this.q.getOutputStream();
         this.Me.write(zz);
         byte[] var1 = new byte[14];
         int var2 = this.q.getInputStream().read(var1);
         if (var2 == zz.length && Arrays.equals(var1, zz)) {
            this.Hk = new gK(this);
            this.Hk.setDaemon(true);
            this.Hk.start();
            this.oQ = new eh(this);
            this.oQ.setDaemon(true);
            this.oQ.start();
            this.Dw = new Wk(this);
            this.Uv = new Q(this);
            this.oW = new pX(this);
            this.gO = new Tf(this);
            this.nf = new Kk(this);
            if (this.LK) {
               this.Dw.oW();
            }

            QF var11 = this.Dw.q();
            this.io = Version.create(var11.RF, var11.xK);
            nG var4 = this.Dw.Uv();
            this.RF = new qD(var4.q, var4.RF, var4.xK, var4.Dw, var4.Uv);

            try {
               this.xK = this.zz().gP();
               Object[] var10000 = new Object[]{this.xK};
            } catch (Fx var7) {
               lm.error("Cannot retrieve JDWP server server capabilities");
            }

            this.JY().RF(true);
            this.JY().xK(true);
            this.JY().Dw(true);
            return true;
         } else {
            String var3 = Strings.ff("Unexpected JDWP handshake: %s", Formatter.escapeBytes(var1, 0, var2));
            var3 = var3
               + "\n\nVerify that the Developer Mode and USB Debugging are enabled on your device.\n\nIf Android Studio is opened, please close it, or enable ADB integration in 'Tools>Android'. We also recommend you close DDMS or other monitoring tools that can interfere with app debugging.\n\nIf it still does not work, try disconnecting and reconnecting your device, or try with another device.";
            throw new zy(var3);
         }
      } catch (Fx | IOException var8) {
         lm.catching(var8);
         if (this.q != null) {
            try {
               this.q.close();
            } catch (IOException var6) {
            }

            this.q = null;
         }

         return false;
      } catch (zy var9) {
         if (this.q != null) {
            try {
               this.q.close();
            } catch (IOException var5) {
            }

            this.q = null;
         }

         throw var9;
      }
   }

   eh lm() {
      return this.oQ;
   }

   public Wk zz() {
      return this.Dw;
   }

   public Q JY() {
      return this.Uv;
   }

   public pX HF() {
      return this.oW;
   }

   @Override
   public boolean q(boolean var1) {
      if (!this.q()) {
         return false;
      } else {
         try {
            if (this.Dw != null) {
               if (var1) {
                  this.Dw.q(-1);
               } else {
                  this.Dw.Dw();
               }
            }
         } catch (Fx | IOException var6) {
            lm.catching(var6);
         }

         try {
            this.q.close();
         } catch (IOException var5) {
            lm.catching(var5);
         }

         this.q = null;
         if (this.Hk != null) {
            this.Hk.interrupt();

            try {
               this.Hk.join();
            } catch (InterruptedException var4) {
               lm.catching(var4);
            }
         }

         if (this.oQ != null) {
            this.oQ.interrupt();

            try {
               this.oQ.join();
            } catch (InterruptedException var3) {
               lm.catching(var3);
            }
         }

         return true;
      }
   }

   @Override
   public Version RF() {
      return this.io;
   }

   @Override
   public boolean Dw() {
      return this.Uv.xK();
   }

   @Override
   public boolean Uv() {
      return this.Uv.Dw();
   }

   @Override
   public boolean oW() {
      return this.Uv.Uv();
   }

   Hv q(byte[] var1) {
      return this.RF.q(var1);
   }

   wc LK() {
      return this.RF.q();
   }

   void RF(byte[] var1) throws IOException {
      synchronized (this.KT) {
         this.Me.write(var1);
      }
   }

   @Override
   public void q(qt var1) {
      this.PV.add(var1);
   }

   @Override
   public void RF(qt var1) {
      this.PV.remove(var1);
   }

   List io() {
      return this.PV;
   }

   void q(zx var1) {
      if (var1.RF()) {
         synchronized (this.Gf) {
            this.Ef.put(var1.Dw(), var1);
            this.Gf.notify();
         }
      } else if (var1.q()) {
         byte var5 = var1.oW();
         byte var3 = var1.gO();
         if (var5 == 64 && var3 == 100) {
            this.oQ.q(var1);
         } else if (var5 == -57 && var3 == 1) {
            this.oQ.q(var1);
         } else {
            lm.error("Unknown JDWP command packet: %s", var1);
         }
      }
   }

   private int PV() {
      return this.xW.getAndIncrement();
   }

   YZ q(int var1, int var2, byte[] var3) {
      int var4 = this.PV();
      byte[] var5 = zx.q(var4, (byte)0, (byte)var1, (byte)var2, var3);
      return new YZ(var4, var5);
   }

   YZ q(int var1, int var2, int var3, byte[] var4) {
      byte[] var5 = zx.q(var1, (byte)var2, (short)var3, var4);
      return new YZ(var1, var5);
   }

   private zx RF(int var1) throws IOException, Fx {
      zx var2;
      synchronized (this.Gf) {
         int var4 = 0;

         while (!this.Ef.containsKey(var1)) {
            try {
               if (var4++ > 0) {
                  if (var4 == 1) {
                     Object[] var8 = new Object[0];
                  }

                  if (!this.Hk.isAlive()) {
                     throw new IOException("The receiver is dead");
                  }
               }

               this.Gf.wait(1000L);
               if (this.qa >= 1 && var4 >= this.qa) {
                  throw new IOException(Strings.ff("A JDWP debugger seems blocked: a synchronous query is not receiving a response (id: %d)", var1));
               }
            } catch (InterruptedException var6) {
            }
         }

         var2 = (zx)this.Ef.get(var1);
      }

      if (var2.nf() != 0) {
         throw new Fx(var2.Dw(), var2.nf());
      } else {
         return var2;
      }
   }

   Hv q(YZ var1) throws IOException, Fx {
      this.RF(var1.RF);
      zx var2 = this.RF(var1.q);
      return this.q(var2.gP());
   }

   public m qa() {
      return this.cC;
   }

   public void q(m var1) {
      this.cC = var1;
   }

   @Override
   public String nf() {
      return this.Uv.RF();
   }

   @Override
   public List gP() {
      return this.Uv.oW();
   }

   @Override
   public boolean q(long var1) {
      return this.Uv.nf(var1);
   }

   @Override
   public boolean RF(long var1) {
      return this.Uv.q(var1, true);
   }

   @Override
   public boolean xK(long var1) {
      return this.Uv.q(var1, false);
   }

   @Override
   public String Dw(long var1) {
      return this.Uv.gO(var1);
   }

   @Override
   public DebuggerThreadStatus Uv(long var1) {
      return this.Uv.gP(var1);
   }

   @Override
   public List q(long var1, int var3) {
      try {
         if (var3 <= 0) {
            var3 = -1;
         }

         NB var4 = this.Dw.q(var1, 0, var3);
         return Arrays.asList(var4.q);
      } catch (Fx | IOException var5) {
         lm.catching(var5);
         return null;
      }
   }

   @Override
   public String oW(long var1) {
      try {
         return this.Uv.oW(var1);
      } catch (zy | IOException var4) {
         lm.catching(var4);
         return null;
      }
   }

   @Override
   public List q(String var1) {
      try {
         return this.Uv.q(var1);
      } catch (zy | IOException var3) {
         lm.catching(var3);
         return null;
      }
   }

   @Override
   public String q(long var1, long var3) {
      try {
         return this.Uv.q(var1, var3);
      } catch (zy | IOException var6) {
         lm.catching(var6);
         return null;
      }
   }

   @Override
   public boolean q(Nv var1, int var2) {
      return this.gO.q(var1, var2);
   }

   @Override
   public boolean q(Nv var1) {
      return this.gO.q(var1);
   }

   @Override
   public boolean za() {
      return this.gO.Dw();
   }

   @Override
   public oG gO(long var1) {
      try {
         Jz var3 = this.Dw.q(var1, 0, 1).q[0];
         return var3.RF;
      } catch (Fx | IOException var4) {
         return null;
      }
   }

   @Override
   public ch q(long var1, long var3, hf var5) {
      try {
         ch[] var6 = this.Dw.q(var1, var3, new hf[]{var5});
         return var6[0];
      } catch (Fx | IOException var7) {
         lm.catching(var7);
         return null;
      }
   }

   @Override
   public boolean RF(long var1, int var3) {
      try {
         if (var3 == 2) {
            synchronized (this.gP) {
               aB var5 = (aB)this.gP.get(var1);
               if (var5 != null) {
                  return false;
               }

               this.gP.put(var1, null);
            }
         }

         ArrayList var9 = new ArrayList();
         Vm var10 = Vm.q(var1, 0, var3);
         var9.add(var10);
         var9.add(Vm.RF("com.sun.*"));
         var9.add(Vm.RF("java.*"));
         var9.add(Vm.RF("javax.*"));
         var9.add(Vm.RF("org.omg.*"));
         var9.add(Vm.RF("sun.*"));
         var9.add(Vm.RF("jdk.internal.*"));
         var9.add(Vm.RF("junit.*"));
         var9.add(Vm.RF("com.intellij.rt.*"));
         var9.add(Vm.RF("com.yourkit.runtime.*"));
         var9.add(Vm.RF("com.springsource.loaded.*"));
         var9.add(Vm.RF("org.springsource.loaded.*"));
         var9.add(Vm.RF("javassist.*"));
         var9.add(Vm.RF("org.apache.webbeans.*"));
         var9.add(Vm.RF("com.ibm.ws.*"));
         var9.add(Vm.RF("org.codehaus.groovy.*"));
         var9.add(Vm.RF("groovy.*"));
         aB var6 = this.Uv.q(dN.q, this.qa(), new to((Vm[])var9.toArray(new Vm[var9.size()])));
         if (var3 == 2) {
            Object[] var10000 = new Object[]{var6};
            this.gP.put(var1, var6);
         }

         return true;
      } catch (Fx | IOException var8) {
         return false;
      }
   }

   public void nf(long var1) {
      this.za = var1;
   }

   public boolean xK(long var1, int var3) {
      if (var1 == 0L) {
         var1 = this.za;
         if (var1 == 0L) {
            return false;
         }
      }

      if (this.Uv(var1) != DebuggerThreadStatus.PAUSED) {
         return false;
      } else {
         return !this.RF(var1, var3) ? false : this.xK(var1);
      }
   }

   public boolean Hk() {
      return this.xK(this.za, 0);
   }

   public boolean Me() {
      return this.xK(this.za, 1);
   }

   @Override
   public String toString() {
      return Strings.ff("JDWP{%s:%d}", this.JY, this.HF);
   }
}
