package com.pnfsoftware.jeb.util.encoding;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IntegerLEB128 {
   public static void writeULEB128(OutputStream var0, int var1) throws IOException {
      if ((var1 & -128) == 0) {
         var0.write(var1);
      } else if ((var1 & -16384) == 0) {
         var0.write(128 | var1 & 127);
         var0.write(var1 >>> 7 & 127);
      } else if ((var1 & -2097152) == 0) {
         var0.write(128 | var1 & 127);
         var0.write(128 | var1 >>> 7 & 127);
         var0.write(var1 >>> 14 & 127);
      } else if ((var1 & -268435456) == 0) {
         var0.write(128 | var1 & 127);
         var0.write(128 | var1 >>> 7 & 127);
         var0.write(128 | var1 >>> 14 & 127);
         var0.write(var1 >>> 21 & 127);
      } else {
         var0.write(128 | var1 & 127);
         var0.write(128 | var1 >>> 7 & 127);
         var0.write(128 | var1 >>> 14 & 127);
         var0.write(128 | var1 >>> 21 & 127);
         var0.write(var1 >>> 28);
      }
   }

   public static int readULEB128(InputStream var0) throws IOException {
      int var1 = 0;
      int var2 = -1;
      byte var3 = 0;

      for (int var4 = 0; (var2 & 128) != 0; var4++) {
         if (var4 == 5) {
            throw new IOException("Illegal ULEB128-encoded integer");
         }

         var2 = var0.read();
         if (var2 == -1) {
            throw new IOException("Not enough data");
         }

         var1 |= (var2 & 127) << var3;
         var3 += 7;
      }

      return var1;
   }

   public static IntegerLEB128.DecodedInt decodeULEB128(InputStream var0) throws IOException {
      IntegerLEB128.DecodedInt var1 = new IntegerLEB128.DecodedInt();
      int var2 = 0;
      int var3 = -1;
      byte var4 = 0;

      for (int var5 = 0; (var3 & 128) != 0; var5++) {
         if (var5 == 5) {
            throw new IOException("Illegal ULEB128-encoded integer");
         }

         var3 = var0.read();
         var1.encodedSize++;
         if (var3 == -1) {
            throw new IOException("Not enough data");
         }

         var2 |= (var3 & 127) << var4;
         var4 += 7;
      }

      var1.value = var2;
      return var1;
   }

   public static void writeSLEB128(OutputStream var0, int var1) throws IOException {
      boolean var2 = true;

      while (var2) {
         int var3 = var1 & 127;
         var1 >>= 7;
         if ((var1 != 0 || (var3 & 64) != 0) && (var1 != -1 || (var3 & 64) == 0)) {
            var3 |= 128;
         } else {
            var2 = false;
         }

         var0.write(var3);
      }
   }

   public static class DecodedInt {
      public int value;
      public int encodedSize;
   }
}
