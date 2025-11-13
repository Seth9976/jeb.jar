package com.pnfsoftware.jeb.corei.parsers.simatic;

import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;

public class tw extends AbstractEOptimizer {
   public tw() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
   }

   @Override
   public int perform() {
      int var1 = 0;
      tw.eo var2 = null;

      for (BasicBlock var4 : this.cfg) {
         int var5 = 0;

         for (IEStatement var7 : var4) {
            if (var7.isAssignToVar()) {
               IEAssign var8 = var7.asAssign();
               IEVar var9 = var8.getDstOperand().asVar();
               if (var9.getName().contains("AR")) {
                  IEGeneric var10 = var8.getSrcOperand();
                  if (var10.isOperation(OperationType.AND)) {
                     IEGeneric var11 = var10.asOperation().getOperand2();
                     if (var11.getBitsize() == 32 && var11 instanceof IEImm) {
                        int var12 = (int)var11.asImm().getValueAsLong();
                        if ((var12 & 0xFF000000) == 0) {
                           IDFA var13 = this.cfg.doDataFlowAnalysis();
                           long var14 = var4.getAddressOfInstruction(var5);
                           int var16 = var9.getId();

                           for (long var19 : var13.getDefUses(var14, var16)) {
                              if (var13.checkSingleDef(var19, var16, var14)) {
                                 IEStatement var21 = (IEStatement)this.cfg.getInstruction(var19);
                                 if (var2 == null) {
                                    var2 = new tw.eo();
                                 }

                                 var2.q(var16);
                                 var21.visitInstructionPostOrder(var2, true);
                                 var1 += var2.q;
                              }
                           }
                        }
                     }
                  }
               }
            }

            var5++;
         }
      }

      return this.postPerform(var1);
   }

   private static class eo implements IEVisitor {
      int q;
      int RF;

      void q(int var1) {
         this.q = 0;
         this.RF = var1;
      }

      public void q(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
         if (var1.isSlice(24, 32)) {
            IESlice var4 = var1.asSlice();
            if (var4.getWholeExpression().isVar(this.RF) && var2.replaceSubExpression(var1, EUtil.zero(8))) {
               this.q++;
            }
         }
      }
   }
}
