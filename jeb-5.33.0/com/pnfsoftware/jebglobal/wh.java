package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;

class wh extends DP.Sv {
   wh(int var1) {
      super(var1);
   }

   public void pC(DP.Av var1) throws MemoryException {
      var1.mem.writeInt(var1.P(), (int)(var1.S() + var1.A() | var1.pC()));
   }
}
