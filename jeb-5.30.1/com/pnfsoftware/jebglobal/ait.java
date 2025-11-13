package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICVisitor;

class ait implements ICVisitor {
   ait(ais var1, ICStatement var2, ICLabel var3) {
      this.xK = var1;
      this.q = var2;
      this.RF = var3;
   }

   public void q(ICElement var1, ICElement var2, IVisitResults var3) {
      if (var1 == this.q) {
         var3.interrupt(true);
      } else if (var1 == this.RF) {
         var3.interrupt(false);
      }
   }
}
