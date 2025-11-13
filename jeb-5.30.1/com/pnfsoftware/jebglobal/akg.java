package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COperatorType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICVisitor;

class akg implements ICVisitor {
   akg(ake var1) {
      this.q = var1;
   }

   public void q(ICElement var1, ICElement var2, IVisitResults var3) {
      if (var1 instanceof ICOperation && CUtil.isOperation((ICOperation)var1, COperatorType.LOG_AND, COperatorType.LOG_OR)) {
         ICOperation var4 = (ICOperation)var1;
         if (CUtil.isIntegerValue(var4.getFirstOperand(), 1L) || CUtil.isIntegerValue(var4.getSecondOperand(), 0L)) {
            var2.replaceSubElement(var1, var4.getOperatorType() == COperatorType.LOG_AND ? var4.getSecondOperand() : var4.getFirstOperand());
         } else if (CUtil.isIntegerValue(var4.getFirstOperand(), 0L) || CUtil.isIntegerValue(var4.getSecondOperand(), 1L)) {
            var2.replaceSubElement(var1, var4.getOperatorType() == COperatorType.LOG_AND ? var4.getFirstOperand() : var4.getSecondOperand());
         }
      }
   }
}
