package com.pnfsoftware.jeb.core.units.code.java;

public abstract class AbstractJBlockOptimizer extends AbstractJOptimizer {
   public AbstractJBlockOptimizer() {
   }

   public AbstractJBlockOptimizer(JOptimizerType var1) {
      super(var1);
   }

   public AbstractJBlockOptimizer(JOptimizerType var1, String var2) {
      super(var1, var2);
   }

   @Override
   public int perform() {
      return this.m == null ? 0 : this.performInternal(this.m.getBody(), null);
   }

   protected int performInternal(IJavaBlock var1, IJavaElement var2) {
      int var3 = 0;

      for (int var4 = 0; var4 < var1.size(); var4++) {
         IJavaStatement var5 = var1.get(var4);
         if (var5 instanceof IJavaCompound) {
            for (IJavaBlock var7 : ((IJavaCompound)var5).getBlocks()) {
               var3 += this.performInternal(var7, var1);
            }
         }
      }

      return var3 + this.optimizeBlock(var1, var2);
   }

   public abstract int optimizeBlock(IJavaBlock var1, IJavaElement var2);
}
