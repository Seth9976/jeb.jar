package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBreak;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGenericBreakable;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICVisitor;

class akl implements ICVisitor {
   akl(ICBlock var1) {
      this.q = var1;
   }

   public void q(ICElement var1, ICElement var2, IVisitResults var3) {
      if (var1 instanceof ICGenericBreakable) {
         var3.skipChildren();
      }

      if (var1 instanceof ICBreak && var1 != this.q.get(this.q.size() - 1)) {
         var3.interrupt(false);
      }
   }
}
