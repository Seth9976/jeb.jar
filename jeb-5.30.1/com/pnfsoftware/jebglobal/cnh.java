package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.codeobject.ELFStandardRelocOperations;
import com.pnfsoftware.jeb.core.units.codeobject.ELFStandardRelocator;

class cnh extends ELFStandardRelocator {
   cnh(int var1) {
      super(var1);
   }

   @Override
   protected void applyInternal(ELFStandardRelocOperations var1) throws MemoryException {
      if (var1.elfUnit.getHeader().getBitsize() == 32) {
         var1.mem.writeInt(var1.P(), (int)(var1.B() + var1.A()));
      } else if (var1.elfUnit.getHeader().getBitsize() == 64) {
         var1.mem.writeLong(var1.P(), var1.B() + var1.A());
      }
   }
}
