package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;

public class cjq {
   long pC;
   long A;
   int kS;
   private chj wS;

   public static cjq pC(cjz var0, long var1, long var3, boolean var5) throws MemoryException {
      IVirtualMemory var6 = var0.pC.getMemory();
      boolean var9 = var0.pC.getProcessor().getMode() == 64;
      cjq var10 = new cjq();
      long var7 = var1 + 4L;
      var10.pC = var6.readInt(var7) & 4294967295L;
      if (var10.pC != 0L && var9) {
         var10.pC += var3;
      }

      var7 += 4L;
      var7 += 4L;
      var10.A = var6.readInt(var7) & 4294967295L;
      if (var10.A != 0L && var9) {
         var10.A += var3;
      }

      var10.kS = var9 ? 20 : 16;
      if (var10.pC != 0L) {
         var10.wS = chj.pC(var6, var10.pC, var9);
         if (var10.wS == null) {
            return null;
         }
      }

      if (var5) {
         if (var0.ld != null) {
            var0.pC.defineData(var1, var0.ld);
         }

         if (var10.wS != null) {
            INativeContinuousItem var11 = var0.pC.getModel().getItemAt(var10.wS.pC());
            if ((var11 == null || !(var11 instanceof auw)) && var0.ED != null) {
               var0.pC.defineData(var10.wS.pC(), var0.A.getType("TypeDescriptor"));
               var11 = var0.pC.getModel().getItemAt(var10.wS.pC());
               if (var11 != null) {
                  var11.setName("??_R0" + var10.wS.kS().substring(1) + "@8");
                  ((a)var0.pC).pC((INativeDataItem)var11);
               }

               var0.pC.recordAnalysisComment(var10.wS.A(), "name");
            }
         }
      }

      return var10;
   }
}
