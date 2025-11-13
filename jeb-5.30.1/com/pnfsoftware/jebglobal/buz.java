package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;

class buz implements IDVisitor {
   public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
      IJavaType var4 = var1.getType();
      if (var4 == null || !var4.isSmallInt()) {
         var3.interrupt(false);
      }
   }
}
