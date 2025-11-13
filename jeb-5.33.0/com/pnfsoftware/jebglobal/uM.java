package com.pnfsoftware.jebglobal;

class uM extends vS {
   uM(ZW var1) {
      super(var1);
   }

   @Override
   int pC(byte[] var1) {
      int var2 = super.pC(var1);
      if (var2 == 2) {
         int var3 = (var1[0] & 64) >>> 6;
         if (var3 == 0) {
            return -1;
         }
      }

      return var2;
   }
}
