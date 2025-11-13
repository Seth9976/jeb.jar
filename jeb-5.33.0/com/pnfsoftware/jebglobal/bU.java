package com.pnfsoftware.jebglobal;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.google.common.primitives.UnsignedBytes;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public final class bU {
   public static String pC(ByteBuffer var0, int var1, bU.Av var2) {
      int var4 = A(var0, var1, var2);
      var1 += pC(var4, var2);
      int var3;
      if (var2 == bU.Av.pC) {
         var3 = A(var0, var1, var2);
         var1 += pC(var3, var2);
      } else {
         var3 = var4 * 2;
      }

      return new String(var0.array(), var1, var3, var2.pC());
   }

   public static byte[] pC(String var0, bU.Av var1) {
      byte[] var2 = var0.getBytes(var1.pC());
      ByteArrayDataOutput var3 = ByteStreams.newDataOutput(var2.length + 5);
      pC(var3, var0.length(), var1);
      if (var1 == bU.Av.pC) {
         pC(var3, var2.length, var1);
      }

      var3.write(var2);
      if (var1 == bU.Av.pC) {
         var3.write(0);
      } else {
         var3.writeShort(0);
      }

      return var3.toByteArray();
   }

   private static void pC(ByteArrayDataOutput var0, int var1, bU.Av var2) {
      if (var1 < 0) {
         var0.write(0);
      } else {
         if (var2 == bU.Av.pC) {
            if (var1 > 127) {
               var0.write((var1 & 32512) >> 8 | 128);
            }

            var0.write(var1 & 0xFF);
         } else {
            if (var1 > 32767) {
               int var3 = (var1 & 2147418112) >> 16 | 32768;
               var0.write(var3 & 0xFF);
               var0.write((var3 & 0xFF00) >> 8);
            }

            int var4 = var1 & 65535;
            var0.write(var4 & 0xFF);
            var0.write((var4 & 0xFF00) >> 8);
         }
      }
   }

   private static int pC(int var0, bU.Av var1) {
      return (var1 == bU.Av.pC ? 1 : 2) * (var0 >= (var1 == bU.Av.pC ? 128 : 32768) ? 2 : 1);
   }

   private static int A(ByteBuffer var0, int var1, bU.Av var2) {
      return var2 == bU.Av.pC ? pC(var0, var1) : A(var0, var1);
   }

   private static int pC(ByteBuffer var0, int var1) {
      int var2 = UnsignedBytes.toInt(var0.get(var1));
      if ((var2 & 128) != 0) {
         var2 = (var2 & 127) << 8 | UnsignedBytes.toInt(var0.get(var1 + 1));
      }

      return var2;
   }

   private static int A(ByteBuffer var0, int var1) {
      int var2 = var0.getShort(var1) & '\uffff';
      if ((var2 & 32768) != 0) {
         var2 = (var2 & 32767) << 16 | var0.getShort(var1 + 2) & '\uffff';
      }

      return var2;
   }

   public static enum Av {
      pC(StandardCharsets.UTF_8),
      A(StandardCharsets.UTF_16LE);

      private final Charset kS;

      private Av(Charset var3) {
         this.kS = var3;
      }

      public Charset pC() {
         return this.kS;
      }
   }
}
