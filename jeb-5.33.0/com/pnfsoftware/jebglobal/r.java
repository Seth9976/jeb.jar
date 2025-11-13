package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;

class r extends DP.Sv {
   r(int var1) {
      super(var1);
   }

   protected void pC(DP.Av var1) throws MemoryException {
      int var2 = (int)((var1.S() + var1.A() | var1.pC()) - var1.P());
      int var3 = var1.mem.readInt(var1.P());
      var1.mem.writeInt(var1.P(), var3 & 0xFF000000 | var2 >> 2 & 16777215);
   }
}
