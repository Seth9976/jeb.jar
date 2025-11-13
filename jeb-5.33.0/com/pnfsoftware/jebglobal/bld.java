package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.java.IJVisitor;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDefinition;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;

class bld implements IJVisitor {
   bld(blc var1, IJavaDefinition var2) {
      this.A = var1;
      this.pC = var2;
   }

   public void pC(IJavaElement var1, IJavaElement var2, IVisitResults var3) {
      if (var1 == this.pC.getIdentifier()) {
         var3.interrupt(false);
      }
   }
}
