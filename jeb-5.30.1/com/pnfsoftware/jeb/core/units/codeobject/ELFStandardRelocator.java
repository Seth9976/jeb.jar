package com.pnfsoftware.jeb.core.units.codeobject;

import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;

public abstract class ELFStandardRelocator extends ELFRelocationApplicator {
   public ELFStandardRelocator(int var1) {
      super(var1);
   }

   @Override
   public void apply(IELFRelocationApplicator.RelocatedFile var1, IELFRelocationApplicator.RelocInstance var2) throws MemoryException {
      ELFStandardRelocOperations var3 = new ELFStandardRelocOperations(var1, var2);
      this.applyInternal(var3);
   }
}
