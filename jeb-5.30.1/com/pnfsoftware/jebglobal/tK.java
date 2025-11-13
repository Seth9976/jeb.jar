package com.pnfsoftware.jebglobal;

enum tK {
   q,
   RF,
   xK;

   static tK q(long var0, fA var2, Ia.nI var3, AL var4, bR var5) {
      String var6 = var2.Dw().q();
      if (var4 != null && var4.q()) {
         int var9 = var2.getProcessorMode() == 16 ? 1 : 0;
         var3.q = var4.xK();
         var3.q(var4.Uv, var2);
         var5.q(var3, var4.RF + 4L | var9);
         return xK;
      } else {
         if (!var6.equals("MOV")) {
            if (var6.equals("LDR")) {
               if (var2.RF().length != 2 || var2.RF()[1].getOperandType() != 7) {
                  return q;
               }
            } else if (!var6.equals("ADD")) {
               if (!var6.equals("TBB") && !var6.equals("TBH")) {
                  if (var2.RF().length == 1) {
                     if (!var5.q(var2, var0 - var2.getSize(), var3)) {
                        return q;
                     }

                     return xK;
                  }

                  return q;
               }

               CW var7 = var2.RF()[0];
               CW[] var8 = var7.oW();
               if (var8.length < 2) {
                  return q;
               }
            }
         }

         return RF;
      }
   }
}
