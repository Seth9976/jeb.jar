package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.DecompilerException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.EExpressionGenerator;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.EExpressionMatcher;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.SubstitutionDefinition;

public abstract class AbstractEExpressionOptimizer extends AbstractEOptimizer {
   protected boolean skipStatementProcessing = true;
   protected boolean skipLeftSideOfAssignment = false;
   protected IEStatement currentStatement = null;

   public AbstractEExpressionOptimizer(DataChainsUpdatePolicy var1) {
      super(var1);
   }

   @Override
   protected int perform() {
      boolean[] var1 = new boolean[]{false};
      int var2 = 0;

      for (BasicBlock var4 : this.cfg.getBlocks()) {
         for (int var5 = 0; var5 < var4.size(); var5++) {
            IEStatement var6 = (IEStatement)var4.get(var5);
            this.currentStatement = var6;

            int var7;
            do {
               var7 = 0;
               if (!this.skipStatementProcessing) {
                  AbstractEExpressionOptimizer.EOR var8 = this.optimizeExpression(var6);
                  if (var8 != null) {
                     var8.safeCopyType(var6);
                     IEGeneric var9 = var8.getExpression();
                     if (!(var9 instanceof IEStatement)) {
                        throw new DecompilerException("Expected a statement IRE");
                     }

                     var6 = (IEStatement)var8.getExpression();
                     var4.set(var5, var6);
                     if (var8.getUpdateDFA()) {
                        var1[0] = true;
                     }

                     var7++;
                  }
               }

               if (var7 == 0) {
                  if (this.skipLeftSideOfAssignment && var6 instanceof IEAssign var12) {
                     var7 += this.optimize(var12, var12.getSrcOperand(), var1, null);
                     if (var12.getDstOperand() instanceof IEMem) {
                        IEMem var14 = (IEMem)var12.getDstOperand();
                        var7 += this.optimize(var12.getDstOperand(), var14.getReference(), var1, null);
                     }
                  } else {
                     for (IEGeneric var10 : EUtil.getSubExpressions(var6)) {
                        var7 += this.optimize(var6, var10, var1, null);
                     }
                  }
               }

               var2 += var7;
            } while (var7 > 0);
         }
      }

      this.currentStatement = null;
      return this.postPerform(var2, var1[0]);
   }

   @Override
   public IEGeneric performOnExpression(IEGeneric var1, IERoutineContext var2) {
      this.ectx = var2;
      IEGeneric[] var3 = new IEGeneric[1];
      int var4 = this.optimize(null, var1, null, var3);
      if (var4 <= 0) {
         return null;
      } else {
         return var3[0] != null ? var3[0] : var1;
      }
   }

   private int optimize(IEGeneric var1, IEGeneric var2, boolean[] var3, IEGeneric[] var4) {
      AbstractEExpressionOptimizer.EOR var5 = this.optimizeExpression(var2);
      if (var5 != null) {
         var5.safeCopyType(var2);
         if (var1 == null) {
            if (var4 != null) {
               var4[0] = var5.getExpression();
            }
         } else if (!var1.replaceSubExpression(var2, var5.getExpression())) {
            return 0;
         }

         if (var5.getUpdateDFA() && var3 != null) {
            var3[0] = true;
         }

         return 1;
      } else {
         int var6 = 0;

         for (IEGeneric var8 : EUtil.getSubExpressions(var2)) {
            var6 += this.optimize(var2, var8, var3, var4);
         }

         return var6;
      }
   }

   protected abstract AbstractEExpressionOptimizer.EOR optimizeExpression(IEGeneric var1);

   protected IEGeneric doSubstitution(IEGeneric var1, SubstitutionDefinition... var2) {
      for (SubstitutionDefinition var6 : var2) {
         EExpressionMatcher var7 = new EExpressionMatcher(var6.getPattern());
         if (var7.isMatch(var1)) {
            return new EExpressionGenerator(this.ectx, var6.getReplacement()).generate(var7.getMatchMap());
         }
      }

      return null;
   }

   public static class EOR {
      private IEGeneric e;
      private boolean updateDFA;

      public static AbstractEExpressionOptimizer.EOR create(IEGeneric var0, boolean var1) {
         return var0 == null ? null : new AbstractEExpressionOptimizer.EOR(var0, var1);
      }

      public static AbstractEExpressionOptimizer.EOR create(IEGeneric var0) {
         return create(var0, false);
      }

      private EOR(IEGeneric var1, boolean var2) {
         if (var1 == null) {
            throw new NullPointerException("An expression was expected");
         } else {
            this.e = var1;
            this.updateDFA = var2;
         }
      }

      public IEGeneric getExpression() {
         return this.e;
      }

      public boolean getUpdateDFA() {
         return this.updateDFA;
      }

      public void safeCopyType(IEGeneric var1) {
         if (var1.getType() != null && this.e.getType() == null) {
            if (this.e instanceof IEImm && !((IEImm)this.e).isMutable()) {
               this.e = ((IEImm)this.e).duplicateToMutable();
            }

            this.e.setType(var1.getType(), null);
         }
      }
   }
}
