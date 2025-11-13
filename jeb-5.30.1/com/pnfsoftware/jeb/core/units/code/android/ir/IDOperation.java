package com.pnfsoftware.jeb.core.units.code.android.ir;

import com.pnfsoftware.jeb.core.units.code.java.IJavaOperator;
import com.pnfsoftware.jeb.core.units.code.java.IJavaOperatorFactory;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;

public interface IDOperation extends IDExpression {
   IJavaOperator getOperator();

   JavaOperatorType getOperatorType();

   void setOperator(IJavaOperator var1);

   void setOperator(JavaOperatorType var1, IJavaOperatorFactory var2);

   default IDExpression getOperand1() {
      return this.getLeft();
   }

   default void setOperand1(IDExpression var1) {
      this.setLeft(var1);
   }

   default IDExpression getOperand2() {
      return this.getRight();
   }

   default void setOperand2(IDExpression var1) {
      this.setRight(var1);
   }

   IDExpression getLeft();

   void setLeft(IDExpression var1);

   IDExpression getRight();

   void setRight(IDExpression var1);

   IDExpression getCondPredicate();

   void setCondPredicate(IDExpression var1);

   IDExpression getCondTrueExpression();

   IDExpression getCondFalseExpression();

   boolean isUnary();

   boolean isConditional();

   boolean isCast();

   boolean isCast(IJavaType var1);

   void updateConversionOperators(DTypeInfo var1, IDGlobalContext var2);

   IDOperation duplicate();

   boolean canReverse();

   void reverse();
}
