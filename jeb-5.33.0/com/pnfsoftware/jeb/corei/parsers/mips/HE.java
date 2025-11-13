package com.pnfsoftware.jeb.corei.parsers.mips;

import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;

class HE extends cq.Sv {
   HE(int var1) {
      super(var1);
   }

   public void pC(cq.Av var1) throws MemoryException {
      cq.pC(var1, (int)(var1.A() + var1.S()) >> 2);
   }
}
