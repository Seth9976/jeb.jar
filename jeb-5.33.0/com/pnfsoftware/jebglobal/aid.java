package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COperatorType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCElementOptimizer;

public class aid extends AbstractCElementOptimizer {
   @Override
   protected ICElement optimizeElement(ICElement var1, ICElement var2) {
      return var1 instanceof ICOperation ? pC((ICOperation)var1, this.m, this.cf) : null;
   }

   static ICExpression pC(ICOperation var0, ICMethod var1, ICConstantFactory var2) {
      if (var0.getOperatorType() == COperatorType.LOG_OR || var0.getOperatorType() == COperatorType.LOG_AND) {
         add.Sv var3 = add.Sv.pC(var0);
         if (var3 != null) {
            return add.pC(var3, var1, var2);
         }
      }

      return null;
   }
}
