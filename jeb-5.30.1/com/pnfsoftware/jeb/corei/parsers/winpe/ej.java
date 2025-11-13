package com.pnfsoftware.jeb.corei.parsers.winpe;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class ej {
   @SerId(1)
   public List q = new ArrayList();

   public static ej q(long var0, IVirtualMemory var2, long var3) throws MemoryException {
      ej var5 = new ej();
      long var6 = var0;

      while (true) {
         oM var8 = oM.q(var6, var2, var3);
         if (var8 == null) {
            return var5;
         }

         var5.q.add(var8);
         var6 += 20L;
      }
   }
}
