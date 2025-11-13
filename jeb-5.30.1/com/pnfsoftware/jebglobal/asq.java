package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.Collection;

public class asq extends AbstractEOptimizer {
   private static final StructuredLogger q = aeg.q(asq.class);
   private boolean RF;

   public asq() {
      super(DataChainsUpdatePolicy.UPDATE_PERFORMED_INTERNALLY);
   }

   public void q(boolean var1) {
      this.RF = var1;
   }

   public boolean q() {
      return this.RF;
   }

   @Override
   protected int perform() {
      int var1 = 0;

      while (true) {
         int var2 = 0;

         for (int var3 = 0; var3 < this.cfg.size(); var3++) {
            BasicBlock var4 = this.cfg.get(var3);

            label112:
            for (int var5 = 0; var5 < var4.size(); var5++) {
               IEStatement var6 = (IEStatement)var4.get(var5);
               IEVar var7 = null;
               IEVar var8 = null;
               if (var6 instanceof IEAssign var9 && var9.getSrcOperand() instanceof IEVar && var9.getDstOperand() instanceof IEVar) {
                  var7 = (IEVar)var9.getSrcOperand();
                  var8 = (IEVar)var9.getDstOperand();
               }

               if (var7 != null && var8 != null && (var6.getFlags() & 1) == 0) {
                  IDFA var25 = this.cfg.doDataFlowAnalysis();
                  long var10 = var4.getAddressOfInstruction(var5);
                  Collection var12 = var25.getDefUses(var10, var8.getId(), 2);
                  if (var12 != null && var12.size() == 1) {
                     long var13 = (Long)var12.iterator().next();
                     if (this.cfg.getBlockContaining(var13) != var4) {
                        IEStatement var15 = (IEStatement)this.cfg.getInstruction(var13);
                        if (var15 != null && (var15.getFlags() & 2) == 0) {
                           var12 = var25.getUseDefs(var10, var7.getId(), 2);
                           if (var12 != null && var12.size() == 1) {
                              long var16 = (Long)var12.iterator().next();
                              long var18 = var4.getAddressOfInstruction(var5);
                              Collection var20 = var25.getUseDefs(var13, var8.getId());
                              if (var20.size() >= 2) {
                                 for (long var22 : var20) {
                                    if (var22 != var18) {
                                       IEStatement var24 = (IEStatement)this.cfg.getInstruction(var22);
                                       if (!this.q(var24, var8, var7)
                                          || !var25.checkSingleUse(var22, var8.getId(), var13)
                                          || !var25.checkSingleDef(var22, var7.getId(), var16)) {
                                          continue label112;
                                       }
                                    }
                                 }

                                 if (this.RF) {
                                    var12 = var25.getDefUses(var16, var7.getId());
                                    if (var12 == null || !var12.equals(var20)) {
                                       continue;
                                    }
                                 }

                                 if (var25.checkSingleSource(var13, var7.getId(), var16)) {
                                    if (var15.replaceUsedVar(var8, var7) == 0) {
                                       return var1;
                                    }

                                    this.cfg.invalidateDataFlowAnalysis(var13);
                                    var2++;
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         if (var2 == 0) {
            return var1;
         }

         var1 += var2;
      }
   }

   private boolean q(IEStatement var1, IEVar var2, IEVar var3) {
      return var1 instanceof IEAssign && ((IEAssign)var1).getDstOperand() == var2 && ((IEAssign)var1).getSrcOperand() == var3 && (var1.getFlags() & 1) == 0;
   }
}
