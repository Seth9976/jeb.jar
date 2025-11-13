package com.pnfsoftware.jebglobal;

class xJ implements jp {
   private int[] pC = null;

   public xJ(int... var1) {
      this.pC = var1;
   }

   @Override
   public String pC(tz var1, byte[] var2) {
      int[] var3 = this.pC;
      if (this.pC.length == 1 && this.pC[0] == -1) {
         var3 = new int[var1.kS()];
         int var4 = 0;

         while (var4 < var1.kS()) {
            var3[var4] = var4++;
         }
      }

      for (int var7 : var3) {
         if (((gZ)var1.pC(var7)).E(var2)) {
            return "Register n " + var7 + " should not be PC";
         }
      }

      return null;
   }
}
