package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.List;

public class ccz {
   private static final ILogger pC = GlobalLog.getLogger(ccz.class);
   private List A = new ArrayList();
   private StringBuilder kS = new StringBuilder();

   public void pC(IDExpression var1) {
      if (var1 instanceof IDImm var3) {
         this.pC(var3);
      } else if (var1 instanceof IDVar var4) {
         this.pC(var4);
      } else {
         if (!(var1 instanceof IDOperation var2)) {
            throw new asz();
         }

         this.pC(var2);
      }
   }

   public int A(IDExpression var1) {
      int var2 = var1.getType().getSlotCount();
      if (var2 == 1) {
         return 32;
      } else if (var2 == 2) {
         return 64;
      } else {
         throw new RuntimeException();
      }
   }

   public void pC(IDImm var1) {
      IJavaType var3 = var1.getType();
      if (!var3.isFloatingPointType() && !var3.isObject() && !var3.isVoid()) {
         String var2;
         if (var3.isBoolean()) {
            boolean var4 = (var1.getValueAsLong() & 1L) == 1L;
            var2 = var4 ? "true" : "false";
         } else {
            int var5 = this.A(var1);
            if (var5 == 32) {
               var2 = Strings.ff("#x%08X", (int)var1.getRawValue());
            } else {
               if (var5 != 64) {
                  throw new RuntimeException();
               }

               var2 = Strings.ff("#x%016X", var1.getRawValue());
            }
         }

         this.kS.append(var2);
      } else {
         throw new asz();
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public void pC(IDOperation var1) {
      switch (var1.getOperatorType()) {
         case ADD:
            this.kS.append("(bvadd ");
            this.A(var1);
            this.kS.append(")");
            break;
         case SUB:
            this.kS.append("(bvsub ");
            this.A(var1);
            this.kS.append(")");
            break;
         case MUL:
            this.kS.append("(bvmul ");
            this.A(var1);
            this.kS.append(")");
            break;
         case DIV:
            this.kS.append("(bvsdiv ");
            this.A(var1);
            this.kS.append(")");
            break;
         case REM:
            this.kS.append("(bvsrem ");
            this.A(var1);
            this.kS.append(")");
            break;
         case NEG:
            this.kS.append("(bvneg ");
            this.pC(var1.getOperand2());
            this.kS.append(")");
            break;
         case EQ:
            this.kS.append("(= ");
            this.A(var1);
            this.kS.append(")");
            break;
         case NE:
            this.kS.append("(not (= ");
            this.A(var1);
            this.kS.append("))");
            break;
         case LOG_IDENT:
            IDExpression var3 = var1.getOperand2();
            if (!var3.getType().isBoolean()) {
               throw new asz();
            }

            this.pC(var3);
            break;
         case LOG_NOT:
            IDExpression var2 = var1.getOperand2();
            if (!var2.getType().isBoolean()) {
               throw new asz();
            }

            this.kS.append("(not ");
            this.pC(var2);
            this.kS.append(")");
            break;
         case LOG_OR:
            this.kS.append("(or ");
            this.kS(var1);
            this.kS.append(")");
            break;
         case LOG_AND:
            this.kS.append("(and ");
            this.kS(var1);
            this.kS.append(")");
            break;
         case LE:
            this.kS.append("(bvsle ");
            this.A(var1);
            this.kS.append(")");
            break;
         case LT:
            this.kS.append("(bvslt ");
            this.A(var1);
            this.kS.append(")");
            break;
         case GE:
            this.kS.append("(bvsge ");
            this.A(var1);
            this.kS.append(")");
            break;
         case GT:
            this.kS.append("(bvsgt ");
            this.A(var1);
            this.kS.append(")");
            break;
         case AND:
            this.kS.append("(bvand ");
            this.A(var1);
            this.kS.append(")");
            break;
         case OR:
            this.kS.append("(bvor ");
            this.A(var1);
            this.kS.append(")");
            break;
         case NOT:
            this.kS.append("(bvnot ");
            this.pC(var1.getOperand2());
            this.kS.append(")");
            break;
         case XOR:
            this.kS.append("(bvxor ");
            this.A(var1);
            this.kS.append(")");
            break;
         case SHL:
            this.kS.append("(bvshl ");
            this.A(var1);
            this.kS.append(")");
            break;
         case USHR:
            this.kS.append("(bvlshr ");
            this.A(var1);
            this.kS.append(")");
            break;
         case SHR:
            this.kS.append("(bvashr ");
            this.A(var1);
            this.kS.append(")");
            break;
         case COND_EXP:
            this.kS.append("(ite ");
            this.pC(var1.getCondPredicate());
            this.kS.append(" ");
            this.pC(var1.getCondTrueExpression());
            this.kS.append(" ");
            this.pC(var1.getCondFalseExpression());
            this.kS.append(")");
            break;
         default:
            throw new asz("No translation available for operation: " + var1.getOperatorType());
      }
   }

   public void pC(IDVar var1) {
      if (!this.A.contains(var1)) {
         this.A.add(var1);
      }

      this.kS.append(this.kS(var1));
   }

   private void A(IDOperation var1) {
      this.pC(var1.getOperand1());
      this.kS.append(" ");
      this.pC(var1.getOperand2());
   }

   private void kS(IDOperation var1) {
      IDExpression var2 = var1.getOperand1();
      if (!var2.getType().isBoolean()) {
         throw new asz();
      } else {
         IDExpression var3 = var1.getOperand2();
         if (!var3.getType().isBoolean()) {
            throw new asz();
         } else {
            this.pC(var2);
            this.kS.append(" ");
            this.pC(var3);
         }
      }
   }

   private String kS(IDExpression var1) {
      return Strings.ff("v%d", this.A.indexOf(var1));
   }

   public String[] pC() {
      StringBuilder var1 = new StringBuilder();

      for (IDVar var3 : this.A) {
         IJavaType var4 = var3.getType();
         if (var4.isFloatingPointType() || var4.isObject() || var4.isVoid()) {
            throw new asz();
         }

         String var5 = this.kS(var3);
         if (var4.isBoolean()) {
            Strings.ff(var1, "(declare-const %s Bool)\n", var5);
         } else {
            Strings.ff(var1, "(declare-const %s (_ BitVec %d))\n", var5, this.A(var3));
         }
      }

      String var6 = Strings.ff("%s(assert (and %s))\n", var1, this.kS);
      String var7 = Strings.ff("%s(assert (and (not %s)))\n", var1, this.kS);
      return new String[]{var6, var7};
   }
}
