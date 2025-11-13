package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;

class EUtil$3 implements IEVisitor {
   public void process(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1.isMem()) {
         var3.interrupt(false);
      }
   }
}
