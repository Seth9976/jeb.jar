package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IJavaConditionalExpression extends IJavaExpression {
   IJavaExpression getPredicate();

   void setPredicate(IJavaExpression var1);

   IJavaExpression getExpressionTrue();

   void setExpressionTrue(IJavaExpression var1);

   IJavaExpression getExpressionFalse();

   void setExpressionFalse(IJavaExpression var1);

   IJavaConditionalExpression duplicate();
}
