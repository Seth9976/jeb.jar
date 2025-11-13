package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDGlobalContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class bqr {
   private IDGlobalContext pC;
   private IDImm A;
   private int kS;
   private List wS;
   private List UT;

   public static boolean pC(IDExpression var0) {
      if (!var0.visitDepthPre(new bqs())) {
         return false;
      } else if (!pC(var0, true)) {
         return false;
      } else {
         IDOperation var1 = var0.asOperation();
         return pC(var1.getOperand1(), false) || pC(var1.getOperand2(), false);
      }
   }

   private static boolean pC(IDExpression var0, boolean var1) {
      if (var0 instanceof IDOperation) {
         IDOperation var2 = var0.asOperation();
         JavaOperatorType var3 = var2.getOperatorType();
         if (var3.isAnyOf(JavaOperatorType.ADD, JavaOperatorType.SUB)) {
            return true;
         }

         if (var1) {
            return false;
         }

         if (var3 == JavaOperatorType.MUL) {
            return var2.getOperand2() instanceof IDImm;
         }

         if (var3 == JavaOperatorType.NOT) {
            return pC(var2.getOperand2(), true);
         }
      }

      return false;
   }

   public bqr(IDMethodContext var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.pC = var1.getGlobalContext();
      }
   }

   public List pC() {
      return Collections.unmodifiableList(this.UT);
   }

   public boolean A(IDExpression var1) {
      this.A = null;
      this.wS = new ArrayList();
      this.UT = new ArrayList();
      return !this.pC(var1, 1L) ? false : this.wS.size() >= 3 || this.wS.size() == 2 && this.A != null && !this.A.isZero();
   }

   private boolean pC(IDExpression var1, long var2) {
      if (var1 instanceof IDOperation) {
         IDOperation var4 = var1.asOperation();
         JavaOperatorType var5 = var4.getOperatorType();
         if (var5 == JavaOperatorType.ADD || var5 == JavaOperatorType.SUB) {
            if (!this.pC(var4.getOperand1(), var2)) {
               return false;
            }

            if (!this.pC(var4.getOperand2(), var5 == JavaOperatorType.ADD ? var2 : -var2)) {
               return false;
            }
         } else if (var5 == JavaOperatorType.MUL) {
            if (!(var4.getOperand2() instanceof IDImm)) {
               return false;
            }

            IDImm var6 = var4.getOperand2().asImm();
            if (!var6.canReadAsLong()) {
               return false;
            }

            var2 *= var6.getValueAsLong();
            if (!this.pC(var4.getOperand1(), var2)) {
               return false;
            }
         } else if (var5 == JavaOperatorType.NOT) {
            this.pC(-var2, var4.getOperand2(), false);
            this.pC(-var2, this.pC.createImm(1L, var4.getOperand2().getType()), true);
         } else {
            this.pC(var2, var1, false);
         }
      } else {
         this.pC(var2, var1, false);
      }

      return true;
   }

   private void pC(long var1, IDExpression var3, boolean var4) {
      if (var3 instanceof IDImm) {
         IDImm var5 = this.pC.createImm(var1, var3.getType());
         if (this.A == null) {
            this.A = var3.asImm()._mul(var5);
         } else {
            this.A = this.A._add(var3.asImm()._mul(var5));
         }

         if (!var4) {
            this.kS++;
         }
      } else {
         this.wS.add(new bqr.Av(var1, var3));
      }
   }

   public List pC(boolean var1) {
      HashMap var2 = new HashMap();
      int var3 = 0;

      for (bqr.Av var5 : this.wS) {
         Long var6 = (Long)var2.get(var5.A);
         if (var6 == null) {
            var6 = 0L;
         } else {
            var3++;
         }

         var2.put(var5.A, var6 + var5.pC);
      }

      if (var1 && var3 == 0 && this.kS < 2) {
         return null;
      } else {
         ArrayList var8 = new ArrayList();

         for (IDExpression var10 : var2.keySet()) {
            Long var7 = (Long)var2.get(var10);
            if (var7 == 0L) {
               this.UT.add(var10);
            } else {
               var8.add(new bqr.Av(var7, var10));
            }
         }

         return var8;
      }
   }

   public IDExpression A() {
      List var1 = this.pC(true);
      if (var1 == null) {
         return null;
      } else if (var1.isEmpty()) {
         if (this.A == null) {
            this.A = this.pC.createImm(0L, ((bqr.Av)this.wS.get(0)).A.getType());
         }

         return this.A;
      } else {
         bqu.Av var2 = new bqu.Av();
         var1.sort(new bqt(this, var2));
         Object var5 = ((bqr.Av)var1.get(0)).pC(this.pC);

         for (int var3 = 1; var3 < var1.size(); var3++) {
            bqr.Av var4 = (bqr.Av)var1.get(var3);
            if (var4.pC > 0L) {
               var5 = this.pC.createOperation(null, JavaOperatorType.ADD, (IDExpression)var5, var4.pC(this.pC));
            } else {
               var5 = this.pC.createOperation(null, JavaOperatorType.SUB, (IDExpression)var5, var4.A(this.pC));
            }
         }

         if (this.A != null && !this.A.isZero()) {
            if (this.A.getRawValue() > 0L) {
               var5 = this.pC.createOperation(null, JavaOperatorType.ADD, (IDExpression)var5, this.A);
            } else {
               var5 = this.pC.createOperation(null, JavaOperatorType.SUB, (IDExpression)var5, this.A._neg());
            }
         }

         return (IDExpression)var5;
      }
   }

   public static class Av {
      long pC;
      IDExpression A;

      public Av(long var1, IDExpression var3) {
         if (var1 != 0L && var3 != null) {
            this.pC = var1;
            this.A = var3;
         } else {
            throw new IllegalArgumentException();
         }
      }

      public IDExpression pC(IDGlobalContext var1) {
         return (IDExpression)(this.pC == 1L ? this.A : var1.createOperation(this.A.getType(), JavaOperatorType.MUL, this.A, this.A.spawn(this.pC)));
      }

      public IDExpression A(IDGlobalContext var1) {
         long var2 = this.pC > 0L ? this.pC : -this.pC;
         return (IDExpression)(var2 == 1L ? this.A : var1.createOperation(this.A.getType(), JavaOperatorType.MUL, this.A, this.A.spawn(var2)));
      }

      @Override
      public String toString() {
         return this.pC == 1L ? this.A.toString() : Strings.ff("%d x %s", this.pC, this.A);
      }
   }
}
