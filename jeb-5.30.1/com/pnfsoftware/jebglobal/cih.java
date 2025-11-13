package com.pnfsoftware.jebglobal;

import com.microsoft.z3.BitVecNum;
import com.microsoft.z3.Context;
import com.microsoft.z3.Expr;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDGlobalContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import com.pnfsoftware.jeb.util.base.AutoCloseable2;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class cih implements AutoCloseable2 {
   private static final ILogger q = GlobalLog.getLogger(cih.class);
   private Context RF;
   private List xK = new ArrayList();

   public cih() {
      this(null);
   }

   public cih(Context var1) {
      if (var1 == null) {
         var1 = new Context();
      }

      this.RF = var1;
   }

   public Context q() {
      if (this.RF == null) {
         throw new IllegalStateException("The Z3 context was closed");
      } else {
         return this.RF;
      }
   }

   @Override
   public void close() {
      if (this.RF == null) {
         throw new IllegalStateException("Already closed");
      } else {
         this.RF.close();
         this.RF = null;
      }
   }

   public Expr q(IDExpression var1) {
      if (var1 instanceof IDImm) {
         return this.q((IDImm)var1);
      } else if (var1 instanceof IDVar) {
         return this.q((IDVar)var1);
      } else if (var1 instanceof IDOperation) {
         return this.q((IDOperation)var1);
      } else {
         throw new RuntimeException();
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

   public Expr q(IDImm var1) {
      IJavaType var2 = var1.getType();
      if (var2.isFloatingPointType() || var2.isObject() || var2.isVoid()) {
         throw new RuntimeException();
      } else if (var2.isBoolean()) {
         boolean var4 = (var1.getValueAsLong() & 1L) == 1L;
         return this.RF.mkBool(var4);
      } else {
         int var3 = this.RF(var1);
         if (var3 == 32) {
            return this.RF.mkBV((int)var1.getRawValue(), 32);
         } else if (var3 == 64) {
            return this.RF.mkBV(var1.getRawValue(), 64);
         } else {
            throw new RuntimeException();
         }
      }
   }

   public Expr q(IDOperation var1) {
      switch (var1.getOperatorType()) {
         case ADD:
            return this.RF.mkBVAdd(this.q(var1.getOperand1()), this.q(var1.getOperand2()));
         case SUB:
            return this.RF.mkBVSub(this.q(var1.getOperand1()), this.q(var1.getOperand2()));
         case MUL:
            return this.RF.mkBVMul(this.q(var1.getOperand1()), this.q(var1.getOperand2()));
         case DIV:
            return this.RF.mkBVSDiv(this.q(var1.getOperand1()), this.q(var1.getOperand2()));
         case REM:
            return this.RF.mkBVSRem(this.q(var1.getOperand1()), this.q(var1.getOperand2()));
         case NEG:
            return this.RF.mkBVNeg(this.q(var1.getOperand2()));
         case NOT:
            return this.RF.mkBVNot(this.q(var1.getOperand2()));
         case AND:
            return this.RF.mkBVAND(this.q(var1.getOperand1()), this.q(var1.getOperand2()));
         case OR:
            return this.RF.mkBVOR(this.q(var1.getOperand1()), this.q(var1.getOperand2()));
         case XOR:
            return this.RF.mkBVXOR(this.q(var1.getOperand1()), this.q(var1.getOperand2()));
         case SHL:
            return this.RF.mkBVSHL(this.q(var1.getOperand1()), this.q(var1.getOperand2()));
         case USHR:
            return this.RF.mkBVLSHR(this.q(var1.getOperand1()), this.q(var1.getOperand2()));
         case SHR:
            return this.RF.mkBVASHR(this.q(var1.getOperand1()), this.q(var1.getOperand2()));
         case LOG_IDENT:
            return this.RF.mkNot(this.RF.mkNot(this.q(var1.getOperand2())));
         case LOG_NOT:
            return this.RF.mkNot(this.q(var1.getOperand2()));
         case EQ:
            return this.RF.mkEq(this.q(var1.getOperand1()), this.q(var1.getOperand2()));
         case NE:
            return this.RF.mkNot(this.RF.mkEq(this.q(var1.getOperand1()), this.q(var1.getOperand2())));
         case LOG_OR:
            return this.RF.mkOr(new Expr[]{this.q(var1.getOperand1()), this.q(var1.getOperand2())});
         case LOG_AND:
            return this.RF.mkAnd(new Expr[]{this.q(var1.getOperand1()), this.q(var1.getOperand2())});
         case LE:
            return this.RF.mkBVSLE(this.q(var1.getOperand1()), this.q(var1.getOperand2()));
         case LT:
            return this.RF.mkBVSLT(this.q(var1.getOperand1()), this.q(var1.getOperand2()));
         case GE:
            return this.RF.mkBVSGE(this.q(var1.getOperand1()), this.q(var1.getOperand2()));
         case GT:
            return this.RF.mkBVSGT(this.q(var1.getOperand1()), this.q(var1.getOperand2()));
         case COND_EXP:
            return this.RF.mkITE(this.q(var1.getCondPredicate()), this.q(var1.getCondTrueExpression()), this.q(var1.getCondFalseExpression()));
         default:
            throw new RuntimeException("No translation available for operation: " + var1.getOperatorType());
      }
   }

   public Expr q(IDVar var1) {
      IJavaType var2 = var1.getType();
      if (!var2.isFloatingPointType() && !var2.isObject()) {
         String var3 = this.RF(var1);
         return (Expr)(var2.isBoolean() ? this.RF.mkBoolConst(var3) : this.RF.mkBVConst(var3, this.RF((IDExpression)var1)));
      } else {
         throw new RuntimeException();
      }
   }

   public String RF(IDVar var1) {
      if (!this.xK.contains(var1)) {
         this.xK.add(var1);
      }

      return Strings.ff("v%d", this.xK.indexOf(var1));
   }

   public IDVar q(String var1) {
      if (!var1.startsWith("v")) {
         throw new RuntimeException();
      } else {
         return (IDVar)this.xK.get(Integer.parseInt(var1.substring(1)));
      }
   }

   public Map RF() {
      HashMap var1 = new HashMap();

      for (int var2 = 0; var2 < this.xK.size(); var2++) {
         var1.put("v" + var2, (IDVar)this.xK.get(var2));
      }

      return var1;
   }

   public cih.eo q(IDMethodContext var1) {
      return new cih.eo(var1, this.RF());
   }

   public static class eo {
      static Map q = new HashMap();
      static Map RF = new HashMap();
      private IDGlobalContext xK;
      private Map Dw;

      private eo(IDMethodContext var1, Map var2) {
         this.xK = var1.getGlobalContext();
         this.Dw = var2;
      }

      public IDExpression q(Expr var1) {
         if (var1 instanceof BitVecNum var5) {
            if (var5.getSortSize() == 32) {
               return this.xK.createInt(var5.getInt());
            } else if (var5.getSortSize() == 64) {
               return this.xK.createLong(var5.getLong());
            } else {
               throw new RuntimeException();
            }
         } else {
            Expr[] var2 = var1.getArgs();
            String var3 = var1.getFuncDecl().getName().toString();
            if (var2.length == 0) {
               IDVar var4 = (IDVar)this.Dw.get(var3);
               if (var4 != null) {
                  return var4;
               }
            }

            JavaOperatorType var6 = (JavaOperatorType)q.get(var3);
            if (var6 != null) {
               return this.xK.createOperation(null, var6, this.q(var2[0]), this.q(var2[1]));
            } else {
               var6 = (JavaOperatorType)RF.get(var3);
               if (var6 != null) {
                  return this.xK.createOperation(null, var6, null, this.q(var2[0]));
               } else {
                  throw new RuntimeException();
               }
            }
         }
      }

      static {
         q.put("bvadd", JavaOperatorType.ADD);
         q.put("bvsub", JavaOperatorType.SUB);
         q.put("bvmul", JavaOperatorType.MUL);
         q.put("bvsdiv", JavaOperatorType.DIV);
         q.put("bvsrem", JavaOperatorType.REM);
         q.put("bvand", JavaOperatorType.AND);
         q.put("bvor", JavaOperatorType.OR);
         q.put("bvxor", JavaOperatorType.XOR);
         q.put("bvshl", JavaOperatorType.SHL);
         q.put("bvlshr", JavaOperatorType.USHR);
         q.put("bvashr", JavaOperatorType.SHR);
         q.put("bvsle", JavaOperatorType.LE);
         q.put("bvslt", JavaOperatorType.LT);
         q.put("bvsge", JavaOperatorType.GE);
         q.put("bvsgt", JavaOperatorType.GT);
         q.put("eq", JavaOperatorType.EQ);
         RF.put("bvneg", JavaOperatorType.NEG);
         RF.put("bvnot", JavaOperatorType.NOT);
         q.put("not", JavaOperatorType.LOG_NOT);
      }
   }
}
