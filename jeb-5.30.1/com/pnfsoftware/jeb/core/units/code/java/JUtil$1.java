package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import java.util.List;

class JUtil$1 implements IJVisitor {
   JUtil$1(List var1) {
      this.val$idents = var1;
   }

   public void process(IJavaElement var1, IJavaElement var2, IVisitResults var3) {
      if (var1 instanceof IJavaIdentifier) {
         this.val$idents.add((IJavaIdentifier)var1);
      }
   }
}
