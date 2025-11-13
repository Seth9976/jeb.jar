package com.pnfsoftware.jebglobal;

class sD extends ji.rQ {
   @Override
   public boolean pC(byte[] var1, zj var2) {
      return super.pC(var1, var2) && (var1[1] & 63) == 0 && (var1[2] & 47) == 0 && (var1[3] & 128) == 0;
   }
}
