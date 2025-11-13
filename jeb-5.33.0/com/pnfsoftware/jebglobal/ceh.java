package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStateHooks;
import java.nio.ByteBuffer;

class ceh implements IEStateHooks {
   ceh(ceg var1, long var2, long var4, ByteBuffer var6) {
      this.wS = var1;
      this.pC = var2;
      this.A = var4;
      this.kS = var6;
   }

   @Override
   public Boolean onWriteMemory(EState var1, long var2, byte[] var4) {
      try {
         if (var2 >= this.pC && var2 < this.A) {
            int var5 = (int)(var2 - this.pC);
            this.kS.put(var5, var4);
         }
      } catch (Exception var6) {
      }

      return null;
   }

   @Override
   public Integer onReadMemory(EState var1, long var2, byte[] var4) {
      try {
         if (var2 >= this.pC && var2 < this.A) {
            int var5 = (int)(var2 - this.pC);
            this.kS.get(var5, var4);
            return 0;
         }
      } catch (Exception var6) {
      }

      return null;
   }
}
