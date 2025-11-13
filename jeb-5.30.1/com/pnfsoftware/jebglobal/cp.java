package com.pnfsoftware.jebglobal;

class cp implements YB {
   private int[] q = null;

   public cp(int... var1) {
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
         rR var8 = (rR)var1.q(var7);
         if (var8.oW(var2)) {
            return "Register n " + var7 + " should not be PC";
         }

         if (var8.Uv(var2) == 0 && var8.RF(var2) == (var8 instanceof YH ? 30 : 14)) {
            return "Register n " + var7 + " should not be LR";
         }
      }

      return null;
   }
}
