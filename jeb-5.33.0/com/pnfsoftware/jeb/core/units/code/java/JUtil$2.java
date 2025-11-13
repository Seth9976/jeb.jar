package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;

class JUtil$2 implements IJVisitor {
   public void process(IJavaElement var1, IJavaElement var2, IVisitResults var3) {
      if (var1.canCauseException()) {
         var3.interrupt(false);
      }
   }
}
