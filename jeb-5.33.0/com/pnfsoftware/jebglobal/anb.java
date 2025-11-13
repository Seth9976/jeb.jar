package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;

class anb implements IEVisitor {
   anb(ana var1, Set var2, EState var3) {
      this.kS = var1;
      this.pC = var2;
      this.A = var3;
   }

   public void pC(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var2 instanceof IEMem && var1 == ((IEMem)var2).getReference()) {
         Set var4 = EUtil.getUsedVarIds(var1);
         if (!CollectionUtils.intersection(this.pC, var4).isEmpty()) {
            EState var5 = new EState(this.A, false);

            try {
               long var6 = var1.evaluate(var5).getValueAsLong();
               int var8 = ((IEMem)var2).getBitsize() / 8;
               long var9 = var6 + var8;
               if (var9 > this.kS.fI) {
                  this.kS.fI = var9;
               }
            } catch (Exception var11) {
            }
         }
      }
   }
}
