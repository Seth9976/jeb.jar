package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;

public class cur {
   int q;
   long RF;
   long xK;
   int Dw;

   public static cur q(cus var0, long var1, boolean var3, int var4, int var5) throws MemoryException {
      IVirtualMemory var6 = var0.xK();
      int var9 = var4 == 3 ? -1 : -2;
      cur var10 = new cur();
      var10.q = var6.readInt(var1);
      long var7 = var1 + 4L;
      var10.RF = var6.readPointer(var7);
      var7 += 4L;
      var10.xK = var6.readPointer(var7);
      var10.Dw = 12;
      if (var10.q != var9 && var10.q >= var5) {
         return null;
      } else if (var10.RF != 0L && !cub.Dw(var0.RF(), var10.RF)) {
         return null;
      } else if (!cub.Dw(var0.RF(), var10.xK)) {
         return null;
      } else {
         if (var3) {
            if (var4 == 3) {
               if (var0.HF != null) {
                  var0.RF().defineData(var1, var0.HF);
               }
            } else if (var4 == 4 && var0.LK != null) {
               var0.RF().defineData(var1, var0.LK);
            }
         }

         return var10;
      }
   }

   public boolean q() {
      return this.RF == 0L;
   }
}
