package com.pnfsoftware.jebglobal;

class nk extends de.qV {
   @Override
   public boolean q(byte[] var1, mZ var2) {
      return (var1[0] & 4) == 0
         ? (var1[0] & 3) == 0 && (var1[1] & 255) == 0 && (var1[2] & 44) == 40
         : super.q(var1, var2) && (var1[0] & 3) == 0 && (var1[1] & 255) == 0 && (var1[2] & 44) == 0;
   }
}
