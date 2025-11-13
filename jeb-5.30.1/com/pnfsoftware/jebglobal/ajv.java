package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIdentifier;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICVisitor;

class ajv implements ICVisitor {
   ajv(aju.eo var1) {
      this.q = var1;
   }

   public void q(ICElement var1, ICElement var2, IVisitResults var3) {
      if (var1 instanceof ICIdentifier) {
         this.q.xK.add((ICIdentifier)var1);
      }
   }
}
