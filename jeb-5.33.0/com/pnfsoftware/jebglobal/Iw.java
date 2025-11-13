package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.BinaryPattern;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class Iw extends BinaryPattern {
   @SerId(1)
   private boolean pC = false;
   @SerId(2)
   private byte[] A = null;

   public Iw(byte[] var1, byte[] var2, int var3, int var4, boolean var5, byte[] var6) {
      super(var1, var2, var3, var4);
   }

   public static BinaryPattern pC(Endianness var0, byte[] var1, byte[] var2, int var3, int var4) {
      byte[] var5 = var1;
      if (var0.isLittle()) {
         var1 = pC(var1, var3);
         var2 = pC(var2, var3);
      }

      return new Iw(var1, var2, 0, var4, !var0.isLittle(), var5);
   }

   private static byte[] pC(byte[] var0, int var1) {
      byte[] var2 = new byte[var0.length];
      ArrayUtil.copyBytes(var2, 0, var0, 0, var0.length);
      EndianUtil.swapByGroup(var2, var1);
      return var2;
   }

   public boolean pC() {
      return this.pC;
   }
}
