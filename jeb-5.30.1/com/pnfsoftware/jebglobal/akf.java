package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICVisitor;
import java.util.List;

class akf implements ICVisitor {
   akf(ake var1, List var2) {
      this.RF = var1;
      this.q = var2;
   }

   public void q(ICElement var1, ICElement var2, IVisitResults var3) {
      if (this.q.contains(var1)) {
         var2.replaceSubElement(var1, ake.q(this.RF).createInt32(1));
         var3.interrupt(true);
      }
   }
}
