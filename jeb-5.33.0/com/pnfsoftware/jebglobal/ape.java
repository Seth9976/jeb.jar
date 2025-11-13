package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJump;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.util.collect.CollectionUtil;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class ape extends AbstractEOptimizer {
   private static final StructuredLogger pC = aco.pC(ape.class);

   public ape() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
   }

   @Override
   protected int perform() {
      int var1 = 0;

      for (BasicBlock var3 : this.cfg) {
         for (int var4 = 1; var4 < var3.size(); var4++) {
            IEStatement var5 = (IEStatement)var3.get(var4);
            if (var5 instanceof IEJump && ((IEJump)var5).getCondition() != null) {
               IEStatement var6 = (IEStatement)var3.get(var4 - 1);
               if (var6 instanceof IEAssign) {
                  IEGeneric var7 = ((IEJump)var5).getCondition();
                  ape.Av var8 = this.pC(var7);
                  if (var8 != null
                     && this.pC((IEAssign)var6, var8)
                     && var8.sY.getBitsize() == var8.A.getBitsize()
                     && var8.sY.getBitsize() == var8.wS.getBitsize()
                     && !CollectionUtil.hasIntersection(EUtil.getUsedVarIds(var8.A), EUtil.getUsedVarIds(var8.kS))) {
                     IEOperation var9 = this.ectx
                        .createOperation(var8.UT, var8.A.duplicate(), this.ectx.createOperation(var8.pC, var8.sY, var8.wS.duplicate()));
                     ((IEJump)var5).setCondition(var9);
                     var1++;
                     var4++;
                  }
               }
            }
         }
      }

      return this.postPerform(var1);
   }

   private ape.Av pC(IEGeneric var1) {
      if (!(var1 instanceof IEOperation)) {
         return null;
      } else {
         OperationType var2 = ((IEOperation)var1).getOperationType();
         if (var2 != null && var2.getOperandCount() == 2) {
            switch (var2) {
               case LOG_EQ:
               case LOG_NEQ:
               case GE_S:
               case GT_S:
               case LE_S:
               case LT_S:
               case GE_U:
               case GT_U:
               case LE_U:
               case LT_U:
                  IEGeneric var3 = ((IEOperation)var1).getOperand1();
                  if (!(var3 instanceof IEVar)) {
                     return null;
                  }

                  ape.Av var4 = new ape.Av();
                  var4.UT = var2;
                  var4.E = (IEVar)var3;
                  var4.sY = ((IEOperation)var1).getOperand2();
                  return var4;
               default:
                  return null;
            }
         } else {
            return null;
         }
      }
   }

   private boolean pC(IEAssign var1, ape.Av var2) {
      IEGeneric var3 = var1.getSrcOperand();
      OperationType var4 = EUtil.getOperation(var3, OperationType.ADD, OperationType.SUB);
      if (var4 == null) {
         return false;
      } else {
         IEGeneric var5 = ((IEOperation)var3).getOperand1();
         IEGeneric var6 = ((IEOperation)var3).getOperand2();
         if (!var5.equals(var2.E)) {
            return false;
         } else {
            var2.pC = var4;
            var2.A = var1.getDstOperand();
            var2.kS = var3;
            var2.wS = var6;
            return true;
         }
      }
   }

   private static class Av {
      OperationType pC;
      IEGeneric A;
      IEGeneric kS;
      IEGeneric wS;
      OperationType UT;
      IEGeneric E;
      IEGeneric sY;
   }
}
