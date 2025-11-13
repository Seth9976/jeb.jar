package com.pnfsoftware.jeb.corei.parsers.riscv;

import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.codeobject.ELFStandardRelocOperations;
import com.pnfsoftware.jeb.core.units.codeobject.ELFStandardRelocator;
import com.pnfsoftware.jeb.core.units.codeobject.IELFRelocationApplicator;

class rQ extends ELFStandardRelocator {
   rQ(int var1) {
      super(var1);
   }

   @Override
   public boolean canApply(IELFRelocationApplicator.RelocInstance var1) {
      return true;
   }

   @Override
   protected void applyInternal(ELFStandardRelocOperations var1) throws MemoryException {
      if (var1.se != null && var1.se.getAddress() == 0L) {
         if (var1.elfUnit.getHeader().getBitsize() == 32) {
            long var2 = var1.mem.readInt(var1.P()) & 4294967295L;
            var1.mem.writeInt(var1.P(), (int)(var1.deltaImageBase + var2));
         } else if (var1.elfUnit.getHeader().getBitsize() == 64) {
            long var4 = var1.mem.readLong(var1.P());
            var1.mem.writeLong(var1.P(), var1.deltaImageBase + var4);
         }
      } else if (var1.elfUnit.getHeader().getBitsize() == 32) {
         var1.mem.writeInt(var1.P(), (int)var1.S());
      } else if (var1.elfUnit.getHeader().getBitsize() == 64) {
         var1.mem.writeLong(var1.P(), var1.S());
      }
   }
}
