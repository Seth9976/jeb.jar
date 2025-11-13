package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJump;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEUntranslatedInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.EExpressionMatcher;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.Node;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.O;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.Util;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import java.util.Collection;

public class cq extends AbstractEOptimizer {
   public cq() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
   }

   @Override
   protected int perform() {
      oP var1 = (oP)this.ectx.getGlobalContext().getConverter();
      int var2 = 0;

      for (BasicBlock var4 : this.cfg) {
         for (int var5 = 0; var5 < var4.size(); var5++) {
            IEStatement var6 = (IEStatement)var4.get(var5);
            if (oP.pC(var6, 241)) {
               IEUntranslatedInstruction var7 = (IEUntranslatedInstruction)var6;
               IEGeneric var8 = var7.getParameterExpression(4);
               IEGeneric var9 = var7.getParameterExpression(6);
               if (this.pC(var4, var5, var8) && this.pC(var4, var5, var9)) {
                  IEGeneric var10 = var7.getParameterExpression(0);
                  Node var11 = Util.N(O.MUL, Util.N(O.COMPOSE_2, Util.N(O.EQ, Util.LV(0), Util.LC(0L)), Util.LC(0L)), Util.LC(2300L));
                  EExpressionMatcher var12 = new EExpressionMatcher(var11);
                  if (var12.isMatch(var10)) {
                     IEGeneric var13 = (IEGeneric)var12.getMatchMap().get(0);
                     if (var7.getParameterExpression(2) == var13) {
                        IEGeneric var14 = var7.getParameterExpression(1);
                        IEUntranslatedInstruction var15 = oP.pC(this.ectx, var7.getNativeAddress(), -2147483645, var14, var13);
                        var15.setReturnExpression(var7.getReturnExpression());
                        var15.copyProperties(var7);
                        var4.set(var5, var15);
                        var2++;
                     }
                  }
               }
            }
         }
      }

      label77:
      for (int var16 = 0; var16 < this.cfg.size(); var16++) {
         BasicBlock var17 = this.cfg.get(var16);

         for (int var18 = 0; var18 < var17.size() - 1; var18++) {
            IEStatement var19 = (IEStatement)var17.get(var18);
            if (oP.pC(var19, -2147483645)) {
               IEUntranslatedInstruction var20 = (IEUntranslatedInstruction)var19;
               IEGeneric var21 = var20.getReturnExpression();
               IEStatement var22 = (IEStatement)var17.get(var18 + 1);
               if (var22 instanceof IEJump var23) {
                  IEGeneric var24 = var23.getCondition();
                  if (EUtil.isMatchDuaryOperation(var24, OperationType.LOG_NEQ, var21, var1.hK) && var16 < this.cfg.size()) {
                     BasicBlock var25 = this.cfg.get(var16 + 1);
                     if (oP.A((IEStatement)var25.getLast())) {
                        for (int var26 = 0; var26 < var25.size() - 1; var26++) {
                           if (oP.pC((IEStatement)var25.get(var26), 62, 61) == null) {
                              continue label77;
                           }
                        }

                        GK var27 = uX.pC(-2147483644);
                        var20.setNativeMnemonic(var27.A());
                        var20.setTag(var27.pC());
                        var20.setReturnExpression(null);
                        var23.setCondition(EUtil.one(256));
                        var2++;
                     }
                  }
               }
            }
         }
      }

      return this.postPerform(var2);
   }

   private boolean pC(BasicBlock var1, int var2, IEGeneric var3) {
      if (EUtil.isImmZero(var3)) {
         return true;
      } else {
         if (EUtil.isOperation(var3, OperationType.SUB)) {
            IEGeneric var4 = ((IEOperation)var3).getOperand1();
            IEGeneric var5 = ((IEOperation)var3).getOperand2();
            IEVar var6 = null;
            IEGeneric var7 = null;
            if (var4 instanceof IEVar) {
               var6 = (IEVar)var4;
               var7 = var5;
            } else if (var5 instanceof IEVar) {
               var6 = (IEVar)var5;
               var7 = var4;
            }

            if (var6 != null) {
               IDFA var8 = this.cfg.doDataFlowAnalysis();
               Collection var9 = var8.getUseDefs(var1.getAddressOfInstruction(var2), var6.getId(), 2);
               if (var9.size() == 1) {
                  long var10 = (Long)var9.iterator().next();
                  if (var10 >= 0L) {
                     IEStatement var12 = (IEStatement)this.cfg.getInstruction(var10);
                     if (var12 instanceof IEAssign && ((IEAssign)var12).getLeftOperand() == var6) {
                        IEGeneric var13 = ((IEAssign)var12).getRightOperand();
                        if (var13.equals(var7)) {
                           return true;
                        }
                     }
                  }
               }
            }
         }

         return false;
      }
   }
}
