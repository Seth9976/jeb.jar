package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.java.IJVisitor;
import com.pnfsoftware.jeb.core.units.code.java.IJavaContinue;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaGoto;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLabel;

class bpe implements IJVisitor {
   bpe(bpd var1, IJavaLabel var2) {
      this.RF = var1;
      this.q = var2;
   }

   public void q(IJavaElement var1, IJavaElement var2, IVisitResults var3) {
      if (var1 instanceof IJavaGoto || var1 instanceof IJavaContinue) {
         if (this.q != null && var1 instanceof IJavaGoto && ((IJavaGoto)var1).getLabel() == this.q) {
            return;
         }

         var3.interrupt(false);
      }
   }
}
