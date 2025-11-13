package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class ON extends Thread {
   private static final ILogger q = GlobalLog.getLogger(ON.class);
   private oH RF;
   private Object xK = new Object();
   private List Dw = new ArrayList();
   private volatile boolean Uv;

   public ON(oH var1) {
      super(ON.class.getSimpleName());
      this.RF = var1;
   }

   public void q(boolean var1) {
      this.Uv = var1;
   }

   public boolean q() {
      return this.Uv;
   }

   public void q(cq var1) {
      synchronized (this.xK) {
         this.Dw.add(var1);
         this.xK.notify();
      }
   }

   @Override
   public void run() {
      while (true) {
         cq var1;
         synchronized (this.xK) {
            while (this.Dw.isEmpty()) {
               try {
                  this.xK.wait(1000L);
               } catch (InterruptedException var8) {
                  this.RF = null;
                  return;
               }
            }

            var1 = (cq)this.Dw.remove(0);
         }

         try {
            synchronized (this.RF.Dw) {
               this.RF(var1);
            }
         } catch (IOException var6) {
            q.catching(var6);
            return;
         } catch (WI var7) {
            q.catching(var7);
         }
      }
   }

   private void RF(cq var1) throws IOException, WI {
      this.RF.Uv = false;
      Yl var2 = this.RF.q(var1.xK());
      if (var2.q() == 79) {
         for (HJ var15 : this.RF.wF()) {
            var15.q(var2.RF());
         }
      } else {
         q.debug("Received stop-reply from server: %s", var2);
         af var3 = af.q;
         Ht var4 = null;
         Long var5 = null;
         boolean var6 = true;
         if (var2.lm()) {
            var3 = af.xK;
         } else {
            tV var7 = (tV)this.RF.qa.get(var2.xK());
            if (var7 == tV.RF) {
               this.RF.oW.gO();
            } else if (var7 == tV.xK) {
               var6 = false;
               this.RF.oW.gO();
            } else {
               List var9 = var2.gO();
               if (var9 != null) {
                  this.RF.oW.xK = var9;
               }

               var5 = var2.za().q("pc");
               if (var5 == null) {
                  var4 = this.RF.oW.RF(var2.Uv());
                  var5 = var4.getProgramCounter();
               }

               boolean var8;
               if (this.RF.za.q()) {
                  if (this.RF.Rr() != var2.Uv()) {
                     try {
                        this.RF.oW.gO();
                     } catch (IOException var11) {
                        q.catching(var11);
                     }

                     return;
                  }

                  var8 = true;
                  if (var4 == null) {
                     var4 = this.RF.oW.RF(var2.Uv());
                  }

                  this.RF.za.q(var4, false);
                  this.RF.za.q(false);
               } else {
                  var8 = this.RF.gP.RF(var5);
               }

               this.RF.gP.q(false);
               if (var8) {
                  var3 = af.RF;
                  if (this.Uv) {
                     try {
                        this.RF.oW.gO();
                     } catch (IOException var12) {
                        q.catching(var12);
                     }

                     var6 = false;
                  }
               }
            }
         }

         if (var6) {
            for (HJ var18 : this.RF.wF()) {
               var18.q(var3, var2.Dw(), var2.Uv(), var2.xK(), var5 == null ? 0L : var5);
            }
         }
      }
   }
}
