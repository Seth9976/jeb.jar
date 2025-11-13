package com.pnfsoftware.jeb.corei.parsers.x86;

import com.pnfsoftware.jeb.core.units.codeobject.ELFRelocationApplicator;
import com.pnfsoftware.jeb.core.units.codeobject.ELFRelocationContext;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;

class ao extends ELFRelocationContext {
   ao(ELFRelocationApplicator... var1) {
      super(var1);
   }

   @Override
   public boolean canApply(IELFUnit var1, long var2) {
      return var1.getHeader().getMachine() == 3
         && (var1.getHeader().getType() == 3 || var1.getHeader().getType() == 2)
         && !this.isMappedAtPreferredAddress(var1, var2);
   }
}
