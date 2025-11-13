package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import java.util.ArrayList;
import java.util.List;

public class cuh {
   int q;
   private int Dw;
   private long Uv;
   int RF;
   List xK = new ArrayList();

   public static cuh q(cum var0, long var1, long var3, boolean var5) throws MemoryException {
      IVirtualMemory var6 = var0.q.getMemory();
      boolean var9 = var0.q.getProcessor().getMode() == 64;
      cuh var10 = new cuh();
      var10.q = var6.readInt(var1);
      long var7 = var1 + 4L;
      var7 += 4L;
      var7 += 4L;
      var10.Dw = var6.readInt(var7);
      var7 += 4L;
      var10.Uv = var6.readInt(var7) & 4294967295L;
      if (var10.Uv != 0L && var9) {
         var10.Uv += var3;
      }

      var10.RF = 20;
      var7 = var10.Uv;

      for (int var11 = 0; var11 < var10.Dw; var11++) {
         cud var12 = cud.q(var0, var7, var3, var5);
         if (var12 == null) {
            return null;
         }

         var10.xK.add(var12);
         var7 += var12.xK;
      }

      if (var5 && var0.nf != null) {
         var0.q.defineData(var1, var0.nf);
      }

      return var10;
   }
}
