package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;

public class cuo {
   long q;
   long RF;
   long xK;
   long Dw;
   int Uv;

   public static cuo q(cum var0, long var1, long var3, boolean var5) throws MemoryException {
      IVirtualMemory var6 = var0.q.getMemory();
      cuo var9 = new cuo();
      var9.q = var3 + (var6.readInt(var1) & 4294967295L);
      long var7 = var1 + 4L;
      var9.RF = var3 + (var6.readInt(var7) & 4294967295L);
      var7 += 4L;
      var9.xK = var6.readInt(var7) & 4294967295L;
      if (var9.xK != -1L && var9.xK != 0L && var9.xK != 1L) {
         var9.xK += var3;
      }

      var7 += 4L;
      var9.Dw = var6.readInt(var7) & 4294967295L;
      if (var9.Dw != 0L) {
         var9.Dw += var3;
      }

      var9.Uv = 16;
      if (var5 && var0.zz != null) {
         var0.q.defineData(var1, var0.zz);
      }

      return var9;
   }

   public boolean q() {
      return this.Dw == 0L;
   }
}
