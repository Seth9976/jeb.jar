package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public enum COperatorType {
   CUSTOM("()", -1, 1),
   NEG("-", 1, 2, COperatorType.Associativity.RIGHT),
   NOT("~", 1, 2, COperatorType.Associativity.RIGHT),
   LOG_NOT("!", 1, 2, COperatorType.Associativity.RIGHT),
   PTR("*", 1, 2, COperatorType.Associativity.RIGHT),
   REF("&", 1, 2, COperatorType.Associativity.RIGHT),
   CAST("(type)", 1, 2, COperatorType.Associativity.RIGHT),
   SIZEOF("sizeof", 1, 2, COperatorType.Associativity.RIGHT),
   LOG_IDENT("", 1, 2, COperatorType.Associativity.RIGHT),
   MUL("*", 2, 3),
   DIV("/", 2, 3),
   REM("%", 2, 3),
   ADD("+", 2, 4),
   SUB("-", 2, 4),
   SHL("<<", 2, 5),
   SHR(">>", 2, 5),
   USHR(">>>", 2, 5),
   GE(">=", 2, 6),
   GT(">", 2, 6),
   LT("<", 2, 6),
   LE("<=", 2, 6),
   EQ("==", 2, 7),
   NE("!=", 2, 7),
   AND("&", 2, 8),
   XOR("^", 2, 9),
   OR("|", 2, 10),
   LOG_AND("&&", 2, 11),
   LOG_OR("||", 2, 12),
   COND("?:", 3, 13, COperatorType.Associativity.RIGHT);

   private final String str;
   private final int opcount;
   private final int prec;
   private final COperatorType.Associativity asso;

   private COperatorType(String var3, int var4, int var5, COperatorType.Associativity var6) {
      this.str = var3;
      this.opcount = var4;
      this.prec = var5;
      this.asso = var6;
   }

   private COperatorType(String var3, int var4, int var5) {
      this(var3, var4, var5, COperatorType.Associativity.LEFT);
   }

   private COperatorType() {
      this(null, 0, 0, null);
   }

   public int getOperandCount() {
      return this.opcount;
   }

   public int getPrecedence() {
      return this.prec;
   }

   public COperatorType.Associativity getAssociativity() {
      return this.asso;
   }

   @Override
   public String toString() {
      return this.str;
   }

   public COperatorType reverse() {
      switch (this) {
         case EQ:
            return NE;
         case NE:
            return EQ;
         case LT:
            return GE;
         case LE:
            return GT;
         case GT:
            return LE;
         case GE:
            return LT;
         case LOG_NOT:
            return LOG_IDENT;
         case LOG_IDENT:
            return LOG_NOT;
         default:
            return null;
      }
   }

   public COperatorType mirror() {
      switch (this) {
         case EQ:
            return EQ;
         case NE:
            return NE;
         case LT:
            return GT;
         case LE:
            return GE;
         case GT:
            return LT;
         case GE:
            return LE;
         default:
            return null;
      }
   }

   public static enum Associativity {
      LEFT,
      RIGHT;
   }
}
