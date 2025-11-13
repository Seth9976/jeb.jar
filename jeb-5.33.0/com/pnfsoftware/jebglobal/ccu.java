package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import java.util.Set;

class ccu implements IDVisitor {
   ccu(cct var1, Set var2, Set var3) {
      this.kS = var1;
      this.pC = var2;
      this.A = var3;
   }

   public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 instanceof IDVar var4) {
         int var5 = var4.getId();
         if (DUtil.isVirtualVarId(var5)) {
            if (DUtil.isSingleSlotVarId(var5)) {
               this.pC.add(var5);
            } else {
               if (!DUtil.isDoubleSlotVarId(var5)) {
                  throw new RuntimeException();
               }

               this.A.add(var5);
            }
         }
      }
   }
}
