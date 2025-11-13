package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public enum OperationType {
   ADD("+", 2),
   SUB("-", 2),
   MUL("*", 2),
   @Deprecated
   MUL_U("*u", 2),
   DIV_S("/", 2),
   DIV_U("/u", 2),
   REM_S("%", 2),
   REM_U("%u", 2),
   MUL2_S("*&", 2),
   MUL2_U("*&u", 2),
   DIV2_S("/&", 2),
   DIV2_U("/&u", 2),
   AND("&", 2),
   OR("|", 2),
   XOR("^", 2),
   NOT("~", 1),
   LOG_AND("&&", 2),
   LOG_OR("||", 2),
   LOG_NOT("!", 1),
   LOG_EQ("==", 2),
   LOG_NEQ("!=", 2),
   LT_S("<", 2),
   LE_S("<=", 2),
   GT_S(">", 2),
   GE_S(">=", 2),
   LT_U("<u", 2),
   LE_U("<=u", 2),
   GT_U(">u", 2),
   GE_U(">=u", 2),
   SHL("<<", 2),
   SHR(">>u", 2),
   SAR(">>", 2),
   ROR(">><", 2),
   ROL("<<>", 2),
   PAR("PARITY", 1),
   CAST("TRN", 1),
   CAST_S("EXT", 1),
   CARRY("CARRY", 2),
   POW("**", 2),
   FEQ("==", 2),
   FNE("!=", 2),
   FLT("<", 2),
   FGT(">", 2),
   FLE("<=", 2),
   FGE(">=", 2),
   FADD("+", 2),
   FSUB("-", 2),
   FMUL("*", 2),
   FDIV("/", 2),
   FP2FP("FP2FP", 1),
   FP2INT("FP2INT", 1),
   INT2FP("INT2FP", 1),
   FP2UINT("FP2UINT", 1),
   UINT2FP("UINT2FP", 1),
   FUN("is_unordered", 2),
   ADD_SSAT("+SSAT", 2),
   ADD_USAT("+USAT", 2),
   SUB_SSAT("-SSAT", 2),
   SUB_USAT("-USAT", 2),
   FUNCTION("FUNCTION", -1);

   private final String repr;
   private final int opndcnt;

   private OperationType(String var3, int var4) {
      this.repr = var3;
      this.opndcnt = var4;
   }

   public int getOperandCount() {
      return this.opndcnt;
   }

   @Override
   public String toString() {
      return this.repr;
   }

   public boolean isValid(int var1) {
      return var1 == this.opndcnt;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public boolean shouldNotUse() {
      switch (this) {
         case MUL_U:
         case MUL2_S:
         case MUL2_U:
         case DIV2_S:
         case DIV2_U:
         case CARRY:
            return true;
         default:
            return false;
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public boolean isLogical() {
      switch (this) {
         case LOG_AND:
         case LOG_OR:
         case LOG_NOT:
         case LOG_EQ:
         case LOG_NEQ:
         case LT_S:
         case LE_S:
         case GT_S:
         case GE_S:
         case LT_U:
         case LE_U:
         case GT_U:
         case GE_U:
         case FEQ:
         case FNE:
         case FLT:
         case FGT:
         case FLE:
         case FGE:
            return true;
         default:
            return false;
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public boolean isShift() {
      switch (this) {
         case SHL:
         case SHR:
         case SAR:
         case ROL:
         case ROR:
            return true;
         default:
            return false;
      }
   }

   public boolean isConversion() {
      switch (this) {
         case CAST:
         case CAST_S:
         case FP2FP:
         case FP2INT:
         case INT2FP:
         case FP2UINT:
         case UINT2FP:
            return true;
         default:
            return false;
      }
   }

   public boolean isIntegerConversion() {
      switch (this) {
         case CAST:
         case CAST_S:
            return true;
         default:
            return false;
      }
   }

   public boolean isFloatConversion() {
      switch (this) {
         case FP2FP:
         case FP2INT:
         case INT2FP:
         case FP2UINT:
         case UINT2FP:
            return true;
         default:
            return false;
      }
   }

   public boolean isFloatOperation() {
      switch (this) {
         case FADD:
         case FSUB:
         case FMUL:
         case FDIV:
            return true;
         default:
            return false;
      }
   }

   public boolean isFloatComparison() {
      switch (this) {
         case FEQ:
         case FNE:
         case FLT:
         case FGT:
         case FLE:
         case FGE:
            return true;
         default:
            return false;
      }
   }

   public static OperationType fromName(String var0) {
      for (OperationType var4 : values()) {
         if (var4.toString().equalsIgnoreCase(var0)) {
            return var4;
         }
      }

      throw new IllegalArgumentException("Could not find constant: " + var0);
   }

   public boolean isAnyOf(OperationType... var1) {
      for (OperationType var5 : var1) {
         if (var5 == this) {
            return true;
         }
      }

      return false;
   }
}
