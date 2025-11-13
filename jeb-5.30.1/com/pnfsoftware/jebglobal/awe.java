package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import com.pnfsoftware.jeb.util.collect.SegmentMap;
import com.pnfsoftware.jeb.util.format.Strings;

class awe implements IEVisitor {
   awe(awd var1, SegmentMap var2, avy var3) {
      this.xK = var1;
      this.q = var2;
      this.RF = var3;
   }

   public void q(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1 instanceof IEVar) {
         int var4 = ((IEVar)var1).getId();
         if (this.q.getSegmentContaining(var4) == null) {
            throw new IllegalStateException(Strings.ff("Post-stage %s: Illegal EVar: %s (id=0x%X)", this.RF, var1, var4));
         }
      }
   }
}
