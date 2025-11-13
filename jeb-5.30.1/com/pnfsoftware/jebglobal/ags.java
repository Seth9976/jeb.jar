package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COperatorType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperator;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class ags implements ICOperator {
   @SerId(1)
   private agu q;
   @SerId(2)
   private COperatorType RF;
   @SerId(3)
   private ICType xK;
   @SerId(4)
   private String Dw;
   @SerId(5)
   private int Uv;

   public ags(COperatorType var1, agu var2) {
      this.RF = var1;
      this.xK = null;
      this.q = var2;
   }

   public ags(ICType var1, agu var2) {
      this.RF = COperatorType.CAST;
      this.xK = var1;
      this.q = var2;
   }

   public ags(String var1, int var2, agu var3) {
      this.RF = COperatorType.CUSTOM;
      this.Dw = var1;
      this.Uv = var2;
      this.q = var3;
   }

   public agu q() {
      return this.q;
   }

   @Override
   public COperatorType getType() {
      return this.RF;
   }

   @Override
   public boolean isRegular() {
      return this.RF != COperatorType.CUSTOM && this.RF != COperatorType.CAST;
   }

   @Override
   public boolean isCustom() {
      return this.RF == COperatorType.CUSTOM;
   }

   @Override
   public boolean isCast() {
      return this.RF == COperatorType.CAST;
   }

   @Override
   public ICType getCastType() {
      if (this.RF != COperatorType.CAST) {
         throw new IllegalStateException();
      } else {
         return this.xK;
      }
   }

   @Override
   public int getOperandCount() {
      return this.RF == COperatorType.CUSTOM ? this.Uv : this.RF.getOperandCount();
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
      if (this.RF == COperatorType.CUSTOM) {
         return this.Dw;
      } else {
         return this.RF == COperatorType.CAST ? "(" + this.xK + ")" : this.RF.toString();
      }
   }

   @Override
   public boolean isValidForCombinedAssignment() {
      switch (this.RF) {
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
      int var2 = this.RF.getPrecedence();
      int var3 = var1.getType().getPrecedence();
      return var2 - var3;
   }

   @Override
   public int getPrecedence() {
      return this.RF.getPrecedence();
   }

   @Override
   public COperatorType.Associativity getAssociativity() {
      return this.RF.getAssociativity();
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.xK == null ? 0 : this.xK.hashCode());
      var1 = 31 * var1 + (this.Dw == null ? 0 : this.Dw.hashCode());
      var1 = 31 * var1 + this.Uv;
      return 31 * var1 + (this.RF == null ? 0 : this.RF.hashCode());
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
         ags var2 = (ags)var1;
         if (this.RF != var2.RF) {
            return false;
         } else {
            if (this.xK == null) {
               if (var2.xK != null) {
                  return false;
               }
            } else if (!this.xK.equals(var2.xK)) {
               return false;
            }

            if (this.Dw == null) {
               if (var2.Dw != null) {
                  return false;
               }
            } else if (!this.Dw.equals(var2.Dw)) {
               return false;
            }

            return this.Uv == var2.Uv;
         }
      }
   }

   public ags RF() {
      COperatorType var1 = this.RF.reverse();
      return var1 != null ? this.q.q(var1) : null;
   }

   public ags xK() {
      COperatorType var1 = this.RF.mirror();
      return var1 != null ? this.q.q(var1) : null;
   }
}
