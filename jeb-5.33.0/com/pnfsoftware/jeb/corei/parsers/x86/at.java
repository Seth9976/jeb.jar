package com.pnfsoftware.jeb.corei.parsers.x86;

import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.codeobject.ELFStandardRelocOperations;
import com.pnfsoftware.jeb.core.units.codeobject.ELFStandardRelocator;

class at extends ELFStandardRelocator {
   at(int var1) {
      super(var1);
   }

   @Override
   protected void applyInternal(ELFStandardRelocOperations var1) throws MemoryException {
      var1.mem.writeInt(var1.P(), (int)(var1.S() + var1.A()), var1.endianness);
   }
}
