package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface ICOperation extends ICLeftExpression {
   ICOperator getOperator();

   void setOperator(ICOperator var1);

   default COperatorType getOperatorType() {
      return this.getOperator().getType();
   }

   default boolean checkOperatorType(COperatorType var1) {
      return this.getOperator().getType() == var1;
   }

   int getCountOfOperands();

   ICExpression getFirstOperand();

   void setFirstOperand(ICExpression var1);

   ICExpression getSecondOperand();

   void setSecondOperand(ICExpression var1);

   ICExpression getThirdOperand();

   void setThirdOperand(ICExpression var1);

   boolean reverse(ICOperatorFactory var1);

   boolean mirror(ICOperatorFactory var1);

   ICOperation duplicate();
}
