package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIdentifier;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICVisitor;

class ahx implements ICVisitor {
   ahx(ahw.Av var1) {
      this.pC = var1;
   }

   public void pC(ICElement var1, ICElement var2, IVisitResults var3) {
      if (var1 instanceof ICIdentifier) {
         this.pC.kS.add((ICIdentifier)var1);
      }
   }
}
