package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJump;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.Set;

public class apg extends AbstractEOptimizer {
   private static final StructuredLogger pC = aco.pC(apg.class);

   public apg() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
   }

   @Override
   protected int perform() {
      int var1 = 0;

      for (BasicBlock var3 : this.cfg) {
         if (var3.size() >= 3) {
            int var4 = var3.size() - 1;
            IEStatement var5 = (IEStatement)var3.get(var4);
            if (var5 instanceof IEJump && ((IEJump)var5).getCondition() != null) {
               IEStatement var6 = (IEStatement)var3.get(var4 - 1);
               IEStatement var7 = (IEStatement)var3.get(var4 - 2);
               if (var6 instanceof IEAssign && var7 instanceof IEAssign) {
                  IEGeneric var8 = ((IEJump)var5).getCondition();
                  apg.Av var9 = this.pC(var8);
                  if (var9 != null && this.A((IEAssign)var6, var9) && this.pC((IEAssign)var7, var9)) {
                     IEOperation var10 = this.ectx.createOperation(var9.A, var9.wS, this.ectx.createOperation(var9.UT, var9.kS, var9.E));
                     IEAssign var11 = (IEAssign)var7;
                     var11.replaceSubExpression(var11.getSrcOperand(), var10);
                     var3.remove(var4 - 1);
                     var3.add(var4 - 2, var6);
                     var1++;
                  }
               }
            }
         }
      }

      return this.postPerform(var1);
   }

   private boolean pC(IEAssign var1, apg.Av var2) {
      if (!var1.getDstOperand().equals(var2.pC)) {
         return false;
      } else {
         IEGeneric var3 = var1.getSrcOperand();
         if (!EUtil.isComparableOperation(var3)) {
            return false;
         } else {
            IEGeneric var4 = ((IEOperation)var1.getSrcOperand()).getOperand1();
            IEGeneric var5 = ((IEOperation)var1.getSrcOperand()).getOperand2();
            if (!var4.equals(var2.wS)) {
               return false;
            } else if (!(var5 instanceof IEImm)) {
               return false;
            } else {
               var2.A = ((IEOperation)var3).getOperationType();
               var2.kS = (IEImm)var5;
               return true;
            }
         }
      }
   }

   private boolean A(IEAssign var1, apg.Av var2) {
      if (!(var1.getDstOperand() instanceof IEVar)) {
         return false;
      } else {
         IEVar var3 = (IEVar)var1.getDstOperand();
         OperationType var4 = EUtil.getOperation(var1.getSrcOperand(), OperationType.ADD, OperationType.SUB);
         if (var4 == null) {
            return false;
         } else {
            IEGeneric var5 = ((IEOperation)var1.getSrcOperand()).getOperand1();
            IEGeneric var6 = ((IEOperation)var1.getSrcOperand()).getOperand2();
            if (!var5.equals(var3)) {
               return false;
            } else if (!(var6 instanceof IEImm)) {
               return false;
            } else {
               var2.wS = var3;
               var2.UT = var4;
               var2.E = (IEImm)var6;
               return true;
            }
         }
      }
   }

   private apg.Av pC(IEGeneric var1) {
      Set var2 = EUtil.collectVars(var1);
      if (var2.size() != 1) {
         return null;
      } else {
         apg.Av var3 = new apg.Av();
         var3.pC = (IEVar)var2.iterator().next();
         return var3;
      }
   }

   private static class Av {
      IEVar pC;
      OperationType A;
      IEImm kS;
      IEVar wS;
      OperationType UT;
      IEImm E;
   }
}
