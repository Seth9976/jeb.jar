package com.pnfsoftware.jebglobal;

class Rj implements YB {
   @Override
   public String q(Je var1, byte[] var2) {
      Bf var3 = (Bf)var1.Dw();
      return var3.xK() != null && var3.xK().oW(var2) ? "Rm Register should not be PC" : null;
   }
}
