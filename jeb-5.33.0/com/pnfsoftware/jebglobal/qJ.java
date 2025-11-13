package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;

class qJ extends pQ.Sv {
   qJ(int var1) {
      super(var1);
   }

   public void pC(pQ.Av var1) throws MemoryException {
      var1.mem.writeLong(var1.P(), var1.B() + var1.A(), var1.endianness);
   }
}
