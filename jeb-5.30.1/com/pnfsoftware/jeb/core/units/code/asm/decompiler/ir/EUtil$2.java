package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;

class EUtil$2 implements IEVisitor {
   public void process(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1 instanceof IECall || var1 instanceof IEJumpFar || var1 instanceof IEUntranslatedInstruction) {
         var3.interrupt(false);
      }
   }
}
