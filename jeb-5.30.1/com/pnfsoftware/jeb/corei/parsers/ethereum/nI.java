package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.Arrays;

public class nI extends AbstractEOptimizer {
   private static final ILogger q = GlobalLog.getLogger(nI.class);

   public nI() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
   }

   @Override
   protected int perform() {
      int var1 = 0;

      for (BasicBlock var3 : this.cfg) {
         for (int var4 = 0; var4 <= var3.size() - 3; var4++) {
            Couple var5 = this.q(var3, var4, null, null);
            if (var5 != null) {
               IEVar var6 = (IEVar)var5.getFirst();
               IEVar var7 = (IEVar)var5.getSecond();
               var5 = this.q(var3, var4 + 1, var7, null);
               if (var5 != null) {
                  IEVar var8 = (IEVar)var5.getSecond();
                  var5 = this.q(var3, var4 + 2, var8, var6);
                  if (var5 != null) {
                     if (var4 >= 1) {
                        IDFA var9 = this.cfg.doDataFlowAnalysis();
                        if (this.q(var3, var4, var7, var8, var6, var9)) {
                           var1++;
                           this.cfg.invalidateDataFlowAnalysis();
                           continue;
                        }
                     }

                     if (var4 <= var3.size() - 6) {
                        IDFA var12 = this.cfg.doDataFlowAnalysis();
                        if (this.RF(var3, var4, var7, var8, var6, var12)) {
                           var1++;
                           this.cfg.invalidateDataFlowAnalysis();
                        }
                     }
                  }
               }
            }
         }
      }

      return this.postPerform(var1);
   }

   Couple q(BasicBlock var1, int var2, IEGeneric var3, IEGeneric var4) {
      IEStatement var5 = (IEStatement)var1.get(var2);
      if (var5 instanceof IEAssign) {
         IEGeneric var6 = ((IEAssign)var5).getDstOperand();
         if (var6 instanceof IEVar && (var3 == null || var3 == var6)) {
            IEGeneric var7 = ((IEAssign)var5).getSrcOperand();
            if (var7 instanceof IEVar && var7 != var6 && (var4 == null || var4 == var7)) {
               return new Couple((IEVar)var6, (IEVar)var7);
            }
         }
      }

      return null;
   }

   IEGeneric q(BasicBlock var1, int var2, IEGeneric var3) {
      Assert.a(var3 != null);
      IEStatement var4 = (IEStatement)var1.get(var2);
      if (var4 instanceof IEAssign) {
         IEGeneric var5 = ((IEAssign)var4).getDstOperand();
         if (var5 instanceof IEVar && var3 == var5) {
            return ((IEAssign)var4).getSrcOperand();
         }
      }

      return null;
   }

   Couple q(BasicBlock var1, int var2, IEGeneric... var3) {
      Assert.a(var3.length >= 1);
      IEStatement var4 = (IEStatement)var1.get(var2);
      if (var4 instanceof IEAssign) {
         IEGeneric var5 = ((IEAssign)var4).getDstOperand();
         if (var5 instanceof IEVar && Arrays.asList(var3).contains(var5)) {
            IEGeneric var6 = ((IEAssign)var4).getSrcOperand();
            return new Couple((IEVar)var5, var6);
         }
      }

      return null;
   }

   boolean q(BasicBlock var1, int var2, IEVar var3, IEVar var4, IEVar var5, IDFA var6) {
      Couple var7 = this.q(var1, var2 - 1, var3, var4);
      if (var7 == null) {
         return false;
      } else {
         IEVar var8 = (IEVar)var7.getFirst();
         IEVar var9 = var8 == var3 ? var4 : var3;
         IEGeneric var10 = (IEGeneric)var7.getSecond();
         if (EUtil.collectVars(var10).contains(var8)) {
            return false;
         } else if (!var6.checkSingleUse(var1.getAddressOfInstruction(var2), var5.getId(), var1.getAddressOfInstruction(var2 + 2))) {
            return false;
         } else {
            int var11 = 0;

            for (int var12 = var2 + 1; var12 < var2 + 3; var12++) {
               var11 += ((IEStatement)var1.get(var12)).getSize();
            }

            var1.remove(var2 + 1);
            var1.remove(var2 + 1);
            ((IEStatement)var1.get(var2 - 1)).adjustSize(var11);
            IEAssign var14 = ((IEAssign)var1.get(var2 - 1)).duplicateWithNewOperands(var9, var10);
            var14.setSize(((IEStatement)var1.get(var2)).getSize());
            var1.set(var2, var14);
            IEAssign var13 = this.ectx.createAssign(var8, var9);
            var13.copyProperties((IEStatement)var1.get(var2 - 1));
            var1.set(var2 - 1, var13);
            return true;
         }
      }
   }

   boolean RF(BasicBlock var1, int var2, IEVar var3, IEVar var4, IEVar var5, IDFA var6) {
      IEGeneric var7 = this.q(var1, var2 + 3, var5);
      if (var7 == null) {
         return false;
      } else if (!(var7 instanceof IEOperation var8)) {
         return false;
      } else if (var8.getOperand1() == var3 && var8.getOperand2() instanceof IEImm) {
         Couple var9 = this.q(var1, var2 + 4, var3, var4);
         if (var9 == null) {
            return false;
         } else {
            var9 = this.q(var1, var2 + 5, var4, var5);
            if (var9 == null) {
               return false;
            } else if (!var6.checkSingleUse(var1.getAddressOfInstruction(var2 + 3), var5.getId(), var1.getAddressOfInstruction(var2 + 5))) {
               return false;
            } else {
               int var10 = 0;

               for (int var11 = var2; var11 < var2 + 6; var11++) {
                  var10 += ((IEStatement)var1.get(var11)).getSize();
               }

               var1.remove(var2 + 1);
               var1.remove(var2 + 1);
               var1.remove(var2 + 1);
               var1.remove(var2 + 1);
               var1.remove(var2 + 1);
               ((IEStatement)var1.get(var2)).setSize(var10);
               IEAssign var13 = this.ectx.createAssign(var4, this.ectx.createOperation(var8.getOperationType(), var4, var8.getOperand2()));
               var13.setSize(var10);
               var1.set(var2, var13);
               return true;
            }
         }
      } else {
         return false;
      }
   }
}
