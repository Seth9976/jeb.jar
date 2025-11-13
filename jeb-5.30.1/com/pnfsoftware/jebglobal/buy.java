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

public class buy {
   private IDGlobalContext q;
   private IDImm RF;
   private int xK;
   private List Dw;
   private List Uv;
   private static final boolean oW = true;
   private static final boolean gO = false;

   public static boolean q(IDExpression var0) {
      if (!var0.visitDepthPre(new buz())) {
         return false;
      } else if (!q(var0, true)) {
         return false;
      } else {
         IDOperation var1 = var0.asOperation();
         return q(var1.getOperand1(), false) || q(var1.getOperand2(), false);
      }
   }

   private static boolean q(IDExpression var0, boolean var1) {
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
            return q(var2.getOperand2(), true);
         }
      }

      return false;
   }

   public buy(IDMethodContext var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.q = var1.getGlobalContext();
      }
   }

   public List q() {
      return Collections.unmodifiableList(this.Dw);
   }

   public IDImm RF() {
      return this.RF;
   }

   public List xK() {
      return Collections.unmodifiableList(this.Uv);
   }

   public boolean RF(IDExpression var1) {
      this.RF = null;
      this.Dw = new ArrayList();
      this.Uv = new ArrayList();
      return !this.q(var1, 1L) ? false : this.Dw.size() >= 3 || this.Dw.size() == 2 && this.RF != null && !this.RF.isZero();
   }

   private boolean q(IDExpression var1, long var2) {
      if (var1 instanceof IDOperation) {
         IDOperation var4 = var1.asOperation();
         JavaOperatorType var5 = var4.getOperatorType();
         if (var5 == JavaOperatorType.ADD || var5 == JavaOperatorType.SUB) {
            if (!this.q(var4.getOperand1(), var2)) {
               return false;
            }

            if (!this.q(var4.getOperand2(), var5 == JavaOperatorType.ADD ? var2 : -var2)) {
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
            if (!this.q(var4.getOperand1(), var2)) {
               return false;
            }
         } else if (var5 == JavaOperatorType.NOT) {
            this.q(-var2, var4.getOperand2(), false);
            this.q(-var2, this.q.createImm(1L, var4.getOperand2().getType()), true);
         } else {
            this.q(var2, var1, false);
         }
      } else {
         this.q(var2, var1, false);
      }

      return true;
   }

   private void q(long var1, IDExpression var3, boolean var4) {
      if (var3 instanceof IDImm) {
         IDImm var5 = this.q.createImm(var1, var3.getType());
         if (this.RF == null) {
            this.RF = var3.asImm()._mul(var5);
         } else {
            this.RF = this.RF._add(var3.asImm()._mul(var5));
         }

         if (!var4) {
            this.xK++;
         }
      } else {
         this.Dw.add(new buy.eo(var1, var3));
      }
   }

   public List q(boolean var1) {
      HashMap var2 = new HashMap();
      int var3 = 0;

      for (buy.eo var5 : this.Dw) {
         Long var6 = (Long)var2.get(var5.RF);
         if (var6 == null) {
            var6 = 0L;
         } else {
            var3++;
         }

         var2.put(var5.RF, var6 + var5.q);
      }

      if (var1 && var3 == 0 && this.xK < 2) {
         return null;
      } else {
         ArrayList var8 = new ArrayList();

         for (IDExpression var10 : var2.keySet()) {
            Long var7 = (Long)var2.get(var10);
            if (var7 == 0L) {
               this.Uv.add(var10);
            } else {
               var8.add(new buy.eo(var7, var10));
            }
         }

         return var8;
      }
   }

   public IDExpression Dw() {
      List var1 = this.q(true);
      if (var1 == null) {
         return null;
      } else if (var1.isEmpty()) {
         if (this.RF == null) {
            this.RF = this.q.createImm(0L, ((buy.eo)this.Dw.get(0)).RF.getType());
         }

         return this.RF;
      } else {
         bvb.eo var2 = new bvb.eo();
         var1.sort(new bva(this, var2));
         Object var5 = ((buy.eo)var1.get(0)).q(this.q);

         for (int var3 = 1; var3 < var1.size(); var3++) {
            buy.eo var4 = (buy.eo)var1.get(var3);
            if (var4.q > 0L) {
               var5 = this.q.createOperation(null, JavaOperatorType.ADD, (IDExpression)var5, var4.q(this.q));
            } else {
               var5 = this.q.createOperation(null, JavaOperatorType.SUB, (IDExpression)var5, var4.RF(this.q));
            }
         }

         if (this.RF != null && !this.RF.isZero()) {
            if (this.RF.getRawValue() > 0L) {
               var5 = this.q.createOperation(null, JavaOperatorType.ADD, (IDExpression)var5, this.RF);
            } else {
               var5 = this.q.createOperation(null, JavaOperatorType.SUB, (IDExpression)var5, this.RF._neg());
            }
         }

         return (IDExpression)var5;
      }
   }

   public static class eo {
      long q;
      IDExpression RF;

      public eo(long var1, IDExpression var3) {
         if (var1 != 0L && var3 != null) {
            this.q = var1;
            this.RF = var3;
         } else {
            throw new IllegalArgumentException();
         }
      }

      public long q() {
         return this.q;
      }

      public IDExpression RF() {
         return this.RF;
      }

      public IDExpression q(IDGlobalContext var1) {
         return (IDExpression)(this.q == 1L ? this.RF : var1.createOperation(this.RF.getType(), JavaOperatorType.MUL, this.RF, this.RF.spawn(this.q)));
      }

      public IDExpression RF(IDGlobalContext var1) {
         long var2 = this.q > 0L ? this.q : -this.q;
         return (IDExpression)(var2 == 1L ? this.RF : var1.createOperation(this.RF.getType(), JavaOperatorType.MUL, this.RF, this.RF.spawn(var2)));
      }

      @Override
      public String toString() {
         return this.q == 1L ? this.RF.toString() : Strings.ff("%d x %s", this.q, this.RF);
      }
   }
}
