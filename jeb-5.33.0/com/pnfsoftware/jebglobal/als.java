package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class als {
   private IERoutineContext pC;
   private IEImm A;
   private int kS;
   private List wS;
   private List UT;

   public static boolean pC(IEGeneric var0) {
      if (pC(var0, true)) {
         IEOperation var1 = var0.asOperation();
         if (pC(var1.getOperand1(), false)) {
            return true;
         }

         if (pC(var1.getOperand2(), false)) {
            return true;
         }
      }

      return false;
   }

   private static boolean pC(IEGeneric var0, boolean var1) {
      if (var0 instanceof IEOperation) {
         IEOperation var2 = var0.asOperation();
         OperationType var3 = var2.getOperationType();
         if (var3.isAnyOf(OperationType.ADD, OperationType.SUB)) {
            return true;
         }

         if (var1) {
            return false;
         }

         if (var3 == OperationType.MUL) {
            return var2.getOperand2() instanceof IEImm;
         }

         if (var3 == OperationType.NOT) {
            return pC(var2.getOperand1(), true);
         }
      }

      return false;
   }

   public als(IERoutineContext var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.pC = var1;
      }
   }

   public List pC() {
      return Collections.unmodifiableList(this.UT);
   }

   public boolean A(IEGeneric var1) {
      this.A = null;
      this.wS = new ArrayList();
      this.UT = new ArrayList();
      return !this.pC(var1, 1L) ? false : this.wS.size() >= 3 || this.wS.size() == 2 && this.A != null && !this.A.isZero();
   }

   private boolean pC(IEGeneric var1, long var2) {
      if (var1 instanceof IEOperation) {
         IEOperation var4 = var1.asOperation();
         OperationType var5 = var4.getOperationType();
         if (var5 == OperationType.ADD || var5 == OperationType.SUB) {
            if (!this.pC(var4.getOperand1(), var2)) {
               return false;
            }

            if (!this.pC(var4.getOperand2(), var5 == OperationType.ADD ? var2 : -var2)) {
               return false;
            }
         } else if (var5 == OperationType.MUL) {
            if (!(var4.getOperand2() instanceof IEImm)) {
               return false;
            }

            IEImm var6 = var4.getOperand2().asImm();
            if (!var6.canReadAsLong()) {
               return false;
            }

            var2 *= var6.getValueAsLong();
            if (!this.pC(var4.getOperand1(), var2)) {
               return false;
            }
         } else if (var5 == OperationType.NOT) {
            this.pC(-var2, var4.getOperand1(), false);
            this.pC(-var2, ajr.pC(1L, var4.getOperand1().getBitsize()), true);
         } else {
            this.pC(var2, var1, false);
         }
      } else {
         this.pC(var2, var1, false);
      }

      return true;
   }

   private void pC(long var1, IEGeneric var3, boolean var4) {
      if (var3 instanceof IEImm) {
         if (this.A == null) {
            this.A = var3.asImm()._mul(EUtil.imm(var1, var3.getBitsize()));
         } else {
            this.A = this.A._add(var3.asImm()._mul(EUtil.imm(var1, var3.getBitsize())));
         }

         if (!var4) {
            this.kS++;
         }
      } else {
         this.wS.add(new als.Av(var1, var3));
      }
   }

   public List pC(boolean var1) {
      HashMap var2 = new HashMap();
      int var3 = 0;

      for (als.Av var5 : this.wS) {
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

         for (IEGeneric var10 : var2.keySet()) {
            Long var7 = (Long)var2.get(var10);
            if (var7 == 0L) {
               this.UT.add(var10);
            } else {
               var8.add(new als.Av(var7, var10));
            }
         }

         return var8;
      }
   }

   public IEGeneric A() {
      List var1 = this.pC(true);
      if (var1 == null) {
         return null;
      } else if (var1.isEmpty()) {
         if (this.A == null) {
            this.A = EUtil.zero(((als.Av)this.wS.get(0)).A.getBitsize());
         }

         return this.A;
      } else {
         ake var2 = new ake(this.pC);
         var1.sort(new alt(this, var2));
         Object var5 = ((als.Av)var1.get(0)).pC();

         for (int var3 = 1; var3 < var1.size(); var3++) {
            als.Av var4 = (als.Av)var1.get(var3);
            if (var4.pC > 0L) {
               var5 = EUtil.add((IEGeneric)var5, var4.pC());
            } else {
               var5 = EUtil.sub((IEGeneric)var5, var4.A());
            }
         }

         if (this.A != null && !this.A.isZero()) {
            if (this.A._signum() > 0) {
               var5 = EUtil.add((IEGeneric)var5, this.A);
            } else {
               var5 = EUtil.sub((IEGeneric)var5, this.A._neg());
            }
         }

         return (IEGeneric)var5;
      }
   }

   public static class Av {
      long pC;
      IEGeneric A;

      public Av(long var1, IEGeneric var3) {
         if (var1 != 0L && var3 != null) {
            this.pC = var1;
            this.A = var3;
         } else {
            throw new IllegalArgumentException();
         }
      }

      public IEGeneric pC() {
         return (IEGeneric)(this.pC == 1L ? this.A : EUtil.mul(this.A, EUtil.imm(this.pC, this.A.getBitsize())));
      }

      public IEGeneric A() {
         long var1 = this.pC > 0L ? this.pC : -this.pC;
         return (IEGeneric)(var1 == 1L ? this.A : EUtil.mul(this.A, EUtil.imm(var1, this.A.getBitsize())));
      }

      @Override
      public String toString() {
         return this.pC == 1L ? this.A.toString() : Strings.ff("%d x %s", this.pC, this.A);
      }
   }
}
