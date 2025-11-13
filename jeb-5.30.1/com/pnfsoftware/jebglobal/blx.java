package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.java.IJVisitor;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;

class blx implements IJVisitor {
   blx(com.pnfsoftware.jeb.corei.parsers.dexdec.ej var1) {
      this.q = var1;
   }

   public void q(IJavaElement var1, IJavaElement var2, IVisitResults var3) {
      bmj var4 = (bmj)var1;
      if (var4.d_() == null) {
         var4.q(this.q);
      }
   }
}
