package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import java.util.ArrayList;
import java.util.List;

public class ckd {
   int pC;
   int A;
   int kS;
   int wS;
   int UT;
   List E = new ArrayList();

   public static ckd pC(ckf var0, long var1, boolean var3, int var4) throws MemoryException {
      IVirtualMemory var5 = var0.A();
      long var6 = var1;
      ckd var8 = new ckd();
      if (var4 == 4) {
         var8.A = var5.readInt(var1);
         var6 = var1 + 4L;
         var8.kS = var5.readInt(var6);
         var6 += 4L;
         var8.wS = var5.readInt(var6);
         var6 += 4L;
         var8.UT = var5.readInt(var6);
         var6 += 4L;
      }

      int var9 = 0;

      while (true) {
         cke var10;
         try {
            var10 = cke.pC(var0, var6, var3, var4, var9);
         } catch (MemoryException var11) {
            break;
         }

         if (var10 == null) {
            break;
         }

         var6 += var10.wS;
         var8.E.add(var10);
         if (!var0.pC().getModel().getReferenceManager().getReferencesTo(var6).isEmpty()
            || var0.pC().getModel().getItemAt(var6) instanceof INativeInstructionItem) {
            break;
         }

         var9++;
      }

      if (var8.E.isEmpty()) {
         return null;
      } else {
         var8.pC = var4;
         if (var3 && var4 == 4 && var0.xC != null) {
            var0.pC().defineData(var1, var0.xC);
         }

         return var8;
      }
   }
}
