package com.pnfsoftware.jebglobal;

public class x implements YB {
   private int[] q = null;
   private int RF = 0;

   public x(int var1, int... var2) {
      this.RF = var1;
      this.q = var2;
   }

   @Override
   public String q(Je var1, byte[] var2) {
      for (int var3 = 0; var3 < this.q.length; var3++) {
         for (int var4 = var3 + 1; var4 < this.q.length; var4++) {
            long var5 = k.q(var2, this.q[var3], this.RF);
            long var7 = k.q(var2, this.q[var4], this.RF);
            if (var5 == var7) {
               return "Equal values";
            }
         }
      }

      return null;
   }
}
