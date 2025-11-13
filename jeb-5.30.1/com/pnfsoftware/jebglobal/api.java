package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;

class api implements IEVisitor {
   api(aph var1, Set var2, EState var3) {
      this.xK = var1;
      this.q = var2;
      this.RF = var3;
   }

   public void q(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var2 instanceof IEMem && var1 == ((IEMem)var2).getReference()) {
         Set var4 = EUtil.getUsedVarIds(var1);
         if (!CollectionUtils.intersection(this.q, var4).isEmpty()) {
            EState var5 = new EState(this.RF, false);

            try {
               long var6 = var1.evaluate(var5).getValueAsLong();
               int var8 = ((IEMem)var2).getBitsize() / 8;
               long var9 = var6 + var8;
               if (var9 > this.xK.PV) {
                  this.xK.PV = var9;
               }
            } catch (Exception var11) {
            }
         }
      }
   }
}
