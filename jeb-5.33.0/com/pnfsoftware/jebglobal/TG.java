package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;

class TG extends DP.Sv {
   TG(int var1) {
      super(var1);
   }

   protected void pC(DP.Av var1) throws MemoryException {
      int var2 = (int)((var1.S() + var1.A() | var1.pC()) - var1.P());
      DP.pC(var1, var2, false);
   }
}
