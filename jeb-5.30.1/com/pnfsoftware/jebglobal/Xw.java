package com.pnfsoftware.jebglobal;

class Xw implements YB {
   private int[] q = null;

   public Xw(int... var1) {
      this.q = var1;
   }

   @Override
   public String q(Je var1, byte[] var2) {
      for (int var3 = 0; var3 < this.q.length; var3++) {
         for (int var4 = var3 + 1; var4 < this.q.length; var4++) {
            int var5 = ((rR)var1.q(var3)).RF(var2);
            int var6 = ((rR)var1.q(var4)).RF(var2);
            if (var5 == var6) {
               return "Registers must not be the same";
            }
         }
      }

      return null;
   }
}
