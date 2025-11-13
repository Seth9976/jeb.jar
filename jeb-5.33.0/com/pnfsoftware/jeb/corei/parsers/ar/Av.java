package com.pnfsoftware.jeb.corei.parsers.ar;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

@Ser
public class Av {
   @SerId(1)
   public int pC;
   @SerId(2)
   public int[] A;
   @SerId(3)
   public String[] kS;
   @SerId(4)
   private int wS;

   public static Av pC(ByteBuffer var0) {
      Av var1 = new Av();

      try {
         var1.pC = var0.getInt();
         var1.wS = 4;
         var1.A = new int[var1.pC];

         for (int var2 = 0; var2 < var1.pC; var2++) {
            var1.A[var2] = var0.getInt();
         }

         var1.wS = var1.wS + var1.pC * 4;
         var1.kS = new String[var1.pC];

         for (int var6 = 0; var6 < var1.pC; var6++) {
            StringBuilder var3 = new StringBuilder();

            for (byte var4 = var0.get(); var4 != 0; var4 = var0.get()) {
               var3.append((char)var4);
            }

            var1.wS = var1.wS + var3.length() + 1;
            var1.kS[var6] = var3.toString();
         }
      } catch (BufferUnderflowException var5) {
         var1.wS = 0;
      }

      return var1;
   }

   public int pC() {
      return this.wS;
   }
}
