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
public class alt extends ane implements IEGroupElt {
   @SerId(1)
   private IEGroup q;
   @SerId(2)
   private IEGeneric RF;

   public alt(IEGroup var1, IEGeneric var2) {
      if (var1 == null) {
         throw new NullPointerException("Collection cannot be null");
      } else if (var2 == null) {
         throw new NullPointerException("Index cannot be null");
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
         alt var3 = (alt)var1;
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
   public IEGroup getGroup() {
      return this.q;
   }

   @Override
   public IEGeneric getIndex() {
      return this.RF;
   }

   @Override
   public int getBitsize() {
      return this.q.getElementBitsize();
   }

   @Override
   public int getPriority() {
      return 50;
   }

   @Override
   public void getUsed(EDefUseInfo var1) {
      this.q.getUsed(var1);
      this.RF.getUsed(var1);
   }

   @Override
   public void getDefinedOrUsedAsDestination(EDefUseInfo var1) {
      this.q.getDefinedOrUsedAsDestination(var1);
      this.RF.getUsed(var1);
   }

   @Override
   public boolean accessesMemory() {
      return this.q.accessesMemory() || this.RF.accessesMemory();
   }

   @Override
   public int replaceVar(IEVar var1, IEGeneric var2) throws IllegalIntermediateExpressionException {
      int var3 = 0;
      if (this.RF == var1) {
         RF(this.RF, var2);
         this.RF = var2.duplicate();
         var3++;
      } else {
         var3 += this.RF.replaceVar(var1, var2);
      }

      return var3;
   }

   @Override
   public void collectSubExpressions(Collection var1) {
      var1.add(this.q);
      var1.add(this.RF);
   }

   @Override
   public boolean replaceSubExpression(IEGeneric var1, IEGeneric var2) throws IllegalIntermediateExpressionException {
      q(var1, var2);
      if (var1 == this.RF) {
         RF(this.RF, var2);
         this.RF = var2;
         return true;
      } else if (var1 == this.q) {
         RF(this.q, var2);
         this.q = (als)var2;
         return true;
      } else {
         return false;
      }
   }

   @Override
   public void updateTypes(ETypeInfo var1) {
   }

   public alt q() {
      return (alt)this.q(new alt((IEGroup)this.q.duplicate(), this.RF.duplicate()));
   }

   @Override
   public IEImm evaluate(EState var1) {
      IEImm var2 = this.RF.evaluate(var1);
      if (var2 == null) {
         return null;
      } else {
         long var3 = var2.getValueAsLong();
         if (var3 >= 0L && var3 < this.q.getElementsCount()) {
            IEGeneric var5 = this.q.getElement((int)var3);
            return var5.evaluate(var1);
         } else {
            throw new RuntimeException("Out of bound in IR evaluation -- TBI: handle this (native exception)");
         }
      }
   }

   @Override
   public ICElement generateC(IERoutineContext var1, ICMethod var2) {
      ICExpression var3 = (ICExpression)this.q.generateC(var1, var2);
      ICExpression var4 = (ICExpression)this.RF.generateC(var1, var2);
      return var2.getElementFactory().createArrayElement(var3, var4);
   }

   @Override
   public void q(and var1) {
      var1.q(this.q);
      var1.bracket();
      var1.q(this.RF);
      var1.bracketClose();
      var1.RF(this);
   }
}
