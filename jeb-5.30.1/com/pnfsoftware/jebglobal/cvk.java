package com.pnfsoftware.jebglobal;

public class cvk {
   public static void q(byte[] var0, byte[] var1) {
      q(var0, 0, var0.length, var1, 0, var1.length);
   }

   public static void q(byte[] var0, byte[] var1, int var2, int var3) {
      q(var0, 0, var0.length, var1, var2, var3);
   }

   public static void q(byte[] var0, int var1, int var2, byte[] var3, int var4, int var5) {
      byte[] var9 = new byte[256];

      for (int var6 = 0; var6 < 256; var6++) {
         var9[var6] = (byte)var6;
      }

      int var7 = 0;
      int var8 = var1;

      for (int var13 = 0; var13 < 256; var13++) {
         if (var8 == var2) {
            var8 = var1;
         }

         var7 = (var7 + var9[var13] + var0[var8]) % 256 & 0xFF;
         byte var10 = var9[var13];
         var9[var13] = var9[var7];
         var9[var7] = var10;
         var8++;
      }

      int var14 = 0;
      var7 = 0;

      for (int var16 = var4; var16 < var5; var16++) {
         var14 = (var14 + 1) % 256 & 0xFF;
         var7 = (var7 + var9[var14]) % 256 & 0xFF;
         byte var11 = var9[var14];
         var9[var14] = var9[var7];
         var9[var7] = var11;
         byte var12 = var9[(var9[var14] + var9[var7]) % 256 & 0xFF];
         var3[var16] ^= var12;
      }
   }
}
