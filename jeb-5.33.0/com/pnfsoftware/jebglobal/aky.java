package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVariable;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.IVariableProvider;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;

public class aky implements IVariableProvider {
   aki pC;

   public aky(IERoutineContext var1) {
      this.pC = (aki)var1;
   }

   @Override
   public IVariable get(int var1) {
      return this.pC.pC(var1);
   }

   @Override
   public IVariable getContaining(int var1) {
      return this.pC.A(var1);
   }
}
