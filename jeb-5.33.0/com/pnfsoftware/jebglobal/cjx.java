package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import java.util.ArrayList;
import java.util.List;

public class cjx {
   int pC;
   private int E;
   private List sY = new ArrayList();
   long A;
   int kS;
   long wS;
   cjs UT;

   public static cjx pC(cjz var0, long var1, long var3, boolean var5) throws MemoryException {
      IVirtualMemory var6 = var0.pC.getMemory();
      cjx var7 = new cjx();
      int var10 = var6.readByte(var1) & 255;
      long var8 = var1 + 1L;
      var7.pC = var10 >> 3 & 31;
      var7.E = var6.readByte(++var8) & 255;
      var8++;
      var8++;
      int var11 = 0;

      while (var11 < var7.E) {
         cjv var12 = cjv.pC(var0, var8, var5);
         if (var12 == null) {
            return var7;
         }

         var7.sY.add(var12);
         var8 += var12.pC * 2;
         var11 += var12.pC;
      }

      if (var7.E % 2 == 1) {
         var8 += 2L;
      }

      if ((var7.pC & 4) != 0) {
         var7.UT = cjs.pC(var0, var8, var3, var5);
         if (var7.UT == null) {
            return null;
         }
      } else if ((var7.pC & 3) != 0) {
         var7.A = var3 + (var6.readInt(var8) & 4294967295L);
         var8 += 4L;
         var7.kS = var6.readInt(var8);
         var7.wS = var8;
      }

      if (var5) {
         pC(var0, var1);
      }

      return var7;
   }

   private static void pC(cjz var0, long var1) {
      if (var0.gp != null) {
         var0.pC.defineData(var1, var0.gp);
      }
   }

   boolean pC() {
      return (this.pC & 3) != 0;
   }
}
