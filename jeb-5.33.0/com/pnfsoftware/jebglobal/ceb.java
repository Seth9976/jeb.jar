package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStateHooks;

class ceb implements IEStateHooks {
   ceb(cdz var1) {
      this.pC = var1;
   }

   @Override
   public Boolean onWriteMemory(EState var1, long var2, byte[] var4) {
      if (var2 >= 57662133764096L && var2 < 57662133796864L) {
         Object[] var10000 = new Object[0];
         int var5 = (int)(var2 - 57662133764096L);
         int var6 = var5 / 256;
      }

      return null;
   }
}
