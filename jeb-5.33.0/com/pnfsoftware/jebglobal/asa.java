package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESwitch;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEStatementOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.util.base.Couple;
import java.util.ArrayList;

public class asa extends AbstractEStatementOptimizer {
   public asa() {
      super(DataChainsUpdatePolicy.UPDATE_NOT_NECESSARY);
   }

   @Override
   protected IEStatement optimizeStatement(IEStatement var1) {
      if (!(var1 instanceof IESwitch var2)) {
         return null;
      } else {
         int var3 = var2.getDefaultAddress();
         ArrayList var4 = new ArrayList();

         for (Couple var6 : var2.getCases()) {
            if ((Integer)var6.getSecond() == var3) {
               var4.add((IEGeneric)var6.getFirst());
            }
         }

         if (var4.isEmpty()) {
            return null;
         } else {
            for (IEGeneric var8 : var4) {
               var2.removeCase(var8);
            }

            return var2;
         }
      }
   }
}
