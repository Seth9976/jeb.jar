package com.pnfsoftware.jeb.corei.parsers.x86;

import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.codeobject.ELFStandardRelocOperations;
import com.pnfsoftware.jeb.core.units.codeobject.ELFStandardRelocator;
import com.pnfsoftware.jeb.core.units.codeobject.IELFRelocationApplicator;

class LK extends ELFStandardRelocator {
   LK(int var1) {
      super(var1);
   }

   @Override
   public boolean canApply(IELFRelocationApplicator.RelocInstance var1) {
      return true;
   }

   @Override
   protected void applyInternal(ELFStandardRelocOperations var1) throws MemoryException {
      if (var1.se != null && var1.se.getAddress() == 0L) {
         long var2 = var1.mem.readInt(var1.P()) & 4294967295L;
         var1.mem.writeInt(var1.P(), (int)(var1.deltaImageBase + var2));
      } else {
         var1.mem.writeInt(var1.P(), (int)var1.S(), var1.endianness);
      }
   }
}
