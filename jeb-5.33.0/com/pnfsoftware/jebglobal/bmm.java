package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.java.IJVisitor;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;

class bmm implements IJVisitor {
   bmm(bml var1, IJavaStatement var2, IJavaStatement var3, IJavaIdentifier var4) {
      this.wS = var1;
      this.pC = var2;
      this.A = var3;
      this.kS = var4;
   }

   public void pC(IJavaElement var1, IJavaElement var2, IVisitResults var3) {
      if (var1 == this.pC || var1 == this.A) {
         var3.skipChildren();
      } else if (var1 == this.kS) {
         var3.interrupt(false);
      }
   }
}
