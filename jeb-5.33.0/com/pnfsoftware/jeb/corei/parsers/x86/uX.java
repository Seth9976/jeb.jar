package com.pnfsoftware.jeb.corei.parsers.x86;

import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.codeobject.ELFStandardRelocOperations;
import com.pnfsoftware.jeb.core.units.codeobject.ELFStandardRelocator;

class uX extends ELFStandardRelocator {
   uX(int var1) {
      super(var1);
   }

   @Override
   public void applyInternal(ELFStandardRelocOperations var1) throws MemoryException {
   }
}
