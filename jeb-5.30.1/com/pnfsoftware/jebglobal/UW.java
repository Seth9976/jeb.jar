package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;

class UW extends Ra.CU {
   UW(int var1) {
      super(var1);
   }

   public void q(Ra.eo var1) throws MemoryException {
      var1.mem.writeInt(var1.P(), (int)(var1.B() + var1.A()));
   }
}
