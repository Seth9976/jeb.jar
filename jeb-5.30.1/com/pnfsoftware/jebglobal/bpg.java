package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.java.IJVisitor;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import java.util.Collection;

class bpg implements IJVisitor {
   bpg(bpd var1, Collection var2, IJavaIdentifier var3) {
      this.xK = var1;
      this.q = var2;
      this.RF = var3;
   }

   public void q(IJavaElement var1, IJavaElement var2, IVisitResults var3) {
      if (var1 instanceof IJavaStatement && this.q != null && this.q.contains(var1)) {
         var3.skipChildren();
      } else if (var1 == this.RF) {
         var3.interrupt(false);
      }
   }
}
