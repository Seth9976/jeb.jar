package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.core.units.code.android.ir.DFormattingContext;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IJavaOperator {
   IJavaOperatorFactory getFactory();

   boolean is(JavaOperatorType var1);

   boolean isAnyOf(JavaOperatorType... var1);

   boolean isNoneOf(JavaOperatorType... var1);

   JavaOperatorType getOperatorType();

   default JavaOperatorType getType() {
      return this.getOperatorType();
   }

   boolean isUnary();

   boolean isBinary();

   boolean isArithmetic();

   boolean isLogical();

   boolean isCast();

   boolean isCastToPrimitive();

   IJavaType getCastType();

   boolean isValidForCombinedAssignment();

   int getPrecedenceDelta(IJavaOperator var1) throws Exception;

   int getPrecedence();

   JavaOperatorType.Associativity getAssociativity();

   boolean canCauseException();

   void format(DFormattingContext var1);

   IJavaOperator getReverse();

   IJavaOperator getMirror();
}
