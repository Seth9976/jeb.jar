package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CElementType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COperatorType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICAssignment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBreak;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConditionalStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantInteger32;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLeftExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICReturn;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICSwitchStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCElementOptimizer;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class aii extends AbstractCElementOptimizer {
   private static final StructuredLogger pC = aco.pC(aii.class);

   public aii() {
      super(true);
   }

   @Override
   protected ICElement optimizeElement(ICElement var1, ICElement var2) {
      if (!(var1 instanceof ICIfStm var3)) {
         return null;
      } else {
         int var4 = 0;

         for (int var5 = 0; var5 < var3.size(); var5++) {
            ICPredicate var6 = null;
            ICExpression var8 = null;
            boolean var9 = false;
            ICBlock var7;
            if (var3.hasDefaultBlock() && var5 == var3.size() - 1) {
               var9 = true;
               var7 = var3.getDefaultBlock();
            } else {
               var6 = var3.getBranchPredicate(var5);
               var7 = var3.getBranchBody(var5);
               if (!(var6.getExpression() instanceof ICOperation var10)) {
                  continue;
               }

               var8 = pC(var10);
            }

            if (var9 || var8 != null) {
               if (!var7.isEmpty() && var7.get(0) instanceof ICConditionalStatement var19) {
                  ICExpression var22 = pC(var19);
                  if (var22 != null && (var9 || var22 == var8)) {
                     ICBlock var23 = this.ef.createBlock();
                     if (var19.getDefaultBlock() != null) {
                        for (int var24 = 0; var24 < var19.getDefaultBlock().size(); var24++) {
                           var23.add(var19.getDefaultBlock().get(var24));
                        }
                     }

                     if (var19 instanceof ICSwitchStm && !var23.isEmpty() && var23.get(var23.size() - 1) instanceof ICBreak var27 && var27.getLabel() == null) {
                        var23.remove(var23.size() - 1);
                     }

                     if (var7.size() > 1) {
                        if (!A(var19)) {
                           continue;
                        }

                        for (int var26 = 1; var26 < var7.size(); var26++) {
                           var23.add(var7.get(var26));
                        }
                     }

                     if (this.pC(var3, var5, var6, var19, var23, var22)) {
                        Object[] var10000 = new Object[0];
                        var4++;
                        var5--;
                     }
                  }
               } else if (!var7.isEmpty() && var7.size() == 1) {
                  ICStatement var21 = var7.get(0);
                  Object var12 = null;
                  Object var13 = null;
                  ICExpression var14 = null;
                  if (var21 instanceof ICAssignment var15 && CUtil.isOperation(var15.getRight(), COperatorType.COND)) {
                     ICLeftExpression var30 = var15.getLeft();
                     ICOperation var18 = (ICOperation)var15.getRight();
                     var14 = var18.getFirstOperand();
                     var12 = this.ef.createAssignment(var30, var18.getSecondOperand());
                     var13 = this.ef.createAssignment(var30.duplicate(), var18.getThirdOperand());
                  } else if (var21 instanceof ICReturn var16 && CUtil.isOperation(var16.getExpression(), COperatorType.COND)) {
                     ICOperation var17 = (ICOperation)var16.getExpression();
                     var14 = var17.getFirstOperand();
                     var12 = this.ef.createReturn(var17.getSecondOperand());
                     var13 = this.ef.createReturn(var17.getThirdOperand());
                  }

                  if (var12 != null && var13 != null) {
                     ICExpression var28 = pC(var14);
                     if (var28 != null && (var9 || var28 == var8)) {
                        ((ICStatement)var12).addPhysicalOffsets(var21.getPhysicalOffsets());
                        ((ICStatement)var13).addPhysicalOffsets(var21.getPhysicalOffsets());
                        if (var9) {
                           var3.addBranch(this.ef.createPredicate(var14), this.ef.createBlock((ICStatement)var12));
                           var3.setDefaultBlock(this.ef.createBlock((ICStatement)var13));
                        } else {
                           var3.removeBranch(var5);
                           ICPredicate var29 = this.ef.createPredicate(CUtil.andL(this.m, var6.getExpression(), var14));
                           var3.insertBranch(var5, var29, this.ef.createBlock((ICStatement)var12));
                           var3.insertBranch(var5 + 1, var6.duplicate(), this.ef.createBlock((ICStatement)var13));
                        }

                        var4++;
                     }
                  }
               }
            }
         }

         return var4 > 0 ? var3 : null;
      }
   }

   private boolean pC(ICIfStm var1, int var2, ICPredicate var3, ICConditionalStatement var4, ICBlock var5, ICExpression var6) {
      boolean var7 = var3 == null;
      if (var4 instanceof ICIfStm var9) {
         if (!var7) {
            var1.removeBranch(var2);
         } else {
            var1.setDefaultBlock(null);
         }

         for (int var10 = 0; var10 < var4.sizeWithoutDefault(); var10++) {
            ICPredicate var11 = var9.getBranchPredicate(var10);
            if (!var7) {
               var11 = this.pC(var3, var11);
            }

            var1.insertBranch(var2 + var10, var11, var9.getBranchBody(var10));
         }
      } else {
         if (!(var4 instanceof ICSwitchStm var8)) {
            return false;
         }

         if (ail.pC(this.ef, var8)) {
            return false;
         }

         if (ail.pC(var8)) {
            return false;
         }

         if (!var7) {
            var1.removeBranch(var2);
         } else {
            var1.setDefaultBlock(null);
         }

         List var19 = var8.getBlocks();

         for (int var20 = 0; var20 < var19.size(); var20++) {
            ICBlock var12 = (ICBlock)var19.get(var20);
            List var13 = var8.getKeysForBlock(var12);
            if (!var12.isEmpty() && var12.getLast() instanceof ICBreak) {
               var12.removeLast();
            }

            if (var13 != null && !var13.isEmpty()) {
               ArrayList var14 = new ArrayList();

               for (BigInteger var16 : var13) {
                  if (var16.bitCount() > 31) {
                     return false;
                  }

                  ICConstantInteger32 var17 = this.cf.createInt32(var16.intValue());
                  ICPredicate var18 = this.ef.createPredicate(CUtil.eq(this.m, var6.duplicate(), var17));
                  if (!var7) {
                     var18 = this.pC(var3, var18);
                  }

                  var14.add(var18);
               }

               ICPredicate var21 = (ICPredicate)var14.remove(0);

               while (!var14.isEmpty()) {
                  var21 = this.ef.createPredicate(CUtil.orL(this.m, var21.getExpression(), ((ICPredicate)var14.remove(0)).getExpression()));
               }

               var1.insertBranch(var2 + var20, var21, var12);
            }
         }
      }

      if (var3 != null) {
         var1.insertBranch(var2 + var4.sizeWithoutDefault(), var3, var5);
      } else {
         if (var5.isEmpty()) {
            var5 = null;
         }

         var1.setDefaultBlock(var5);
      }

      Object[] var10000 = new Object[]{var3 == null ? "default" : var3};
      return true;
   }

   private ICPredicate pC(ICPredicate var1, ICPredicate var2) {
      ICOperation var3 = CUtil.andL(this.m, var1.getExpression().duplicate(), var2.getExpression());
      ICExpression var4 = aid.pC(var3, this.m, this.cf);
      return this.ef.createPredicate((ICExpression)(var4 != null ? var4 : var3));
   }

   static ICExpression pC(ICExpression var0) {
      return var0 instanceof ICOperation var1 ? pC(var1) : null;
   }

   private static ICExpression pC(ICOperation var0) {
      if (add.pC(var0)
         && var0.getFirstOperand().getElementType() == CElementType.Identifier
         && var0.getSecondOperand().getElementType() == CElementType.Constant) {
         return var0.getFirstOperand();
      } else {
         return CUtil.isOperation(var0, COperatorType.LOG_AND, COperatorType.LOG_OR, COperatorType.LOG_NOT, COperatorType.LOG_IDENT)
            ? pC(var0.getFirstOperand(), var0.getSecondOperand())
            : null;
      }
   }

   private static ICExpression pC(ICExpression var0, ICExpression var1) {
      if (var0 instanceof ICOperation var2 && var1 instanceof ICOperation var3) {
         ICExpression var4 = pC(var2);
         ICExpression var5 = pC(var3);
         return var4 == var5 ? var4 : null;
      } else {
         return null;
      }
   }

   static ICExpression pC(ICConditionalStatement var0) {
      if (var0 instanceof ICIfStm var1) {
         ICExpression var3 = null;

         for (int var4 = 0; var4 < var1.sizeWithoutDefault(); var4++) {
            ICExpression var5 = pC(var1.getBranchPredicate(var4).getExpression());
            if (var5 == null) {
               return null;
            }

            if (var3 == null) {
               var3 = var5;
            } else if (var3 != var5) {
               return null;
            }
         }

         return var3;
      } else {
         return var0 instanceof ICSwitchStm var2 ? var2.getSwitchedExpression() : null;
      }
   }

   static boolean A(ICConditionalStatement var0) {
      List var1 = var0.getConditionalBlocks();

      for (int var2 = 0; var2 < var0.sizeWithoutDefault(); var2++) {
         CUtil.BreakFlowResult var3 = CUtil.getBreakingFlowResult((ICStatement)var1.get(var2));
         if (var3.getStatus() != CUtil.BreakFlowStatus.TRUE || !var3.isLastInstruction()) {
            return false;
         }
      }

      return true;
   }
}
