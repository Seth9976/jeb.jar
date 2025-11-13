package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import com.pnfsoftware.jeb.util.base.Couple;
import java.util.List;

class bzx implements IDVisitor {
   bzx(bzv var1, IDField var2, List var3) {
      this.kS = var1;
      this.pC = var2;
      this.A = var3;
   }

   public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (!(var1 instanceof IDInstruction)) {
         if (var1.hasSideEffects(this.kS.ctx, true)) {
            var3.interrupt(false);
         } else if (var1.equals(this.pC)) {
            this.A.add(new Couple(var1, var2));
         }
      }
   }
}
