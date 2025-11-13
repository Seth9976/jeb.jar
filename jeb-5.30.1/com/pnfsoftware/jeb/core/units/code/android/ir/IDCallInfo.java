package com.pnfsoftware.jeb.core.units.code.android.ir;

public interface IDCallInfo extends IDInvokeInfo {
   String getMethodSignature();

   String getMethodName();

   int getCountOfArguments();

   boolean hasThis();

   DInvokeType getInvokeType();

   void setArgument(int var1, IDExpression var2);

   IDExpression getArgument(int var1);

   void upgradeMistypedArguments(DTypeInfo var1, IDGlobalContext var2);

   IDCallInfo duplicate();
}
