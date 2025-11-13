package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ILinkInfoProvider;

class ced implements ILinkInfoProvider {
   ced(cdz var1) {
      this.pC = var1;
   }

   @Override
   public long resolveImportedSymbol(String var1, int var2, IVirtualMemory var3, IELFUnit var4) {
      long var5 = this.pC.Sc.findRegisteredRoutine(var1);
      if (var5 == 0L) {
         cee.Av var7 = this.pC.WR.kS(var1);
         if (var7 != null && var7.wS()) {
            return 0L;
         }

         if (var7 != null) {
            var5 = var7.pC();
         }

         if (var5 == 0L) {
            var5 = this.pC.Sc.createPseudoRoutine(var1);
         } else {
            this.pC.Sc.registerRoutine(var5, var1);
         }
      }

      return var5;
   }
}
