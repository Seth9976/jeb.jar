package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import java.util.ArrayList;
import java.util.List;

public class cju {
   int pC;
   private int wS;
   private long UT;
   int A;
   List kS = new ArrayList();

   public static cju pC(cjz var0, long var1, long var3, boolean var5) throws MemoryException {
      IVirtualMemory var6 = var0.pC.getMemory();
      boolean var9 = var0.pC.getProcessor().getMode() == 64;
      cju var10 = new cju();
      var10.pC = var6.readInt(var1);
      long var7 = var1 + 4L;
      var7 += 4L;
      var7 += 4L;
      var10.wS = var6.readInt(var7);
      var7 += 4L;
      var10.UT = var6.readInt(var7) & 4294967295L;
      if (var10.UT != 0L && var9) {
         var10.UT += var3;
      }

      var10.A = 20;
      var7 = var10.UT;

      for (int var11 = 0; var11 < var10.wS; var11++) {
         cjq var12 = cjq.pC(var0, var7, var3, var5);
         if (var12 == null) {
            return null;
         }

         var10.kS.add(var12);
         var7 += var12.kS;
      }

      if (var5 && var0.ys != null) {
         var0.pC.defineData(var1, var0.ys);
      }

      return var10;
   }
}
