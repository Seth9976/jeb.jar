package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.java.IJVisitor;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;

class bqn implements IJVisitor {
   bqn(bqm.CU var1, bqm.eo var2) {
      this.RF = var1;
      this.q = var2;
   }

   public void q(IJavaElement var1, IJavaElement var2, IVisitResults var3) {
      if (var1 == this.q.Dw) {
         var3.interrupt(false);
      }
   }
}
