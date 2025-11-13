package com.pnfsoftware.jebglobal;

class HD extends de.qV {
   @Override
   public boolean q(byte[] var1, mZ var2) {
      return super.q(var1, var2) && (var1[1] & 15) == 0 && (var1[2] & 15) == 0;
   }
}
