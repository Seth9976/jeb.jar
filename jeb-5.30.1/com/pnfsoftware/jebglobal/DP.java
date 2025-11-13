package com.pnfsoftware.jebglobal;

class DP implements YB {
   @Override
   public String q(Je var1, byte[] var2) {
      Bf var3 = (Bf)var1.Dw();
      return var3.RF().oW(var2) ? "Base Register should not be PC" : null;
   }
}
