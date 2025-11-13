package com.pnfsoftware.jebglobal;

class SM implements YB {
   @Override
   public String q(Je var1, byte[] var2) {
      int var3 = var1.xK();
      Bf var4 = (Bf)var1.Dw();
      if (var4.q(var2)) {
         int var5 = var4.RF().RF(var2);
         if (var5 == ((rR)var1.q(0)).RF(var2)) {
            return "Conflict between LDR memory loading and writeback on register " + var5;
         }

         if (var3 == 3 && var5 == ((rR)var1.q(1)).RF(var2)) {
            return "Conflict between LDR memory loading and writeback on register " + var5;
         }
      }

      return null;
   }
}
