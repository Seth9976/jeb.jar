package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface ICPredicate extends ICExpression {
   ICExpression getExpression();

   void setExpression(ICExpression var1);

   boolean isLitteralTrue();

   boolean isLitteralFalse();

   void reverse(ICOperatorFactory var1);

   ICPredicate duplicateAndReverse(ICOperatorFactory var1);

   ICPredicate duplicate();
}
