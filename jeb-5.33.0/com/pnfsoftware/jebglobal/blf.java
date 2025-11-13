package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.java.IJVisitor;
import com.pnfsoftware.jeb.core.units.code.java.IJavaContinue;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaGoto;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLabel;

class blf implements IJVisitor {
   blf(ble var1, IJavaLabel var2) {
      this.A = var1;
      this.pC = var2;
   }

   public void pC(IJavaElement var1, IJavaElement var2, IVisitResults var3) {
      if (var1 instanceof IJavaGoto || var1 instanceof IJavaContinue) {
         if (this.pC != null && var1 instanceof IJavaGoto && ((IJavaGoto)var1).getLabel() == this.pC) {
            return;
         }

         var3.interrupt(false);
      }
   }
}
