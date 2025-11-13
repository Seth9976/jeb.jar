package com.pnfsoftware.jebglobal;

@Deprecated
class Eq implements YB {
   @Override
   public String q(Je var1, byte[] var2) {
      Bf var3 = (Bf)var1.Dw();
      return var3.q(var2) && var3.RF().oW(var2) ? "Can not writeback when Base Register is PC" : null;
   }
}
