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
public class VQ extends bp {
   @SerId(1)
   private ane RF;

   private VQ(ane var1, ane var2) {
      super(var1);
      this.RF = var2;
   }

   public ane q() {
      return this.RF;
   }

   @Override
   public ICElement generateC(IERoutineContext var1, ICMethod var2) {
      ICElement var3 = super.generateC(var1, var2);
      if (var3 instanceof ICOperation && ((ICOperation)var3).getOperatorType() == COperatorType.COND) {
         ICExpression var4 = ((ICOperation)var3).getFirstOperand();
         if (var4 instanceof ICPredicate && ((ICPredicate)var4).getExpression() instanceof ICOperation) {
            ICOperation var5 = (ICOperation)((ICPredicate)var4).getExpression();
            if (var5.getOperatorType() == COperatorType.CAST) {
               return new yK((ICOperation)var3, var5.getFirstOperand().duplicate());
            }
         }
      }

      return var3;
   }

   @Override
   public ane RF() {
      return new VQ(this.q.RF(), this.RF.RF());
   }

   public static ane q(ane var0, ane var1) {
      return (ane)(var0 instanceof alu ? var0 : new VQ(var0, var1));
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      return 31 * var1 + (this.RF == null ? 0 : this.RF.hashCode());
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
         VQ var3 = (VQ)var1;
         if (this.RF == null) {
            if (var3.RF != null) {
               return false;
            }
         } else if (!this.RF.equalsEx(var3.RF, var2)) {
            return false;
         }

         return true;
      }
   }
}
