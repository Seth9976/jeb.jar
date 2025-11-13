package com.pnfsoftware.jebglobal;

class YR extends de.qV {
   @Override
   public boolean q(byte[] var1, mZ var2) {
      boolean var3 = this.q(var1) && (var1[2] & 223) == 0;
      if (!var3) {
         return false;
      } else {
         int var4 = var1[1] & 7;
         int var5 = var1[3] & 255;
         return (var1[1] & 32) == 0 ? (var5 & 1 << var4) != 0 : (var5 & 1 << var4) == 0;
      }
   }
}
