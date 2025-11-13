package com.pnfsoftware.jeb.core.units.code.asm.cfg;

import com.pnfsoftware.jeb.core.units.code.IVariable;

public interface IVariableProvider {
   IVariable get(int var1);

   IVariable getContaining(int var1);
}
