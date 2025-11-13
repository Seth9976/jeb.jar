package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class qZ extends Thread {
   private static final ILogger pC = GlobalLog.getLogger(qZ.class);
   private bA A;
   private Object kS = new Object();
   private List wS = new ArrayList();
   private Map UT = new ConcurrentHashMap();

   public qZ(bA var1) {
      this.A = var1;
   }

   public synchronized void pC(BG var1) {
      synchronized (this.kS) {
         this.wS.add(var1);
         this.kS.notify();
      }
   }

   @Override
   public void run() {
      while (true) {
         BG var1;
         synchronized (this.kS) {
            while (this.wS.isEmpty()) {
               try {
                  this.kS.wait(1000L);
                  if (this.A.pC()) {
                     continue;
                  }

                  Thread.sleep(2000L);
                  if (!this.wS.isEmpty()) {
                     break;
                  }
               } catch (InterruptedException var5) {
                  this.A = null;
                  return;
               }

               return;
            }

            var1 = (BG)this.wS.remove(0);
         }

         try {
            this.A(var1);
         } catch (IOException var4) {
            pC.error("An IO exception happened in the JDWP event processor routine");
            pC.catching(var4);
            return;
         }
      }
   }

   private void A(BG var1) throws IOException {
      if (var1.wS() != 64) {
         if (var1.wS() == -57 && var1.UT() == 1) {
            pC.debug("Target issued a DDM chunk command (will ignore): %s", var1);
         } else {
            pC.error("Target issued an unknown command (will ignore): %p", var1);
         }
      } else {
         AN var2 = this.A.pC(var1.sY());
         Jh var3 = Jh.pC(var2.pC());
         int var4 = var2.wS();
         Object[] var10000 = new Object[]{var3, var4};

         for (int var5 = 0; var5 < var4; var5++) {
            lz var6 = lz.pC(var2.pC());
            int var7 = var2.wS();
            var10000 = new Object[]{var6, var7};
            qZ.Av var8 = (qZ.Av)this.UT.get(var7);
            N var9 = var8 == null ? null : var8.pC;
            long var10 = 0L;
            switch (var6) {
               case A:
               case pC:
                  var10 = var2.sY();
                  Jx var30 = var2.WR();
                  if (var9 != null) {
                     var9.pC(var7, var10, var30, 0L);
                  }

                  for (xl var39 : this.A.NS()) {
                     var39.pC(var10, var30, var6 == lz.pC);
                  }

                  LP var37 = null;
                  if (var6 == lz.pC) {
                     try {
                        var37 = new LP(var6, var7);
                        this.A.oT().pC(var37);
                     } catch (oY var23) {
                        pC.catching(var23);
                     }
                  }

                  if (this.A.ld.containsKey(var10)) {
                     LP var40 = (LP)this.A.ld.remove(var10);
                     var10000 = new Object[]{var40};
                     if (var37 == null || !var37.equals(var40)) {
                        try {
                           this.A.oT().pC(var40);
                        } catch (oY var22) {
                           pC.catching(var22);
                        }
                     }
                  }
                  break;
               case wS:
                  var10 = var2.sY();
                  Jx var29 = var2.WR();
                  var2.pC();
                  var2.E();
                  Jx var42 = var2.WR();
                  if (var9 != null) {
                     var9.pC(var7, var10, var42, 0L);
                  }

                  var10000 = new Object[]{var10, var29, var42};

                  for (xl var18 : this.A.NS()) {
                     var18.pC(var10, var29);
                  }
                  break;
               case oT:
               case fI:
                  var10 = var2.sY();
                  Jx var28 = var2.WR();
                  if (var9 != null) {
                     var9.pC(var7, var10, var28, 0L);
                  }

                  var2.pC();
                  var2.ys();
                  var2.oT();
                  uG.pC(var2);
                  if (var6 == lz.fI) {
                     var2.vP();
                  }

                  for (xl var20 : this.A.NS()) {
                     var20.A(var10, var28);
                  }
                  break;
               case NS:
                  var10 = var2.sY();
                  Jx var27 = var2.WR();
                  if (var9 != null) {
                     var9.pC(var7, var10, var27, 0L);
                  }

                  var10000 = new Object[]{var10, var27};

                  for (xl var38 : this.A.NS()) {
                     var38.kS(var10, var27);
                  }
                  break;
               case vP:
               case xC:
                  var10 = var2.sY();
                  Jx var26 = var2.WR();
                  if (var9 != null) {
                     var9.pC(var7, var10, var26, 0L);
                  }

                  var10000 = new Object[]{var10, var26};
                  rG var34 = var6 == lz.xC ? var2.vP() : null;
                  if (this.A.sY.pC(var26.wS)) {
                     for (xl var41 : this.A.NS()) {
                        var41.pC(var10, var26, var34);
                     }
                  } else {
                     try {
                        this.A.gp().gp(var10);
                     } catch (oY var21) {
                        pC.catching(var21);
                     }
                  }
                  break;
               case E:
                  var10 = var2.sY();
                  if (var9 != null) {
                     var9.pC(var7, var10, null, 0L);
                  }

                  for (xl var33 : this.A.NS()) {
                     var33.pC(var10);
                  }
                  break;
               case sY:
                  var10 = var2.sY();
                  if (var9 != null) {
                     var9.pC(var7, var10, null, 0L);
                  }

                  for (xl var32 : this.A.NS()) {
                     var32.A(var10);
                  }
                  break;
               case ys:
                  var10 = var2.sY();
                  var2.pC();
                  long var31 = var2.ys();
                  String var15 = var2.NS();
                  int var16 = var2.wS();
                  if (var9 != null) {
                     var9.pC(var7, var10, null, var31);
                  }

                  var10000 = new Object[]{var15, var16};

                  for (xl var46 : this.A.NS()) {
                     ;
                  }
                  break;
               case gp:
                  if (var9 != null) {
                     var9.pC(var7, var10, null, 0L);
                  }
                  break;
               case UO:
                  if (var9 != null) {
                     var9.pC(var7, var10, null, 0L);
                  }
                  break;
               case Ab:
                  if (var9 != null) {
                     var9.pC(var7, var10, null, 0L);
                  }

                  for (xl var13 : this.A.NS()) {
                     var13.pC();
                  }
                  break;
               default:
                  if (var9 != null) {
                     var9.pC(var7, var10, null, 0L);
                  }

                  pC.error("Unsupported event type: %s", var6);
            }

            if (var9 != null) {
               this.pC(var7, var10);
            }
         }
      }
   }

   public synchronized int pC(lz var1, Fz var2, N var3, boolean var4) {
      if (var3 == null) {
         return -1;
      } else {
         try {
            if (var4) {
               var2.pC(AM.pC(1));
            }

            int var5 = this.A.oT().pC(var1, Jh.A, var2).A();
            qZ.Av var6 = new qZ.Av();
            var6.pC = var3;
            var6.A = var4 ? 1 : -1;
            this.UT.put(var5, var6);
            return var5;
         } catch (oY | IOException var7) {
            return -1;
         }
      }
   }

   private synchronized boolean pC(int var1, long var2) {
      try {
         this.A.gp().gp(var2);
         qZ.Av var4 = (qZ.Av)this.UT.get(var1);
         var4.A--;
         if (var4.A == 0) {
            this.UT.remove(var1);
         }

         return true;
      } catch (oY | IOException var5) {
         return false;
      }
   }

   public synchronized boolean pC(int var1) {
      try {
         lz var2 = this.A.gp().A(var1);
         if (var2 == null) {
            return false;
         } else {
            this.A.oT().pC(new LP(var2, var1));
            this.UT.remove(var1);
            return true;
         }
      } catch (oY | IOException var3) {
         return false;
      }
   }

   private static class Av {
      N pC;
      int A;
   }
}
