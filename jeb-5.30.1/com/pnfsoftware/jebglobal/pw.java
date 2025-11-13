package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;

class pw extends Ra.CU {
   pw(int var1) {
      super(var1);
   }

   protected void q(Ra.eo var1) throws MemoryException {
      var1.mem.writeInt(var1.P(), (int)(var1.S() + var1.A() | var1.q()));
   }
}
