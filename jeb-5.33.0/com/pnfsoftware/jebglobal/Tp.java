package com.pnfsoftware.jebglobal;

class Tp implements jp {
   private int[] pC = null;

   public Tp(int... var1) {
      this.pC = var1;
   }

   @Override
   public String pC(tz var1, byte[] var2) {
      for (int var6 : this.pC) {
         if (((gZ)var1.pC(var6)).A(var2) % 2 != 0) {
            return "Register n " + var6 + " should be even";
         }
      }

      return null;
   }
}
