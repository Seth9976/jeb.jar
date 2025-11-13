package com.pnfsoftware.jebglobal;

class xs implements YB {
   private static boolean q(byte[] var0) {
      int var1 = var0[1] & 15;
      int var2 = 1 << var1;
      int var3 = (var0[2] & 255) << 8 | var0[3] & 255;
      return (var2 & var3) != 0;
   }

   @Override
   public String q(Je var1, byte[] var2) {
      if (q(var2)) {
         int var3 = var2[1] & 15;
         return "Conflict between LDM memory loading and writeback on register " + var3;
      } else {
         return null;
      }
   }
}
