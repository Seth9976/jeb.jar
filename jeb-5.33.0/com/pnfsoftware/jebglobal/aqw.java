package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.DUI;
import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEReturn;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.util.base.Couple;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class aqw extends AbstractEOptimizer {
   public aqw() {
      super(DataChainsUpdatePolicy.UPDATE_PERFORMED_INTERNALLY);
   }

   @Override
   protected int perform() {
      IDFA var1 = this.cfg.doDataFlowAnalysis();

      for (BasicBlock var3 : this.cfg) {
         if (var3.outsize() == 0 && !var1.isTerminator(var3)) {
            return 0;
         }
      }

      int var17 = 0;

      while (true) {
         int var18 = 0;
         LinkedHashMap var4 = new LinkedHashMap();
         Integer var5 = null;
         HashSet var6 = new HashSet();

         for (BasicBlock var8 : this.cfg) {
            IEStatement var9 = (IEStatement)var8.get(0);
            DUI var10 = var1.getDUI(var8.getBase(), var9);
            long var11 = var8.getBase() + var9.getSize();

            for (int var13 = 1; var13 < var8.size() + 1; var13++) {
               IEStatement var14 = null;
               DUI var15 = null;
               if (var13 < var8.size()) {
                  var14 = (IEStatement)var8.get(var13);
                  var15 = var1.getDUI(var11, var14);
               }

               var6.addAll(var10.getUse());
               var6.addAll(var10.getUsePot());
               if (var5 != null) {
                  var6.remove(var5);
                  var5 = null;
               } else if (var14 != null
                  && var9 instanceof IEAssign
                  && var9.asAssign().getDstOperand() instanceof IEVar
                  && EUtil.hasNoSideEffect(var9.asAssign().getSrcOperand())
                  && var10.getDefPot().isEmpty()
                  && var10.getDef().size() == 1) {
                  int var16 = (Integer)var10.getDef().iterator().next();
                  if (!var6.contains(var16) && var15.getUsePot().isEmpty() && var15.getUse().contains(var16)) {
                     if (var4.containsKey(var16)) {
                        var4.remove(var16);
                        var6.add(var16);
                     } else if (EUtil.countVariablePresence(var14, this.ectx.getVariableById(var16)) == 1) {
                        var4.put(var16, new Couple(var8, var13));
                        var5 = var16;
                     }
                  }
               }

               if (var14 == null) {
                  break;
               }

               var9 = var14;
               var10 = var15;
               var11 += var14.getSize();
            }
         }

         for (Integer var20 : var4.keySet()) {
            if (!var6.contains(var20)) {
               Couple var21 = (Couple)var4.get(var20);
               BasicBlock var22 = (BasicBlock)var21.getFirst();
               int var23 = (Integer)var21.getSecond();
               long var12 = var22.getAddressOfInstruction(var23 - 1);
               if (this.getMasterOptimizerSafe().canDiscardUnusedDefinition(this.ectx, var12, var20)) {
                  IEStatement var24 = (IEStatement)var22.get(var23 - 1);
                  if (!EUtil.isSPAssignOrPCAssign(var24) && (var24.getFlags() & 1) == 0) {
                     IEStatement var25 = (IEStatement)var22.get(var23);
                     if ((var25.getFlags() & 2) == 0 && (this.getMasterOptimizerSafe().getMode().isUnfriendly() || !(var25 instanceof IEReturn))) {
                        IEGeneric var26 = var24.asAssign().getSrcOperand();
                        var25.replaceUsedVar(this.ectx.getVariableById(var20), var26);
                        var22.set(var23 - 1, this.ectx.createNop(var24));
                        var18++;
                        var1.notifyInstructionUpdate(var22.getAddressOfInstruction(var23));
                        var1.notifyInstructionUpdate(var12);
                     }
                  }
               }
            }
         }

         if (var18 == 0) {
            return var17;
         }

         var17 += var18;
      }
   }
}
