package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;

class cku extends ckp.CU {
   cku(int var1) {
      super(var1);
   }

   public void q(ckp.eo var1) throws MemoryException {
      var1.mem.writeInt(var1.P(), (int)(var1.S() + var1.A()), var1.endianness);
   }
}
