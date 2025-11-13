package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFGUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJump;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEReturn;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import java.util.ArrayDeque;
import java.util.Arrays;

public class arh extends AbstractEOptimizer {
   boolean q = true;
   boolean RF = false;
   boolean xK = true;
   boolean Dw = false;

   public arh() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
   }

   @Override
   protected int perform() {
      int var1 = 0;

      for (int var2 = 1; var2 < this.cfg.size() - 1; var2++) {
         BasicBlock var3 = this.cfg.get(var2);
         if (var3.allinsize() == 1 && var3.size() == 1 && ((IEStatement)var3.get(0)).isAssign()) {
            IEStatement var4 = (IEStatement)var3.get(0);
            BasicBlock var5 = this.cfg.get(var2 - 1);
            IEStatement var6;
            IEStatement var7;
            if (var5.size() >= 2
               && (var6 = (IEStatement)var5.get(-1)).isConditionalJump()
               && (var7 = (IEStatement)var5.get(-2)).isAssign()
               && var7.asAssign().getDstOperand().isVar()
               && !EUtil.hasSideEffect(var7.asAssign().getSrcOperand())) {
               BasicBlock var8 = this.cfg.get(var2 + 1);
               if (var8.allinsize() == 2 && (int)var8.getBase() == var6.asJump().getBranchAddress()) {
                  IEGeneric var9 = var4.asAssign().getDstOperand();
                  if (var9.equals(var7.asAssign().getDstOperand())) {
                     IEVar var10 = var9.asVar();
                     Object[] var10000 = new Object[]{var10};
                     IEGeneric var11 = var4.asAssign().getSrcOperand();
                     if (!var11.isCond()) {
                        IEGeneric var12 = var7.asAssign().getSrcOperand();
                        if (!var12.isCond()) {
                           IEGeneric var13 = var6.asJump().getCondition();
                           if (!EUtil.hasExplicitlyUsedVar(var13, var10)
                              && !EUtil.hasExplicitlyUsedVar(var12, var10)
                              && !EUtil.hasExplicitlyUsedVar(var11, var10)) {
                              IECond var14;
                              if (this.q) {
                                 var14 = this.ectx.createCond(EUtil.notL(var13.duplicate()), var11.duplicate(), var12.duplicate());
                              } else {
                                 var14 = this.ectx.createCond(var13.duplicate(), var12.duplicate(), var11.duplicate());
                              }

                              if (var11.getType() != null && var11.getType().equals(var12.getType())) {
                                 var14.setType(var11.getType());
                              }

                              IEAssign var15 = this.ectx.createAssign(var10.duplicate(), var14);
                              this.cfg.deleteEdge(var5, var3);
                              this.cfg.deleteEdge(var5, var8);
                              this.cfg.addEdge(var5, var8);
                              var15.copyProperties(var6);
                              var5.set(var5.size() - 1, var15);
                              amw.q(this.cfg);
                              var1++;
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      for (int var16 = 1; var16 < this.cfg.size() - 2; var16++) {
         BasicBlock var18 = this.cfg.get(var16);
         if (var18.allinsize() == 1 && var18.size() == 2 && ((IEStatement)var18.get(0)).isAssign() && ((IEStatement)var18.get(1)).isUnconditionalJump()) {
            IEJump var20 = ((IEStatement)var18.get(1)).asJump();
            BasicBlock var22 = this.cfg.get(var16 - 1);
            if (((IEStatement)var22.getLast()).isConditionalJump()) {
               IEJump var24 = ((IEStatement)var22.getLast()).asJump();
               BasicBlock var26 = this.cfg.get(var16 + 1);
               if (var26.allinsize() == 1
                  && (int)var26.getBase() == var24.getBranchAddress()
                  && var26.size() == 1
                  && ((IEStatement)var26.get(0)).isAssign()
                  && var20.getBranchAddress() == (int)var26.getEndAddress()) {
                  IEGeneric var28 = ((IEStatement)var18.get(0)).asAssign().getLeftOperand();
                  if (var28 instanceof IEVar && var28.equals(((IEStatement)var26.get(0)).asAssign().getDstOperand())) {
                     IEVar var30 = (IEVar)var28;
                     Object[] var43 = new Object[]{var30};
                     IEGeneric var32 = ((IEStatement)var18.get(0)).asAssign().getSrcOperand();
                     if (!var32.isCond()) {
                        IEGeneric var34 = ((IEStatement)var26.get(0)).asAssign().getSrcOperand();
                        if (!var34.isCond()) {
                           IEGeneric var36 = var24.getCondition();
                           IECond var38;
                           if (this.RF) {
                              var38 = this.ectx.createCond(EUtil.notL(var36.duplicate()), var32.duplicate(), var34.duplicate());
                           } else {
                              var38 = this.ectx.createCond(var36.duplicate(), var34.duplicate(), var32.duplicate());
                           }

                           if (var32.getType() != null && var32.getType().equals(var34.getType())) {
                              var38.setType(var32.getType());
                           }

                           IEAssign var40 = this.ectx.createAssign(var30.duplicate(), var38);
                           BasicBlock var42 = this.cfg.get(var16 + 2);
                           this.cfg.deleteEdge(var22, var18);
                           this.cfg.deleteEdge(var22, var26);
                           this.cfg.addEdge(var22, var42);
                           var40.copyProperties(var24);
                           var40.setSize(((IEStatement)var22.getLast()).getSize());
                           var22.set(var22.size() - 1, var40);
                           CFGUtil.removeUnreachableBlocks(this.cfg, new ArrayDeque(Arrays.asList(var18, var26)));
                           amw.q(this.cfg);
                           var1++;
                        }
                     }
                  }
               }
            }
         }
      }

      if (this.Dw) {
         for (int var17 = 0; var17 < this.cfg.size() - 1; var17++) {
            BasicBlock var19 = this.cfg.get(var17);
            IEStatement var21 = (IEStatement)var19.getLast();
            if (var21.isConditionalJump()) {
               IEJump var23 = var21.asJump();
               BasicBlock var25 = this.cfg.get(var17 + 1);
               BasicBlock var27 = this.cfg.getBlockAt((long)var23.getBranchAddress());
               if (var25.size() == 1 && var27.size() == 1) {
                  IEStatement var29 = (IEStatement)var25.get(0);
                  IEStatement var31 = (IEStatement)var27.get(0);
                  if (var29.isReturn() && var31.isReturn() && var29.asReturn().getValues().size() == 1 && var31.asReturn().getValues().size() == 1) {
                     IEGeneric var33 = var29.asReturn().getValue();
                     if (!var33.isCond()) {
                        IEGeneric var35 = var31.asReturn().getValue();
                        if (!var35.isCond() && var35.getBitsize() == var33.getBitsize()) {
                           IEGeneric var37 = var23.getCondition();
                           IECond var39;
                           if (this.xK) {
                              var39 = this.ectx.createCond(EUtil.notL(var37.duplicate()), var33.duplicate(), var35.duplicate());
                           } else {
                              var39 = this.ectx.createCond(var37.duplicate(), var35.duplicate(), var33.duplicate());
                           }

                           if (var33.getType() != null && var33.getType().equals(var35.getType())) {
                              var39.setType(var33.getType());
                           }

                           IEReturn var41 = this.ectx.createReturn(var39);
                           this.cfg.deleteEdge(var19, var25);
                           this.cfg.deleteEdge(var19, var27);
                           var41.copyProperties(var23);
                           var41.setSize(var21.getSize());
                           var19.set(var19.size() - 1, var41);
                           amw.q(this.cfg);
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
}
