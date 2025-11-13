package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COperatorType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class yP extends pF {
   @SerId(1)
   private ala A;

   private yP(ala var1, ala var2) {
      super(var1);
      this.A = var2;
   }

   public ala pC() {
      return this.A;
   }

   @Override
   public ICElement generateC(IERoutineContext var1, ICMethod var2) {
      ICElement var3 = super.generateC(var1, var2);
      if (var3 instanceof ICOperation && ((ICOperation)var3).getOperatorType() == COperatorType.COND) {
         ICExpression var4 = ((ICOperation)var3).getFirstOperand();
         if (var4 instanceof ICPredicate && ((ICPredicate)var4).getExpression() instanceof ICOperation) {
            ICOperation var5 = (ICOperation)((ICPredicate)var4).getExpression();
            if (var5.getOperatorType() == COperatorType.CAST) {
               return new wE((ICOperation)var3, var5.getFirstOperand().duplicate());
            }
         }
      }

      return var3;
   }

   @Override
   public ala A() {
      return new yP(this.pC.A(), this.A.A());
   }

   public static ala pC(ala var0, ala var1) {
      return (ala)(var0 instanceof ajr ? var0 : new yP(var0, var1));
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      return 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      return this.equalsEx(var1, true);
   }

   @Override
   public boolean equalsEx(Object var1, boolean var2) {
      if (this == var1) {
         return true;
      } else if (!super.equalsEx(var1, var2)) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         yP var3 = (yP)var1;
         if (this.A == null) {
            if (var3.A != null) {
               return false;
            }
         } else if (!this.A.equalsEx(var3.A, var2)) {
            return false;
         }

         return true;
      }
   }
}
