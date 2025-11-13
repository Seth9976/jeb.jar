package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface ICOperator {
   String OP_ROR = "__ror__";
   String OP_ROL = "__rol__";
   String OP_POW = "__pow__";
   String OP_CARRY = "__carry__";
   String OP_PARITY = "__parity__";

   COperatorType getType();

   default boolean checkType(COperatorType... var1) {
      COperatorType var2 = this.getType();

      for (COperatorType var6 : var1) {
         if (var6 == var2) {
            return true;
         }
      }

      return false;
   }

   boolean isRegular();

   boolean isCustom();

   boolean isCast();

   ICType getCastType();

   int getOperandCount();

   boolean isUnary();

   boolean isBinary();

   boolean isTertiary();

   boolean isValidForCombinedAssignment();

   int getPrecedenceDelta(ICOperator var1) throws Exception;

   int getPrecedence();

   COperatorType.Associativity getAssociativity();

   ICOperator getReverse();

   ICOperator getMirror();
}
