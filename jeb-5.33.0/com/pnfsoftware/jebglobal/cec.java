package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStateHooks;

class cec implements IEStateHooks {
   cec(cdz var1) {
      this.pC = var1;
   }

   @Override
   public Boolean onWriteMemory(EState var1, long var2, byte[] var4) {
      if (var2 >= 57662116986880L && var2 < 57662117019648L) {
         Object[] var10000 = new Object[0];
         int var5 = (int)(var2 - 57662116986880L);
         int var6 = var5 / 256;
         this.pC.kS().getValue(1, var6);
      }

      return null;
   }
}
