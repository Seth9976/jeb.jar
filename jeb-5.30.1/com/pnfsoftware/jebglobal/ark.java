package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IdRanges;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJump;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESwitch;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.OptimizerMode;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ark extends AbstractEOptimizer {
   private static final StructuredLogger q = aeg.q(ark.class);

   public ark() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
   }

   @Override
   protected int perform() {
      int var1 = 0;
      List var2 = this.cfg.getBlocks();

      for (int var3 = 0; var3 < var2.size(); var3++) {
         BasicBlock var4 = (BasicBlock)var2.get(var3);
         if (this.getMasterOptimizer() == null || this.getMasterOptimizer().getMode().meetsRequirement(OptimizerMode.UNFRIENDLY)) {
            IEGeneric var5 = null;
            IEGeneric var6 = null;

            for (int var7 = 0; var7 < var4.size(); var7++) {
               IEStatement var8 = (IEStatement)var4.get(var7);
               if (var8 instanceof IEAssign && !EUtil.isPCAssign(var8)) {
                  IEGeneric var9 = ((IEAssign)var8).getSrcOperand();
                  IEGeneric var10 = ((IEAssign)var8).getDstOperand();
                  if (var9.equals(var6) && this.q(var5, var6)) {
                     IEGeneric var11 = var5.duplicate();
                     if (var8.replaceSubExpression(var9, var11)) {
                        Object[] var10000 = new Object[]{var4.getAddressOfInstruction(var7)};
                        var1++;
                        var5 = var11;
                        var6 = var10;
                     }
                  } else {
                     var5 = var9;
                     var6 = var10;
                  }
               } else {
                  var5 = null;
                  var6 = null;
               }
            }
         }

         for (int var14 = 0; var14 < var4.size() - 1; var14++) {
            IEStatement var16 = (IEStatement)var4.get(var14);
            if (var16.isAssign()) {
               IEAssign var18 = var16.asAssign();
               if (var18.getDstOperand().isVar() && var18.getSrcOperand().isCompose()) {
                  IEVar var20 = var18.getDstOperand().asVar();
                  IECompose var22 = var18.getSrcOperand().asCompose();
                  IEGeneric var24 = var22.getLowPart();
                  if (EUtil.hasNoSideEffect(var24) && this.q(var24, var18.getDstOperand())) {
                     int var26 = var24.getBitsize();
                     AtomicInteger var12 = new AtomicInteger();
                     IEStatement var13 = (IEStatement)var4.get(var14 + 1);
                     var13.visitDepthPre(new arl(this, var20, var26, var24, var12));
                     if (var12.get() > 0) {
                        Object[] var29 = new Object[]{var4.getAddressOfInstruction(var14 + 1)};
                        var1++;
                     }
                  }
               }
            }
         }

         if (var3 >= 1 && var4.size() == 1) {
            IEStatement var15 = (IEStatement)var4.get(0);
            if (var15.isConditionalJump() || var15.isSwitch()) {
               BasicBlock var17 = this.cfg.get(var3 - 1);
               if (var17.size() >= 2) {
                  IEStatement var19 = (IEStatement)var17.getLast();
                  if (var19.isConditionalJump() || var19.isSwitch()) {
                     IEStatement var21 = (IEStatement)var17.get(var17.size() - 2);
                     if (var21 instanceof IEAssign && ((IEAssign)var21).getDstOperand() instanceof IEVar && ((IEAssign)var21).getSrcOperand() instanceof IEVar) {
                        IEVar var23 = ((IEAssign)var21).getDstOperand().asVar();
                        IEVar var25 = ((IEAssign)var21).getSrcOperand().asVar();
                        IEGeneric var27 = this.q(var15);
                        IEGeneric var28 = this.q(var19);
                        if (var27.examine(var1x -> var1x == var23) && var28.examine(var1x -> var1x == var25) && var28.replaceVar(var25, var23) >= 1) {
                           var1++;
                        }
                     }
                  }
               }
            }
         }
      }

      return this.postPerform(var1);
   }

   IEGeneric q(IEStatement var1) {
      if (var1 instanceof IEJump) {
         return ((IEJump)var1).getCondition();
      } else if (var1 instanceof IESwitch) {
         return ((IESwitch)var1).getControlExpression();
      } else {
         throw new RuntimeException();
      }
   }

   private boolean q(IEGeneric var1, IEGeneric var2) {
      if (var1 instanceof IEImm) {
         return true;
      } else if (var2 instanceof IEMem) {
         return false;
      } else {
         IdRanges var3 = var1.getUsed();
         IdRanges var4 = var2.getUsed();
         return !var3.hasIntersection(var4);
      }
   }
}
