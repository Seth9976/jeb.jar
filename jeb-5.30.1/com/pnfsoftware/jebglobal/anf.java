package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGroup;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IERange;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import java.util.List;

class anf implements IEVisitor {
   anf(ane var1, List var2) {
      this.RF = var1;
      this.q = var2;
   }

   public void q(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (!(var1 instanceof IEImm) && !(var1 instanceof IEVar) && !(var1 instanceof IERange) && !(var1 instanceof IEGroup)) {
         this.q.add(var1);
      }
   }
}
