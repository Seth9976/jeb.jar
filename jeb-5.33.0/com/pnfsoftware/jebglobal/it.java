package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class it extends Thread {
   private static final ILogger pC = GlobalLog.getLogger(it.class);
   private aI A;
   private Object kS = new Object();
   private List wS = new ArrayList();
   private volatile boolean UT;

   public it(aI var1) {
      super(it.class.getSimpleName());
      this.A = var1;
   }

   public void pC(boolean var1) {
      this.UT = var1;
   }

   public void pC(Fi var1) {
      synchronized (this.kS) {
         this.wS.add(var1);
         this.kS.notify();
      }
   }

   @Override
   public void run() {
      while (true) {
         Fi var1;
         synchronized (this.kS) {
            while (this.wS.isEmpty()) {
               try {
                  this.kS.wait(1000L);
               } catch (InterruptedException var8) {
                  this.A = null;
                  return;
               }
            }

            var1 = (Fi)this.wS.remove(0);
         }

         try {
            synchronized (this.A.wS) {
               this.A(var1);
            }
         } catch (IOException var6) {
            pC.catching(var6);
            return;
         } catch (Ae var7) {
            pC.catching(var7);
         }
      }
   }

   private void A(Fi var1) throws IOException, Ae {
      this.A.UT = false;
      Ez var2 = this.A.pC(var1.pC());
      if (var2.pC() == 79) {
         for (ZB var15 : this.A.FE()) {
            var15.pC(var2.A());
         }
      } else {
         pC.debug("Received stop-reply from server: %s", var2);
         rH var3 = rH.pC;
         LD var4 = null;
         Long var5 = null;
         boolean var6 = true;
         if (var2.ys()) {
            var3 = rH.kS;
         } else {
            rs var7 = (rs)this.A.ED.get(var2.kS());
            if (var7 == rs.A) {
               this.A.E.sY();
            } else if (var7 == rs.kS) {
               var6 = false;
               this.A.E.sY();
            } else {
               List var9 = var2.E();
               if (var9 != null) {
                  this.A.E.kS = var9;
               }

               var5 = var2.sY().pC("pc");
               if (var5 == null) {
                  var4 = this.A.E.A(var2.UT());
                  var5 = var4.getProgramCounter();
               }

               boolean var8;
               if (this.A.gp.pC()) {
                  if (this.A.os() != var2.UT()) {
                     try {
                        this.A.E.sY();
                     } catch (IOException var11) {
                        pC.catching(var11);
                     }

                     return;
                  }

                  var8 = true;
                  if (var4 == null) {
                     var4 = this.A.E.A(var2.UT());
                  }

                  this.A.gp.pC(var4, false);
                  this.A.gp.pC(false);
               } else {
                  var8 = this.A.ld.pC(var5);
               }

               this.A.ld.pC(false);
               if (var8) {
                  var3 = rH.A;
                  if (this.UT) {
                     try {
                        this.A.E.sY();
                     } catch (IOException var12) {
                        pC.catching(var12);
                     }

                     var6 = false;
                  }
               }
            }
         }

         if (var6) {
            for (ZB var18 : this.A.FE()) {
               var18.pC(var3, var2.wS(), var2.UT(), var2.kS(), var5 == null ? 0L : var5);
            }
         }
      }
   }
}
