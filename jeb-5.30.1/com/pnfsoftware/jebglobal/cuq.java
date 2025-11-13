package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import java.util.ArrayList;
import java.util.List;

public class cuq {
   int q;
   int RF;
   int xK;
   int Dw;
   int Uv;
   List oW = new ArrayList();

   public static cuq q(cus var0, long var1, boolean var3, int var4) throws MemoryException {
      IVirtualMemory var5 = var0.xK();
      long var6 = var1;
      cuq var8 = new cuq();
      if (var4 == 4) {
         var8.RF = var5.readInt(var1);
         var6 = var1 + 4L;
         var8.xK = var5.readInt(var6);
         var6 += 4L;
         var8.Dw = var5.readInt(var6);
         var6 += 4L;
         var8.Uv = var5.readInt(var6);
         var6 += 4L;
      }

      int var9 = 0;

      while (true) {
         cur var10;
         try {
            var10 = cur.q(var0, var6, var3, var4, var9);
         } catch (MemoryException var11) {
            break;
         }

         if (var10 == null) {
            break;
         }

         var6 += var10.Dw;
         var8.oW.add(var10);
         if (!var0.RF().getModel().getReferenceManager().getReferencesTo(var6).isEmpty()
            || var0.RF().getModel().getItemAt(var6) instanceof INativeInstructionItem) {
            break;
         }

         var9++;
      }

      if (var8.oW.isEmpty()) {
         return null;
      } else {
         var8.q = var4;
         if (var3 && var4 == 4 && var0.io != null) {
            var0.RF().defineData(var1, var0.io);
         }

         return var8;
      }
   }
}
