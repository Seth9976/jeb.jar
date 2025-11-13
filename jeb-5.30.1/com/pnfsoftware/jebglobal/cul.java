package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;

public class cul {
   int q;
   long RF;
   int xK;

   public static cul q(cum var0, long var1, long var3, boolean var5) throws MemoryException {
      IVirtualMemory var6 = var0.q.getMemory();
      boolean var9 = var0.q.getProcessor().getMode() == 64;
      cul var10 = new cul();
      var10.q = var6.readInt(var1);
      long var7 = var1 + 4L;
      var10.RF = var6.readInt(var7) & 4294967295L;
      if (var10.RF != 0L && var9) {
         var10.RF += var3;
      }

      var10.xK = 8;
      if (var5 && var0.Uv != null) {
         var0.q.defineData(var1, var0.Uv);
      }

      return var10;
   }
}
