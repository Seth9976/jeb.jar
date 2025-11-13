package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import java.util.concurrent.atomic.AtomicInteger;

class apo implements IEVisitor {
   apo(apm var1, IEVar var2, boolean var3, AtomicInteger var4) {
      this.Dw = var1;
      this.q = var2;
      this.RF = var3;
      this.xK = var4;
   }

   public void q(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1 == this.q || var1 instanceof IEOperation && !EUtil.isLogicalOperation(var1)) {
         IEGeneric var4 = this.Dw.q(var1, var2, this.q, this.RF, null);
         if (var4 != null) {
            var3.setReplacedNode(var4);
            this.xK.incrementAndGet();
         }
      }
   }
}
