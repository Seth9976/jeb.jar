package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface IJavaCall extends IJavaExpression, IJavaStatement {
   int CALLTYPE_REGULAR = 0;
   int CALLTYPE_SUPER = 1;
   int CALLTYPE_LAMBDA = 2;
   int CALLTYPE_STATIC = 3;

   IJavaMethod getMethod();

   void setMethod(IJavaMethod var1, int var2);

   int getCallType();

   boolean isSuperCall();

   boolean isLambdaCall();

   boolean isStaticCall();

   String getMethodSignature();

   String getMethodClass();

   String getMethodName();

   int getArgumentCount();

   List getArguments();

   IJavaExpression getArgument(int var1);

   void addArgument(IJavaExpression var1);

   void insertArgument(int var1, IJavaExpression var2);

   void setArgument(int var1, IJavaExpression var2);

   IJavaExpression removeArgument(int var1);

   IJavaCall duplicate();
}
