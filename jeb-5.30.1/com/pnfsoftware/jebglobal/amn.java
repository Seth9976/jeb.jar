package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.IllegalIntermediateExpressionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EDefUseInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.ETypeInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IERange;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collection;

@Ser
public class amn extends ane implements IESlice {
   @SerId(1)
   private IEGeneric q;
   @SerId(2)
   private IERange RF;

   amn(IEGeneric var1, int var2, int var3) {
      this(var1, new amj(var2, var3));
   }

   private amn(IEGeneric var1, IERange var2) {
      if (var1 == null) {
         throw new NullPointerException("Null expression");
      } else if (var2 == null) {
         throw new NullPointerException("Null range");
      } else if (var2.getEnd() > var1.getBitsize()) {
         throw new IllegalArgumentException(Strings.ff("End of range exceeds expression's bitsize: %d > %d", var2.getEnd(), var1.getBitsize()));
      } else {
         this.q = var1;
         this.RF = var2;
      }
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + (this.q == null ? 0 : this.q.hashCode());
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
         amn var3 = (amn)var1;
         if (this.q == null) {
            if (var3.q != null) {
               return false;
            }
         } else if (!this.q.equalsEx(var3.q, var2)) {
            return false;
         }

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

   @Override
   public IEGeneric getWholeExpression() {
      return this.q;
   }

   @Override
   public IERange getRange() {
      return this.RF;
   }

   @Override
   public int getBitStart() {
      return this.RF.getBegin();
   }

   @Override
   public int getBitEnd() {
      return this.RF.getEnd();
   }

   @Override
   public int getBitsize() {
      return this.RF.getRangeLength();
   }

   @Override
   public int getPriority() {
      return 40;
   }

   @Override
   public void getDefinedOrUsedAsDestination(EDefUseInfo var1) {
      if (!(this.q instanceof IEVar var2)) {
         this.q.getDefinedOrUsedAsDestination(var1);
      } else {
         int var3 = amy.q(var2.getId(), this.RF.getBegin());
         var1.getDef().add(var2, var3, this.RF.getRangeLength());
         if (this.getBitStart() > 0 || this.getBitEnd() < this.q.getBitsize()) {
            var1.getUse().add(var2, var3, this.RF.getRangeLength());
         }
      }
   }

   @Override
   public void getUsed(EDefUseInfo var1) {
      if (this.q instanceof IEVar var2) {
         int var3 = amy.q(var2.getId(), this.RF.getBegin());
         var1.getUse().add(var2, var3, this.RF.getRangeLength());
      } else {
         this.q.getUsed(var1);
      }
   }

   @Override
   public boolean accessesMemory() {
      return this.q.accessesMemory();
   }

   @Override
   public int replaceVar(IEVar var1, IEGeneric var2) throws IllegalIntermediateExpressionException {
      int var3 = 0;
      if (this.q == var1) {
         RF(this.q, var2);
         this.q = var2.duplicate();
         var3++;
      } else {
         var3 += this.q.replaceVar(var1, var2);
      }

      return var3;
   }

   @Override
   public void collectSubExpressions(Collection var1) {
      var1.add(this.q);
      var1.add(this.RF);
   }

   @Override
   public boolean replaceSubExpression(IEGeneric var1, IEGeneric var2) {
      if (var1 == this.q) {
         if (var2.getBitsize() < this.RF.getEnd()) {
            throw new IllegalIntermediateExpressionException("New expression is too short for range");
         } else {
            this.q = var2;
            return true;
         }
      } else if (var1 == this.RF) {
         IERange var3 = (IERange)var2;
         if (var3.getRangeLength() != this.RF.getRangeLength()) {
            throw new IllegalIntermediateExpressionException("New range does not conserve bit length");
         } else if (this.q.getBitsize() < var3.getEnd()) {
            throw new IllegalIntermediateExpressionException("Expression is too short for new range");
         } else {
            this.RF = var3;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void updateTypes(ETypeInfo var1) {
      this.q.updateTypes(var1);
   }

   public amn q() {
      return (amn)this.q(new amn(this.q.duplicate(), this.RF));
   }

   @Override
   public IEImm evaluate(EState var1) {
      IEImm var2 = this.q.evaluate(var1);
      if (var2 == null) {
         return null;
      } else {
         var2 = var2._shr(this.getBitStart());
         return var2.truncate(this.getBitsize());
      }
   }

   @Override
   public ICElement generateC(IERoutineContext var1, ICMethod var2) {
      IEVar var3 = new apc(this).q(var1, var2);
      return var3.generateC(var1, var2);
   }

   @Override
   public void q(and var1) {
      var1.q(this.q);
      var1.q(this.RF);
      var1.RF(this);
   }
}
