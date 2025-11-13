package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;

public class cjy {
   int pC;
   long A;
   int kS;

   public static cjy pC(cjz var0, long var1, long var3, boolean var5) throws MemoryException {
      IVirtualMemory var6 = var0.pC.getMemory();
      boolean var9 = var0.pC.getProcessor().getMode() == 64;
      cjy var10 = new cjy();
      var10.pC = var6.readInt(var1);
      long var7 = var1 + 4L;
      var10.A = var6.readInt(var7) & 4294967295L;
      if (var10.A != 0L && var9) {
         var10.A += var3;
      }

      var10.kS = 8;
      if (var5 && var0.UT != null) {
         var0.pC.defineData(var1, var0.UT);
      }

      return var10;
   }
}
