package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ILinkInfoProvider;

class cjp implements ILinkInfoProvider {
   cjp(cjo var1) {
      this.q = var1;
   }

   @Override
   public long resolveImportedSymbol(String var1, int var2, IVirtualMemory var3, IELFUnit var4) {
      long var5 = this.q.Hk.findRegisteredRoutine(var1);
      if (var5 == 0L) {
         cjq.eo var7 = this.q.JY.xK(var1);
         if (var7 != null && var7.oW()) {
            return 0L;
         }

         if (var7 != null) {
            var5 = var7.q();
         }

         if (var5 == 0L) {
            var5 = this.q.Hk.createPseudoRoutine(var1);
         } else {
            this.q.Hk.registerRoutine(var5, var1);
         }
      }

      return var5;
   }
}
