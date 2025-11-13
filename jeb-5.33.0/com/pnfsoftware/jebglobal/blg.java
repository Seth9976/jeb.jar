package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.java.IJVisitor;
import com.pnfsoftware.jeb.core.units.code.java.IJavaAssignment;
import com.pnfsoftware.jeb.core.units.code.java.IJavaConstant;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDefinition;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import java.util.Collection;

class blg implements IJVisitor {
   blg(ble var1, Collection var2, IJavaIdentifier var3, IJavaStatement[] var4) {
      this.wS = var1;
      this.pC = var2;
      this.A = var3;
      this.kS = var4;
   }

   public void pC(IJavaElement var1, IJavaElement var2, IVisitResults var3) {
      if (var1 instanceof IJavaStatement && this.pC != null && this.pC.contains(var1)) {
         var3.skipChildren();
      } else if (var1 instanceof IJavaAssignment
         && ((IJavaAssignment)var1).getLeft() instanceof IJavaDefinition
         && ((IJavaAssignment)var1).getRight() instanceof IJavaConstant
         && ((IJavaDefinition)((IJavaAssignment)var1).getLeft()).getIdentifier() == this.A) {
         this.kS[0] = (IJavaStatement)var1;
         var3.interrupt(true);
      }
   }
}
