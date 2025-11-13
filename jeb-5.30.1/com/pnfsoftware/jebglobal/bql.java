package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.java.IJVisitor;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;

class bql implements IJVisitor {
   bql(bqk var1, IJavaStatement var2, IJavaStatement var3, IJavaIdentifier var4) {
      this.Dw = var1;
      this.q = var2;
      this.RF = var3;
      this.xK = var4;
   }

   public void q(IJavaElement var1, IJavaElement var2, IVisitResults var3) {
      if (var1 == this.q || var1 == this.RF) {
         var3.skipChildren();
      } else if (var1 == this.xK) {
         var3.interrupt(false);
      }
   }
}
