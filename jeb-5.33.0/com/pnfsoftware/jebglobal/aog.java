package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.EvaluationException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EDefUseInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJump;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.OptimizerMode;
import com.pnfsoftware.jeb.util.collect.IdentityHashSet;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class aog extends AbstractEOptimizer {
   private Set pC = new LinkedHashSet();
   private Set A = new IdentityHashSet();

   public aog() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
      this.setRequiredModeThreshold(OptimizerMode.UNFRIENDLY);
   }

   @Override
   protected int perform() {
      int var1 = 0;
      var1 += this.pC(0);
      if (!this.pC.isEmpty()) {
         this.cfg = EUtil.expandStatementSizes(this.ectx, this.pC, 2);
         var1 += this.pC(1);
      }

      if (var1 > 0) {
         akt.pC(this.cfg);
      }

      return this.postPerform(var1);
   }

   private int pC(int var1) {
      Object[] var10000 = new Object[]{var1 + 1};
      int var2 = 0;

      for (int var3 = 0; var3 < this.cfg.size(); var3++) {
         BasicBlock var4 = this.cfg.get(var3);
         IEStatement var5 = (IEStatement)var4.getLast();
         if ((var1 != 1 || this.A.contains(var5)) && var5.isJump()) {
            IEJump var6 = var5.asJump();
            IEStatement var7;
            if (var4.size() >= 2) {
               var7 = (IEStatement)var4.get(var4.size() - 2);
            } else {
               if (var3 < 1) {
                  continue;
               }

               var7 = (IEStatement)this.cfg.get(var3 - 1).getLast();
            }

            if (var7 instanceof IEAssign) {
               IEGeneric var9 = var7.asAssign().getDstOperand();
               IEGeneric var10 = var7.asAssign().getSrcOperand();
               if (var9.isVar() && var10.isImm()) {
                  IEVar var11 = var9.asVar();
                  EState var12 = null;
                  HashSet var13 = null;
                  IEStatement var14 = var7;
                  int var15 = 0;

                  boolean var16;
                  for (var16 = false;
                     var14 != null && (var14.isAssign() || var14.isJump()) && var5.findByType(IEMem.class) == null;
                     var14 = (IEStatement)this.cfg.getInstruction(var12.getVirtualPC())
                  ) {
                     if (var14.isConditionalJump()) {
                        var16 = true;
                     }

                     EDefUseInfo var17 = new EDefUseInfo(0, this.ectx);
                     var14.getDefUse(var17);
                     if (pC(var17.getDefinedVarIds(), var11.getId()) || pC(var17.getUsedVarIds(), var11.getId())) {
                        break;
                     }

                     if (var12 == null) {
                        var12 = this.ectx.getGlobalContext().buildEmptyState();
                        var12.setRoutineContext(this.ectx);
                        var12.setVirtualPC((int)this.cfg.findInstruction(var14).getOffset());
                        var13 = new HashSet();
                     }

                     if (!var13.add(var12.getVirtualPC())) {
                        var14 = null;
                        break;
                     }

                     try {
                        var14.evaluate(var12);
                     } catch (EvaluationException var24) {
                        logger.catching(var24);
                        break;
                     }

                     var15++;
                  }

                  if (var15 >= 2 && var14 != null && var16) {
                     long var25 = this.cfg.findInstruction(var14).getOffset();
                     BasicBlock var19 = this.cfg.getBlockAt(var25);
                     if (var19 != null) {
                        IEImm var20 = var12.getValue(var11);
                        if (var6.getCondition() != null || var6.getBranchAddress() != var25 || !var10.equalsEx(var20, false)) {
                           var10000 = new Object[]{this.cfg.findInstruction(var7).getOffset(), var7, var5};
                           if (var4.size() >= 2) {
                              var10000 = new Object[0];
                              this.cfg.deleteOutEdges(var4);
                              var7.replaceSubExpression(var10, var20);
                              var6.setCondition(null);
                              var6.setBranchAddress((int)var25);
                              this.cfg.addEdge(var4, var19);
                           } else {
                              var10000 = new Object[0];
                              Long var21 = var7.getPrimaryLowerLevelAddress();
                              if (var21 == null) {
                                 var10000 = new Object[0];
                                 continue;
                              }

                              if (var7.getSize() < 2) {
                                 if (var1 == 0) {
                                    var10000 = new Object[0];
                                    this.pC.add(var7);
                                    this.A.add(var5);
                                 } else {
                                    var10000 = new Object[0];
                                 }
                                 continue;
                              }

                              var7.replaceSubExpression(var10, var20);
                              BasicBlock var22 = this.cfg.get(var3 - 1);
                              this.cfg.deleteOutEdges(var22);
                              IEJump var23 = this.ectx.createJump((int)var25);
                              var23.copyLowerLevelAddresses(var7);
                              var22.add(var23);
                              var7.adjustSize(-1);
                              this.cfg.addEdge(var22, var19);
                           }

                           var2++;
                           var10000 = new Object[0];
                        }
                     }
                  }
               }
            }
         }
      }

      return var2;
   }

   public static boolean pC(List var0, Integer var1) {
      return var0.contains(var1) ? var0.size() > 1 : var0.size() > 0;
   }
}
