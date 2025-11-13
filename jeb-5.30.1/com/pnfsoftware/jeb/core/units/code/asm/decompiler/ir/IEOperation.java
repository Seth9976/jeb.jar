package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface IEOperation extends IEGeneric {
   String getOperationName();

   int getCountOfOperands();

   IEGeneric getOperand1();

   IEGeneric getOperand2();

   IEGeneric getOperand(int var1);

   List getOperands();

   OperationType getOperationType();

   FunctionOptype getCustomOperationType();

   default boolean isCustomOperation() {
      return this.getOperationType() == OperationType.FUNCTION && this.getCustomOperationType() != null;
   }

   default boolean isCustomOperation(String var1) {
      return this.isCustomOperation() && this.getCustomOperationType().getName().equals(var1);
   }
}
