package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDArrayElt;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInvokeInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import java.util.Set;

class cfl implements IDVisitor {
   cfl(cfk var1, Set var2, Set var3, Set var4) {
      this.Dw = var1;
      this.q = var2;
      this.RF = var3;
      this.xK = var4;
   }

   public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 instanceof IDInvokeInfo) {
         var1.collectVarIds(this.q);
         var3.skipChildren();
      } else if (var1 instanceof IDInstruction && ((IDInstruction)var1).isReturn()) {
         var1.collectVarIds(this.q);
         var3.skipChildren();
      } else if (var1 instanceof IDInstruction && ((IDInstruction)var1).isAssign() && ((IDInstruction)var1).getAssignDestination() instanceof IDField) {
         var1.collectVarIds(this.q);
         var3.skipChildren();
      } else if (var1 instanceof IDInstruction && ((IDInstruction)var1).isAssign() && ((IDInstruction)var1).getAssignDestination() instanceof IDArrayElt) {
         IDArrayElt var4 = ((IDInstruction)var1).getAssignDestination().asArrayElt();
         IDExpression var5 = var4.getArray();
         if (var5 instanceof IDVar) {
            int var6 = var5.asVar().getId();
            if (!this.RF.contains(var6) && !this.xK.contains(var6)) {
               return;
            }
         }

         var1.collectVarIds(this.q);
         var3.skipChildren();
      }
   }
}
