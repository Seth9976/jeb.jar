package com.pnfsoftware.jebglobal;

class WX extends kT {
   WX(dD var1) {
      super(var1);
   }

   @Override
   int q(byte[] var1) {
      int var2 = super.q(var1);
      if (var2 == 2) {
         int var3 = (var1[0] & 64) >>> 6;
         if (var3 == 0) {
            return -1;
         }
      }

      return var2;
   }
}
