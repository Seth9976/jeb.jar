package com.pnfsoftware.jeb.core.units.code.java;

public abstract class AbstractJStatementOptimizer extends AbstractJOptimizer {
   public AbstractJStatementOptimizer() {
   }

   public AbstractJStatementOptimizer(JOptimizerType var1) {
      super(var1);
   }

   public AbstractJStatementOptimizer(JOptimizerType var1, String var2) {
      super(var1, var2);
   }

   @Override
   public int perform() {
      return this.m == null ? 0 : this.performInternal(this.m.getBody());
   }

   protected int performInternal(IJavaBlock var1) {
      int var2 = 0;
      int var3 = 0;

      while (var3 < var1.size()) {
         IJavaStatement var4 = var1.get(var3);
         int var5 = this.optimizeStatement(var4);
         if (var5 > 0) {
            var2 += var5;
         } else {
            if (var4 instanceof IJavaCompound) {
               for (IJavaBlock var7 : ((IJavaCompound)var4).getBlocks()) {
                  var2 += this.performInternal(var7);
               }
            }

            var3++;
         }
      }

      return var2;
   }

   public abstract int optimizeStatement(IJavaStatement var1);
}
