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

public class bqq extends AbstractJOptimizer {
   @Override
   public int perform() {
      if (this.m == null) {
         return 0;
      } else {
         bqq.eo var1 = new bqq.eo();
         this.m.visitDepthPre(var1);
         int var2 = 0;

         for (IJavaDefinition var4 : var1.q.keySet()) {
            IJavaIdentifier var5 = var4.getIdentifier();
            if (!var1.RF.contains(var5)) {
               IJavaElement var6 = (IJavaElement)var1.q.get(var4);
               if (var6 instanceof IJavaBlock var7 && var7.remove(var4)) {
                  var2++;
               }
            }
         }

         return var2;
      }
   }

   private class eo implements IJVisitor {
      Map q = new HashMap();
      Set RF = new HashSet();

      public void q(IJavaElement var1, IJavaElement var2, IVisitResults var3) {
         if (var1 instanceof IJavaDefinition var4) {
            this.q.put(var4, var2);
            var3.skipChildren();
         } else if (var1 instanceof IJavaIdentifier var5) {
            this.RF.add(var5);
         }
      }
   }
}
