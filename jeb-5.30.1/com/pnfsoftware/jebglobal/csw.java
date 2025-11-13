package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.codeobject.ELFStandardRelocOperations;
import com.pnfsoftware.jeb.core.units.codeobject.ELFStandardRelocator;

class csw extends ELFStandardRelocator {
   csw(int var1) {
      super(var1);
   }

   @Override
   protected void applyInternal(ELFStandardRelocOperations var1) throws MemoryException {
      var1.mem.writeInt(var1.P(), (int)(var1.B() + var1.A()), var1.endianness);
   }
}
