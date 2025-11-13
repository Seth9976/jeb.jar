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
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collection;

@Ser
public class bp extends ane {
   @SerId(1)
   ane q;

   public bp(ane var1) {
      this.q = var1;
   }

   @Override
   public void getUsed(EDefUseInfo var1) {
      this.q.getUsed(var1);
   }

   @Override
   public void getDefinedOrUsedAsDestination(EDefUseInfo var1) {
      this.q.getDefinedOrUsedAsDestination(var1);
   }

   @Override
   public void q(and var1) {
      this.q.q(var1);
   }

   @Override
   public void collectSubExpressions(Collection var1) {
      var1.add(this.q);
   }

   @Override
   public int getBitsize() {
      return this.q.getBitsize();
   }

   @Override
   public boolean replaceSubExpression(IEGeneric var1, IEGeneric var2) throws IllegalIntermediateExpressionException {
      if (this.q == var1) {
         RF(this.q, var2);
         this.q = (ane)var2;
         return true;
      } else {
         return false;
      }
   }

   @Override
   public int getPriority() {
      return this.q.getPriority();
   }

   @Override
   public boolean accessesMemory() {
      return this.q.accessesMemory();
   }

   @Override
   public IEImm evaluate(EState var1) {
      return this.q.evaluate(var1);
   }

   @Override
   public int replaceVar(IEVar var1, IEGeneric var2) throws IllegalIntermediateExpressionException {
      return this.q.replaceVar(var1, var2);
   }

   @Override
   public void updateTypes(ETypeInfo var1) {
      this.q.updateTypes(var1);
   }

   @Override
   public ane RF() {
      return new bp(this.q.RF());
   }

   @Override
   public ICElement generateC(IERoutineContext var1, ICMethod var2) {
      return this.q.generateC(var1, var2);
   }

   public ane xK() {
      return this.q;
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      return 31 * var1 + (this.q == null ? 0 : this.q.hashCode());
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
         bp var3 = (bp)var1;
         if (this.q == null) {
            if (var3.q != null) {
               return false;
            }
         } else if (!this.q.equalsEx(var3.q, var2)) {
            return false;
         }

         return true;
      }
   }
}
