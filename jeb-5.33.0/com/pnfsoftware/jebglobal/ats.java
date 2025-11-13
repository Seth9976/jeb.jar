package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.BranchTarget;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class ats extends atf {
   private static final StructuredLogger WR = aco.pC(ats.class);

   public ats() {
      super(atg.sY);
   }

   @Override
   protected boolean kS() {
      int var1 = this.pC(WR, "First round of general IR optimizations");
      if (var1 > 0) {
         Object[] var10000 = new Object[0];
      }

      var1 = this.UT();
      if (var1 > 0) {
         Object[] var7 = new Object[]{var1};
         if (this.wS()) {
            return false;
         }
      }

      Object[] var8 = new Object[0];
      amw var2 = new amw();
      var1 = var2.performOnTarget(this.ys);
      if (var1 > 0) {
         var8 = new Object[]{var1};
         this.pC(WR, "Re-running general optimizations after successful small-primitive insertion");
      }

      var8 = new Object[0];
      var8 = new Object[0];
      amf var3 = new amf();
      var1 = var3.performOnTarget(this.ys);
      if (var1 > 0) {
         var8 = new Object[]{var1};
         this.pC(WR, "Re-running general optimizations after successful large-primitive insertion");
      }

      var8 = new Object[0];
      var8 = new Object[0];
      this.gp.registerOptimizer(new amw());
      var8 = new Object[0];
      this.gp.registerOptimizer(new amf());
      return true;
   }

   int UT() {
      int var1 = 0;

      for (BasicBlock var3 : this.ys.getCfg()) {
         IEStatement var4 = (IEStatement)var3.getLast();
         if (EUtil.isPCAssign(var4)) {
            IEAssign var5 = var4.asAssign();
            if (var5.isBreakingFlow()) {
               IEGeneric var6 = var5.getSrcOperand();
               if (var6.isCond()) {
                  IECond var7 = var6.asCond();
                  IEGeneric var8 = var7.getCondition();
                  IEGeneric var9 = var7.getExpressionTrue();
                  IEGeneric var10 = var7.getExpressionFalse();
                  if (var8.isImm() && var9.isImm() && var10.isImm()) {
                     boolean var11 = ((IEImm)var8)._signum() != 0;
                     long var12 = var9.asImm().getValueAsAddress();
                     long var14 = var10.asImm().getValueAsAddress();
                     long var16 = var11 ? var12 : var14;
                     Long var18 = this.ys.convertNativeAddress(var12);
                     Long var19 = this.ys.convertNativeAddress(var14);
                     if (var11 && var18 == null || !var11 && var19 == null) {
                        Long var20 = var5.getPrimaryLowerLevelAddress();
                        if (this.E.getNativeItemAt(var20) instanceof INativeInstructionItem) {
                           CodePointer var21 = this.E.getProcessor().createEntryPoint(var16);
                           if (this.E.recordDynamicBranchTarget(var20, true, new BranchTarget(var21))) {
                              Object[] var10000 = new Object[]{var20, var3.getLastAddress(), var4, var16};
                              var1++;
                           }
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
