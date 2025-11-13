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

public class brb extends AbstractJStatementOptimizer {
   private int q;

   @Override
   public int optimizeStatement(IJavaStatement var1) {
      this.q = 0;
      IdentityHashSet var2 = new IdentityHashSet();

      for (IJavaElement var4 : var1.getSubElements()) {
         this.q(var4, var1, var2);
      }

      return this.q;
   }

   private void q(IJavaElement var1, IJavaElement var2, IdentityHashSet var3) {
      if (!var3.contains(var1)) {
         var3.add(var1);
         if (!JUtil.isClassMethodField((IJavaElement)var1)) {
            if (var1 instanceof IJavaPredicate && !this.q(var2)) {
               IJavaExpression var4 = ((IJavaPredicate)var1).getExpression();
               if (var2.replaceSubElement((IJavaElement)var1, var4)) {
                  var1 = var4;
                  this.q++;
               }
            }

            for (IJavaElement var5 : ((IJavaElement)var1).getSubElements()) {
               this.q(var5, (IJavaElement)var1, var3);
            }
         }
      }
   }

   private boolean q(IJavaElement var1) {
      return var1 instanceof IJavaIf || var1 instanceof IJavaWhile || var1 instanceof IJavaDoWhile || var1 instanceof IJavaFor;
   }
}
