package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import java.util.concurrent.atomic.AtomicInteger;

public class att extends AbstractEOptimizer {
   public att() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
   }

   @Override
   protected int perform() {
      int var1 = 0;

      for (BasicBlock var3 : this.cfg) {
         for (int var4 = 0; var4 < var3.size() - 1; var4++) {
            IEStatement var5 = (IEStatement)var3.get(var4);
            if (var5.isAssign()) {
               IEAssign var6 = var5.asAssign();
               if (var6.getDstOperand().isVar()) {
                  IEVar var7 = var6.getDstOperand().asVar();
                  IEGeneric var8 = var6.getSrcOperand();
                  if (EUtil.calculateComplexity(var8) > 3) {
                     IEStatement var9 = (IEStatement)var3.get(var4 + 1);
                     if (EUtil.hasExplicitlyUsedVar(var9, var7) && !EUtil.hasExplicitlyUsedVar(var8, var7)) {
                        AtomicInteger var10 = new AtomicInteger();
                        var9.visitInstruction(new atu(this, var8, var7, var10), true);
                        if (var10.get() > 0) {
                           var1 += var10.get();
                           this.cfg.invalidateDataFlowAnalysis(var3.getAddressOfInstruction(var4 + 1));
                        }
                     }
                  }
               }
            }
         }
      }

      return var1;
   }
}
