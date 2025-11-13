package com.pnfsoftware.jeb.corei.parsers.x86;

import com.pnfsoftware.jeb.core.units.codeobject.ELFRelocationApplicator;
import com.pnfsoftware.jeb.core.units.codeobject.ELFRelocationContext;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ISymbolInformation;
import com.pnfsoftware.jebglobal.cgv;

class jY extends ELFRelocationContext {
   jY(ELFRelocationApplicator... var1) {
      super(var1);
   }

   @Override
   public boolean canApply(IELFUnit var1, long var2) {
      if (var1.getHeader().getMachine() == 3) {
         for (ISymbolInformation var5 : var1.getSymbols()) {
            if (cgv.pC.contains(var5.getName())) {
               return true;
            }
         }
      }

      return false;
   }
}
