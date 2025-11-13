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

public class akh extends AbstractCElementOptimizer {
   private static final StructuredLogger q = aeg.q(akh.class);

   public akh() {
      super(true);
   }

   @Override
   protected ICElement optimizeElement(ICElement var1, ICElement var2) {
      if (!(var1 instanceof ICIfStm)) {
         return null;
      } else {
         int var3 = 0;
         ICIfStm var4 = (ICIfStm)var1;

         for (int var5 = 0; var5 < var4.size(); var5++) {
            ICPredicate var6 = null;
            ICExpression var8 = null;
            boolean var9 = false;
            ICBlock var7;
            if (var4.hasDefaultBlock() && var5 == var4.size() - 1) {
               var9 = true;
               var7 = var4.getDefaultBlock();
            } else {
               var6 = var4.getBranchPredicate(var5);
               var7 = var4.getBranchBody(var5);
               if (!(var6.getExpression() instanceof ICOperation)) {
                  continue;
               }

               ICOperation var10 = (ICOperation)var6.getExpression();
               var8 = q(var10);
            }

            if (var9 || var8 != null) {
               if (!var7.isEmpty() && var7.get(0) instanceof ICConditionalStatement) {
                  ICConditionalStatement var17 = (ICConditionalStatement)var7.get(0);
                  ICExpression var18 = q(var17);
                  if (var18 != null && (var9 || var18 == var8)) {
                     ICBlock var19 = this.ef.createBlock();
                     if (var17.getDefaultBlock() != null) {
                        for (int var20 = 0; var20 < var17.getDefaultBlock().size(); var20++) {
                           var19.add(var17.getDefaultBlock().get(var20));
                        }
                     }

                     if (var17 instanceof ICSwitchStm && !var19.isEmpty()) {
                        ICStatement var21 = var19.get(var19.size() - 1);
                        if (var21 instanceof ICBreak && ((ICBreak)var21).getLabel() == null) {
                           var19.remove(var19.size() - 1);
                        }
                     }

                     if (var7.size() > 1) {
                        if (!RF(var17)) {
                           continue;
                        }

                        for (int var22 = 1; var22 < var7.size(); var22++) {
                           var19.add(var7.get(var22));
                        }
                     }

                     if (this.q(var4, var5, var6, var17, var19, var18)) {
                        Object[] var10000 = new Object[0];
                        var3++;
                        var5--;
                     }
                  }
               } else if (!var7.isEmpty() && var7.size() == 1) {
                  ICStatement var16 = var7.get(0);
                  Object var11 = null;
                  Object var12 = null;
                  ICExpression var13 = null;
                  if (var16 instanceof ICAssignment && CUtil.isOperation(((ICAssignment)var16).getRight(), COperatorType.COND)) {
                     ICLeftExpression var23 = ((ICAssignment)var16).getLeft();
                     ICOperation var15 = (ICOperation)((ICAssignment)var16).getRight();
                     var13 = var15.getFirstOperand();
                     var11 = this.ef.createAssignment(var23, var15.getSecondOperand());
                     var12 = this.ef.createAssignment(var23, var15.getThirdOperand());
                  } else if (var16 instanceof ICReturn && CUtil.isOperation(((ICReturn)var16).getExpression(), COperatorType.COND)) {
                     ICOperation var14 = (ICOperation)((ICReturn)var16).getExpression();
                     var13 = var14.getFirstOperand();
                     var11 = this.ef.createReturn(var14.getSecondOperand());
                     var12 = this.ef.createReturn(var14.getThirdOperand());
                  }

                  if (var11 != null && var12 != null) {
                     ICExpression var24 = q(var13);
                     if (var24 != null && (var9 || var24 == var8)) {
                        ((ICStatement)var11).addPhysicalOffsets(var16.getPhysicalOffsets());
                        ((ICStatement)var12).addPhysicalOffsets(var16.getPhysicalOffsets());
                        if (var9) {
                           var4.addBranch(this.ef.createPredicate(var13), this.ef.createBlock((ICStatement)var11));
                           var4.setDefaultBlock(this.ef.createBlock((ICStatement)var12));
                        } else {
                           var4.removeBranch(var5);
                           ICPredicate var25 = this.ef.createPredicate(CUtil.andL(this.m, var6.getExpression(), var13));
                           var4.insertBranch(var5, var25, this.ef.createBlock((ICStatement)var11));
                           var4.insertBranch(var5 + 1, var6.duplicate(), this.ef.createBlock((ICStatement)var12));
                        }

                        var3++;
                     }
                  }
               }
            }
         }

         return var3 > 0 ? var4 : null;
      }
   }

   private boolean q(ICIfStm var1, int var2, ICPredicate var3, ICConditionalStatement var4, ICBlock var5, ICExpression var6) {
      boolean var7 = var3 == null;
      if (var4 instanceof ICIfStm) {
         if (!var7) {
            var1.removeBranch(var2);
         } else {
            var1.setDefaultBlock(null);
         }

         ICIfStm var8 = (ICIfStm)var4;

         for (int var9 = 0; var9 < var4.sizeWithoutDefault(); var9++) {
            ICPredicate var10 = var8.getBranchPredicate(var9);
            if (!var7) {
               var10 = this.q(var3, var10);
            }

            var1.insertBranch(var2 + var9, var10, var8.getBranchBody(var9));
         }
      } else {
         if (!(var4 instanceof ICSwitchStm var18)) {
            return false;
         }

         if (akk.q(this.ef, var18)) {
            return false;
         }

         if (akk.q(var18)) {
            return false;
         }

         if (!var7) {
            var1.removeBranch(var2);
         } else {
            var1.setDefaultBlock(null);
         }

         List var19 = var18.getBlocks();

         for (int var20 = 0; var20 < var19.size(); var20++) {
            ICBlock var11 = (ICBlock)var19.get(var20);
            List var12 = var18.getKeysForBlock(var11);
            if (!var11.isEmpty() && var11.getLast() instanceof ICBreak) {
               var11.removeLast();
            }

            if (var12 != null && !var12.isEmpty()) {
               ArrayList var13 = new ArrayList();

               for (BigInteger var15 : var12) {
                  if (var15.bitCount() > 31) {
                     return false;
                  }

                  ICConstantInteger32 var16 = this.cf.createInt32(var15.intValue());
                  ICPredicate var17 = this.ef.createPredicate(CUtil.eq(this.m, var6.duplicate(), var16));
                  if (!var7) {
                     var17 = this.q(var3, var17);
                  }

                  var13.add(var17);
               }

               ICPredicate var21 = (ICPredicate)var13.remove(0);

               while (!var13.isEmpty()) {
                  var21 = this.ef.createPredicate(CUtil.orL(this.m, var21.getExpression(), ((ICPredicate)var13.remove(0)).getExpression()));
               }

               var1.insertBranch(var2 + var20, var21, var11);
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

   private ICPredicate q(ICPredicate var1, ICPredicate var2) {
      ICOperation var3 = CUtil.andL(this.m, var1.getExpression().duplicate(), var2.getExpression());
      ICExpression var4 = akc.q(var3, this.m, this.cf);
      return this.ef.createPredicate((ICExpression)(var4 != null ? var4 : var3));
   }

   static ICExpression q(ICExpression var0) {
      return !(var0 instanceof ICOperation) ? null : q((ICOperation)var0);
   }

   private static ICExpression q(ICOperation var0) {
      if (aew.q(var0)
         && var0.getFirstOperand().getElementType() == CElementType.Identifier
         && var0.getSecondOperand().getElementType() == CElementType.Constant) {
         return var0.getFirstOperand();
      } else {
         return CUtil.isOperation(var0, COperatorType.LOG_AND, COperatorType.LOG_OR, COperatorType.LOG_NOT, COperatorType.LOG_IDENT)
            ? q(var0.getFirstOperand(), var0.getSecondOperand())
            : null;
      }
   }

   private static ICExpression q(ICExpression var0, ICExpression var1) {
      if (var0 instanceof ICOperation && var1 instanceof ICOperation) {
         ICExpression var2 = q((ICOperation)var0);
         ICExpression var3 = q((ICOperation)var1);
         return var2 == var3 ? var2 : null;
      } else {
         return null;
      }
   }

   static ICExpression q(ICConditionalStatement var0) {
      if (var0 instanceof ICIfStm var1) {
         ICExpression var2 = null;

         for (int var3 = 0; var3 < var1.sizeWithoutDefault(); var3++) {
            ICExpression var4 = q(var1.getBranchPredicate(var3).getExpression());
            if (var4 == null) {
               return null;
            }

            if (var2 == null) {
               var2 = var4;
            } else if (var2 != var4) {
               return null;
            }
         }

         return var2;
      } else {
         return var0 instanceof ICSwitchStm ? ((ICSwitchStm)var0).getSwitchedExpression() : null;
      }
   }

   static boolean RF(ICConditionalStatement var0) {
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
