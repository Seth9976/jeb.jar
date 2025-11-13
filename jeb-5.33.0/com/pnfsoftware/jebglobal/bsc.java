package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.DTypeInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTypeFactory;
import com.pnfsoftware.jeb.util.base.Assert;

class bsc implements IDVisitor {
   bsc(brz var1, IJavaTypeFactory var2, DTypeInfo var3) {
      this.kS = var1;
      this.pC = var2;
      this.A = var3;
   }

   public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
      IJavaType var4 = var1.getType();
      if (var4 != null && var4.isWildcard()) {
         Assert.a(var4.isClassOrInterface());
         var4 = this.pC.createType(var4.getName());
         var1.setType(var4, this.A, true);
      }
   }
}
