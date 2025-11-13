package com.pnfsoftware.jeb.corei.parsers.arm.converter;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.io.EndianUtil;

public class ma {
   public static void pC(int[] var0, byte[] var1) {
      pC(var0, var1, 0);
   }

   public static void pC(int[] var0, byte[] var1, int var2) {
      EndianUtil.intToLEBytes(var0[0], var1, var2);
      EndianUtil.intToLEBytes(var0[1], var1, var2 + 4);
      if (var0.length != 2) {
         Assert.a(var0.length == 4);
         EndianUtil.intToLEBytes(var0[2], var1, var2 + 8);
         EndianUtil.intToLEBytes(var0[3], var1, var2 + 12);
      }
   }

   public static void pC(byte[] var0, int[] var1) {
      var1[0] = EndianUtil.littleEndianBytesToInt(var0, 0);
      var1[1] = EndianUtil.littleEndianBytesToInt(var0, 4);
      if (var0.length != 8) {
         Assert.a(var0.length == 16);
         var1[2] = EndianUtil.littleEndianBytesToInt(var0, 8);
         var1[3] = EndianUtil.littleEndianBytesToInt(var0, 12);
      }
   }
}
