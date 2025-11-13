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

public class anx {
   private IERoutineContext q;
   private IEImm RF;
   private int xK;
   private List Dw;
   private List Uv;
   private static final boolean oW = true;
   private static final boolean gO = false;

   public static boolean q(IEGeneric var0) {
      if (q(var0, true)) {
         IEOperation var1 = var0.asOperation();
         if (q(var1.getOperand1(), false)) {
            return true;
         }

         if (q(var1.getOperand2(), false)) {
            return true;
         }
      }

      return false;
   }

   private static boolean q(IEGeneric var0, boolean var1) {
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
            return q(var2.getOperand1(), true);
         }
      }

      return false;
   }

   public anx(IERoutineContext var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.q = var1;
      }
   }

   public List q() {
      return Collections.unmodifiableList(this.Dw);
   }

   public IEImm RF() {
      return this.RF;
   }

   public List xK() {
      return Collections.unmodifiableList(this.Uv);
   }

   public boolean RF(IEGeneric var1) {
      this.RF = null;
      this.Dw = new ArrayList();
      this.Uv = new ArrayList();
      return !this.q(var1, 1L) ? false : this.Dw.size() >= 3 || this.Dw.size() == 2 && this.RF != null && !this.RF.isZero();
   }

   private boolean q(IEGeneric var1, long var2) {
      if (var1 instanceof IEOperation) {
         IEOperation var4 = var1.asOperation();
         OperationType var5 = var4.getOperationType();
         if (var5 == OperationType.ADD || var5 == OperationType.SUB) {
            if (!this.q(var4.getOperand1(), var2)) {
               return false;
            }

            if (!this.q(var4.getOperand2(), var5 == OperationType.ADD ? var2 : -var2)) {
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
            if (!this.q(var4.getOperand1(), var2)) {
               return false;
            }
         } else if (var5 == OperationType.NOT) {
            this.q(-var2, var4.getOperand1(), false);
            this.q(-var2, alu.q(1L, var4.getOperand1().getBitsize()), true);
         } else {
            this.q(var2, var1, false);
         }
      } else {
         this.q(var2, var1, false);
      }

      return true;
   }

   private void q(long var1, IEGeneric var3, boolean var4) {
      if (var3 instanceof IEImm) {
         if (this.RF == null) {
            this.RF = var3.asImm()._mul(EUtil.imm(var1, var3.getBitsize()));
         } else {
            this.RF = this.RF._add(var3.asImm()._mul(EUtil.imm(var1, var3.getBitsize())));
         }

         if (!var4) {
            this.xK++;
         }
      } else {
         this.Dw.add(new anx.eo(var1, var3));
      }
   }

   public List q(boolean var1) {
      HashMap var2 = new HashMap();
      int var3 = 0;

      for (anx.eo var5 : this.Dw) {
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

         for (IEGeneric var10 : var2.keySet()) {
            Long var7 = (Long)var2.get(var10);
            if (var7 == 0L) {
               this.Uv.add(var10);
            } else {
               var8.add(new anx.eo(var7, var10));
            }
         }

         return var8;
      }
   }

   public IEGeneric Dw() {
      List var1 = this.q(true);
      if (var1 == null) {
         return null;
      } else if (var1.isEmpty()) {
         if (this.RF == null) {
            this.RF = EUtil.zero(((anx.eo)this.Dw.get(0)).RF.getBitsize());
         }

         return this.RF;
      } else {
         amh var2 = new amh(this.q);
         var1.sort(new any(this, var2));
         Object var5 = ((anx.eo)var1.get(0)).xK();

         for (int var3 = 1; var3 < var1.size(); var3++) {
            anx.eo var4 = (anx.eo)var1.get(var3);
            if (var4.q > 0L) {
               var5 = EUtil.add((IEGeneric)var5, var4.xK());
            } else {
               var5 = EUtil.sub((IEGeneric)var5, var4.Dw());
            }
         }

         if (this.RF != null && !this.RF.isZero()) {
            if (this.RF._signum() > 0) {
               var5 = EUtil.add((IEGeneric)var5, this.RF);
            } else {
               var5 = EUtil.sub((IEGeneric)var5, this.RF._neg());
            }
         }

         return (IEGeneric)var5;
      }
   }

   public static class eo {
      long q;
      IEGeneric RF;

      public eo(long var1, IEGeneric var3) {
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

      public IEGeneric RF() {
         return this.RF;
      }

      public IEGeneric xK() {
         return (IEGeneric)(this.q == 1L ? this.RF : EUtil.mul(this.RF, EUtil.imm(this.q, this.RF.getBitsize())));
      }

      public IEGeneric Dw() {
         long var1 = this.q > 0L ? this.q : -this.q;
         return (IEGeneric)(var1 == 1L ? this.RF : EUtil.mul(this.RF, EUtil.imm(var1, this.RF.getBitsize())));
      }

      @Override
      public String toString() {
         return this.q == 1L ? this.RF.toString() : Strings.ff("%d x %s", this.q, this.RF);
      }
   }
}
