package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class cjd extends AbstractEOptimizer {
   private static final StructuredLogger A = aco.pC(cjd.class);
   static boolean pC = false;

   public cjd() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
   }

   @Override
   protected int perform() {
      if (!pC) {
         return 0;
      } else if (this.ectx.isAllowUnsafeAnalysis() && this.getMasterOptimizerSafe().getMode().isAggressive()) {
         int var1 = 0;

         for (BasicBlock var3 : this.cfg) {
            for (int var4 = 0; var4 < var3.size(); var4++) {
               IEStatement var5 = (IEStatement)var3.get(var4);
               if (var5 instanceof IEAssign) {
                  IEGeneric var6 = ((IEAssign)var5).getDstOperand();
                  IEGeneric var7 = ((IEAssign)var5).getSrcOperand();
                  if (var6 instanceof IEVar && ((IEVar)var6).isStackVariable()) {
                     IDFA var8 = this.cfg.doDataFlowAnalysis(false, 2);
                     if (var8.getDefUses(var3.getAddressOfInstruction(var4), ((IEVar)var6).getId()).isEmpty()) {
                        if (var7 instanceof IEVar) {
                           Integer var9 = this.ectx.getUnderlyingRegisterId(((IEVar)var7).getId());
                           if (var9 != null && (var9 == 320 || var9 == 384 || var9 == 448)) {
                              var3.set(var4, this.ectx.createNop((IEAssign)var5));
                              var1++;
                           }
                        } else if (var7 instanceof IEImm && var7.getType() != null && "void*".equals(var7.getType().toString())) {
                           var3.set(var4, this.ectx.createNop((IEAssign)var5));
                           var1++;
                        }
                     }
                  }
               }
            }
         }

         return this.postPerform(var1);
      } else {
         return 0;
      }
   }
}
