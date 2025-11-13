package com.pnfsoftware.jebglobal;

class NN extends de.qV {
   @Override
   public boolean q(byte[] var1, mZ var2) {
      return super.q(var1, var2) && (var1[1] & 63) == 0 && (var1[2] & 47) == 0 && (var1[3] & 128) == 0;
   }
}
