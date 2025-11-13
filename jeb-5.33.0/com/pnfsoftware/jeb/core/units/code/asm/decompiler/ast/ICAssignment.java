package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface ICAssignment extends ICSourceElement, ICStatement {
   boolean isSimpleAssignment();

   boolean isCombinedOperatorAssignment();

   ICOperator getCombinedOperator();

   boolean isUnaryOperatorAssignment();

   void getUnaryOperator(boolean[] var1);

   ICLeftExpression getLeft();

   ICExpression getRight();

   void setLeft(ICLeftExpression var1);

   void setRight(ICExpression var1);

   void setCombinedOperatorAssignment(ICOperator var1, ICExpression var2);

   void setCombinedOperator(ICOperator var1);

   void setUnaryOperator(boolean var1, boolean var2);

   ICAssignment duplicate();

   ICAssignment duplicate(boolean var1);
}
