package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tf {
   private static final ILogger Dw = GlobalLog.getLogger(Tf.class);
   public static int q = 0;
   public static int RF = 1;
   public static int xK = 2;
   private Ux Uv;
   private m oW = m.RF;
   private Map gO = new HashMap();
   private Map nf = new HashMap();
   private Map gP = new HashMap();

   Tf(Ux var1) {
      this.Uv = var1;
   }

   public synchronized m q() {
      return this.oW;
   }

   public synchronized void q(m var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Need a suspendPolicy");
      } else {
         this.oW = var1;
      }
   }

   public synchronized List RF() {
      return new ArrayList(this.gO.values());
   }

   synchronized List xK() {
      return new ArrayList(this.nf.keySet());
   }

   public synchronized boolean q(Nv var1, int var2) {
      return this.q(var1, var2, false);
   }

   public synchronized boolean q(Nv var1, int var2, boolean var3) {
      try {
         String var4 = q(var1.oW);
         Tf.eo var5 = (Tf.eo)this.nf.get(var4);
         if (var5 != null) {
            if (var5.q.contains(var1)) {
               return true;
            }
         } else {
            var5 = new Tf.eo();
            this.nf.put(var4, var5);
            var5.RF = this.q(var4, new th(this, var4, var2));
         }

         List var6 = this.Uv.JY().xK(var1.oW);
         boolean var7 = false;

         for (long var9 : var6) {
            oG var11 = this.Uv.JY().q(var1, var9);
            if (var11 != null) {
               this.q(var11, var2);
               var7 = true;
            }
         }

         if (!var7) {
            if (!var6.isEmpty()) {
               String var13 = Strings.ff(S.L("Cannot resolve method for %s for now. Do you expect future classload?"), var1);
               Dw.warn(var13);
               if (var3) {
                  return false;
               }
            } else {
               Dw.info(S.L("Set breakpoint on %s. Class is not loaded"), var1);
            }
         }

         var5.q.add(var1);
         return true;
      } catch (zy | IOException var12) {
         Dw.catching(var12);
         return false;
      }
   }

   private synchronized boolean q(oG var1, int var2) {
      try {
         CM var3 = (CM)this.gO.get(var1);
         if (var3 != null) {
            return false;
         } else {
            to var4 = new to();
            ArrayList var5 = new ArrayList();
            switch (var1.RF) {
               case -3:
                  var4.q(Vm.q(new oG((byte)1, var1.xK, var1.Dw, 0L)));
                  var5.add(this.Uv.JY().q(dN.HF, this.q(), var4));
                  break;
               case -2:
                  var4.q(Vm.q(var1.xK, var1.Dw));
                  if (var2 == q || (var2 & RF) != 0) {
                     var5.add(this.Uv.JY().q(dN.lm, this.q(), var4));
                  }

                  if (var2 == q || (var2 & xK) != 0) {
                     var5.add(this.Uv.JY().q(dN.zz, this.q(), var4));
                  }
                  break;
               case -1:
               case 0:
               default:
                  return false;
               case 1:
                  var4 = new to();
                  var4.q(Vm.q(var1));
                  var5.add(this.Uv.JY().q(dN.RF, this.q(), var4));
            }

            var3 = new CM(var1, var5);
            this.gO.put(var1, var3);
            if (var1.RF == -3 && this.RF(var1.xK)) {
               var3.xK = var1.xK;
               var3.Dw = var1.Dw;
            }

            return true;
         }
      } catch (Fx | IOException var6) {
         Dw.catching(var6);
         return false;
      }
   }

   public synchronized boolean Dw() {
      while (this.nf.isEmpty()) {
         Tf.eo var1 = (Tf.eo)this.nf.values().iterator().next();

         for (Nv var3 : new ArrayList(var1.q)) {
            this.q(var3);
         }
      }

      return true;
   }

   public synchronized boolean q(Nv var1) {
      try {
         String var2 = q(var1.oW);
         Tf.eo var3 = (Tf.eo)this.nf.get(var2);
         if (var3 == null) {
            return false;
         } else if (!var3.q.remove(var1)) {
            return false;
         } else {
            if (var3.q.isEmpty()) {
               this.q(var3.RF);
               this.nf.remove(var2);
            }

            for (oG var5 : this.Uv.JY().q(var1)) {
               this.q(var5);
            }

            return true;
         }
      } catch (zy | IOException var6) {
         Dw.catching(var6);
         return false;
      }
   }

   private synchronized boolean q(oG var1) {
      try {
         CM var2 = (CM)this.gO.get(var1);
         if (var2 == null) {
            return false;
         } else {
            for (aB var4 : var2.RF) {
               this.Uv.JY().q(var4);
            }

            if (var2.xK != 0L) {
               this.xK(var2.xK);
            }

            this.gO.remove(var1);
            return true;
         }
      } catch (Fx | IOException var5) {
         Dw.catching(var5);
         return false;
      }
   }

   private static String q(String var0) {
      return var0.substring(1, var0.length() - 1).replace('/', '.');
   }

   synchronized boolean q(long var1) {
      if (var1 == 0L) {
         return false;
      } else {
         for (CM var4 : this.gO.values()) {
            if (var4.Dw == var1) {
               return true;
            }
         }

         return false;
      }
   }

   private synchronized boolean RF(long var1) {
      try {
         CM.eo var3 = (CM.eo)this.gP.get(var1);
         if (var3 != null) {
            var3.q++;
            return true;
         } else {
            to var4 = new to();
            var4.q(Vm.RF(var1));
            aB var5 = this.Uv.JY().q(dN.io, m.RF, var4);
            this.gP.put(var1, new CM.eo(var5));
            return true;
         }
      } catch (Fx | IOException var6) {
         Dw.catching(var6);
         return false;
      }
   }

   private synchronized boolean xK(long var1) {
      try {
         CM.eo var3 = (CM.eo)this.gP.get(var1);
         if (var3 == null) {
            return false;
         } else if (var3.q <= 0) {
            throw new si("Unexpected count for method tracking in bpman: " + var3.q);
         } else {
            var3.q--;
            if (var3.q == 0) {
               this.Uv.JY().q(var3.RF);
               this.gP.remove(var1);
            }

            return true;
         }
      } catch (Fx | IOException var4) {
         Dw.catching(var4);
         return false;
      }
   }

   private int q(String var1, Gu var2) {
      to var3 = new to();
      Vm var4 = Vm.q(var1);
      var3.q(var4);
      return this.Uv.lm().q(dN.nf, var3, var2, false);
   }

   private boolean q(int var1) {
      return this.Uv.lm().q(var1);
   }

   private static class eo {
      List q = new ArrayList();
      int RF;
   }
}
