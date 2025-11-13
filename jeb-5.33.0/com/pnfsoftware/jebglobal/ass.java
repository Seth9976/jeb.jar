package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

class ass implements IEVisitor {
   ass(asr var1, Set var2, AtomicInteger var3) {
      this.kS = var1;
      this.pC = var2;
      this.A = var3;
   }

   public void pC(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1.isMem() && var1.asMem().getSegment() instanceof IEVar) {
         this.pC.add(var1.asMem().getSegment().asVar());
      } else if (var1.isOperation(OperationType.MUL, OperationType.SHL)) {
         this.A.incrementAndGet();
      } else if (var1.isCompose() && var1.asCompose().getPartsCount() == 2) {
         IECompose var4 = var1.asCompose();
         if (var4.getLowPart().getBitsize() <= 4 && EUtil.isZero(var4.getLowPart())) {
            if (var4.getHighPart().isSlice()) {
               IESlice var5 = var4.getHighPart().asSlice();
               if (var5.getBitStart() == 0) {
                  this.A.incrementAndGet();
               }
            } else if (EUtil.isZeroExtend(var4.getHighPart())) {
               this.A.incrementAndGet();
            }
         }
      }
   }
}
