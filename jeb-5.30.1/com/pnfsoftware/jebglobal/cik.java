package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInvokeInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;

class cik implements IDVisitor {
   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
      boolean var4 = false;
      IJavaType var5 = var1.getType();
      if (var5 == null || var5.isFloatingPointType() || var5.isObject() || var5.isVoid()) {
         var4 = true;
      } else if (var1 instanceof IDInvokeInfo || var1 instanceof IDField) {
         var4 = true;
      } else if (var1 instanceof IDOperation) {
         switch (((IDOperation)var1).getOperatorType()) {
            case CAST_TO_BOOLEAN:
            case CAST_TO_BYTE:
            case CAST_TO_CHAR:
            case CAST_TO_SHORT:
            case CAST_TO_INT:
            case CAST_TO_LONG:
            case CAST_TO_FLOAT:
            case CAST_TO_DOUBLE:
            case CAST_CONVERSION:
            case CAST_TO_OBJECT:
            case CONCAT:
            case INSTANCEOF:
               var4 = true;
         }
      }

      if (var4) {
         var3.interrupt(false);
      }
   }
}
