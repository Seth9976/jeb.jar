package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.JUtil;
import java.util.function.Predicate;

class bpt implements Predicate {
   bpt(bps var1, IJavaExpression var2) {
      this.RF = var1;
      this.q = var2;
   }

   public boolean q(IJavaStatement var1) {
      return JUtil.isMonitorExit(var1, this.q);
   }
}
