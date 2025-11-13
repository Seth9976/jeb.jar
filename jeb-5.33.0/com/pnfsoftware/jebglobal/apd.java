package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.INode;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.INodeHandler;

class apd implements INodeHandler {
   @Override
   public IEGeneric process(INode var1, IEGeneric var2) {
      if (var2 instanceof IEImm var3 && var3.canReadAsLong() && var3._signum() >= 0) {
         long var4 = var3.getValueAsLong();
         int var6 = var3.getBitsize() / 2;
         long var7 = (1L << var6) - 1L;
         if ((var4 & var7) == var4) {
            return EUtil.imm(var4, var6);
         }
      }

      return null;
   }
}
