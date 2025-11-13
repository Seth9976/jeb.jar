package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;

class tr extends Ra.CU {
   tr(int var1) {
      super(var1);
   }

   protected void q(Ra.eo var1) throws MemoryException {
      int var2 = (int)((var1.S() + var1.A() | var1.q()) - var1.P());
      Ra.q(var1, var2, false);
   }
}
