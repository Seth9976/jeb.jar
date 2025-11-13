package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICVisitor;
import java.util.List;

class aig implements ICVisitor {
   aig(aif var1, List var2) {
      this.A = var1;
      this.pC = var2;
   }

   public void pC(ICElement var1, ICElement var2, IVisitResults var3) {
      if (this.pC.contains(var1)) {
         var2.replaceSubElement(var1, aif.pC(this.A).createInt32(1));
         var3.interrupt(true);
      }
   }
}
