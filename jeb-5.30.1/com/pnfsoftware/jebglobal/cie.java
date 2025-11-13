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
import java.util.Collections;
import java.util.List;

public class cie {
   private static final ILogger q = GlobalLog.getLogger(cie.class);
   private List RF = new ArrayList();
   private StringBuilder xK = new StringBuilder();

   public cie() {
      this.RF = new ArrayList();
      this.xK = new StringBuilder();
   }

   public cie(cie var1) {
      this.RF = new ArrayList(var1.RF);
      this.xK = new StringBuilder(var1.xK);
   }

   public List q() {
      return Collections.unmodifiableList(this.RF);
   }

   public String q(IDVar var1) {
      int var2 = this.RF.indexOf(var1);
      return var2 < 0 ? null : "v" + var2;
   }

   public StringBuilder RF() {
      return this.xK;
   }

   public void q(IDExpression var1) {
      if (var1 instanceof IDImm) {
         this.q((IDImm)var1);
      } else if (var1 instanceof IDVar) {
         this.RF((IDVar)var1);
      } else {
         if (!(var1 instanceof IDOperation)) {
            throw new cig();
         }

         this.q((IDOperation)var1);
      }
   }

   public int RF(IDExpression var1) {
      int var2 = var1.getType().getSlotCount();
      if (var2 == 1) {
         return 32;
      } else if (var2 == 2) {
         return 64;
      } else {
         throw new RuntimeException();
      }
   }

   public void q(IDImm var1) {
      IJavaType var3 = var1.getType();
      if (!var3.isFloatingPointType() && !var3.isObject() && !var3.isVoid()) {
         String var2;
         if (var3.isBoolean()) {
            boolean var4 = (var1.getValueAsLong() & 1L) == 1L;
            var2 = var4 ? "true" : "false";
         } else {
            int var5 = this.RF(var1);
            if (var5 == 32) {
               var2 = Strings.ff("#x%08X", (int)var1.getRawValue());
            } else {
               if (var5 != 64) {
                  throw new RuntimeException();
               }

               var2 = Strings.ff("#x%016X", var1.getRawValue());
            }
         }

         this.xK.append(var2);
      } else {
         throw new cig();
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public void q(IDOperation var1) {
      switch (var1.getOperatorType()) {
         case ADD:
            this.xK.append("(bvadd ");
            this.RF(var1);
            this.xK.append(")");
            break;
         case SUB:
            this.xK.append("(bvsub ");
            this.RF(var1);
            this.xK.append(")");
            break;
         case MUL:
            this.xK.append("(bvmul ");
            this.RF(var1);
            this.xK.append(")");
            break;
         case DIV:
            this.xK.append("(bvsdiv ");
            this.RF(var1);
            this.xK.append(")");
            break;
         case REM:
            this.xK.append("(bvsrem ");
            this.RF(var1);
            this.xK.append(")");
            break;
         case NEG:
            this.xK.append("(bvneg ");
            this.q(var1.getOperand2());
            this.xK.append(")");
            break;
         case EQ:
            this.xK.append("(= ");
            this.RF(var1);
            this.xK.append(")");
            break;
         case NE:
            this.xK.append("(not (= ");
            this.RF(var1);
            this.xK.append("))");
            break;
         case LOG_IDENT:
            IDExpression var3 = var1.getOperand2();
            if (!var3.getType().isBoolean()) {
               throw new cig();
            }

            this.q(var3);
            break;
         case LOG_NOT:
            IDExpression var2 = var1.getOperand2();
            if (!var2.getType().isBoolean()) {
               throw new cig();
            }

            this.xK.append("(not ");
            this.q(var2);
            this.xK.append(")");
            break;
         case LOG_OR:
            this.xK.append("(or ");
            this.xK(var1);
            this.xK.append(")");
            break;
         case LOG_AND:
            this.xK.append("(and ");
            this.xK(var1);
            this.xK.append(")");
            break;
         case LE:
            this.xK.append("(bvsle ");
            this.RF(var1);
            this.xK.append(")");
            break;
         case LT:
            this.xK.append("(bvslt ");
            this.RF(var1);
            this.xK.append(")");
            break;
         case GE:
            this.xK.append("(bvsge ");
            this.RF(var1);
            this.xK.append(")");
            break;
         case GT:
            this.xK.append("(bvsgt ");
            this.RF(var1);
            this.xK.append(")");
            break;
         case AND:
            this.xK.append("(bvand ");
            this.RF(var1);
            this.xK.append(")");
            break;
         case OR:
            this.xK.append("(bvor ");
            this.RF(var1);
            this.xK.append(")");
            break;
         case NOT:
            this.xK.append("(bvnot ");
            this.q(var1.getOperand2());
            this.xK.append(")");
            break;
         case XOR:
            this.xK.append("(bvxor ");
            this.RF(var1);
            this.xK.append(")");
            break;
         case SHL:
            this.xK.append("(bvshl ");
            this.RF(var1);
            this.xK.append(")");
            break;
         case USHR:
            this.xK.append("(bvlshr ");
            this.RF(var1);
            this.xK.append(")");
            break;
         case SHR:
            this.xK.append("(bvashr ");
            this.RF(var1);
            this.xK.append(")");
            break;
         case COND_EXP:
            this.xK.append("(ite ");
            this.q(var1.getCondPredicate());
            this.xK.append(" ");
            this.q(var1.getCondTrueExpression());
            this.xK.append(" ");
            this.q(var1.getCondFalseExpression());
            this.xK.append(")");
            break;
         default:
            throw new cig("No translation available for operation: " + var1.getOperatorType());
      }
   }

   public void RF(IDVar var1) {
      if (!this.RF.contains(var1)) {
         this.RF.add(var1);
      }

      this.xK.append(this.xK(var1));
   }

   private void RF(IDOperation var1) {
      this.q(var1.getOperand1());
      this.xK.append(" ");
      this.q(var1.getOperand2());
   }

   private void xK(IDOperation var1) {
      IDExpression var2 = var1.getOperand1();
      if (!var2.getType().isBoolean()) {
         throw new cig();
      } else {
         IDExpression var3 = var1.getOperand2();
         if (!var3.getType().isBoolean()) {
            throw new cig();
         } else {
            this.q(var2);
            this.xK.append(" ");
            this.q(var3);
         }
      }
   }

   private String xK(IDExpression var1) {
      return Strings.ff("v%d", this.RF.indexOf(var1));
   }

   public String[] xK() {
      StringBuilder var1 = new StringBuilder();

      for (IDVar var3 : this.RF) {
         IJavaType var4 = var3.getType();
         if (var4.isFloatingPointType() || var4.isObject() || var4.isVoid()) {
            throw new cig();
         }

         String var5 = this.xK(var3);
         if (var4.isBoolean()) {
            Strings.ff(var1, "(declare-const %s Bool)\n", var5);
         } else {
            Strings.ff(var1, "(declare-const %s (_ BitVec %d))\n", var5, this.RF((IDExpression)var3));
         }
      }

      String var6 = Strings.ff("%s(assert (and %s))\n", var1, this.xK);
      String var7 = Strings.ff("%s(assert (and (not %s)))\n", var1, this.xK);
      return new String[]{var6, var7};
   }
}
