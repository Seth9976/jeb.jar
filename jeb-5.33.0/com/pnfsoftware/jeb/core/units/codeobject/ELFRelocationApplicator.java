package com.pnfsoftware.jeb.core.units.codeobject;

import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;

public abstract class ELFRelocationApplicator implements IELFRelocationApplicator {
   private int type;

   public ELFRelocationApplicator(int var1) {
      this.type = var1;
   }

   @Override
   public int getType() {
      return this.type;
   }

   protected abstract void applyInternal(ELFStandardRelocOperations var1) throws MemoryException;
}
