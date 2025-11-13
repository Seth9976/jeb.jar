package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.dex.IDalvikInstructionSwitchData;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class bis implements IDalvikInstructionSwitchData {
   @SerId(1)
   int q;
   @SerId(2)
   bis.eo[] RF;

   public bis(int var1, int var2) {
      this.q = var1;
      this.RF = new bis.eo[var2];
   }

   @Override
   public int getOffset() {
      return this.q;
   }

   @Override
   public int[][] getElements() {
      int[][] var1 = new int[this.RF.length][2];
      int var2 = 0;

      for (bis.eo var6 : this.RF) {
         var1[var2][0] = var6.q;
         var1[var2][1] = var6.RF;
         var2++;
      }

      return var1;
   }

   @Ser
   public static class eo {
      @SerId(1)
      int q;
      @SerId(2)
      int RF;

      public eo(int var1, int var2) {
         this.q = var1;
         this.RF = var2;
      }

      public int q() {
         return this.q;
      }

      public int RF() {
         return this.RF;
      }
   }
}
