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

public class art extends AbstractEOptimizer {
   private static final StructuredLogger q = aeg.q(art.class);

   public art() {
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
                  art.eo var9 = this.q(var8);
                  if (var9 != null && this.RF((IEAssign)var6, var9) && this.q((IEAssign)var7, var9)) {
                     IEOperation var10 = this.ectx.createOperation(var9.RF, var9.Dw, this.ectx.createOperation(var9.Uv, var9.xK, var9.oW));
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

   private boolean q(IEAssign var1, art.eo var2) {
      if (!var1.getDstOperand().equals(var2.q)) {
         return false;
      } else {
         IEGeneric var3 = var1.getSrcOperand();
         if (!EUtil.isComparableOperation(var3)) {
            return false;
         } else {
            IEGeneric var4 = ((IEOperation)var1.getSrcOperand()).getOperand1();
            IEGeneric var5 = ((IEOperation)var1.getSrcOperand()).getOperand2();
            if (!var4.equals(var2.Dw)) {
               return false;
            } else if (!(var5 instanceof IEImm)) {
               return false;
            } else {
               var2.RF = ((IEOperation)var3).getOperationType();
               var2.xK = (IEImm)var5;
               return true;
            }
         }
      }
   }

   private boolean RF(IEAssign var1, art.eo var2) {
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
               var2.Dw = var3;
               var2.Uv = var4;
               var2.oW = (IEImm)var6;
               return true;
            }
         }
      }
   }

   private art.eo q(IEGeneric var1) {
      Set var2 = EUtil.collectVars(var1);
      if (var2.size() != 1) {
         return null;
      } else {
         art.eo var3 = new art.eo();
         var3.q = (IEVar)var2.iterator().next();
         return var3;
      }
   }

   private static class eo {
      IEVar q;
      OperationType RF;
      IEImm xK;
      IEVar Dw;
      OperationType Uv;
      IEImm oW;
   }
}
