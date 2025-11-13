package com.pnfsoftware.jebglobal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class TS {
   public static int q(InputStream var0) throws IOException {
      byte var1 = 0;
      int var2 = 0;

      while (var1 < 32) {
         int var3 = var0.read();
         var2 |= (var3 & 127) << var1;
         var1 += 7;
         if ((var3 & 128) == 0) {
            return var2;
         }
      }

      throw new RuntimeException();
   }

   public static int q(ByteBuffer var0) {
      int var1 = var0.get();
      if (var1 < 0) {
         int var2 = var1 & 127;
         byte var3 = var0.get();
         if (var3 < 0) {
            int var4 = var2 | (var3 & 127) << 7;
            byte var5 = var0.get();
            if (var5 < 0) {
               int var6 = var4 | (var5 & 127) << 14;
               byte var7 = var0.get();
               if (var7 < 0) {
                  byte var8 = var0.get();
                  var1 = var6 | (var7 & 127) << 21 | var8 << 28;

                  while (var8 < 0) {
                     var8 = var0.get();
                  }
               } else {
                  var1 = var6 | var7 << 21;
               }
            } else {
               var1 = var4 | var5 << 14;
            }
         } else {
            var1 = var2 | var3 << 7;
         }
      }

      return var1;
   }

   public static void q(int var0, OutputStream var1) throws IOException {
      int var2 = 0;
      int var3 = var0;

      do {
         var2++;
         var3 >>>= 7;
      } while (var3 != 0);

      byte[] var4 = new byte[var2];
      int var5 = 0;

      while (true) {
         int var6 = var0 & 127;
         var0 >>>= 7;
         int var7 = var0 == 0 ? 0 : 128;
         var4[var5] = (byte)(var7 + var6);
         if (var0 == 0) {
            var1.write(var4);
            return;
         }

         var5++;
      }
   }
}
