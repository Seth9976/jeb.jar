package com.pnfsoftware.jebglobal;

import com.google.common.collect.BoundType;
import com.google.common.collect.Range;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COperatorType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGoto;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCBlockOptimizer;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class aif extends AbstractCBlockOptimizer {
   private static final StructuredLogger pC = aco.pC(aif.class);

   @Override
   protected int optimizeBlock(ICBlock var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var1.size(); var3++) {
         if (var1.get(var3) instanceof ICIfStm var5) {
            for (int var6 = 1; var6 < var5.sizeWithoutDefault(); var6++) {
               ICPredicate var7 = var5.getBranchPredicate(var6);

               for (int var8 = 0; var8 < var6; var8++) {
                  if (add.pC((ICExpression)var5.getBranchPredicate(var8), (ICExpression)var7)) {
                     Object[] var10000 = new Object[]{var5.getBranchPredicate(var8)};
                     var5.setBranchPredicate(var6, this.ef.createPredicate(this.cf.createInt32(0)));
                     var6--;
                     var2++;
                     break;
                  }
               }
            }

            boolean var14;
            do {
               var14 = false;

               for (int var15 = 0; var15 < var5.sizeWithoutDefault() - 1; var15++) {
                  ICPredicate var18 = var5.getBranchPredicate(var15);
                  ICPredicate var9 = var5.getBranchPredicate(var15 + 1);
                  if (CUtil.hasNoSideEffects(var18) && CUtil.hasNoSideEffects(var9)) {
                     ICPredicate var10 = this.pC(var5, var9, var15 + 1);
                     if (var10 != null) {
                        Object[] var24 = new Object[]{var10};
                        var2++;
                        var9 = var10;
                     }

                     Integer var11 = this.pC(var18, var9);
                     if (var11 != null && var11 > 0) {
                        var5.setBranchPredicate(var15, var9);
                        var5.setBranchPredicate(var15 + 1, var18);
                        ICBlock var12 = var5.getBranchBody(var15);
                        var5.setBranchBody(var15, var5.getBranchBody(var15 + 1));
                        var5.setBranchBody(var15 + 1, var12);
                        Object[] var25 = new Object[]{var18, var9};
                        var2++;
                        var14 = true;
                        var10 = this.pC(var5, var5.getBranchPredicate(var15 + 1), var15 + 1);
                        if (var10 != null) {
                           var5.setBranchPredicate(var15 + 1, this.ef.createPredicate(var10));
                        }
                     }

                     var9 = var5.getBranchPredicate(var15 + 1);
                     ArrayList var23 = new ArrayList();

                     for (int var13 = 0; var13 < var15 + 1; var13++) {
                        var23.add(CUtil.notLDeepReplace(this.m, var5.getBranchPredicate(var13).getExpression()));
                     }

                     var9.visitDepthPost(new aig(this, var23));
                     var9.visitDepthPost(new aih(this));
                  }
               }

               if (!var14) {
                  ICPredicate var16 = this.pC(var5, null, var5.sizeWithoutDefault());
                  if (var16 != null) {
                     Object[] var26 = new Object[]{var16};
                     var2++;
                     var14 = true;
                  }
               }
            } while (var14);

            if (var5.hasDefaultBlock()) {
               ICBlock var17 = var5.getDefaultBlock();
               ICStatement var19 = var17.size() > 0 ? var17.get(0) : null;

               while (var5.size() > 1 && CUtil.hasNoSideEffects(var5.getBranchPredicate(var5.sizeWithoutDefault() - 1))) {
                  ICBlock var21 = var5.getBranchBody(var5.sizeWithoutDefault() - 1);
                  if (var5.getDefaultBlock().size() == 1 && var5.getDefaultBlock().equals(var21)) {
                     Object[] var29 = new Object[]{var5.getBranchPredicate(var5.sizeWithoutDefault() - 1)};
                     var5.removeBranch(var5.sizeWithoutDefault() - 1);
                     var2++;
                  } else if (var19 instanceof ICLabel && var21.size() == 1 && var21.get(0) instanceof ICGoto && ((ICGoto)var21.get(0)).getLabel() == var19) {
                     Object[] var28 = new Object[]{var5.getBranchPredicate(var5.sizeWithoutDefault() - 1)};
                     var5.removeBranch(var5.sizeWithoutDefault() - 1);
                     var2++;
                  } else {
                     if (!(var19 instanceof ICGoto) || !(var21.get(0) instanceof ICLabel) || ((ICGoto)var19).getLabel() != var21.get(0)) {
                        break;
                     }

                     Object[] var27 = new Object[]{var5.getBranchPredicate(var5.sizeWithoutDefault() - 1)};
                     var5.setDefaultBlock(var5.getBranchBody(var5.sizeWithoutDefault() - 1));
                     var5.removeBranch(var5.sizeWithoutDefault() - 1);
                     var2++;
                  }
               }

               if (var5.size() == 1) {
                  var1.remove(var3);
                  var1.insertAll(var3, var5.getDefaultBlock());
                  var3--;
               }
            }
         }
      }

      return var2;
   }

   private ICPredicate pC(ICIfStm var1, ICPredicate var2, Integer var3) {
      if (var2 == null) {
         if (!var1.hasDefaultBlock() || CUtil.isOperation(var1.getBranchPredicate(var1.sizeWithoutDefault() - 1).getExpression(), COperatorType.EQ)) {
            return null;
         }

         Object var9 = CUtil.notLDeepReplace(this.m, var1.getBranchPredicate(0).getExpression().duplicate());

         for (int var11 = 1; var11 < var3; var11++) {
            var9 = CUtil.andL(this.m, (ICExpression)var9, CUtil.notLDeepReplace(this.m, var1.getBranchPredicate(var11).getExpression().duplicate()));
         }

         if (var9 instanceof ICOperation) {
            Object var12;
            if (CUtil.isOperation((ICExpression)var9, COperatorType.EQ)) {
               var12 = var9;
            } else {
               var12 = aid.pC((ICOperation)var9, this.m, this.cf);
            }

            if (var12 != null && CUtil.isOperation((ICExpression)var12, COperatorType.EQ)) {
               var2 = this.ef.createPredicate((ICExpression)var12);
               ICBlock var6 = var1.getDefaultBlock();
               var1.setDefaultBlock(var1.getBranchBody(var1.sizeWithoutDefault() - 1));
               var1.removeBranch(var1.sizeWithoutDefault() - 1);
               var1.addBranch(var2, var6);
               return var2;
            }
         }
      } else if (!CUtil.isOperation(var2.getExpression(), COperatorType.EQ)) {
         ICOperation var4 = CUtil.andL(this.m, var2.getExpression(), CUtil.notLDeepReplace(this.m, var1.getBranchPredicate(0).getExpression().duplicate()));

         for (int var5 = 1; var5 < var3; var5++) {
            var4 = CUtil.andL(this.m, var4, CUtil.notLDeepReplace(this.m, var1.getBranchPredicate(var5).getExpression().duplicate()));
         }

         ICExpression var10 = aid.pC(var4, this.m, this.cf);
         if (var10 != null
            && (CUtil.countAllSubElements(var10) < CUtil.countAllSubElements(var2.getExpression()) || CUtil.isOperation(var10, COperatorType.EQ))) {
            var2 = this.ef.createPredicate(var10);
            var1.setBranchPredicate(var3, var2);
            return var2;
         }
      }

      return null;
   }

   public Integer pC(ICPredicate var1, ICPredicate var2) {
      if (var1.getExpression() instanceof ICOperation && var2.getExpression() instanceof ICOperation) {
         ICOperation var3 = (ICOperation)var1.getExpression();
         ICOperation var4 = (ICOperation)var2.getExpression();
         add.Sv var5 = add.Sv.pC(var3);
         if (var5 == null) {
            return null;
         } else {
            add.Sv var6 = add.Sv.pC(var4);
            if (var6 == null) {
               return null;
            } else if (var5.kS() == var6.kS() && !var5.A(var6)) {
               aif.Av var7 = new aif.Av(var5);
               aif.Av var8 = new aif.Av(var6);
               if (var7.A) {
                  return 1;
               } else if (var8.A) {
                  return -1;
               } else if (var7.kS.isEmpty()) {
                  return var8.kS.isEmpty() ? 0 : -1;
               } else if (var8.kS.isEmpty()) {
                  return 1;
               } else if (var7.kS.size() == var7.pC.size() * 2) {
                  if (var8.kS.size() == var8.pC.size() * 2) {
                     return var7.pC.size() != var8.pC.size()
                        ? var7.pC.size() - var8.pC.size()
                        : ((BigInteger)var7.pC.get(0)).compareTo((BigInteger)var8.pC.get(0));
                  } else {
                     return -1;
                  }
               } else if (var8.kS.size() == var8.pC.size() * 2) {
                  return 1;
               } else {
                  BigInteger var9 = (BigInteger)var7.kS.get(0);
                  BigInteger var10 = (BigInteger)var8.kS.get(0);
                  if (var9 == null) {
                     return var10 == null ? ((BigInteger)var7.kS.get(1)).compareTo((BigInteger)var8.kS.get(1)) : -1;
                  } else {
                     return var10 == null ? 1 : var9.compareTo(var10);
                  }
               }
            } else {
               return null;
            }
         }
      } else {
         return null;
      }
   }

   private static class Av {
      private Set wS;
      List pC = new ArrayList();
      boolean A;
      List kS = new ArrayList();

      public Av(add.Sv var1) {
         this.wS = var1.UT().asRanges();
         if (add.pC(this.wS)) {
            this.A = true;
         } else {
            for (Range var3 : this.wS) {
               if (add.pC(var3)) {
                  this.pC
                     .add(var3.lowerBoundType() == BoundType.CLOSED ? (BigInteger)var3.lowerEndpoint() : ((BigInteger)var3.lowerEndpoint()).add(BigInteger.ONE));
               }

               this.kS
                  .add(
                     var3.hasLowerBound()
                        ? (
                           var3.lowerBoundType() == BoundType.CLOSED
                              ? (BigInteger)var3.lowerEndpoint()
                              : ((BigInteger)var3.lowerEndpoint()).add(BigInteger.ONE)
                        )
                        : null
                  );
               this.kS
                  .add(
                     var3.hasUpperBound()
                        ? (
                           var3.upperBoundType() == BoundType.CLOSED
                              ? (BigInteger)var3.upperEndpoint()
                              : ((BigInteger)var3.upperEndpoint()).subtract(BigInteger.ONE)
                        )
                        : null
                  );
            }
         }
      }
   }
}
