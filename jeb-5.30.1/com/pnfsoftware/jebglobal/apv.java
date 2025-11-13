package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import java.util.Map;

class apv implements IEVisitor {
   apv(aps var1, Map var2) {
      this.RF = var1;
      this.q = var2;
   }

   public void q(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1.isOperation(OperationType.CAST, OperationType.CAST_S)) {
         IEOperation var4 = var1.asOperation();
         if (var4.getOperand1() instanceof IEVar) {
            IEVar var5 = (IEVar)var4.getOperand1();
            if (var5.getType() == null || var5.getType().isUndefined()) {
               OperationType var7 = var4.getOperationType();
               byte var6;
               if (var7 == OperationType.CAST && var4.getBitsize() > var5.getBitsize()) {
                  var6 = 1;
               } else {
                  if (var7 != OperationType.CAST_S || var4.getBitsize() <= var5.getBitsize()) {
                     return;
                  }

                  var6 = -1;
               }

               Integer var8 = (Integer)this.q.get(var5);
               if (var8 == null) {
                  this.q.put(var5, Integer.valueOf(var6));
               } else if (var8 != 0 && var8 != var6) {
                  this.q.put(var5, 0);
               }
            }
         }
      }
   }
}
