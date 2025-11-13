package com.pnfsoftware.jebglobal;

class ju implements jp {
   @Override
   public String pC(tz var1, byte[] var2) {
      int var3 = var1.kS();
      LF var4 = (LF)var1.wS();
      if (var4.pC(var2)) {
         int var5 = var4.A().A(var2);
         if (var5 == ((gZ)var1.pC(0)).A(var2)) {
            return "Conflict between LDR memory loading and writeback on register " + var5;
         }

         if (var3 == 3 && var5 == ((gZ)var1.pC(1)).A(var2)) {
            return "Conflict between LDR memory loading and writeback on register " + var5;
         }
      }

      return null;
   }
}
