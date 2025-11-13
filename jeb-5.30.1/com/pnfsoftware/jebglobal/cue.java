package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;

public class cue {
   long q;
   int RF;
   int xK;

   public static cue q(cum var0, long var1, long var3, boolean var5) throws MemoryException {
      IVirtualMemory var6 = var0.q.getMemory();
      cue var9 = new cue();
      var9.q = var6.readInt(var1);
      if (var9.q != 0L) {
         var9.q += var3;
      }

      long var7 = var1 + 4L;
      var9.RF = var6.readInt(var7);
      var9.xK = 8;
      if (var5 && var0.oW != null) {
         var0.q.defineData(var1, var0.oW);
      }

      return var9;
   }
}
