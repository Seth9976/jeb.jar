package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;

class ckx extends ckp.CU {
   ckx(int var1) {
      super(var1);
   }

   public void q(ckp.eo var1) throws MemoryException {
      ckp.q(var1, (int)(var1.A() + var1.S()) >> 2);
   }
}
