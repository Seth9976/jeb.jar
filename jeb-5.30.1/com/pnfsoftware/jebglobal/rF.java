package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;

class rF extends xf.CU {
   rF(int var1) {
      super(var1);
   }

   public void q(xf.eo var1) throws MemoryException {
      var1.mem.writeLong(var1.P(), var1.S() + var1.A(), var1.endianness);
   }
}
