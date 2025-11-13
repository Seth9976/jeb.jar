package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.java.IJVisitor;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import java.util.Collection;

class blh implements IJVisitor {
   blh(ble var1, Collection var2, IJavaIdentifier var3) {
      this.kS = var1;
      this.pC = var2;
      this.A = var3;
   }

   public void pC(IJavaElement var1, IJavaElement var2, IVisitResults var3) {
      if (var1 instanceof IJavaStatement && this.pC != null && this.pC.contains(var1)) {
         var3.skipChildren();
      } else if (var1 == this.A) {
         var3.interrupt(false);
      }
   }
}
