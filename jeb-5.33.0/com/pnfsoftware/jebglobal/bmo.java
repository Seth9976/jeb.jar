package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.java.IJVisitor;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;

class bmo implements IJVisitor {
   bmo(bmn.Sv var1, bmn.Av var2) {
      this.A = var1;
      this.pC = var2;
   }

   public void pC(IJavaElement var1, IJavaElement var2, IVisitResults var3) {
      if (var1 == this.pC.wS) {
         var3.interrupt(false);
      }
   }
}
