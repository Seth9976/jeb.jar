package com.pnfsoftware.jebglobal;

class nf extends ji.rQ {
   @Override
   public boolean pC(byte[] var1, zj var2) {
      return (var1[0] & 4) == 0
         ? (var1[0] & 3) == 0 && (var1[1] & 255) == 0 && (var1[2] & 44) == 40
         : super.pC(var1, var2) && (var1[0] & 3) == 0 && (var1[1] & 255) == 0 && (var1[2] & 44) == 0;
   }
}
