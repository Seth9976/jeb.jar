package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;

public class cke {
   int pC;
   long A;
   long kS;
   int wS;

   public static cke pC(ckf var0, long var1, boolean var3, int var4, int var5) throws MemoryException {
      IVirtualMemory var6 = var0.A();
      int var9 = var4 == 3 ? -1 : -2;
      cke var10 = new cke();
      var10.pC = var6.readInt(var1);
      long var7 = var1 + 4L;
      var10.A = var6.readPointer(var7);
      var7 += 4L;
      var10.kS = var6.readPointer(var7);
      var10.wS = 12;
      if (var10.pC != var9 && var10.pC >= var5) {
         return null;
      } else if (var10.A != 0L && !cjo.wS(var0.pC(), var10.A)) {
         return null;
      } else if (!cjo.wS(var0.pC(), var10.kS)) {
         return null;
      } else {
         if (var3) {
            if (var4 == 3) {
               if (var0.NS != null) {
                  var0.pC().defineData(var1, var0.NS);
               }
            } else if (var4 == 4 && var0.vP != null) {
               var0.pC().defineData(var1, var0.vP);
            }
         }

         return var10;
      }
   }

   public boolean pC() {
      return this.A == 0L;
   }
}
