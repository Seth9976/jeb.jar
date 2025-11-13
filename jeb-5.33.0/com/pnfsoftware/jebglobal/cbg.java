package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDArrayElt;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;

class cbg implements IDVisitor {
   cbg(cbf var1) {
      this.pC = var1;
   }

   public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 instanceof IDArrayElt var4 && var4.getType().isJavaLangObject()) {
         IJavaType var5 = var4.getArray().getType();
         if (var5.isArray()) {
            IJavaType var6 = var5.getArrayTypeDimensionBelow();
            if (var6 != var4.getType() && var2 instanceof IDOperation var7 && var7.isCast()) {
               IJavaType var8 = var7.getType();
               if (this.pC.ctx.getTypeInfoProvider().isCompatible(var8.getSignature(), var6.getSignature()) && var4.setType(var6, null, true)) {
                  this.pC.pC++;
               }
            }
         }
      }
   }
}
