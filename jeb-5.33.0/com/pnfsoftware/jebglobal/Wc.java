package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;

class Wc extends pQ.Sv {
   Wc(int var1) {
      super(var1);
   }

   public void pC(pQ.Av var1) throws MemoryException {
      long var2 = var1.S() + var1.A();
      var2 = (var2 & 4095L) << 10;
      pQ.pC(var1, (int)var2);
   }
}
