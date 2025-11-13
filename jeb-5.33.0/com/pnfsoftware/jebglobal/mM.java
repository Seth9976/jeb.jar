package com.pnfsoftware.jebglobal;

public class mM implements jp {
   private int[] pC = null;
   private int A = 0;

   public mM(int var1, int... var2) {
      this.A = var1;
      this.pC = var2;
   }

   @Override
   public String pC(tz var1, byte[] var2) {
      for (int var3 = 0; var3 < this.pC.length; var3++) {
         for (int var4 = var3 + 1; var4 < this.pC.length; var4++) {
            long var5 = Gq.pC(var2, this.pC[var3], this.A);
            long var7 = Gq.pC(var2, this.pC[var4], this.A);
            if (var5 == var7) {
               return "Equal values";
            }
         }
      }

      return null;
   }
}
