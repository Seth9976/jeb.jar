package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IJavaOperation extends IJavaExpression {
   IJavaOperator getOperator();

   void setOperator(IJavaOperator var1);

   default JavaOperatorType getOperatorType() {
      return this.getOperator().getType();
   }

   default boolean checkOperatorType(JavaOperatorType var1) {
      return this.getOperator().getType() == var1;
   }

   int getCountOfOperands();

   IJavaExpression getLeft();

   void setLeft(IJavaExpression var1);

   IJavaExpression getRight();

   void setRight(IJavaExpression var1);

   boolean canReverse();

   boolean reverse(IJavaOperatorFactory var1);

   boolean canMirror();

   boolean mirror(IJavaOperatorFactory var1);

   IJavaOperation duplicate();
}
