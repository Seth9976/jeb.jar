package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import java.util.ArrayList;
import java.util.List;

public class cka {
   int pC;
   List A = new ArrayList();

   public static cka pC(cjz var0, long var1, long var3, boolean var5) throws MemoryException {
      IVirtualMemory var6 = var0.pC.getMemory();
      cka var9 = new cka();
      var9.pC = var6.readInt(var1);
      long var7 = var1 + 4L;

      for (int var10 = 0; var10 < var9.pC; var10++) {
         ckb var11 = ckb.pC(var0, var7, var3, var5);
         if (var11 == null) {
            return null;
         }

         var9.A.add(var11);
         var7 += var11.UT;
      }

      return var9;
   }
}
