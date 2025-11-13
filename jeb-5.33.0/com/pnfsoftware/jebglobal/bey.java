package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.dex.IDalvikInstructionSwitchData;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class bey implements IDalvikInstructionSwitchData {
   @SerId(1)
   int pC;
   @SerId(2)
   bey.Av[] A;

   public bey(int var1, int var2) {
      this.pC = var1;
      this.A = new bey.Av[var2];
   }

   @Override
   public int getOffset() {
      return this.pC;
   }

   @Override
   public int[][] getElements() {
      int[][] var1 = new int[this.A.length][2];
      int var2 = 0;

      for (bey.Av var6 : this.A) {
         var1[var2][0] = var6.pC;
         var1[var2][1] = var6.A;
         var2++;
      }

      return var1;
   }

   @Ser
   public static class Av {
      @SerId(1)
      int pC;
      @SerId(2)
      int A;

      public Av(int var1, int var2) {
         this.pC = var1;
         this.A = var2;
      }

      public int pC() {
         return this.pC;
      }
   }
}
