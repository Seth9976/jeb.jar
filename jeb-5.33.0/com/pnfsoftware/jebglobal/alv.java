package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.Pointer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import com.pnfsoftware.jeb.util.collect.MultiMap;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

class alv implements IEVisitor {
   alv(alu var1, EState var2, Collection var3, Long var4, MultiMap var5, AtomicInteger var6) {
      this.E = var1;
      this.pC = var2;
      this.A = var3;
      this.kS = var4;
      this.wS = var5;
      this.UT = var6;
   }

   public void pC(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1 instanceof IEMem) {
         IEGeneric var4 = ((IEMem)var1).getReference();

         try {
            long var5 = var4.evaluateAddress(this.pC);
            if (var5 == 0L) {
               return;
            }

            Pointer var7 = new Pointer(var5, var1.getBitsize() / 8, 2);
            this.A.add(var7);
            if (this.kS != null) {
               this.wS.put(var7, this.kS);
            }

            Object[] var10000 = new Object[]{var7};
            this.UT.incrementAndGet();
         } catch (Exception var8) {
         }
      }
   }
}
