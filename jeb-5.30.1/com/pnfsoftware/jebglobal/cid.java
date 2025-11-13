package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;

class cid implements IDVisitor {
   cid(cic var1) {
      this.q = var1;
   }

   public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1.isStringImm() && !"foobar".equals(var1.asImm().getStringValue(this.q.g))) {
         IDImm var4 = this.q.g.createString("foobar");
         var4.setOrigin("This string was generated for tests");
         if (var2.replaceSubExpression(var1, var4)) {
            this.q.q++;
         }
      }
   }
}
