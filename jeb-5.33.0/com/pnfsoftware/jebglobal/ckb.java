package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;

public class ckb {
   long pC;
   long A;
   long kS;
   long wS;
   int UT;

   public static ckb pC(cjz var0, long var1, long var3, boolean var5) throws MemoryException {
      IVirtualMemory var6 = var0.pC.getMemory();
      ckb var9 = new ckb();
      var9.pC = var3 + (var6.readInt(var1) & 4294967295L);
      long var7 = var1 + 4L;
      var9.A = var3 + (var6.readInt(var7) & 4294967295L);
      var7 += 4L;
      var9.kS = var6.readInt(var7) & 4294967295L;
      if (var9.kS != -1L && var9.kS != 0L && var9.kS != 1L) {
         var9.kS += var3;
      }

      var7 += 4L;
      var9.wS = var6.readInt(var7) & 4294967295L;
      if (var9.wS != 0L) {
         var9.wS += var3;
      }

      var9.UT = 16;
      if (var5 && var0.fI != null) {
         var0.pC.defineData(var1, var0.fI);
      }

      return var9;
   }

   public boolean pC() {
      return this.wS == 0L;
   }
}
