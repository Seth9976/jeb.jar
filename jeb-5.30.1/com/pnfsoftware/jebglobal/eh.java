package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class eh extends Thread {
   private static final ILogger q = GlobalLog.getLogger(eh.class);
   private Ux RF;
   private Object xK = new Object();
   private List Dw = new ArrayList();
   private Map Uv = new ConcurrentHashMap();

   public eh(Ux var1) {
      this.RF = var1;
   }

   public synchronized void q(zx var1) {
      synchronized (this.xK) {
         this.Dw.add(var1);
         this.xK.notify();
      }
   }

   @Override
   public void run() {
      while (true) {
         zx var1;
         synchronized (this.xK) {
            while (this.Dw.isEmpty()) {
               try {
                  this.xK.wait(1000L);
                  if (this.RF.q()) {
                     continue;
                  }

                  Thread.sleep(2000L);
                  if (!this.Dw.isEmpty()) {
                     break;
                  }
               } catch (InterruptedException var5) {
                  this.RF = null;
                  return;
               }

               return;
            }

            var1 = (zx)this.Dw.remove(0);
         }

         try {
            this.RF(var1);
         } catch (IOException var4) {
            q.error("An IO exception happened in the JDWP event processor routine");
            q.catching(var4);
            return;
         }
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private void RF(zx var1) throws IOException {
      if (var1.oW() != 64) {
         if (var1.oW() == -57 && var1.gO() == 1) {
            q.debug("Target issued a DDM chunk command (will ignore): %s", var1);
         } else {
            q.error("Target issued an unknown command (will ignore): %p", var1);
         }
      } else {
         Hv var2 = this.RF.q(var1.gP());
         m var3 = m.q(var2.RF());
         int var4 = var2.Uv();
         Object[] var10000 = new Object[]{var3, var4};

         for (int var5 = 0; var5 < var4; var5++) {
            dN var6 = dN.q(var2.RF());
            int var7 = var2.Uv();
            var10000 = new Object[]{var6, var7};
            eh.eo var8 = (eh.eo)this.Uv.get(var7);
            Gu var9 = var8 == null ? null : var8.q;
            long var10 = 0L;
            switch (var6) {
               case RF:
               case q:
                  var10 = var2.nf();
                  oG var30 = var2.oQ();
                  if (var9 != null) {
                     var9.q(var7, var10, var30, 0L);
                  }

                  for (qt var39 : this.RF.io()) {
                     var39.q(var10, var30, var6 == dN.q);
                  }

                  aB var37 = null;
                  if (var6 == dN.q) {
                     try {
                        var37 = new aB(var6, var7);
                        this.RF.JY().q(var37);
                     } catch (Fx var23) {
                        q.catching(var23);
                     }
                  }

                  if (this.RF.gP.containsKey(var10)) {
                     aB var40 = (aB)this.RF.gP.remove(var10);
                     var10000 = new Object[]{var40};
                     if (var37 == null || !var37.equals(var40)) {
                        try {
                           this.RF.JY().q(var40);
                        } catch (Fx var22) {
                           q.catching(var22);
                        }
                     }
                  }
                  break;
               case Dw:
                  var10 = var2.nf();
                  oG var29 = var2.oQ();
                  var2.RF();
                  var2.gO();
                  oG var42 = var2.oQ();
                  if (var9 != null) {
                     var9.q(var7, var10, var42, 0L);
                  }

                  var10000 = new Object[]{var10, var29, var42};

                  for (qt var18 : this.RF.io()) {
                     var18.q(var10, var29);
                  }
                  break;
               case lm:
               case zz:
                  var10 = var2.nf();
                  oG var28 = var2.oQ();
                  if (var9 != null) {
                     var9.q(var7, var10, var28, 0L);
                  }

                  var2.RF();
                  var2.HF();
                  var2.Me();
                  VO.q(var2);
                  if (var6 == dN.zz) {
                     var2.KT();
                  }

                  for (qt var20 : this.RF.io()) {
                     var20.RF(var10, var28);
                  }
                  break;
               case HF:
                  var10 = var2.nf();
                  oG var27 = var2.oQ();
                  if (var9 != null) {
                     var9.q(var7, var10, var27, 0L);
                  }

                  var10000 = new Object[]{var10, var27};

                  for (qt var38 : this.RF.io()) {
                     var38.xK(var10, var27);
                  }
                  break;
               case LK:
               case io:
                  var10 = var2.nf();
                  oG var26 = var2.oQ();
                  if (var9 != null) {
                     var9.q(var7, var10, var26, 0L);
                  }

                  var10000 = new Object[]{var10, var26};
                  ch var34 = var6 == dN.io ? var2.KT() : null;
                  if (this.RF.gO.q(var26.Dw)) {
                     for (qt var41 : this.RF.io()) {
                        var41.q(var10, var26, var34);
                     }
                  } else {
                     try {
                        this.RF.zz().zz(var10);
                     } catch (Fx var21) {
                        q.catching(var21);
                     }
                  }
                  break;
               case oW:
                  var10 = var2.nf();
                  if (var9 != null) {
                     var9.q(var7, var10, null, 0L);
                  }

                  for (qt var33 : this.RF.io()) {
                     var33.q(var10);
                  }
                  break;
               case gO:
                  var10 = var2.nf();
                  if (var9 != null) {
                     var9.q(var7, var10, null, 0L);
                  }

                  for (qt var32 : this.RF.io()) {
                     var32.RF(var10);
                  }
                  break;
               case nf:
                  var10 = var2.nf();
                  var2.RF();
                  long var31 = var2.HF();
                  String var15 = var2.xW();
                  int var16 = var2.Uv();
                  if (var9 != null) {
                     var9.q(var7, var10, null, var31);
                  }

                  var10000 = new Object[]{var15, var16};

                  for (qt var46 : this.RF.io()) {
                     ;
                  }
                  break;
               case za:
                  if (var9 != null) {
                     var9.q(var7, var10, null, 0L);
                  }
                  break;
               case oQ:
                  if (var9 != null) {
                     var9.q(var7, var10, null, 0L);
                  }
                  break;
               case xW:
                  if (var9 != null) {
                     var9.q(var7, var10, null, 0L);
                  }

                  for (qt var13 : this.RF.io()) {
                     var13.q();
                  }
                  break;
               default:
                  if (var9 != null) {
                     var9.q(var7, var10, null, 0L);
                  }

                  q.error("Unsupported event type: %s", var6);
            }

            if (var9 != null) {
               this.q(var7, var10);
            }
         }
      }
   }

   public synchronized int q(dN var1, to var2, Gu var3, boolean var4) {
      if (var3 == null) {
         return -1;
      } else {
         try {
            if (var4) {
               var2.q(Vm.q(1));
            }

            int var5 = this.RF.JY().q(var1, m.RF, var2).RF();
            eh.eo var6 = new eh.eo();
            var6.q = var3;
            var6.RF = var4 ? 1 : -1;
            this.Uv.put(var5, var6);
            return var5;
         } catch (Fx | IOException var7) {
            return -1;
         }
      }
   }

   private synchronized boolean q(int var1, long var2) {
      try {
         this.RF.zz().zz(var2);
         eh.eo var4 = (eh.eo)this.Uv.get(var1);
         var4.RF--;
         if (var4.RF == 0) {
            this.Uv.remove(var1);
         }

         return true;
      } catch (Fx | IOException var5) {
         return false;
      }
   }

   public synchronized boolean q(int var1) {
      try {
         dN var2 = this.RF.zz().RF(var1);
         if (var2 == null) {
            return false;
         } else {
            this.RF.JY().q(new aB(var2, var1));
            this.Uv.remove(var1);
            return true;
         }
      } catch (Fx | IOException var3) {
         return false;
      }
   }

   private static class eo {
      Gu q;
      int RF;
   }
}
