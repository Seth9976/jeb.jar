package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;

class mL extends DP.Sv {
   mL(int var1) {
      super(var1);
   }

   protected void pC(DP.Av var1) throws MemoryException {
      var1.mem.writeInt(var1.P(), (int)((var1.S() + var1.A() | var1.pC()) - var1.P()) & 2147483647);
   }
}
