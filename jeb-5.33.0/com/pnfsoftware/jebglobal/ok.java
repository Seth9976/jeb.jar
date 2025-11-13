package com.pnfsoftware.jebglobal;

enum ok {
   pC,
   A,
   kS;

   static ok pC(long var0, com.pnfsoftware.jeb.corei.parsers.arm.rQ var2, Ro.K var3, uj var4, Sp var5) {
      String var6 = var2.wS().pC();
      if (var4 != null && var4.pC()) {
         int var9 = var2.getProcessorMode() == 16 ? 1 : 0;
         var3.pC = var4.kS();
         var3.pC(var4.UT, var2);
         var5.pC(var3, var4.A + 4L | var9);
         return kS;
      } else {
         if (!var6.equals("MOV")) {
            if (var6.equals("LDR")) {
               if (var2.A().length != 2 || var2.A()[1].getOperandType() != 7) {
                  return pC;
               }
            } else if (!var6.equals("ADD")) {
               if (!var6.equals("TBB") && !var6.equals("TBH")) {
                  if (var2.A().length == 1) {
                     if (!var5.pC(var2, var0 - var2.getSize(), var3)) {
                        return pC;
                     }

                     return kS;
                  }

                  return pC;
               }

               Yg var7 = var2.A()[0];
               Yg[] var8 = var7.E();
               if (var8.length < 2) {
                  return pC;
               }
            }
         }

         return A;
      }
   }
}
