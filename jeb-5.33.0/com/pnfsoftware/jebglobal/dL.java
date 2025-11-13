package com.pnfsoftware.jebglobal;

class dL extends bQ.Av {
   dL(ji.DH... var1) {
      super(var1);
   }

   @Override
   public boolean pC(byte[] var1, zj var2) {
      boolean var3 = (var1[2] & 112) == 0 && (var1[3] & 240) == 0;
      return var3 && super.pC(var1, var2);
   }
}
