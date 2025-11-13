package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.IllegalIntermediateExpressionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EDefUseInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.ETypeInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGroup;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGroupElt;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collection;

@Ser
public class ajq extends ala implements IEGroupElt {
   @SerId(1)
   private IEGroup pC;
   @SerId(2)
   private IEGeneric A;

   public ajq(IEGroup var1, IEGeneric var2) {
      if (var1 == null) {
         throw new NullPointerException("Collection cannot be null");
      } else if (var2 == null) {
         throw new NullPointerException("Index cannot be null");
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
         ajq var3 = (ajq)var1;
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
   public IEGroup getGroup() {
      return this.pC;
   }

   @Override
   public IEGeneric getIndex() {
      return this.A;
   }

   @Override
   public int getBitsize() {
      return this.pC.getElementBitsize();
   }

   @Override
   public int getPriority() {
      return 50;
   }

   @Override
   public void getUsed(EDefUseInfo var1) {
      this.pC.getUsed(var1);
      this.A.getUsed(var1);
   }

   @Override
   public void getDefinedOrUsedAsDestination(EDefUseInfo var1) {
      this.pC.getDefinedOrUsedAsDestination(var1);
      this.A.getUsed(var1);
   }

   @Override
   public boolean accessesMemory() {
      return this.pC.accessesMemory() || this.A.accessesMemory();
   }

   @Override
   public int replaceVar(IEVar var1, IEGeneric var2) throws IllegalIntermediateExpressionException {
      int var3 = 0;
      if (this.A == var1) {
         A(this.A, var2);
         this.A = var2.duplicate();
         var3++;
      } else {
         var3 += this.A.replaceVar(var1, var2);
      }

      return var3;
   }

   @Override
   public void collectSubExpressions(Collection var1) {
      var1.add(this.pC);
      var1.add(this.A);
   }

   @Override
   public boolean replaceSubExpression(IEGeneric var1, IEGeneric var2) throws IllegalIntermediateExpressionException {
      pC(var1, var2);
      if (var1 == this.A) {
         A(this.A, var2);
         this.A = var2;
         return true;
      } else if (var1 == this.pC) {
         A(this.pC, var2);
         this.pC = (ajp)var2;
         return true;
      } else {
         return false;
      }
   }

   @Override
   public void updateTypes(ETypeInfo var1) {
   }

   public ajq pC() {
      return (ajq)this.pC(new ajq((IEGroup)this.pC.duplicate(), this.A.duplicate()));
   }

   @Override
   public IEImm evaluate(EState var1) {
      IEImm var2 = this.A.evaluate(var1);
      if (var2 == null) {
         return null;
      } else {
         long var3 = var2.getValueAsLong();
         if (var3 >= 0L && var3 < this.pC.getElementsCount()) {
            IEGeneric var5 = this.pC.getElement((int)var3);
            return var5.evaluate(var1);
         } else {
            throw new RuntimeException("Out of bound in IR evaluation -- TBI: handle this (native exception)");
         }
      }
   }

   @Override
   public ICElement generateC(IERoutineContext var1, ICMethod var2) {
      ICExpression var3 = (ICExpression)this.pC.generateC(var1, var2);
      ICExpression var4 = (ICExpression)this.A.generateC(var1, var2);
      return var2.getElementFactory().createArrayElement(var3, var4);
   }

   @Override
   public void pC(akz var1) {
      var1.pC(this.pC);
      var1.bracket();
      var1.pC(this.A);
      var1.bracketClose();
      var1.A(this);
   }
}
