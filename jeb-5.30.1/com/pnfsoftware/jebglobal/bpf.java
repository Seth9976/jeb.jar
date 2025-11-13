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

class bpf implements IJVisitor {
   bpf(bpd var1, Collection var2, IJavaIdentifier var3, IJavaStatement[] var4) {
      this.Dw = var1;
      this.q = var2;
      this.RF = var3;
      this.xK = var4;
   }

   public void q(IJavaElement var1, IJavaElement var2, IVisitResults var3) {
      if (var1 instanceof IJavaStatement && this.q != null && this.q.contains(var1)) {
         var3.skipChildren();
      } else if (var1 instanceof IJavaAssignment
         && ((IJavaAssignment)var1).getLeft() instanceof IJavaDefinition
         && ((IJavaAssignment)var1).getRight() instanceof IJavaConstant
         && ((IJavaDefinition)((IJavaAssignment)var1).getLeft()).getIdentifier() == this.RF) {
         this.xK[0] = (IJavaStatement)var1;
         var3.interrupt(true);
      }
   }
}
