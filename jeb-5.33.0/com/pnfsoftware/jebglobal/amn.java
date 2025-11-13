package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.util.math.MathUtil;
import java.util.List;

public abstract class amn {
   private IEGeneric pC;
   private IEGeneric A;
   private List kS;

   public amn(IEGeneric var1, IEGeneric var2) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.pC = var1;
         this.A = var2;
      }
   }

   public boolean pC(List var1) {
      this.kS = var1;
      return this.pC(1L, this.pC, this.A);
   }

   protected abstract anr A(long var1, IEGeneric var3, IEGeneric var4);

   private boolean pC(long var1, IEGeneric var3, IEGeneric var4) {
      if (!(var3 instanceof IEVar) && !(var3 instanceof IEImm)) {
         if (var3 instanceof IEOperation var5) {
            OperationType var6 = ((IEOperation)var3).getOperationType();
            IEGeneric var7 = var5.getOperand1();
            IEGeneric var8 = var5.getOperand2();
            if (var6 == OperationType.ADD) {
               return this.pC(var1, var7, var5) && this.pC(var1, var8, var5);
            }

            if (var6 == OperationType.SUB) {
               return this.pC(var1, var7, var5) && this.pC(-var1, var8, var5);
            }

            if (var6 == OperationType.MUL) {
               if (var8 instanceof IEImm && ((IEImm)var8).canReadAsLong()) {
                  long var12 = ((IEImm)var8).getValueAsLong();
                  return this.pC(var1 * var12, var7, var5);
               }

               if (var7 instanceof IEImm && ((IEImm)var7).canReadAsLong()) {
                  long var9 = ((IEImm)var7).getValueAsLong();
                  return this.pC(var1 * var9, var8, var5);
               }
            } else if (var6 == OperationType.SHL && var8 instanceof IEImm && ((IEImm)var8).canReadAsLong()) {
               long var13 = MathUtil.pow(2L, ((IEImm)var8).getValueAsLong());
               return this.pC(var1 * var13, var7, var5);
            }
         }

         IEGeneric var11 = this.pC(var3, var4);
         if (var11 != null) {
            this.kS.add(this.A(var1, var11, null));
            return true;
         } else {
            return false;
         }
      } else {
         this.kS.add(this.A(var1, var3, var4));
         return true;
      }
   }

   protected IEGeneric pC(IEGeneric var1, IEGeneric var2) {
      return null;
   }
}
