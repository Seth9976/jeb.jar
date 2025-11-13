package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;

public class cjr {
   long pC;
   int A;
   int kS;

   public static cjr pC(cjz var0, long var1, long var3, boolean var5) throws MemoryException {
      IVirtualMemory var6 = var0.pC.getMemory();
      cjr var9 = new cjr();
      var9.pC = var6.readInt(var1);
      if (var9.pC != 0L) {
         var9.pC += var3;
      }

      long var7 = var1 + 4L;
      var9.A = var6.readInt(var7);
      var9.kS = 8;
      if (var5 && var0.E != null) {
         var0.pC.defineData(var1, var0.E);
      }

      return var9;
   }
}
