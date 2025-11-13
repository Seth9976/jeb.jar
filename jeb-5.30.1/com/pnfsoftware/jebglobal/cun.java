package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import java.util.ArrayList;
import java.util.List;

public class cun {
   int q;
   List RF = new ArrayList();

   public static cun q(cum var0, long var1, long var3, boolean var5) throws MemoryException {
      IVirtualMemory var6 = var0.q.getMemory();
      cun var9 = new cun();
      var9.q = var6.readInt(var1);
      long var7 = var1 + 4L;

      for (int var10 = 0; var10 < var9.q; var10++) {
         cuo var11 = cuo.q(var0, var7, var3, var5);
         if (var11 == null) {
            return null;
         }

         var9.RF.add(var11);
         var7 += var11.Uv;
      }

      return var9;
   }
}
