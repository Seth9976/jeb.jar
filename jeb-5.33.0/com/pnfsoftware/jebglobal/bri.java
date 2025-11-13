package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;

class bri implements IDVisitor {
   bri(brg var1) {
      this.pC = var1;
   }

   public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 instanceof IDImm) {
         this.pC.vP++;
      } else if (var1 instanceof IDVar var4) {
         this.pC.xC.add(var4.getId());
      } else if (var1 instanceof IDOperation) {
         this.pC.NS++;
      }
   }
}
