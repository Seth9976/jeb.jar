package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import java.io.IOException;

public class bM {
   private static int pC = 536870911;
   private static int A = 1073741823;

   public static int[] pC(EX var0, int var1) throws IOException {
      int[] var2 = new int[var1];
      int var3 = 0;

      while (var3 != var1) {
         int var4 = Math.min(var1 - var3, pC);
         pC(var0, var4, var2, var3);
         var3 += var4;
      }

      return var2;
   }

   private static void pC(EX var0, int var1, int[] var2, int var3) throws IOException {
      byte[] var4 = new byte[var1 * 4];
      var0.readFully(var4);
      byte var5 = 0;

      for (int var6 = 0; var6 < var1; var6++) {
         var2[var3 + var6] = EndianUtil.littleEndianBytesToInt(var4, var5);
         var5 += 4;
      }
   }

   public static String pC(EX var0, int var1, boolean var2) throws IOException {
      StringBuilder var3 = new StringBuilder();

      while (var1-- != 0) {
         short var4 = var0.readShort();
         if (var4 == 0) {
            break;
         }

         var3.append((char)var4);
      }

      if (var2 && var1 > 0) {
         var0.skipBytes(var1 * 2);
      }

      return var3.toString();
   }

   public static int A(EX var0, int var1) throws IOException {
      return pC(var0, var1, true, true);
   }

   public static int pC(EX var0, int var1, boolean var2, boolean var3) throws IOException {
      if (var1 < 0) {
         return 0;
      } else if (var1 == 0) {
         return 0;
      } else {
         int var4 = -1;

         int var5;
         try {
            var4 = var0.pC(var1);
            if (var2 && var4 != var1) {
               throw new IOException(Strings.ff("Skipped only %d/%d bytes", var4, var1));
            }

            var5 = var4;
         } finally {
            if (var3) {
               if (var4 == var1) {
                  var0.pC(S.L("Skipped %d unknown bytes"), var4);
               } else {
                  var0.pC(S.L("Skipped %d/%d unknown bytes"), var4, var1);
               }
            }
         }

         return var5;
      }
   }
}
