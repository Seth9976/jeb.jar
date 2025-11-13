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

class aoa implements IEVisitor {
   aoa(anz var1, EState var2, Collection var3, Long var4, MultiMap var5, AtomicInteger var6) {
      this.oW = var1;
      this.q = var2;
      this.RF = var3;
      this.xK = var4;
      this.Dw = var5;
      this.Uv = var6;
   }

   public void q(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1 instanceof IEMem) {
         IEGeneric var4 = ((IEMem)var1).getReference();

         try {
            long var5 = var4.evaluateAddress(this.q);
            if (var5 == 0L) {
               return;
            }

            Pointer var7 = new Pointer(var5, var1.getBitsize() / 8, 2);
            this.RF.add(var7);
            if (this.xK != null) {
               this.Dw.put(var7, this.xK);
            }

            Object[] var10000 = new Object[]{var7};
            this.Uv.incrementAndGet();
         } catch (Exception var8) {
         }
      }
   }
}
