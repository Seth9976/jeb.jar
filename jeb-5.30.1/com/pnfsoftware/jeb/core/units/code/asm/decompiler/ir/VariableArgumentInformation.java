package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import java.util.List;

public class VariableArgumentInformation {
   List varArgTypes;

   public VariableArgumentInformation(List var1) {
      this.varArgTypes = var1;
   }

   public List getVarArgTypes() {
      return this.varArgTypes;
   }
}
