package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.HashMap;
import java.util.Map;

@Ser(oldId = 65599)
public enum JavaOperatorType {
   ADD,
   SUB,
   MUL,
   DIV,
   REM,
   AND,
   OR,
   XOR,
   SHL,
   SHR,
   USHR,
   NEG,
   NOT,
   LOG_IDENT,
   LOG_NOT,
   LOG_OR,
   LOG_AND,
   INSTANCEOF,
   EQ,
   NE,
   LT,
   GE,
   GT,
   LE,
   CAST_TO_BYTE,
   CAST_TO_CHAR,
   CAST_TO_SHORT,
   CAST_TO_INT,
   CAST_TO_LONG,
   CAST_TO_FLOAT,
   CAST_TO_DOUBLE,
   CAST_TO_BOOLEAN,
   CAST_TO_OBJECT,
   CONCAT,
   COND_EXP,
   CAST_CONVERSION;

   private static final Map prec_table = new HashMap();
   private static final Map asso_table = new HashMap();
   private static final Map asso_table_per_precedence = new HashMap();

   public int getPrecedence() {
      Integer var1 = (Integer)prec_table.get(this);
      return var1 == null ? 0 : var1;
   }

   public JavaOperatorType.Associativity getAssociativity() {
      int var1 = this.getPrecedence();
      return (JavaOperatorType.Associativity)asso_table_per_precedence.get(var1);
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public JavaOperatorType getMirror() {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.RuntimeException: invalid constant type: Lcom/pnfsoftware/jeb/core/units/code/java/JavaOperatorType; with value 28
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ConstExprent.toJava(ConstExprent.java:356)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SwitchStatement.toJava(SwitchStatement.java:171)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.RootStatement.toJava(RootStatement.java:36)
      //   at org.jetbrains.java.decompiler.main.ClassWriter.writeMethod(ClassWriter.java:1326)
      //
      // Bytecode:
      // 00: getstatic com/pnfsoftware/jeb/core/units/code/java/JavaOperatorType$1.$SwitchMap$com$pnfsoftware$jeb$core$units$code$java$JavaOperatorType [I
      // 03: aload 0
      // 04: invokevirtual com/pnfsoftware/jeb/core/units/code/java/JavaOperatorType.ordinal ()I
      // 07: iaload
      // 08: tableswitch 64 1 6 40 44 48 52 56 60
      // 30: getstatic com/pnfsoftware/jeb/core/units/code/java/JavaOperatorType.EQ Lcom/pnfsoftware/jeb/core/units/code/java/JavaOperatorType;
      // 33: areturn
      // 34: getstatic com/pnfsoftware/jeb/core/units/code/java/JavaOperatorType.NE Lcom/pnfsoftware/jeb/core/units/code/java/JavaOperatorType;
      // 37: areturn
      // 38: getstatic com/pnfsoftware/jeb/core/units/code/java/JavaOperatorType.LE Lcom/pnfsoftware/jeb/core/units/code/java/JavaOperatorType;
      // 3b: areturn
      // 3c: getstatic com/pnfsoftware/jeb/core/units/code/java/JavaOperatorType.LT Lcom/pnfsoftware/jeb/core/units/code/java/JavaOperatorType;
      // 3f: areturn
      // 40: getstatic com/pnfsoftware/jeb/core/units/code/java/JavaOperatorType.GE Lcom/pnfsoftware/jeb/core/units/code/java/JavaOperatorType;
      // 43: areturn
      // 44: getstatic com/pnfsoftware/jeb/core/units/code/java/JavaOperatorType.GT Lcom/pnfsoftware/jeb/core/units/code/java/JavaOperatorType;
      // 47: areturn
      // 48: aconst_null
      // 49: areturn
   }

   public final JavaOperatorType mirror() {
      return this.getMirror();
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public JavaOperatorType getReverse() {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.RuntimeException: invalid constant type: Lcom/pnfsoftware/jeb/core/units/code/java/JavaOperatorType; with value 28
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ConstExprent.toJava(ConstExprent.java:356)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SwitchStatement.toJava(SwitchStatement.java:171)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.RootStatement.toJava(RootStatement.java:36)
      //   at org.jetbrains.java.decompiler.main.ClassWriter.writeMethod(ClassWriter.java:1326)
      //
      // Bytecode:
      // 00: getstatic com/pnfsoftware/jeb/core/units/code/java/JavaOperatorType$1.$SwitchMap$com$pnfsoftware$jeb$core$units$code$java$JavaOperatorType [I
      // 03: aload 0
      // 04: invokevirtual com/pnfsoftware/jeb/core/units/code/java/JavaOperatorType.ordinal ()I
      // 07: iaload
      // 08: tableswitch 80 1 8 48 52 68 64 60 56 72 76
      // 38: getstatic com/pnfsoftware/jeb/core/units/code/java/JavaOperatorType.NE Lcom/pnfsoftware/jeb/core/units/code/java/JavaOperatorType;
      // 3b: areturn
      // 3c: getstatic com/pnfsoftware/jeb/core/units/code/java/JavaOperatorType.EQ Lcom/pnfsoftware/jeb/core/units/code/java/JavaOperatorType;
      // 3f: areturn
      // 40: getstatic com/pnfsoftware/jeb/core/units/code/java/JavaOperatorType.GE Lcom/pnfsoftware/jeb/core/units/code/java/JavaOperatorType;
      // 43: areturn
      // 44: getstatic com/pnfsoftware/jeb/core/units/code/java/JavaOperatorType.GT Lcom/pnfsoftware/jeb/core/units/code/java/JavaOperatorType;
      // 47: areturn
      // 48: getstatic com/pnfsoftware/jeb/core/units/code/java/JavaOperatorType.LE Lcom/pnfsoftware/jeb/core/units/code/java/JavaOperatorType;
      // 4b: areturn
      // 4c: getstatic com/pnfsoftware/jeb/core/units/code/java/JavaOperatorType.LT Lcom/pnfsoftware/jeb/core/units/code/java/JavaOperatorType;
      // 4f: areturn
      // 50: getstatic com/pnfsoftware/jeb/core/units/code/java/JavaOperatorType.LOG_IDENT Lcom/pnfsoftware/jeb/core/units/code/java/JavaOperatorType;
      // 53: areturn
      // 54: getstatic com/pnfsoftware/jeb/core/units/code/java/JavaOperatorType.LOG_NOT Lcom/pnfsoftware/jeb/core/units/code/java/JavaOperatorType;
      // 57: areturn
      // 58: aconst_null
      // 59: areturn
   }

   public final JavaOperatorType reverse() {
      return this.getReverse();
   }

   public boolean isAnyOf(JavaOperatorType... var1) {
      for (JavaOperatorType var5 : var1) {
         if (var5 == this) {
            return true;
         }
      }

      return false;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public boolean isUnary() {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.RuntimeException: invalid constant type: Lcom/pnfsoftware/jeb/core/units/code/java/JavaOperatorType; with value 28
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ConstExprent.toJava(ConstExprent.java:356)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SwitchStatement.toJava(SwitchStatement.java:171)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.RootStatement.toJava(RootStatement.java:36)
      //   at org.jetbrains.java.decompiler.main.ClassWriter.writeMethod(ClassWriter.java:1326)
      //
      // Bytecode:
      // 00: getstatic com/pnfsoftware/jeb/core/units/code/java/JavaOperatorType$1.$SwitchMap$com$pnfsoftware$jeb$core$units$code$java$JavaOperatorType [I
      // 03: aload 0
      // 04: invokevirtual com/pnfsoftware/jeb/core/units/code/java/JavaOperatorType.ordinal ()I
      // 07: iaload
      // 08: tableswitch 74 7 20 72 72 72 72 72 72 72 72 72 72 72 72 72 72
      // 50: bipush 1
      // 51: ireturn
      // 52: bipush 0
      // 53: ireturn
   }

   public boolean isBinary() {
      switch (this) {
         case EQ:
         case NE:
         case GE:
         case GT:
         case LE:
         case LT:
         case ADD:
         case SUB:
         case MUL:
         case DIV:
         case REM:
         case AND:
         case OR:
         case XOR:
         case SHL:
         case SHR:
         case USHR:
         case LOG_OR:
         case LOG_AND:
         case INSTANCEOF:
         case CONCAT:
            return true;
         case LOG_NOT:
         case LOG_IDENT:
         case NEG:
         case NOT:
         case CAST_TO_BYTE:
         case CAST_TO_CHAR:
         case CAST_TO_SHORT:
         case CAST_TO_INT:
         case CAST_TO_LONG:
         case CAST_TO_FLOAT:
         case CAST_TO_DOUBLE:
         case CAST_TO_BOOLEAN:
         case CAST_TO_OBJECT:
         case CAST_CONVERSION:
         default:
            return false;
      }
   }

   public boolean isArithmetic() {
      switch (this) {
         case NEG:
         case NOT:
         case ADD:
         case SUB:
         case MUL:
         case DIV:
         case REM:
         case AND:
         case OR:
         case XOR:
         case SHL:
         case SHR:
         case USHR:
            return true;
         case CAST_TO_BYTE:
         case CAST_TO_CHAR:
         case CAST_TO_SHORT:
         case CAST_TO_INT:
         case CAST_TO_LONG:
         case CAST_TO_FLOAT:
         case CAST_TO_DOUBLE:
         case CAST_TO_BOOLEAN:
         case CAST_TO_OBJECT:
         case CAST_CONVERSION:
         default:
            return false;
      }
   }

   public boolean isLogical() {
      switch (this) {
         case EQ:
         case NE:
         case GE:
         case GT:
         case LE:
         case LT:
         case LOG_NOT:
         case LOG_IDENT:
         case LOG_OR:
         case LOG_AND:
         case INSTANCEOF:
            return true;
         case NEG:
         case NOT:
         case CAST_TO_BYTE:
         case CAST_TO_CHAR:
         case CAST_TO_SHORT:
         case CAST_TO_INT:
         case CAST_TO_LONG:
         case CAST_TO_FLOAT:
         case CAST_TO_DOUBLE:
         case CAST_TO_BOOLEAN:
         case CAST_TO_OBJECT:
         case CAST_CONVERSION:
         case ADD:
         case SUB:
         case MUL:
         case DIV:
         case REM:
         case AND:
         case OR:
         case XOR:
         case SHL:
         case SHR:
         case USHR:
         default:
            return false;
      }
   }

   public boolean isCast() {
      switch (this) {
         case CAST_TO_BYTE:
         case CAST_TO_CHAR:
         case CAST_TO_SHORT:
         case CAST_TO_INT:
         case CAST_TO_LONG:
         case CAST_TO_FLOAT:
         case CAST_TO_DOUBLE:
         case CAST_TO_BOOLEAN:
         case CAST_TO_OBJECT:
         case CAST_CONVERSION:
            return true;
         default:
            return false;
      }
   }

   public boolean isCastToPrimitive() {
      switch (this) {
         case CAST_TO_BYTE:
         case CAST_TO_CHAR:
         case CAST_TO_SHORT:
         case CAST_TO_INT:
         case CAST_TO_LONG:
         case CAST_TO_FLOAT:
         case CAST_TO_DOUBLE:
         case CAST_TO_BOOLEAN:
            return true;
         default:
            return false;
      }
   }

   static {
      prec_table.put(LOG_IDENT, 1);
      prec_table.put(NEG, 2);
      prec_table.put(NOT, 2);
      prec_table.put(LOG_NOT, 2);
      prec_table.put(MUL, 3);
      prec_table.put(DIV, 3);
      prec_table.put(REM, 3);
      prec_table.put(ADD, 4);
      prec_table.put(SUB, 4);
      prec_table.put(CONCAT, 4);
      prec_table.put(SHL, 5);
      prec_table.put(SHR, 5);
      prec_table.put(USHR, 5);
      prec_table.put(LT, 6);
      prec_table.put(LE, 6);
      prec_table.put(GT, 6);
      prec_table.put(GE, 6);
      prec_table.put(INSTANCEOF, 6);
      prec_table.put(EQ, 7);
      prec_table.put(NE, 7);
      prec_table.put(AND, 8);
      prec_table.put(XOR, 9);
      prec_table.put(OR, 10);
      prec_table.put(LOG_AND, 11);
      prec_table.put(LOG_OR, 12);
      asso_table.put(NEG, JavaOperatorType.Associativity.RIGHT);
      asso_table.put(NOT, JavaOperatorType.Associativity.RIGHT);
      asso_table.put(LOG_NOT, JavaOperatorType.Associativity.RIGHT);
      asso_table.put(MUL, JavaOperatorType.Associativity.LEFT);
      asso_table.put(DIV, JavaOperatorType.Associativity.LEFT);
      asso_table.put(REM, JavaOperatorType.Associativity.LEFT);
      asso_table.put(ADD, JavaOperatorType.Associativity.LEFT);
      asso_table.put(SUB, JavaOperatorType.Associativity.LEFT);
      asso_table.put(CONCAT, JavaOperatorType.Associativity.LEFT);
      asso_table.put(SHL, JavaOperatorType.Associativity.LEFT);
      asso_table.put(SHR, JavaOperatorType.Associativity.LEFT);
      asso_table.put(USHR, JavaOperatorType.Associativity.LEFT);
      asso_table.put(LT, JavaOperatorType.Associativity.LEFT);
      asso_table.put(LE, JavaOperatorType.Associativity.LEFT);
      asso_table.put(GT, JavaOperatorType.Associativity.LEFT);
      asso_table.put(GE, JavaOperatorType.Associativity.LEFT);
      asso_table.put(INSTANCEOF, JavaOperatorType.Associativity.LEFT);
      asso_table.put(EQ, JavaOperatorType.Associativity.LEFT);
      asso_table.put(NE, JavaOperatorType.Associativity.LEFT);
      asso_table.put(AND, JavaOperatorType.Associativity.LEFT);
      asso_table.put(XOR, JavaOperatorType.Associativity.LEFT);
      asso_table.put(OR, JavaOperatorType.Associativity.LEFT);
      asso_table.put(LOG_AND, JavaOperatorType.Associativity.LEFT);
      asso_table.put(LOG_OR, JavaOperatorType.Associativity.LEFT);
      asso_table_per_precedence.put(2, JavaOperatorType.Associativity.RIGHT);
      asso_table_per_precedence.put(3, JavaOperatorType.Associativity.LEFT);
      asso_table_per_precedence.put(4, JavaOperatorType.Associativity.LEFT);
      asso_table_per_precedence.put(5, JavaOperatorType.Associativity.LEFT);
      asso_table_per_precedence.put(6, JavaOperatorType.Associativity.LEFT);
      asso_table_per_precedence.put(7, JavaOperatorType.Associativity.LEFT);
      asso_table_per_precedence.put(8, JavaOperatorType.Associativity.LEFT);
      asso_table_per_precedence.put(9, JavaOperatorType.Associativity.LEFT);
      asso_table_per_precedence.put(10, JavaOperatorType.Associativity.LEFT);
      asso_table_per_precedence.put(11, JavaOperatorType.Associativity.LEFT);
      asso_table_per_precedence.put(12, JavaOperatorType.Associativity.LEFT);
   }

   public static enum Associativity {
      LEFT,
      RIGHT;
   }
}
