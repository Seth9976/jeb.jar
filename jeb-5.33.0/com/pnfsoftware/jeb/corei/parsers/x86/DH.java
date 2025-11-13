package com.pnfsoftware.jeb.corei.parsers.x86;

import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.codeobject.ELFStandardRelocOperations;
import com.pnfsoftware.jeb.core.units.codeobject.ELFStandardRelocator;

class DH extends ELFStandardRelocator {
   DH(int var1) {
      super(var1);
   }

   @Override
   public void applyInternal(ELFStandardRelocOperations var1) throws MemoryException {
      var1.mem.writeShort(var1.P(), (short)(var1.S() + var1.A()));
   }
}
