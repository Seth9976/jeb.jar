package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.DFormattingContext;
import com.pnfsoftware.jeb.core.units.code.java.IJavaOperator;
import com.pnfsoftware.jeb.core.units.code.java.IJavaOperatorFactory;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class cim implements IJavaOperator {
   @SerId(1)
   private JavaOperatorType q;
   @SerId(2)
   private IJavaType RF;
   @SerId(3)
   private IJavaOperatorFactory xK;

   cim(JavaOperatorType var1, IJavaOperatorFactory var2) {
      this.q = var1;
      this.RF = null;
      this.xK = var2;
   }

   cim(IJavaType var1, IJavaOperatorFactory var2) {
      this.q = JavaOperatorType.CAST_TO_OBJECT;
      this.RF = var1;
      this.xK = var2;
   }

   @Override
   public IJavaOperatorFactory getFactory() {
      return this.xK;
   }

   @Override
   public int hashCode() {
      return super.hashCode();
   }

   @Override
   public boolean equals(Object var1) {
      return this == var1;
   }

   @Override
   public boolean is(JavaOperatorType var1) {
      return this.q == var1;
   }

   @Override
   public boolean isAnyOf(JavaOperatorType... var1) {
      for (JavaOperatorType var5 : var1) {
         if (this.q == var5) {
            return true;
         }
      }

      return false;
   }

   @Override
   public boolean isNoneOf(JavaOperatorType... var1) {
      for (JavaOperatorType var5 : var1) {
         if (this.q == var5) {
            return false;
         }
      }

      return true;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   @Override
   public JavaOperatorType getOperatorType() {
      switch (this.q) {
         case ADD:
            return JavaOperatorType.ADD;
         case AND:
            return JavaOperatorType.AND;
         case CAST_TO_BOOLEAN:
            return JavaOperatorType.CAST_TO_BOOLEAN;
         case CAST_TO_BYTE:
            return JavaOperatorType.CAST_TO_BYTE;
         case CAST_TO_CHAR:
            return JavaOperatorType.CAST_TO_CHAR;
         case CAST_TO_DOUBLE:
            return JavaOperatorType.CAST_TO_DOUBLE;
         case CAST_TO_FLOAT:
            return JavaOperatorType.CAST_TO_FLOAT;
         case CAST_TO_INT:
            return JavaOperatorType.CAST_TO_INT;
         case CAST_TO_LONG:
            return JavaOperatorType.CAST_TO_LONG;
         case CAST_TO_OBJECT:
            return JavaOperatorType.CAST_TO_OBJECT;
         case CAST_TO_SHORT:
            return JavaOperatorType.CAST_TO_SHORT;
         case CONCAT:
            return JavaOperatorType.CONCAT;
         case DIV:
            return JavaOperatorType.DIV;
         case EQ:
            return JavaOperatorType.EQ;
         case GE:
            return JavaOperatorType.GE;
         case GT:
            return JavaOperatorType.GT;
         case INSTANCEOF:
            return JavaOperatorType.INSTANCEOF;
         case LE:
            return JavaOperatorType.LE;
         case LOG_AND:
            return JavaOperatorType.LOG_AND;
         case LOG_IDENT:
            return JavaOperatorType.LOG_IDENT;
         case LOG_NOT:
            return JavaOperatorType.LOG_NOT;
         case LOG_OR:
            return JavaOperatorType.LOG_OR;
         case LT:
            return JavaOperatorType.LT;
         case MUL:
            return JavaOperatorType.MUL;
         case NE:
            return JavaOperatorType.NE;
         case NEG:
            return JavaOperatorType.NEG;
         case NOT:
            return JavaOperatorType.NOT;
         case OR:
            return JavaOperatorType.OR;
         case REM:
            return JavaOperatorType.REM;
         case SHL:
            return JavaOperatorType.SHL;
         case SHR:
            return JavaOperatorType.SHR;
         case SUB:
            return JavaOperatorType.SUB;
         case USHR:
            return JavaOperatorType.USHR;
         case XOR:
            return JavaOperatorType.XOR;
         case COND_EXP:
            return JavaOperatorType.COND_EXP;
         case CAST_CONVERSION:
            return JavaOperatorType.CAST_CONVERSION;
         default:
            return null;
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   @Override
   public IJavaType getCastType() {
      switch (this.q) {
         case CAST_TO_BOOLEAN:
            return this.getFactory().getTypeFactory().getBoolean();
         case CAST_TO_BYTE:
            return this.getFactory().getTypeFactory().getByte();
         case CAST_TO_CHAR:
            return this.getFactory().getTypeFactory().getChar();
         case CAST_TO_DOUBLE:
            return this.getFactory().getTypeFactory().getDouble();
         case CAST_TO_FLOAT:
            return this.getFactory().getTypeFactory().getFloat();
         case CAST_TO_INT:
            return this.getFactory().getTypeFactory().getInt();
         case CAST_TO_LONG:
            return this.getFactory().getTypeFactory().getLong();
         case CAST_TO_OBJECT:
            return this.RF;
         case CAST_TO_SHORT:
            return this.getFactory().getTypeFactory().getShort();
         case CONCAT:
         case DIV:
         case EQ:
         case GE:
         case GT:
         case INSTANCEOF:
         case LE:
         case LOG_AND:
         case LOG_IDENT:
         case LOG_NOT:
         case LOG_OR:
         case LT:
         case MUL:
         case NE:
         case NEG:
         case NOT:
         case OR:
         case REM:
         case SHL:
         case SHR:
         case SUB:
         case USHR:
         case XOR:
         case COND_EXP:
         default:
            throw new RuntimeException();
         case CAST_CONVERSION:
            throw new RuntimeException("Cast-conversion, resulting type is unknown");
      }
   }

   @Override
   public boolean isUnary() {
      return this.q.isUnary();
   }

   @Override
   public boolean isBinary() {
      return this.q.isBinary();
   }

   @Override
   public boolean isArithmetic() {
      return this.q.isArithmetic();
   }

   @Override
   public boolean isLogical() {
      return this.q.isLogical();
   }

   @Override
   public boolean isCast() {
      return this.q.isCast();
   }

   @Override
   public boolean isCastToPrimitive() {
      return this.q.isCastToPrimitive();
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   @Override
   public String toString() {
      switch (this.q) {
         case ADD:
            return "+";
         case AND:
            return "&";
         case CAST_TO_BOOLEAN:
            return "(boolean)";
         case CAST_TO_BYTE:
            return "(byte)";
         case CAST_TO_CHAR:
            return "(char)";
         case CAST_TO_DOUBLE:
            return "(double)";
         case CAST_TO_FLOAT:
            return "(float)";
         case CAST_TO_INT:
            return "(int)";
         case CAST_TO_LONG:
            return "(long)";
         case CAST_TO_OBJECT:
            return Strings.ff("(%s)", this.RF);
         case CAST_TO_SHORT:
            return "(short)";
         case CONCAT:
            return "+";
         case DIV:
            return "/";
         case EQ:
            return "==";
         case GE:
            return ">=";
         case GT:
            return ">";
         case INSTANCEOF:
            return " instanceof ";
         case LE:
            return "<=";
         case LOG_AND:
            return "&&";
         case LOG_IDENT:
            return "";
         case LOG_NOT:
            return "!";
         case LOG_OR:
            return "||";
         case LT:
            return "<";
         case MUL:
            return "*";
         case NE:
            return "!=";
         case NEG:
            return "-";
         case NOT:
            return "~";
         case OR:
            return "|";
         case REM:
            return "%";
         case SHL:
            return "<<";
         case SHR:
            return ">>";
         case SUB:
            return "-";
         case USHR:
            return ">>>";
         case XOR:
            return "^";
         case COND_EXP:
            return ":?";
         case CAST_CONVERSION:
            return "(?conv)";
         default:
            throw new RuntimeException();
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   @Override
   public boolean isValidForCombinedAssignment() {
      switch (this.q) {
         case ADD:
         case AND:
         case DIV:
         case MUL:
         case OR:
         case REM:
         case SHL:
         case SHR:
         case SUB:
         case USHR:
         case XOR:
            return true;
         case CAST_TO_BOOLEAN:
         case CAST_TO_BYTE:
         case CAST_TO_CHAR:
         case CAST_TO_DOUBLE:
         case CAST_TO_FLOAT:
         case CAST_TO_INT:
         case CAST_TO_LONG:
         case CAST_TO_OBJECT:
         case CAST_TO_SHORT:
         case CONCAT:
         case EQ:
         case GE:
         case GT:
         case INSTANCEOF:
         case LE:
         case LOG_AND:
         case LOG_IDENT:
         case LOG_NOT:
         case LOG_OR:
         case LT:
         case NE:
         case NEG:
         case NOT:
         default:
            return false;
      }
   }

   @Override
   public int getPrecedenceDelta(IJavaOperator var1) throws Exception {
      int var2 = this.q.getPrecedence();
      int var3 = var1.getOperatorType().getPrecedence();
      if (var2 != 0 && var3 != 0) {
         return var2 - var3;
      } else {
         throw new Exception("Cannot calculate precedence delta");
      }
   }

   @Override
   public int getPrecedence() {
      int var1 = this.q.getPrecedence();
      if (var1 == 0) {
         throw new RuntimeException();
      } else {
         return var1;
      }
   }

   @Override
   public JavaOperatorType.Associativity getAssociativity() {
      JavaOperatorType.Associativity var1 = this.q.getAssociativity();
      if (var1 == null) {
         throw new RuntimeException();
      } else {
         return var1;
      }
   }

   @Override
   public IJavaOperator getReverse() {
      JavaOperatorType var1 = this.q.reverse();
      return var1 != null ? this.xK.get(var1) : null;
   }

   @Override
   public IJavaOperator getMirror() {
      JavaOperatorType var1 = this.q.mirror();
      return var1 != null ? this.xK.get(var1) : null;
   }

   @Override
   public boolean canCauseException() {
      return this.q == JavaOperatorType.DIV || this.q == JavaOperatorType.REM || this.isCast();
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   @Override
   public void format(DFormattingContext var1) {
      switch (this.q) {
         case ADD:
            var1.append("+");
            break;
         case AND:
            var1.append("&");
            break;
         case CAST_TO_BOOLEAN:
            var1.append("(boolean)");
            break;
         case CAST_TO_BYTE:
            var1.append("(byte)");
            break;
         case CAST_TO_CHAR:
            var1.append("(char)");
            break;
         case CAST_TO_DOUBLE:
            var1.append("(double)");
            break;
         case CAST_TO_FLOAT:
            var1.append("(float)");
            break;
         case CAST_TO_INT:
            var1.append("(int)");
            break;
         case CAST_TO_LONG:
            var1.append("(long)");
            break;
         case CAST_TO_OBJECT:
            var1.paren();
            this.RF.format(var1);
            var1.parenClose();
            break;
         case CAST_TO_SHORT:
            var1.append("(short)");
            break;
         case CONCAT:
            var1.append("+");
            break;
         case DIV:
            var1.append("/");
            break;
         case EQ:
            var1.append("==");
            break;
         case GE:
            var1.append(">=");
            break;
         case GT:
            var1.append(">");
            break;
         case INSTANCEOF:
            var1.append(" instanceof ");
            break;
         case LE:
            var1.append("<=");
            break;
         case LOG_AND:
            var1.append("&&");
         case LOG_IDENT:
            break;
         case LOG_NOT:
            var1.append("!");
            break;
         case LOG_OR:
            var1.append("||");
            break;
         case LT:
            var1.append("<");
            break;
         case MUL:
            var1.append("*");
            break;
         case NE:
            var1.append("!=");
            break;
         case NEG:
            var1.append("-");
            break;
         case NOT:
            var1.append("~");
            break;
         case OR:
            var1.append("|");
            break;
         case REM:
            var1.append("%");
            break;
         case SHL:
            var1.append("<<");
            break;
         case SHR:
            var1.append(">>");
            break;
         case SUB:
            var1.append("-");
            break;
         case USHR:
            var1.append(">>>");
            break;
         case XOR:
            var1.append("^");
            break;
         case COND_EXP:
         default:
            throw new RuntimeException();
         case CAST_CONVERSION:
            var1.append("(?conv)");
      }
   }
}
