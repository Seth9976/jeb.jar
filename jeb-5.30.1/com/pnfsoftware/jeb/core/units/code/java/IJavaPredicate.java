package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IJavaPredicate extends IJavaExpression {
   IJavaExpression getExpression();

   void setExpression(IJavaExpression var1);

   boolean isLitteralTrue();

   boolean isLitteralFalse();

   void reverse(IJavaOperatorFactory var1);

   IJavaPredicate duplicateAndReverse(IJavaOperatorFactory var1);

   IJavaPredicate duplicate();
}
