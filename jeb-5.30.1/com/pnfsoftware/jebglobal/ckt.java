package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;

class ckt extends ckp.CU {
   ckt(int var1) {
      super(var1);
   }

   public void q(ckp.eo var1) throws MemoryException {
      var1.mem.writeInt(var1.P(), (int)(var1.B() + var1.A()), var1.endianness);
   }
}
