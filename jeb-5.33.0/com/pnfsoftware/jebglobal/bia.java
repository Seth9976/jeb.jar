package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.java.IJVisitor;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;

class bia implements IJVisitor {
   bia(com.pnfsoftware.jeb.corei.parsers.dexdec.Ws var1) {
      this.pC = var1;
   }

   public void pC(IJavaElement var1, IJavaElement var2, IVisitResults var3) {
      bim var4 = (bim)var1;
      if (var4.d_() == null) {
         var4.pC(this.pC);
      }
   }
}
