package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;

class oW extends xf.CU {
   oW(int var1) {
      super(var1);
   }

   public void q(xf.eo var1) throws MemoryException {
      long var2 = (var1.S() + var1.A() & -4096L) - (var1.P() & -4096L);
      var2 >>= 12;
      int var4 = (int)(var2 & 3L);
      var4 <<= 29;
      int var5 = (int)(var2 & 2097148L);
      var5 <<= 3;
      xf.q(var1, var4 | var5);
   }
}
