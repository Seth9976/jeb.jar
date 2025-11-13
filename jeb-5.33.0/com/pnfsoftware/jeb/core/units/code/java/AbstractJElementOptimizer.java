package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.client.S;

public abstract class AbstractJElementOptimizer extends AbstractJOptimizer {
   boolean innerReplacement = false;

   public AbstractJElementOptimizer() {
   }

   public AbstractJElementOptimizer(JOptimizerType var1) {
      super(var1);
   }

   public AbstractJElementOptimizer(JOptimizerType var1, String var2) {
      super(var1, var2);
   }

   @Override
   public int perform() {
      return this.m == null ? 0 : this.optimizeSubElements(this.m);
   }

   private int optimizeSubElements(IJavaElement var1) {
      if (JUtil.isClassMethodField(var1)) {
         return 0;
      } else {
         int var2 = 0;

         for (IJavaElement var4 : var1.getSubElements()) {
            var2 += this.optimizeSubElements(var4);
            IJavaElement var5 = this.optimizeElement(var4, var1);
            if (var5 != null) {
               if (!this.innerReplacement && !var1.replaceSubElement(var4, var5)) {
                  logger.error(S.L("Error when replacing %s by %s"), var4, var5);
               } else {
                  var2++;
               }
            }
         }

         return var2;
      }
   }

   protected abstract IJavaElement optimizeElement(IJavaElement var1, IJavaElement var2);
}
