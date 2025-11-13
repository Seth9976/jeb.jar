package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.AbstractContext;
import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.events.ControllerNotification;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.util.io.LEDataInputStream;
import com.pnfsoftware.jeb.util.io.LEDataOutputStream;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Random;

public class Nt implements Runnable {
   private static final ILogger q = GlobalLog.getLogger(Nt.class);
   private static final int RF = 35000;
   private static final int xK = 3;
   private static final int Dw = 3;
   private Xa Uv;
   private oM oW;
   private long gO;
   private long nf;
   private volatile long gP;

   public Nt(Xa var1, oM var2) {
      if (var1 != null && var2 != null) {
         this.Uv = var1;
         this.oW = var2;
         this.gO = 1L;
         this.gO = this.gO * tl.RF();
         this.gO = this.gO * Licensing.license_id;
         this.gO = this.gO * System.getProperty(cvm.q(new byte[]{54, 28, 21, 11, 92, 7, 6, 5, 17}, 2, 26), "").hashCode();
         this.nf = new Random(System.currentTimeMillis()).nextLong();
      } else {
         throw new IllegalArgumentException();
      }
   }

   public long q() {
      return this.gP;
   }

   @Override
   public void run() {
      int var1 = 0;
      int var2 = 0;
      boolean var3 = false;

      while (true) {
         label68:
         while (true) {
            long var4 = this.xK();
            if (var4 != 0L && var4 != 2L) {
               q.warn(S.L("A problem occurred when attempting to communicate with the JEB controller (code: %d)"), var4);
               boolean var6 = false;
               if (var4 <= -1L) {
                  if (++var1 >= 3) {
                     var6 = true;
                  }
               } else {
                  var6 = true;
               }

               if (!var6) {
                  break;
               }

               ControllerNotification var7 = new ControllerNotification();
               var7.ctlCode = var4;
               var7.ctlHintContinueLeft = 3 - var2 - 1;
               int var8 = 0;
               long var9 = 200L;

               while (true) {
                  var8++;
                  this.Uv.notifyListeners(new JebEvent(J.FloatingNotification, var7));
                  if (var7.processed || var8 >= 6) {
                     if (var7.clientChoice == 1) {
                        if (++var2 < 3) {
                           continue label68;
                        }
                     }

                     AbstractContext.terminate();
                     break label68;
                  }

                  try {
                     Thread.sleep(var9);
                     var9 = (long)(var9 * 1.8);
                  } catch (InterruptedException var11) {
                  }
               }
            }

            if (var4 == 2L && !var3) {
               q.warn(S.L("Your JEB client is older than the controller you are connected to! To avoid unexpected behavior, you should update your software"));
               var3 = true;
            }

            if (var1 != 0 || var2 != 0) {
               q.warn(S.L("The connection with the JEB controller has been restored"));
            }

            var1 = 0;
            var2 = 0;
            break;
         }

         try {
            Thread.sleep(35000L);
         } catch (InterruptedException var12) {
            return;
         }
      }
   }

   private long xK() {
      try {
         ByteArrayOutputStream var1 = new ByteArrayOutputStream();
         LEDataOutputStream var2 = new LEDataOutputStream(var1);
         var2.writeInt(0);
         var2.writeLong(this.gO);
         var2.writeLong(this.nf);
         var2.close();
         int[] var3 = new int[1];
         byte[] var4 = qV.q(tl.RF(), var1.toByteArray(), var3);
         String var5 = qV.q(var4);
         String var6 = this.oW.q(var5);
         if (var6 == null) {
            return -1L;
         } else {
            qV var7 = qV.RF(qV.q(var6.trim()));
            if (var7 == null) {
               return -1L;
            } else {
               this.gP = var7.Uv();
               byte[] var8 = var7.lm();
               LEDataInputStream var9 = new LEDataInputStream(new ByteArrayInputStream(var8));
               int var10 = var9.readInt();
               return var10 != 1 ? -1L : var9.readLong();
            }
         }
      } catch (Exception var11) {
         q.catching(var11);
         return -1L;
      }
   }

   public void RF() {
      try {
         ByteArrayOutputStream var1 = new ByteArrayOutputStream();
         LEDataOutputStream var2 = new LEDataOutputStream(var1);
         var2.writeInt(2);
         var2.writeLong(this.gO);
         var2.writeLong(this.nf);
         var2.close();
         int[] var3 = new int[1];
         byte[] var4 = qV.q(tl.RF(), var1.toByteArray(), var3);
         String var5 = qV.q(var4);
         this.oW.RF(var5);
      } catch (Exception var6) {
         q.catching(var6);
      }
   }
}
