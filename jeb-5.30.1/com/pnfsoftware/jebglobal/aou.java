package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.util.math.MathUtil;
import java.util.ArrayList;
import java.util.List;

public abstract class aou {
   private IEGeneric q;
   private IEGeneric RF;
   private List xK;

   public aou(IEGeneric var1, IEGeneric var2) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.q = var1;
         this.RF = var2;
      }
   }

   public List q() {
      if (this.xK != null) {
         return this.xK;
      } else {
         this.xK = new ArrayList();
         return !this.q(1L, this.q, this.RF) ? null : this.xK;
      }
   }

   public boolean q(List var1) {
      this.xK = var1;
      return this.q(1L, this.q, this.RF);
   }

   protected abstract aqc RF(long var1, IEGeneric var3, IEGeneric var4);

   private boolean q(long var1, IEGeneric var3, IEGeneric var4) {
      if (!(var3 instanceof IEVar) && !(var3 instanceof IEImm)) {
         if (var3 instanceof IEOperation var5) {
            OperationType var6 = ((IEOperation)var3).getOperationType();
            IEGeneric var7 = var5.getOperand1();
            IEGeneric var8 = var5.getOperand2();
            if (var6 == OperationType.ADD) {
               return this.q(var1, var7, var5) && this.q(var1, var8, var5);
            }

            if (var6 == OperationType.SUB) {
               return this.q(var1, var7, var5) && this.q(-var1, var8, var5);
            }

            if (var6 == OperationType.MUL) {
               if (var8 instanceof IEImm && ((IEImm)var8).canReadAsLong()) {
                  long var12 = ((IEImm)var8).getValueAsLong();
                  return this.q(var1 * var12, var7, var5);
               }

               if (var7 instanceof IEImm && ((IEImm)var7).canReadAsLong()) {
                  long var9 = ((IEImm)var7).getValueAsLong();
                  return this.q(var1 * var9, var8, var5);
               }
            } else if (var6 == OperationType.SHL && var8 instanceof IEImm && ((IEImm)var8).canReadAsLong()) {
               long var13 = MathUtil.pow(2L, ((IEImm)var8).getValueAsLong());
               return this.q(var1 * var13, var7, var5);
            }
         }

         IEGeneric var11 = this.q(var3, var4);
         if (var11 != null) {
            this.xK.add(this.RF(var1, var11, null));
            return true;
         } else {
            return false;
         }
      } else {
         this.xK.add(this.RF(var1, var3, var4));
         return true;
      }
   }

   protected IEGeneric q(IEGeneric var1, IEGeneric var2) {
      return null;
   }

   public static class eo implements aqc {
      private long q;
      private IEGeneric RF;
      private IEGeneric xK;

      public eo(long var1, IEGeneric var3, IEGeneric var4) {
         if (var1 != 0L && var3 != null) {
            this.q = var1;
            this.RF = var3;
            this.xK = var4;
         } else {
            throw new IllegalArgumentException();
         }
      }

      @Override
      public long q() {
         return this.q;
      }

      @Override
      public IEGeneric RF() {
         return this.RF;
      }

      @Override
      public IEGeneric xK() {
         return this.xK;
      }

      @Override
      public int hashCode() {
         int var1 = 1;
         var1 = 31 * var1 + (this.RF == null ? 0 : this.RF.hashCode());
         var1 = 31 * var1 + (int)(this.q ^ this.q >>> 32);
         return 31 * var1 + (this.xK == null ? 0 : this.xK.hashCode());
      }

      @Override
      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (var1 == null) {
            return false;
         } else if (this.getClass() != var1.getClass()) {
            return false;
         } else {
            aou.eo var2 = (aou.eo)var1;
            if (this.RF == null) {
               if (var2.RF != null) {
                  return false;
               }
            } else if (!this.RF.equals(var2.RF)) {
               return false;
            }

            if (this.q != var2.q) {
               return false;
            } else {
               if (this.xK == null) {
                  if (var2.xK != null) {
                     return false;
                  }
               } else if (!this.xK.equals(var2.xK)) {
                  return false;
               }

               return true;
            }
         }
      }
   }
}
