package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.codeobject.IELFRelocationApplicator;

class gY extends DP.Sv {
   gY(int var1) {
      super(var1);
   }

   @Override
   public boolean canApply(IELFRelocationApplicator.RelocInstance var1) {
      return true;
   }

   protected void pC(DP.Av var1) throws MemoryException {
      if (var1.se != null && var1.se.getAddress() == 0L) {
         long var2 = var1.mem.readInt(var1.P()) & 4294967295L;
         var1.mem.writeInt(var1.P(), (int)(var1.deltaImageBase + var2 | var1.pC()));
      } else {
         var1.mem.writeInt(var1.P(), (int)(var1.S() + var1.A() | var1.pC()));
      }
   }
}
