package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.JUtil;
import java.util.function.Predicate;

class blu implements Predicate {
   blu(blt var1, IJavaExpression var2) {
      this.A = var1;
      this.pC = var2;
   }

   public boolean pC(IJavaStatement var1) {
      return JUtil.isMonitorExit(var1, this.pC);
   }
}
