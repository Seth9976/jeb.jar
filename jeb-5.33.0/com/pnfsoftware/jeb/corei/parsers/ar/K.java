package com.pnfsoftware.jeb.corei.parsers.ar;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

@Ser
public class K {
   @SerId(1)
   public int pC;
   @SerId(2)
   public int[] A;
   @SerId(3)
   public int kS;
   @SerId(4)
   public short[] wS;
   @SerId(5)
   public String[] UT;
   @SerId(6)
   private int E;

   public static K pC(ByteBuffer var0) {
      K var1 = new K();

      try {
         var1.pC = var0.getInt();
         var1.E = 4;
         var1.A = new int[var1.pC];

         for (int var2 = 0; var2 < var1.pC; var2++) {
            var1.A[var2] = var0.getInt();
         }

         var1.E = var1.E + var1.pC * 4;
         var1.kS = var0.getInt();
         var1.E += 4;
         var1.wS = new short[var1.kS];

         for (int var6 = 0; var6 < var1.kS; var6++) {
            var1.wS[var6] = var0.getShort();
         }

         var1.E = var1.E + var1.kS * 2;
         var1.UT = new String[var1.kS];

         for (int var7 = 0; var7 < var1.kS; var7++) {
            StringBuilder var3 = new StringBuilder();

            for (byte var4 = var0.get(); var4 != 0; var4 = var0.get()) {
               var3.append((char)var4);
            }

            var1.E = var1.E + var3.length() + 1;
            var1.UT[var7] = var3.toString();
         }
      } catch (BufferUnderflowException var5) {
         var1.E = 0;
      }

      return var1;
   }

   public int pC() {
      return this.E;
   }
}
