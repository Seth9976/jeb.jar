package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.collect.BytePipe;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.util.Arrays;

public class qs {
   private static final ILogger q = GlobalLog.getLogger(qs.class);
   private oH RF;

   qs(oH var1) {
      this.RF = var1;
   }

   qs.eo q(String var1, Object... var2) throws IOException, WI {
      StringBuilder var3 = new StringBuilder("vFile:");
      var3.append(var1);
      int var4 = 0;

      for (Object var8 : var2) {
         var3.append(var4 == 0 ? ":" : ",");
         if (var8 instanceof String) {
            var3.append((String)var8);
         } else if (var8 instanceof Integer) {
            Strings.ff(var3, "%x", (Integer)var8);
         } else {
            var3.append(var8.toString());
         }

         var4++;
      }

      byte[] var10 = this.RF.Dw(var3.toString());
      if (var10.length == 0) {
         throw new JG("vFile:" + var1);
      } else if (var10[0] != 70) {
         throw new IOException("Unexpected response to vFile request");
      } else {
         int var12 = 1;

         while (var12 < var10.length && var10[var12] != 44 && var10[var12] != 59) {
            var12++;
         }

         int var11 = MO.nf(Strings.decodeASCII(var10, 1, var12 - 1));
         int var13 = 0;
         if (var12 < var10.length && var10[var12] == 44) {
            int var9 = var12 + 1;

            while (var9 < var10.length && var10[var9] != 59) {
               var9++;
            }

            var13 = MO.nf(Strings.decodeASCII(var10, var12 + 1, var9 - (var12 + 1)));
            if (var10[var9] == 59) {
               var12 = var9;
            }
         }

         byte[] var14 = new byte[0];
         if (var12 < var10.length && var10[var12] == 59) {
            var14 = Arrays.copyOfRange(var10, var12 + 1, var10.length);
         }

         if (var11 < 0) {
            throw new IW(var3.toString(), var11, "errno= " + var13);
         } else {
            return new qs.eo(var11, var13, var14);
         }
      }
   }

   public byte[] q(String var1) throws IOException, WI {
      this.q("setfs", 0);
      qs.eo var2 = this.q("open", MO.Dw(Strings.encodeASCII(var1)), 0, 0);
      int var3 = var2.q;

      try {
         BytePipe var4 = new BytePipe();
         int var5 = 0;

         while (true) {
            var2 = this.q("pread", var3, 16383, var5);
            int var6 = var2.q;
            if (var6 != var2.xK.length) {
               throw new IOException();
            }

            var4.append(var2.xK);
            if (var6 == 0) {
               return var4.getAll();
            }

            var5 += var6;
         }
      } finally {
         this.q("close", var3);
      }
   }

   static class eo {
      private int q;
      private int RF;
      private byte[] xK;

      eo(int var1, int var2, byte[] var3) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
      }

      public int q() {
         return this.q;
      }

      public int RF() {
         return this.RF;
      }

      public byte[] xK() {
         return this.xK;
      }

      @Override
      public String toString() {
         return Strings.ff("result=%d,errno=%d,data=%s", this.q, this.RF, Formatter.byteArrayToHex(this.xK));
      }
   }
}
