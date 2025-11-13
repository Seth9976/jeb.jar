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
public class akk extends ala implements IESlice {
   @SerId(1)
   private IEGeneric pC;
   @SerId(2)
   private IERange A;

   akk(IEGeneric var1, int var2, int var3) {
      this(var1, new akg(var2, var3));
   }

   private akk(IEGeneric var1, IERange var2) {
      if (var1 == null) {
         throw new NullPointerException("Null expression");
      } else if (var2 == null) {
         throw new NullPointerException("Null range");
      } else if (var2.getEnd() > var1.getBitsize()) {
         throw new IllegalArgumentException(Strings.ff("End of range exceeds expression's bitsize: %d > %d", var2.getEnd(), var1.getBitsize()));
      } else {
         this.pC = var1;
         this.A = var2;
      }
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + (this.pC == null ? 0 : this.pC.hashCode());
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
         akk var3 = (akk)var1;
         if (this.pC == null) {
            if (var3.pC != null) {
               return false;
            }
         } else if (!this.pC.equalsEx(var3.pC, var2)) {
            return false;
         }

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

   @Override
   public IEGeneric getWholeExpression() {
      return this.pC;
   }

   @Override
   public IERange getRange() {
      return this.A;
   }

   @Override
   public int getBitStart() {
      return this.A.getBegin();
   }

   @Override
   public int getBitEnd() {
      return this.A.getEnd();
   }

   @Override
   public int getBitsize() {
      return this.A.getRangeLength();
   }

   @Override
   public int getPriority() {
      return 40;
   }

   @Override
   public void getDefinedOrUsedAsDestination(EDefUseInfo var1) {
      if (!(this.pC instanceof IEVar var2)) {
         this.pC.getDefinedOrUsedAsDestination(var1);
      } else {
         int var3 = aku.pC(var2.getId(), this.A.getBegin());
         var1.getDef().add(var2, var3, this.A.getRangeLength());
         if (this.getBitStart() > 0 || this.getBitEnd() < this.pC.getBitsize()) {
            var1.getUse().add(var2, var3, this.A.getRangeLength());
         }
      }
   }

   @Override
   public void getUsed(EDefUseInfo var1) {
      if (this.pC instanceof IEVar var2) {
         int var3 = aku.pC(var2.getId(), this.A.getBegin());
         var1.getUse().add(var2, var3, this.A.getRangeLength());
      } else {
         this.pC.getUsed(var1);
      }
   }

   @Override
   public boolean accessesMemory() {
      return this.pC.accessesMemory();
   }

   @Override
   public int replaceVar(IEVar var1, IEGeneric var2) throws IllegalIntermediateExpressionException {
      int var3 = 0;
      if (this.pC == var1) {
         A(this.pC, var2);
         this.pC = var2.duplicate();
         var3++;
      } else {
         var3 += this.pC.replaceVar(var1, var2);
      }

      return var3;
   }

   @Override
   public void collectSubExpressions(Collection var1) {
      var1.add(this.pC);
      var1.add(this.A);
   }

   @Override
   public boolean replaceSubExpression(IEGeneric var1, IEGeneric var2) {
      if (var1 == this.pC) {
         if (var2.getBitsize() < this.A.getEnd()) {
            throw new IllegalIntermediateExpressionException("New expression is too short for range");
         } else {
            this.pC = var2;
            return true;
         }
      } else if (var1 == this.A) {
         IERange var3 = (IERange)var2;
         if (var3.getRangeLength() != this.A.getRangeLength()) {
            throw new IllegalIntermediateExpressionException("New range does not conserve bit length");
         } else if (this.pC.getBitsize() < var3.getEnd()) {
            throw new IllegalIntermediateExpressionException("Expression is too short for new range");
         } else {
            this.A = var3;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void updateTypes(ETypeInfo var1) {
      this.pC.updateTypes(var1);
   }

   public akk pC() {
      return (akk)this.pC(new akk(this.pC.duplicate(), this.A));
   }

   @Override
   public IEImm evaluate(EState var1) {
      IEImm var2 = this.pC.evaluate(var1);
      if (var2 == null) {
         return null;
      } else {
         var2 = var2._shr(this.getBitStart());
         return var2.truncate(this.getBitsize());
      }
   }

   @Override
   public ICElement generateC(IERoutineContext var1, ICMethod var2) {
      IEVar var3 = new amv(this).pC(var1, var2);
      return var3.generateC(var1, var2);
   }

   @Override
   public void pC(akz var1) {
      var1.pC(this.pC);
      var1.pC(this.A);
      var1.A(this);
   }
}
