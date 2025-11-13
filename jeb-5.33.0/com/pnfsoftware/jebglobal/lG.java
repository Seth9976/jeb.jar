package com.pnfsoftware.jebglobal;

class lG extends ji.rQ {
   @Override
   public boolean pC(byte[] var1, zj var2) {
      return super.pC(var1, var2) && (var1[1] & 15) == 0 && (var1[2] & 15) == 0;
   }
}
