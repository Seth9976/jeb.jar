package com.pnfsoftware.jeb.corei.parsers.ar;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

@Ser
public class eo {
   @SerId(1)
   public int q;
   @SerId(2)
   public int[] RF;
   @SerId(3)
   public String[] xK;
   @SerId(4)
   private int Dw;

   public static eo q(ByteBuffer var0) {
      eo var1 = new eo();

      try {
         var1.q = var0.getInt();
         var1.Dw = 4;
         var1.RF = new int[var1.q];

         for (int var2 = 0; var2 < var1.q; var2++) {
            var1.RF[var2] = var0.getInt();
         }

         var1.Dw = var1.Dw + var1.q * 4;
         var1.xK = new String[var1.q];

         for (int var6 = 0; var6 < var1.q; var6++) {
            StringBuilder var3 = new StringBuilder();

            for (byte var4 = var0.get(); var4 != 0; var4 = var0.get()) {
               var3.append((char)var4);
            }

            var1.Dw = var1.Dw + var3.length() + 1;
            var1.xK[var6] = var3.toString();
         }
      } catch (BufferUnderflowException var5) {
         var1.Dw = 0;
      }

      return var1;
   }

   public int q() {
      return this.Dw;
   }
}
