package com.pnfsoftware.jeb.corei.parsers.winpe;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class Ws {
   @SerId(1)
   public List pC = new ArrayList();

   public static Ws pC(long var0, IVirtualMemory var2, long var3) throws MemoryException {
      Ws var5 = new Ws();
      long var6 = var0;

      while (true) {
         bO var8 = bO.pC(var6, var2, var3);
         if (var8 == null) {
            return var5;
         }

         var5.pC.add(var8);
         var6 += 20L;
      }
   }
}
