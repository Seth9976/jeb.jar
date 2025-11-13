package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;

class Gj extends xf.CU {
   Gj(int var1) {
      super(var1);
   }

   public void q(xf.eo var1) throws MemoryException {
      long var2 = var1.S() + var1.A();
      var2 = (var2 & 4095L) << 10;
      xf.q(var1, (int)var2);
   }
}
