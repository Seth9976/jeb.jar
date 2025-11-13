package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.collect.BytePipe;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;

public class Fi {
   private static final ILogger pC = GlobalLog.getLogger(Fi.class);
   private byte[] A;
   private boolean kS;

   public Fi(byte[] var1) {
      this.A = var1;
   }

   public byte[] pC() {
      return this.A;
   }

   public void pC(boolean var1) {
      this.kS = var1;
   }

   public boolean A() {
      return this.kS;
   }

   public boolean kS() {
      if (this.A.length == 0) {
         return false;
      } else {
         char var1 = (char)this.A[0];
         switch (var1) {
            case 'N':
            case 'S':
            case 'T':
            case 'W':
            case 'X':
            case 'w':
               return true;
            case 'O':
               return this.A.length == 1 || (char)this.A[1] != 'K';
            default:
               return false;
         }
      }
   }

   static byte[] pC(String var0) {
      return pC(Strings.encodeASCII(var0));
   }

   static byte[] pC(byte[] var0) {
      return pC(var0, false);
   }

   private static byte[] pC(byte[] var0, boolean var1) {
      byte[] var2 = kS(var0);
      String var3 = Strings.ff("%02x", A(var0));
      byte[] var4 = new byte[var2.length + 4 + (var1 ? 1 : 0)];
      var4[0] = 36;
      ArrayUtil.copyBytes(var4, 1, var2, 0, var2.length);
      var4[1 + var2.length] = 35;
      var4[1 + var2.length + 1] = (byte)var3.charAt(0);
      var4[1 + var2.length + 2] = (byte)var3.charAt(1);
      if (var1) {
         var4[1 + var2.length + 3] = 10;
      }

      return var4;
   }

   static int A(byte[] var0) {
      return pC(var0, 0, var0.length);
   }

   static int pC(byte[] var0, int var1, int var2) {
      byte var3 = 0;

      for (int var4 = var1; var4 < var2; var4++) {
         var3 += var0[var4];
      }

      return var3 & 0xFF;
   }

   static byte[] kS(byte[] var0) {
      int var1 = 0;

      for (int var2 = 0; var2 < var0.length; var2++) {
         byte var3 = var0[var2];
         if (var3 == 35 || var3 == 36 || var3 == 125) {
            var1++;
         }
      }

      if (var1 == 0) {
         return var0;
      } else {
         byte[] var6 = new byte[var0.length + var1];
         int var7 = 0;

         for (int var4 = 0; var4 < var0.length; var4++) {
            byte var5 = var0[var4];
            if (var5 != 35 && var5 != 36 && var5 != 125) {
               var6[var4 + var7] = var0[var4];
            } else {
               var6[var4 + var7] = 125;
               var6[var4 + var7 + 1] = (byte)(var0[var4] ^ 32);
               var7++;
            }
         }

         return var6;
      }
   }

   static byte[] pC(byte[] var0, int var1, int var2, int var3) {
      BytePipe var4 = new BytePipe();
      int var5 = var1;

      while (var5 < var2) {
         int var6 = var0[var5] & 255;
         if (var6 == 125) {
            var6 = var0[var5 + 1] & 255 ^ 32;
            var4.append(var6);
            var5 += 2;
         } else if (var6 != 42) {
            var4.append(var6);
            var5++;
         } else {
            byte var8 = var0[var5 - 1];
            int var7 = (var0[var5 + 1] & 255) - 29;

            while (var7-- > 0) {
               var4.append((int)var8);
            }

            var5 += 2;
         }
      }

      return var4.getAll();
   }
}
