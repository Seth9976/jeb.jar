package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.codeobject.ELFRelocationApplicator;
import com.pnfsoftware.jeb.core.units.codeobject.ELFRelocationContext;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ISymbolInformation;

class iH extends ELFRelocationContext {
   iH(ELFRelocationApplicator... var1) {
      super(var1);
   }

   @Override
   public boolean canApply(IELFUnit var1, long var2) {
      if (var1.getHeader().getMachine() == 183) {
         for (ISymbolInformation var5 : var1.getSymbols()) {
            if (cgv.pC.contains(var5.getName())) {
               return true;
            }
         }
      }

      return false;
   }
}
