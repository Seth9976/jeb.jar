package com.pnfsoftware.jebglobal;

class mB implements YB {
   private int[] q = null;

   public mB(int... var1) {
      this.q = var1;
   }

   @Override
   public String q(Je var1, byte[] var2) {
      for (int var6 : this.q) {
         if (((rR)var1.q(var6)).RF(var2) % 2 != 0) {
            return "Register n " + var6 + " should be even";
         }
      }

      return null;
   }
}
