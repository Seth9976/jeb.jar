package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJStatementOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDoWhile;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaFor;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIf;
import com.pnfsoftware.jeb.core.units.code.java.IJavaPredicate;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaWhile;
import com.pnfsoftware.jeb.core.units.code.java.JUtil;
import com.pnfsoftware.jeb.util.collect.IdentityHashSet;

public class bna extends AbstractJStatementOptimizer {
   private int pC;

   @Override
   public int optimizeStatement(IJavaStatement var1) {
      this.pC = 0;
      IdentityHashSet var2 = new IdentityHashSet();

      for (IJavaElement var4 : var1.getSubElements()) {
         this.pC(var4, var1, var2);
      }

      return this.pC;
   }

   private void pC(IJavaElement var1, IJavaElement var2, IdentityHashSet var3) {
      if (!var3.contains(var1)) {
         var3.add(var1);
         if (!JUtil.isClassMethodField((IJavaElement)var1)) {
            if (var1 instanceof IJavaPredicate && !this.pC(var2)) {
               IJavaExpression var4 = ((IJavaPredicate)var1).getExpression();
               if (var2.replaceSubElement((IJavaElement)var1, var4)) {
                  var1 = var4;
                  this.pC++;
               }
            }

            for (IJavaElement var5 : ((IJavaElement)var1).getSubElements()) {
               this.pC(var5, (IJavaElement)var1, var3);
            }
         }
      }
   }

   private boolean pC(IJavaElement var1) {
      return var1 instanceof IJavaIf || var1 instanceof IJavaWhile || var1 instanceof IJavaDoWhile || var1 instanceof IJavaFor;
   }
}
