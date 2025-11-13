package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.codeobject.ELFStandardRelocOperations;
import com.pnfsoftware.jeb.core.units.codeobject.ELFStandardRelocator;

class cst extends ELFStandardRelocator {
   cst(int var1) {
      super(var1);
   }

   @Override
   protected void applyInternal(ELFStandardRelocOperations var1) throws MemoryException {
   }
}
