package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.java.AbstractJOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJVisitor;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDefinition;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class bmr extends AbstractJOptimizer {
   @Override
   public int perform() {
      if (this.m == null) {
         return 0;
      } else {
         bmr.Av var1 = new bmr.Av();
         this.m.visitDepthPre(var1);
         int var2 = 0;

         for (IJavaDefinition var4 : var1.pC.keySet()) {
            IJavaIdentifier var5 = var4.getIdentifier();
            if (!var1.A.contains(var5)) {
               IJavaElement var6 = (IJavaElement)var1.pC.get(var4);
               if (var6 instanceof IJavaBlock var7 && var7.remove(var4)) {
                  var2++;
               }
            }
         }

         return var2;
      }
   }

   private class Av implements IJVisitor {
      Map pC = new HashMap();
      Set A = new HashSet();

      public void pC(IJavaElement var1, IJavaElement var2, IVisitResults var3) {
         if (var1 instanceof IJavaDefinition var4) {
            this.pC.put(var4, var2);
            var3.skipChildren();
         } else if (var1 instanceof IJavaIdentifier var5) {
            this.A.add(var5);
         }
      }
   }
}
