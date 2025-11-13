package com.pnfsoftware.jeb.corei.parsers.ar;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

@Ser
public class nI {
   @SerId(1)
   public int q;
   @SerId(2)
   public int[] RF;
   @SerId(3)
   public int xK;
   @SerId(4)
   public short[] Dw;
   @SerId(5)
   public String[] Uv;
   @SerId(6)
   private int oW;

   public static nI q(ByteBuffer var0) {
      nI var1 = new nI();

      try {
         var1.q = var0.getInt();
         var1.oW = 4;
         var1.RF = new int[var1.q];

         for (int var2 = 0; var2 < var1.q; var2++) {
            var1.RF[var2] = var0.getInt();
         }

         var1.oW = var1.oW + var1.q * 4;
         var1.xK = var0.getInt();
         var1.oW += 4;
         var1.Dw = new short[var1.xK];

         for (int var6 = 0; var6 < var1.xK; var6++) {
            var1.Dw[var6] = var0.getShort();
         }

         var1.oW = var1.oW + var1.xK * 2;
         var1.Uv = new String[var1.xK];

         for (int var7 = 0; var7 < var1.xK; var7++) {
            StringBuilder var3 = new StringBuilder();

            for (byte var4 = var0.get(); var4 != 0; var4 = var0.get()) {
               var3.append((char)var4);
            }

            var1.oW = var1.oW + var3.length() + 1;
            var1.Uv[var7] = var3.toString();
         }
      } catch (BufferUnderflowException var5) {
         var1.oW = 0;
      }

      return var1;
   }

   public int q() {
      return this.oW;
   }
}
