package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStateHooks;

class cea implements IEStateHooks {
   cea(cdz var1) {
      this.pC = var1;
   }

   @Override
   public Integer onReadMemory(EState var1, long var2, byte[] var4) {
      if (var2 >= this.pC.JF && var2 < this.pC.Nq) {
         this.pC.pC();
      }

      return null;
   }
}
