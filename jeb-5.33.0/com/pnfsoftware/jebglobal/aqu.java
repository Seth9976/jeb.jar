package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.DUI;
import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.collect.CollectionUtil;
import java.util.ArrayList;
import java.util.List;

public class aqu extends AbstractEOptimizer {
   public aqu() {
      super(DataChainsUpdatePolicy.UPDATE_PERFORMED_INTERNALLY);
   }

   @Override
   protected int perform() {
      IDFA var1 = this.cfg.doDataFlowAnalysis();
      int var2 = 0;

      for (BasicBlock var4 : this.cfg) {
         label101:
         for (int var5 = 0; var5 < var4.size() - 1; var5++) {
            IEStatement var6 = (IEStatement)var4.get(var5);
            IEMem var7 = null;
            IEImm var8 = null;
            if (var6.isAssign()) {
               IEAssign var9 = var6.asAssign();
               if (var9.getDstOperand().isMem() && var9.getSrcOperand().isImm()) {
                  var7 = var9.getDstOperand().asMem();
                  var8 = var9.getSrcOperand().asImm();
               }
            }

            if (var7 != null) {
               List var18 = null;
               int var10 = var5 + 1;

               while (true) {
                  Couple var11;
                  label80:
                  for (var11 = null; var10 < var4.size(); var10++) {
                     IEStatement var12 = (IEStatement)var4.get(var10);
                     ArrayList var13 = new ArrayList();
                     var12.collectUsedExpressions(var13);

                     for (Couple var15 : var13) {
                        IEGeneric var16 = (IEGeneric)var15.getFirst();
                        IEGeneric var17 = (IEGeneric)var15.getSecond();
                        var11 = var17.find(var7, 0, 2, var16);
                        if (var11 != null) {
                           break label80;
                        }
                     }
                  }

                  if (var10 >= var4.size()) {
                     break;
                  }

                  if (var10 > var5 + 1) {
                     for (int var19 = var5 + 1; var19 < var10; var19++) {
                        IEStatement var21 = (IEStatement)var4.get(var19);
                        if (var21.writesMemory()) {
                           continue label101;
                        }
                     }

                     if (var18 == null) {
                        var18 = var7.getUsed().getVarIds();
                     }

                     for (int var20 = var5 + 1; var20 < var10; var20++) {
                        IEStatement var22 = (IEStatement)var4.get(var20);
                        DUI var23 = var1.getDUI(var4.getAddressOfInstruction(var20), var22);
                        if (CollectionUtil.hasIntersection(var18, var23.getDef())
                           || CollectionUtil.hasIntersection(var18, var23.getDefPot())
                           || CollectionUtil.hasIntersection(var18, var23.getSpoiled())) {
                           break;
                        }
                     }
                  }

                  if (((IEGeneric)var11.getFirst()).replaceSubExpression((IEGeneric)var11.getSecond(), var8.duplicateToMutable())) {
                     this.cfg.invalidateDataFlowAnalysis(var4.getAddressOfInstruction(var10));
                     var2++;
                  }
               }
            }
         }
      }

      return var2;
   }
}
