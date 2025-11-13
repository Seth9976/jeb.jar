package com.pnfsoftware.jebglobal;

class FY implements YB {
   private int[] q = null;

   public FY(int... var1) {
      this.q = var1;
   }

   @Override
   public String q(Je var1, byte[] var2) {
      int[] var3 = this.q;
      if (this.q.length == 1 && this.q[0] == -1) {
         var3 = new int[var1.xK()];
         int var4 = 0;

         while (var4 < var1.xK()) {
            var3[var4] = var4++;
         }
      }

      for (int var7 : var3) {
         if (((rR)var1.q(var7)).oW(var2)) {
            return "Register n " + var7 + " should not be PC";
         }
      }

      return null;
   }
}
