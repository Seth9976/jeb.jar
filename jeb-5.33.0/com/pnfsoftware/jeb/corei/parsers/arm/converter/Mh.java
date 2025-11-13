package com.pnfsoftware.jeb.corei.parsers.arm.converter;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.util.base.Assert;

public class Mh {
   public static void pC(EState var0, int var1) {
      Assert.a((var1 & -1075799040) == 234881024);
      int var2 = var1 & 31;
      int var3 = var1 >> 5 & 31;
      int var4 = var1 >> 16 & 31;
      int var5 = var1 >> 30 & 1;
      int var6 = var1 >> 13 & 3;
      if (var5 != 1) {
         throw new RuntimeException("TBI");
      } else {
         IEGlobalContext var7 = var0.getGlobalContext();
         int[][] var8 = new int[var6 + 1][];
         IEImm var9 = var0.getValue(var7.getVariableByName("V" + var3));
         var8[0] = EUtil.immToInt32Array(var9);
         if (var6 >= 1) {
            IEImm var10 = var0.getValue(var7.getVariableByName("V" + (var3 + 1)));
            var8[1] = EUtil.immToInt32Array(var10);
            if (var6 >= 2) {
               IEImm var11 = var0.getValue(var7.getVariableByName("V" + (var3 + 2)));
               var8[2] = EUtil.immToInt32Array(var11);
               if (var6 >= 3) {
                  IEImm var12 = var0.getValue(var7.getVariableByName("V" + (var3 + 3)));
                  var8[3] = EUtil.immToInt32Array(var12);
               }
            }
         }

         IEImm var14 = var0.getValue(var7.getVariableByName("V" + var4));
         int[] var15 = EUtil.immToInt32Array(var14);
         int[] var16 = new int[4];
         pC(var16, var8, var15);
         IEImm var13 = EUtil.int32ArrayToImm(var7, var16);
         var0.setValue(var7.getVariableByName("V" + var2), var13);
      }
   }

   public static void pC(int[] var0, int[][] var1, int[] var2) {
      int var3 = var1.length;
      Assert.a(var3 >= 1 && var3 <= 4);
      byte[] var4 = new byte[16];
      ma.pC(var2, var4);
      byte[] var5 = new byte[16 * var3];

      for (int var6 = 0; var6 < var3; var6++) {
         ma.pC(var1[var6], var5, 16 * var6);
      }

      byte[] var9 = new byte[16];

      for (int var7 = 0; var7 < 16; var7++) {
         int var8 = var4[var7] & 255;
         if (var8 < 16 * var3) {
            var9[var7] = var5[var8];
         }
      }

      ma.pC(var9, var0);
   }
}
