package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;

public class baf extends azs {
   String A;

   public baf(byte[] var1, int var2, int var3) {
      this.A = pC(var1, var2, var3);
   }

   public String A() {
      return this.A;
   }

   @Override
   public bah pC() {
      return bah.pC;
   }

   public static String pC(byte[] var0, int var1, int var2) {
      char[] var3 = new char[var2];
      int var4 = 0;
      int var5 = var1;
      int var6 = var1 + var2;

      while (var5 < var6) {
         int var7 = var0[var5] & 255;
         var5++;
         if (var4 >= var2) {
            pC("MUTF8: string is longer than expected: " + var2, var0, var5);
         }

         if ((var7 & 128) != 0) {
            if ((var7 & 224) == 192) {
               if (var5 >= var6) {
                  pC("MUTF8: 2be: not enough data at offset " + var5, var0, var5);
               }

               int var8 = var0[var5] & 255;
               var5++;
               if ((var8 & 192) == 128) {
                  var7 = (var7 & 31) << 6 | var8 & 63;
               } else {
                  pC("MUTF8: Invalid 2-byte encoding at offset " + var5, var0, var5);
               }
            } else if ((var7 & 240) == 224) {
               if (var5 + 2 > var6) {
                  pC("MUTF8: 3be: not enough data at offset " + var5, var0, var5);
               }

               int var11 = var0[var5] & 255;
               int var9 = var0[++var5] & 255;
               var5++;
               if ((var11 & 192) == 128 && (var9 & 192) == 128) {
                  var7 = (var7 & 15) << 12 | (var11 & 63) << 6 | var9 & 63;
               } else {
                  pC("MUTF8: Invalid 3-byte encoding at offset " + var5, var0, var5);
               }
            } else {
               pC("MUTF8: Invalid encoding at offset " + var5, var0, var5);
            }
         }

         var3[var4++] = (char)var7;
      }

      return new String(var3, 0, var4);
   }

   private static void pC(String var0, byte[] var1, int var2) {
      if (var0 == null) {
         var0 = "Illegal encoding";
      }

      int var3 = var1.length - var2;
      if (var3 < 0) {
         throw new RuntimeException(Strings.ff("%s: error, nothing to decode", var0));
      } else {
         int var4 = Math.min(var3, 16);
         throw new RuntimeException(Strings.ff("%s: %s", var0, Formatter.formatBinaryLine(var1, var2, var4, 0)));
      }
   }
}
