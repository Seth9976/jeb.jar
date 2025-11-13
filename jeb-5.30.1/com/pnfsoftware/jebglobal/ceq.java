package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import com.pnfsoftware.jeb.util.base.Couple;
import java.util.List;

class ceq implements IDVisitor {
   ceq(ceo var1, IDField var2, List var3) {
      this.xK = var1;
      this.q = var2;
      this.RF = var3;
   }

   public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (!(var1 instanceof IDInstruction)) {
         if (var1.hasSideEffects(this.xK.ctx, true)) {
            var3.interrupt(false);
         } else if (var1.equals(this.q)) {
            this.RF.add(new Couple(var1, var2));
         }
      }
   }
}
