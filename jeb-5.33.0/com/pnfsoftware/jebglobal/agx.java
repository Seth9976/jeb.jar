package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICVisitor;

class agx implements ICVisitor {
   agx(agw var1, ICStatement var2, ICLabel var3) {
      this.kS = var1;
      this.pC = var2;
      this.A = var3;
   }

   public void pC(ICElement var1, ICElement var2, IVisitResults var3) {
      if (var1 == this.pC) {
         var3.interrupt(true);
      } else if (var1 == this.A) {
         var3.interrupt(false);
      }
   }
}
