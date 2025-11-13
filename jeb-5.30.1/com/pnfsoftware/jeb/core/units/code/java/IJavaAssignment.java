package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IJavaAssignment extends IJavaStatement {
   IJavaLeftExpression getLeft();

   void setLeft(IJavaLeftExpression var1);

   IJavaExpression getRight();

   void setRight(IJavaExpression var1);

   boolean isSimpleAssignment();

   boolean isCombinedOperatorAssignment();

   IJavaOperator getCombinedOperator();

   void setCombinedOperatorAssignment(IJavaOperator var1, IJavaExpression var2);

   void setCombinedOperator(IJavaOperator var1);

   boolean isUnaryOperatorAssignment();

   void getUnaryOperator(boolean[] var1);

   void setUnaryOperator(boolean var1, boolean var2);

   IJavaAssignment duplicate();
}
