package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IERange;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.IEMasterOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.OptimizerMode;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.primitives.Booleans;
import java.util.Collection;

public class asr extends AbstractEOptimizer {
   private static final StructuredLogger q = aeg.q(asr.class);
   private static final int RF = 12;
   private boolean xK;
   private boolean Dw;
   private boolean Uv;

   public asr() {
      super(DataChainsUpdatePolicy.UPDATE_PERFORMED_INTERNALLY);
      this.setRequiredModeThreshold(OptimizerMode.UNFRIENDLY);
   }

   public void q(boolean var1) {
      this.xK = var1;
   }

   public boolean q() {
      return this.xK;
   }

   public void RF(boolean var1) {
      this.Dw = var1;
   }

   public boolean RF() {
      return this.Dw;
   }

   public void xK(boolean var1) {
      this.Uv = var1;
   }

   public boolean xK() {
      return this.Uv;
   }

   @Override
   protected int perform() {
      if (this.cfg.size() >= 30) {
         return 0;
      } else {
         int var1 = 0;

         while (true) {
            int var2 = 0;

            for (int var3 = 0; var3 < this.cfg.size(); var3++) {
               BasicBlock var4 = this.cfg.get(var3);

               label157:
               for (int var5 = 0; var5 < var4.size(); var5++) {
                  IEStatement var6 = (IEStatement)var4.get(var5);
                  IEVar var7 = null;
                  IEVar var8 = null;
                  if (var6 instanceof IEAssign var9 && var9.getDstOperand() instanceof IEVar) {
                     var8 = (IEVar)var9.getDstOperand();
                     var7 = this.q(var9.getSrcOperand());
                  }

                  if (var7 != null && var8 != null && var7 != var8 && (var6.getFlags() & 1) == 0) {
                     IEGeneric var22 = ((IEAssign)var6).getSrcOperand();
                     IDFA var10 = this.cfg.doDataFlowAnalysis(false, 1);
                     Collection var11 = var10.getUseDefs(var4.getAddressOfInstruction(var5), var7.getId());
                     if (var11 != null && (!this.xK || var11.size() == 1)) {
                        long var12 = var4.getAddressOfInstruction(var5);
                        IEMasterOptimizer var14 = this.getMasterOptimizerSafe();
                        if ((
                              !this.Uv
                                 || !var10.getOutputMap(var8.getId()).contains(var12)
                                 || var14.canDiscardReachingOutDefinition(this.ectx, var12, var8.getId())
                           )
                           && var14.canDiscardUnusedDefinition(this.ectx, var12, var8.getId())) {
                           Collection var15 = var10.getDefUses(var12, var8.getId());
                           if (!var15.isEmpty()) {
                              for (long var17 : var15) {
                                 IEStatement var19 = (IEStatement)this.cfg.getInstruction(var17);
                                 if ((var19.getFlags() & 2) != 0) {
                                    continue label157;
                                 }
                              }

                              if (!(var22 instanceof IEVar)) {
                                 aqa var23 = new aqa(var8);
                                 int var28 = 0;

                                 for (long var33 : var15) {
                                    var28 += var23.q((IEGeneric)this.cfg.getInstruction(var33));
                                    if (var28 >= 2) {
                                       int var21 = EUtil.calculateComplexity(var22);
                                       if (var21 > 12) {
                                          continue label157;
                                       }
                                       break;
                                    }
                                 }
                              }

                              for (long var29 : var15) {
                                 if (!var10.checkSingleDef(var29, var8.getId(), var12)
                                    && EUtil.countVariablePresence((IEGeneric)this.cfg.getInstruction(var29), var8) != 0) {
                                    continue label157;
                                 }
                              }

                              if (var11.size() == 1) {
                                 long var25 = (Long)var11.iterator().next();

                                 for (long var34 : var15) {
                                    if (!var10.checkSingleSource(var34, var7.getId(), var25)) {
                                       continue label157;
                                    }
                                 }
                              } else {
                                 for (long var30 : var15) {
                                    if (!Booleans.toBoolean(var10.isVarReachingFromTo(var7.getId(), var12, var30))) {
                                       continue label157;
                                    }
                                 }
                              }

                              for (long var31 : var15) {
                                 IEStatement var35 = (IEStatement)this.cfg.getInstruction(var31);
                                 if (var35.replaceUsedVar(var8, var22) != 0) {
                                    var2++;
                                 }
                              }

                              this.cfg.invalidateDataFlowAnalysis();
                           }
                        }
                     }
                  }
               }
            }

            if (var2 == 0) {
               return this.postPerform(var1);
            }

            var1 += var2;
         }
      }
   }

   private IEVar q(IEGeneric var1) {
      if (var1 instanceof IEVar) {
         return (IEVar)var1;
      } else if (this.Dw) {
         return null;
      } else if (var1 instanceof IEImm) {
         return null;
      } else {
         asr.eo var2 = new asr.eo();
         var1.visitDepthPre(var2);
         return var2.q;
      }
   }

   private static class eo implements IEVisitor {
      IEVar q;

      public void q(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
         if (var1 instanceof IEVar) {
            if (this.q == null) {
               this.q = (IEVar)var1;
               return;
            }

            if (var1 == this.q) {
               return;
            }
         }

         if (!(var1 instanceof IEImm)
            && !(var1 instanceof IESlice)
            && !(var1 instanceof IERange)
            && !(var1 instanceof IECompose)
            && !(var1 instanceof IECond)
            && !(var1 instanceof IEOperation)) {
            this.q = null;
            var3.interrupt(false);
         }
      }
   }
}
