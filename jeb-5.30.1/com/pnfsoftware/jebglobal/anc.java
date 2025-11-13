package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVariable;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.IVariableProvider;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;

public class anc implements IVariableProvider {
   aml q;

   public anc(IERoutineContext var1) {
      this.q = (aml)var1;
   }

   @Override
   public IVariable get(int var1) {
      return this.q.q(var1);
   }

   @Override
   public IVariable getContaining(int var1) {
      return this.q.RF(var1);
   }
}
