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
public class pF extends ala {
   @SerId(1)
   ala pC;

   public pF(ala var1) {
      this.pC = var1;
   }

   @Override
   public void getUsed(EDefUseInfo var1) {
      this.pC.getUsed(var1);
   }

   @Override
   public void getDefinedOrUsedAsDestination(EDefUseInfo var1) {
      this.pC.getDefinedOrUsedAsDestination(var1);
   }

   @Override
   public void pC(akz var1) {
      this.pC.pC(var1);
   }

   @Override
   public void collectSubExpressions(Collection var1) {
      var1.add(this.pC);
   }

   @Override
   public int getBitsize() {
      return this.pC.getBitsize();
   }

   @Override
   public boolean replaceSubExpression(IEGeneric var1, IEGeneric var2) throws IllegalIntermediateExpressionException {
      if (this.pC == var1) {
         A(this.pC, var2);
         this.pC = (ala)var2;
         return true;
      } else {
         return false;
      }
   }

   @Override
   public int getPriority() {
      return this.pC.getPriority();
   }

   @Override
   public boolean accessesMemory() {
      return this.pC.accessesMemory();
   }

   @Override
   public IEImm evaluate(EState var1) {
      return this.pC.evaluate(var1);
   }

   @Override
   public int replaceVar(IEVar var1, IEGeneric var2) throws IllegalIntermediateExpressionException {
      return this.pC.replaceVar(var1, var2);
   }

   @Override
   public void updateTypes(ETypeInfo var1) {
      this.pC.updateTypes(var1);
   }

   @Override
   public ala A() {
      return new pF(this.pC.A());
   }

   @Override
   public ICElement generateC(IERoutineContext var1, ICMethod var2) {
      return this.pC.generateC(var1, var2);
   }

   public ala kS() {
      return this.pC;
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      return 31 * var1 + (this.pC == null ? 0 : this.pC.hashCode());
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
         pF var3 = (pF)var1;
         if (this.pC == null) {
            if (var3.pC != null) {
               return false;
            }
         } else if (!this.pC.equalsEx(var3.pC, var2)) {
            return false;
         }

         return true;
      }
   }
}
