package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COperatorType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperator;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class aez implements ICOperator {
   @SerId(1)
   private afb pC;
   @SerId(2)
   private COperatorType A;
   @SerId(3)
   private ICType kS;
   @SerId(4)
   private String wS;
   @SerId(5)
   private int UT;

   public aez(COperatorType var1, afb var2) {
      this.A = var1;
      this.kS = null;
      this.pC = var2;
   }

   public aez(ICType var1, afb var2) {
      this.A = COperatorType.CAST;
      this.kS = var1;
      this.pC = var2;
   }

   public aez(String var1, int var2, afb var3) {
      this.A = COperatorType.CUSTOM;
      this.wS = var1;
      this.UT = var2;
      this.pC = var3;
   }

   @Override
   public COperatorType getType() {
      return this.A;
   }

   @Override
   public boolean isRegular() {
      return this.A != COperatorType.CUSTOM && this.A != COperatorType.CAST;
   }

   @Override
   public boolean isCustom() {
      return this.A == COperatorType.CUSTOM;
   }

   @Override
   public boolean isCast() {
      return this.A == COperatorType.CAST;
   }

   @Override
   public ICType getCastType() {
      if (this.A != COperatorType.CAST) {
         throw new IllegalStateException();
      } else {
         return this.kS;
      }
   }

   @Override
   public int getOperandCount() {
      return this.A == COperatorType.CUSTOM ? this.UT : this.A.getOperandCount();
   }

   @Override
   public boolean isUnary() {
      return this.getOperandCount() == 1;
   }

   @Override
   public boolean isBinary() {
      return this.getOperandCount() == 2;
   }

   @Override
   public boolean isTertiary() {
      return this.getOperandCount() == 3;
   }

   @Override
   public String toString() {
      if (this.A == COperatorType.CUSTOM) {
         return this.wS;
      } else {
         return this.A == COperatorType.CAST ? "(" + this.kS + ")" : this.A.toString();
      }
   }

   @Override
   public boolean isValidForCombinedAssignment() {
      switch (this.A) {
         case MUL:
         case DIV:
         case ADD:
         case SUB:
         case REM:
         case SHL:
         case SHR:
         case USHR:
         case AND:
         case XOR:
         case OR:
            return true;
         default:
            return false;
      }
   }

   @Override
   public int getPrecedenceDelta(ICOperator var1) throws Exception {
      int var2 = this.A.getPrecedence();
      int var3 = var1.getType().getPrecedence();
      return var2 - var3;
   }

   @Override
   public int getPrecedence() {
      return this.A.getPrecedence();
   }

   @Override
   public COperatorType.Associativity getAssociativity() {
      return this.A.getAssociativity();
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.kS == null ? 0 : this.kS.hashCode());
      var1 = 31 * var1 + (this.wS == null ? 0 : this.wS.hashCode());
      var1 = 31 * var1 + this.UT;
      return 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         aez var2 = (aez)var1;
         if (this.A != var2.A) {
            return false;
         } else {
            if (this.kS == null) {
               if (var2.kS != null) {
                  return false;
               }
            } else if (!this.kS.equals(var2.kS)) {
               return false;
            }

            if (this.wS == null) {
               if (var2.wS != null) {
                  return false;
               }
            } else if (!this.wS.equals(var2.wS)) {
               return false;
            }

            return this.UT == var2.UT;
         }
      }
   }

   public aez pC() {
      COperatorType var1 = this.A.reverse();
      return var1 != null ? this.pC.pC(var1) : null;
   }

   public aez A() {
      COperatorType var1 = this.A.mirror();
      return var1 != null ? this.pC.pC(var1) : null;
   }
}
