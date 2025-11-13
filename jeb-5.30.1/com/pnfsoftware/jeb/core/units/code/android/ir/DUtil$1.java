package com.pnfsoftware.jeb.core.units.code.android.ir;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;

class DUtil$1 implements IDVisitor {
   DUtil$1(IDExpression var1, IDExpression var2) {
      this.val$target = var1;
      this.val$repl = var2;
   }

   public void process(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 == this.val$target) {
         if (!var2.replaceSubExpression(var1, this.val$repl)) {
            var3.interrupt(true);
         } else {
            var3.setReplacedNode(this.val$repl);
            var3.interrupt(false);
         }
      }
   }
}
