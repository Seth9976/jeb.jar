package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COperatorType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIdentifier;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperation;

public class CMethodSimulatorUtils {
   public static ICExpression getDereferencedExpression(ICElement var0) {
      return var0 instanceof ICOperation && ((ICOperation)var0).getOperatorType() == COperatorType.PTR ? ((ICOperation)var0).getFirstOperand() : null;
   }

   public static Long getDereferencedAddress(ICElement var0, CMethodState var1, CEnvironment var2) {
      Long var3 = null;
      ICExpression var4 = getDereferencedExpression(var0);
      if (var4 != null) {
         var3 = var4.evaluate(var1, var2);
      }

      return var3;
   }

   public static ICIdentifier getBasePointer(ICOperation var0) {
      ICIdentifier var1 = null;
      if (var0.getFirstOperand() instanceof ICIdentifier) {
         var1 = (ICIdentifier)var0.getFirstOperand();
      } else if (var0.getSecondOperand() instanceof ICIdentifier) {
         var1 = (ICIdentifier)var0.getSecondOperand();
      } else if (var0.getThirdOperand() instanceof ICIdentifier) {
         var1 = (ICIdentifier)var0.getThirdOperand();
      }

      return var1;
   }
}
