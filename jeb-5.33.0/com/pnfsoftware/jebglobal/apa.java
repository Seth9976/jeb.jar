package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.DUI;
import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IENop;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.util.collect.CollectionUtil;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class apa extends AbstractEOptimizer {
   private static final StructuredLogger pC = aco.pC(apa.class);

   public apa() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
      this.addTag("dcr");
      this.setPriority(50.0);
   }

   @Override
   protected int perform() {
      return this.pC(1, false, this.getMasterOptimizerSafe().getDisregardedOutputRegisters());
   }

   private int pC(int var1, boolean var2, Set var3) {
      int var4 = 0;

      for (BasicBlock var6 : this.cfg) {
         for (int var7 = 0; var7 < var6.size(); var7++) {
            IEStatement var8 = (IEStatement)var6.get(var7);
            if (var8 instanceof IEAssign) {
               IEAssign var9 = var8.asAssign();
               if (var9.getLeftOperand().equals(var9.getRightOperand())) {
                  IENop var10 = this.ectx.createNop(var9);
                  var6.set(var7, var10);
                  this.cfg.invalidateDataFlowAnalysis(var6.getAddressOfInstruction(var7));
                  var4++;
               }
            }
         }
      }

      if (var1 < 0) {
         var1 = Integer.MAX_VALUE;
      }

      while (var1 > 0) {
         int var12 = this.pC(var2, var3);
         if (var12 == 0) {
            break;
         }

         var4 += var12;
         var1--;
      }

      var4 += this.pC();
      return this.postPerform(var4);
   }

   private int pC(boolean var1, Set var2) {
      int var3 = 0;
      boolean var4 = false;

      int var7;
      for (BasicBlock var6 : this.cfg.getBlocks()) {
         do {
            acj.pC();
            var7 = 0;
            int var8 = var6.size() - 1;

            while (var8 >= 0) {
               if (!(var6.get(var8) instanceof IEAssign)) {
                  var8--;
               } else {
                  IEAssign var9 = (IEAssign)var6.get(var8);
                  long var10 = var6.getAddressOfInstruction(var8);
                  IEVar var13;
                  if (var9.getLeftOperand() instanceof IEVar) {
                     var13 = (IEVar)var9.getLeftOperand();
                  } else {
                     if (!(var9.getLeftOperand() instanceof IESlice) || !(((IESlice)var9.getLeftOperand()).getWholeExpression() instanceof IEVar)) {
                        var8--;
                        continue;
                     }

                     IESlice var12 = (IESlice)var9.getLeftOperand();
                     var13 = (IEVar)var12.getWholeExpression();
                  }

                  if (var13 != this.ectx.getProgramCounter() && var13 != this.ectx.getStackPointer()) {
                     IDFA var14 = this.cfg.getDataFlowAnalysis();
                     if (var14 == null) {
                        var14 = this.cfg.doDataFlowAnalysis();
                        var4 = true;
                     }

                     var14.setMaxBlocks(50);
                     byte var15 = 0;
                     int var16 = var13.getId();
                     Collection var17 = var14.getDefUses(var10, var16, 2, true);
                     if (var17 != null && var17.size() < 2) {
                        if (var17.size() == 0) {
                           var15 = 1;
                        } else if (var17.size() == 1) {
                           long var18 = (Long)var17.iterator().next();
                           if (var18 == var10) {
                              var15 = 1;
                           } else if (var18 == -1L && this.getMasterOptimizerSafe().canDiscardReachingOutDefinition(this.ectx, var10, var16)) {
                              var15 = 2;
                           }
                        }
                     }

                     if (var15 == 0 || var15 == 1 && !this.getMasterOptimizerSafe().canDiscardUnusedDefinition(this.ectx, var10, var16)) {
                        var8--;
                     } else if (akt.pC(this.ectx, var6, var8) == 0) {
                        var8--;
                     } else {
                        this.cfg.invalidateDataFlowAnalysis(var10);
                        var4 = false;
                        var8--;
                        var7++;
                     }
                  } else {
                     var8--;
                  }
               }
            }

            var3 += var7;
         } while (var7 > 0);
      }

      if (var3 > 0 && !var4) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      IDFA var20 = this.cfg.getDataFlowAnalysis();
      if (var20 != null) {
         var20.setMaxBlocks(-1);
      }

      return var3;
   }

   public int pC() {
      IDFA var1 = this.cfg.doDataFlowAnalysis();

      for (BasicBlock var3 : this.cfg) {
         if (var3.outsize() == 0 && !var1.isTerminator(var3)) {
            return 0;
         }
      }

      int var16 = 0;

      while (true) {
         int var17 = 0;
         HashSet var4 = new HashSet();

         for (AddressableInstruction var6 : this.cfg.addressableInstructions()) {
            DUI var7 = var1.getDUI(var6);
            var4.addAll(var7.getUse());
            var4.addAll(var7.getUsePot());
         }

         HashSet var18 = new HashSet();

         for (BasicBlock var20 : this.cfg) {
            long var8 = var20.getBase();

            for (int var10 = 0; var10 < var20.size(); var10++) {
               IEStatement var11 = (IEStatement)var20.get(var10);
               if (var11 instanceof IEAssign && !EUtil.isPCAssign(var11) && var11.asAssign().getDstOperand() instanceof IEVar) {
                  DUI var12 = var1.getDUI(var8, var11);
                  var18.clear();
                  var18.addAll(var12.getDef());
                  var18.addAll(var12.getDefPot());
                  if (!var18.isEmpty() && !CollectionUtil.hasIntersection(var18, var4)) {
                     boolean var13 = true;

                     for (int var15 : var18) {
                        if (!this.getMasterOptimizerSafe().canDiscardUnusedDefinition(this.ectx, var8, var15)) {
                           var13 = false;
                           break;
                        }
                     }

                     if (var13) {
                        IENop var21 = this.ectx.createNop(var11);
                        var20.set(var10, var21);
                        var1.notifyInstructionUpdate(var8);
                        var17++;
                     }
                  }
               }

               var8 += var11.getSize();
            }
         }

         if (var17 == 0) {
            if (var16 > 0) {
               Object[] var10000 = new Object[]{var16};
            }

            return var16;
         }

         var16 += var17;
      }
   }
}
