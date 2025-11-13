package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.OptimizerMode;
import com.pnfsoftware.jeb.util.base.Couple;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ary extends AbstractEOptimizer {
   public ary() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
      this.setRequiredModeThreshold(OptimizerMode.AGGRESSIVE);
   }

   @Override
   protected int perform() {
      int var1 = 0;

      for (BasicBlock var3 : this.cfg) {
         for (IEStatement var5 : var3) {
            ary.Av var6 = new ary.Av();
            if (!var5.visitInstruction(var6)) {
               for (Couple var8 : var6.pC) {
                  IEMem var9 = (IEMem)var8.getFirst();
                  int var10 = var9.getBitsize();
                  IEVar var11 = var9.getReference().asVar();
                  int var12 = var11.getAddress().intValue();
                  IEVar var13 = this.ectx.getStackVariable(var12);
                  if (var13 != null) {
                     int var14 = var13.getBitsize();
                     if (var14 == 32 && var10 == 64) {
                        ArrayList var15 = new ArrayList();
                        var15.add(var13);
                        int var16 = var12 + var14 / 8;

                        for (int var17 = var12 + var10 / 8; var16 < var17; var16 += var14 / 8) {
                           var13 = this.ectx.getStackVariable(var16);
                           if (var13 == null || var13.getBitsize() != var14) {
                              var15 = null;
                              break;
                           }

                           var15.add(var13);
                        }

                        if (var15 != null) {
                           if (this.ectx.getGlobalContext().isBigEndian()) {
                              Collections.reverse(var15);
                           }

                           if (((IEGeneric)var8.getSecond()).replaceSubExpression(var9, this.ectx.createCompose(var15))) {
                              var1++;
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      return this.postPerform(var1);
   }

   private static class Av implements IEVisitor {
      List pC = new ArrayList();

      public void pC(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
         if (var1 instanceof IEMem) {
            if (var2 instanceof IEAssign var4 && var4.getLeftOperand().equals(var1)) {
               return;
            }

            IEMem var5 = var1.asMem();
            if (var5.getReference() instanceof IEVar && var5.getReference().asVar().isStackReference()) {
               this.pC.add(new Couple(var5, var2));
               var3.setVisitResult(false);
            }
         }
      }
   }
}
