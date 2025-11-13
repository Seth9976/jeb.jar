package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.AbstractContext;
import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.client.SystemInformation;
import com.pnfsoftware.jeb.core.events.ControllerNotification;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.LEDataInputStream;
import com.pnfsoftware.jeb.util.io.LEDataOutputStream;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Random;

public class cq implements Runnable {
   private static final ILogger pC = GlobalLog.getLogger(cq.class);
   private GA A;
   private bO kS;
   private long wS;
   private long UT;
   private volatile long E;

   public cq(GA var1, bO var2) {
      if (var1 != null && var2 != null) {
         this.A = var1;
         this.kS = var2;
         this.wS = 1L;
         this.wS = this.wS * vJ.A();
         this.wS = this.wS * Licensing.license_id;
         this.wS = this.wS * System.getProperty(ckx.pC(new byte[]{10, 6, 22, 23, 92, 64, 15, 12, 8}, 1, 127), "").hashCode();
         this.UT = new Random(System.currentTimeMillis()).nextLong();
      } else {
         throw new IllegalArgumentException();
      }
   }

   public long pC() {
      return this.E;
   }

   @Override
   public void run() {
      int var1 = 0;
      int var2 = 0;
      boolean var3 = false;

      while (true) {
         label68:
         while (true) {
            long var4 = this.kS();
            if (var4 != 0L && var4 != 2L) {
               pC.warn(S.L("A problem occurred when attempting to communicate with the JEB controller (code: %d)"), var4);
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
                  this.A.notifyListeners(new JebEvent(J.FloatingNotification, var7));
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
               pC.warn(S.L("Your JEB client is older than the controller you are connected to! To avoid unexpected behavior, you should update your software"));
               var3 = true;
            }

            if (var1 != 0 || var2 != 0) {
               pC.warn(S.L("The connection with the JEB controller has been restored"));
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

   private long kS() {
      try {
         ByteArrayOutputStream var1 = new ByteArrayOutputStream();
         LEDataOutputStream var2 = new LEDataOutputStream(var1);

         try {
            var2.writeInt(0);
            var2.writeLong(this.wS);
            var2.writeLong(this.UT);
            var2.writeInt(1);
            String var3 = Strings.truncateWithSuffix(SystemInformation.username, 256, "...");
            var2.writeInt(var3.length());
            var2.write(Strings.encodeUTF8(var3));
            var3 = Strings.truncateWithSuffix(SystemInformation.compname, 256, "...");
            var2.writeInt(var3.length());
            var2.write(Strings.encodeUTF8(var3));
            var3 = Strings.truncateWithSuffix(this.kS.pC(), 512, "...");
            var2.writeInt(var3.length());
            var2.write(Strings.encodeUTF8(var3));
         } catch (Throwable var11) {
            try {
               var2.close();
            } catch (Throwable var10) {
               var11.addSuppressed(var10);
            }

            throw var11;
         }

         var2.close();
         int[] var13 = new int[1];
         byte[] var16 = pB.pC(vJ.A(), var1.toByteArray(), var13);
         String var4 = pB.pC(var16);
         String var5 = this.kS.pC(var4);
         if (var5 == null) {
            return -1L;
         } else {
            pB var6 = pB.A(pB.pC(var5.trim()));
            if (var6 == null) {
               return -1L;
            } else {
               this.E = var6.kS();
               byte[] var7 = var6.UT();
               LEDataInputStream var8 = new LEDataInputStream(new ByteArrayInputStream(var7));
               int var9 = var8.readInt();
               return var9 != 1 ? -1L : var8.readLong();
            }
         }
      } catch (Exception var12) {
         pC.catching(var12);
         return -1L;
      }
   }

   public void A() {
      try {
         ByteArrayOutputStream var1 = new ByteArrayOutputStream();

         try (LEDataOutputStream var2 = new LEDataOutputStream(var1)) {
            var2.writeInt(2);
            var2.writeLong(this.wS);
            var2.writeLong(this.UT);
         }

         int[] var8 = new int[1];
         byte[] var3 = pB.pC(vJ.A(), var1.toByteArray(), var8);
         String var4 = pB.pC(var3);
         this.kS.A(var4);
      } catch (Exception var7) {
         pC.catching(var7);
      }
   }
}
