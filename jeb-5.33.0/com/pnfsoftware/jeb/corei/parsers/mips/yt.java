package com.pnfsoftware.jeb.corei.parsers.mips;

import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;

class yt extends cq.Sv {
   yt(int var1) {
      super(var1);
   }

   public void pC(cq.Av var1) throws MemoryException {
      var1.mem.writeInt(var1.P(), (int)(var1.S() + var1.A()), var1.endianness);
   }
}
