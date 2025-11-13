package com.pnfsoftware.jebglobal;

class XY extends Xu.eo {
   @Override
   public boolean q(byte[] var1, mZ var2) {
      int var3 = var1[2] & 15;
      int var4 = var1[1] & 15;
      int var5 = k.RF(var1, lO.Dw);
      boolean var6 = var5 <= 7 && var4 <= 7 && var3 <= 7;
      boolean var7 = var5 <= 255 && var3 == var4 && var3 <= 7;
      return var6 || var7;
   }
}
