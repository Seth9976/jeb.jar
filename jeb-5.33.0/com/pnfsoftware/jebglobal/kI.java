package com.pnfsoftware.jebglobal;

class kI implements jp {
   private int[] pC = null;

   public kI(int... var1) {
      this.pC = var1;
   }

   @Override
   public String pC(tz var1, byte[] var2) {
      for (int var3 = 0; var3 < this.pC.length; var3++) {
         for (int var4 = var3 + 1; var4 < this.pC.length; var4++) {
            int var5 = ((gZ)var1.pC(var3)).A(var2);
            int var6 = ((gZ)var1.pC(var4)).A(var2);
            if (var5 == var6) {
               return "Registers must not be the same";
            }
         }
      }

      return null;
   }
}
