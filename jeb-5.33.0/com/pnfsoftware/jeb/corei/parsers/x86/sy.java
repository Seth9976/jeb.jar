package com.pnfsoftware.jeb.corei.parsers.x86;

import com.pnfsoftware.jeb.core.units.codeobject.ELFRelocationApplicator;
import com.pnfsoftware.jeb.core.units.codeobject.ELFRelocationContext;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;

class sy extends ELFRelocationContext {
   sy(ELFRelocationApplicator... var1) {
      super(var1);
   }

   @Override
   public boolean canApply(IELFUnit var1, long var2) {
      return var1.getHeader().getMachine() == 62 && var1.getHeader().getType() == 1;
   }
}
