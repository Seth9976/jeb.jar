package com.pnfsoftware.jeb.corei.parsers.mips;

import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;

class RC extends cq.Sv {
   RC(int var1) {
      super(var1);
   }

   public void pC(cq.Av var1) throws MemoryException {
      cq.pC(var1, cq.pC(var1.pC() + var1.S()));
   }
}
