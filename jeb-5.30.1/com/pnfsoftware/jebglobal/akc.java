package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COperatorType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCElementOptimizer;

public class akc extends AbstractCElementOptimizer {
   @Override
   protected ICElement optimizeElement(ICElement var1, ICElement var2) {
      return var1 instanceof ICOperation ? q((ICOperation)var1, this.m, this.cf) : null;
   }

   static ICExpression q(ICOperation var0, ICMethod var1, ICConstantFactory var2) {
      if (var0.getOperatorType() == COperatorType.LOG_OR || var0.getOperatorType() == COperatorType.LOG_AND) {
         aew.CU var3 = aew.CU.q(var0);
         if (var3 != null) {
            return aew.q(var3, var1, var2);
         }
      }

      return null;
   }
}
