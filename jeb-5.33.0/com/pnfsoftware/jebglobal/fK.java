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

public class fK {
   private static final ILogger wS = GlobalLog.getLogger(fK.class);
   public static int pC = 0;
   public static int A = 1;
   public static int kS = 2;
   private bA UT;
   private Jh E = Jh.A;
   private Map sY = new HashMap();
   private Map ys = new HashMap();
   private Map ld = new HashMap();

   fK(bA var1) {
      this.UT = var1;
   }

   public synchronized Jh pC() {
      return this.E;
   }

   public synchronized boolean pC(PZ var1, int var2) {
      return this.pC(var1, var2, false);
   }

   public synchronized boolean pC(PZ var1, int var2, boolean var3) {
      try {
         String var4 = pC(var1.A);
         fK.Av var5 = (fK.Av)this.ys.get(var4);
         if (var5 != null) {
            if (var5.pC.contains(var1)) {
               return true;
            }
         } else {
            var5 = new fK.Av();
            this.ys.put(var4, var5);
            var5.A = this.pC(var4, new IQ(this, var4, var2));
         }

         List var6 = this.UT.oT().A(var1.A);
         boolean var7 = false;

         for (long var9 : var6) {
            Jx var11 = this.UT.oT().pC(var1, var9);
            if (var11 != null) {
               this.pC(var11, var2);
               var7 = true;
            }
         }

         if (!var7) {
            if (!var6.isEmpty()) {
               String var13 = Strings.ff(S.L("Cannot resolve method for %s for now. Do you expect future classload?"), var1);
               wS.warn(var13);
               if (var3) {
                  return false;
               }
            } else {
               wS.info(S.L("Set breakpoint on %s. Class is not loaded"), var1);
            }
         }

         var5.pC.add(var1);
         return true;
      } catch (wX | IOException var12) {
         wS.catching(var12);
         return false;
      }
   }

   private synchronized boolean pC(Jx var1, int var2) {
      try {
         ts var3 = (ts)this.sY.get(var1);
         if (var3 != null) {
            return false;
         } else {
            Fz var4 = new Fz();
            ArrayList var5 = new ArrayList();
            switch (var1.A) {
               case -3:
                  var4.pC(AM.pC(new Jx((byte)1, var1.kS, var1.wS, 0L)));
                  var5.add(this.UT.oT().pC(lz.NS, this.pC(), var4));
                  break;
               case -2:
                  var4.pC(AM.pC(var1.kS, var1.wS));
                  if (var2 == pC || (var2 & A) != 0) {
                     var5.add(this.UT.oT().pC(lz.oT, this.pC(), var4));
                  }

                  if (var2 == pC || (var2 & kS) != 0) {
                     var5.add(this.UT.oT().pC(lz.fI, this.pC(), var4));
                  }
                  break;
               case -1:
               case 0:
               default:
                  return false;
               case 1:
                  var4 = new Fz();
                  var4.pC(AM.pC(var1));
                  var5.add(this.UT.oT().pC(lz.A, this.pC(), var4));
            }

            var3 = new ts(var1, var5);
            this.sY.put(var1, var3);
            if (var1.A == -3 && this.A(var1.kS)) {
               var3.kS = var1.kS;
               var3.wS = var1.wS;
            }

            return true;
         }
      } catch (oY | IOException var6) {
         wS.catching(var6);
         return false;
      }
   }

   public synchronized boolean pC(PZ var1) {
      try {
         String var2 = pC(var1.A);
         fK.Av var3 = (fK.Av)this.ys.get(var2);
         if (var3 == null) {
            return false;
         } else if (!var3.pC.remove(var1)) {
            return false;
         } else {
            if (var3.pC.isEmpty()) {
               this.pC(var3.A);
               this.ys.remove(var2);
            }

            for (Jx var5 : this.UT.oT().pC(var1)) {
               this.pC(var5);
            }

            return true;
         }
      } catch (wX | IOException var6) {
         wS.catching(var6);
         return false;
      }
   }

   private synchronized boolean pC(Jx var1) {
      try {
         ts var2 = (ts)this.sY.get(var1);
         if (var2 == null) {
            return false;
         } else {
            for (LP var4 : var2.A) {
               this.UT.oT().pC(var4);
            }

            if (var2.kS != 0L) {
               this.kS(var2.kS);
            }

            this.sY.remove(var1);
            return true;
         }
      } catch (oY | IOException var5) {
         wS.catching(var5);
         return false;
      }
   }

   private static String pC(String var0) {
      return var0.substring(1, var0.length() - 1).replace('/', '.');
   }

   synchronized boolean pC(long var1) {
      if (var1 == 0L) {
         return false;
      } else {
         for (ts var4 : this.sY.values()) {
            if (var4.wS == var1) {
               return true;
            }
         }

         return false;
      }
   }

   private synchronized boolean A(long var1) {
      try {
         ts.Av var3 = (ts.Av)this.ld.get(var1);
         if (var3 != null) {
            var3.pC++;
            return true;
         } else {
            Fz var4 = new Fz();
            var4.pC(AM.pC(var1));
            LP var5 = this.UT.oT().pC(lz.xC, Jh.A, var4);
            this.ld.put(var1, new ts.Av(var5));
            return true;
         }
      } catch (oY | IOException var6) {
         wS.catching(var6);
         return false;
      }
   }

   private synchronized boolean kS(long var1) {
      try {
         ts.Av var3 = (ts.Av)this.ld.get(var1);
         if (var3 == null) {
            return false;
         } else if (var3.pC <= 0) {
            throw new yb("Unexpected count for method tracking in bpman: " + var3.pC);
         } else {
            var3.pC--;
            if (var3.pC == 0) {
               this.UT.oT().pC(var3.A);
               this.ld.remove(var1);
            }

            return true;
         }
      } catch (oY | IOException var4) {
         wS.catching(var4);
         return false;
      }
   }

   private int pC(String var1, N var2) {
      Fz var3 = new Fz();
      AM var4 = AM.pC(var1);
      var3.pC(var4);
      return this.UT.ld().pC(lz.ys, var3, var2, false);
   }

   private boolean pC(int var1) {
      return this.UT.ld().pC(var1);
   }

   private static class Av {
      List pC = new ArrayList();
      int A;
   }
}
