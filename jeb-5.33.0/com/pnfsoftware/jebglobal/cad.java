package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import java.util.HashSet;
import java.util.Set;

class cad implements IDVisitor {
   int pC;
   Set A;
   Set kS;

   cad(cab var1) {
      this.wS = var1;
      this.kS = new HashSet();
   }

   public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 instanceof IDCallInfo var4 && var4.hasThis() && var4.getCountOfArguments() >= 1 && var4.getArgument(0) instanceof IDVar var5) {
         if (this.A.contains(var5.getId())) {
            Object var7 = var4.setData("THIS_NOTNULL", true);
            if (!Boolean.TRUE.equals(var7)) {
               this.pC++;
            }
         }

         this.kS.add(var5.getId());
      }
   }
}
