package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EDefUseInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.ETypeInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IERange;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collection;

@Ser
public class amj extends ane implements IERange {
   @SerId(1)
   final int q;
   @SerId(2)
   final int RF;

   amj(int var1, int var2) {
      if (var1 >= 0 && var1 < var2) {
         this.q = var1;
         this.RF = var2;
      } else {
         throw new IllegalArgumentException(Strings.ff("Invalid range: [%d, %d)", var1, var2));
      }
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + this.q;
      return 31 * var1 + this.RF;
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
         amj var3 = (amj)var1;
         return this.q != var3.q ? false : this.RF == var3.RF;
      }
   }

   @Override
   public int getBitsize() {
      throw new RuntimeException("A range has no bitsize - did you mean getRangeLength() ?");
   }

   @Override
   public int getPriority() {
      return 0;
   }

   @Override
   public int getBegin() {
      return this.q;
   }

   @Override
   public int getEnd() {
      return this.RF;
   }

   @Override
   public int getRangeLength() {
      return this.RF - this.q;
   }

   @Override
   public void getUsed(EDefUseInfo var1) {
   }

   @Override
   public void getDefinedOrUsedAsDestination(EDefUseInfo var1) {
   }

   @Override
   public boolean accessesMemory() {
      return false;
   }

   public alu q(EState var1) {
      throw new RuntimeException();
   }

   @Override
   public int replaceVar(IEVar var1, IEGeneric var2) {
      return 0;
   }

   @Override
   public void collectSubExpressions(Collection var1) {
   }

   @Override
   public boolean replaceSubExpression(IEGeneric var1, IEGeneric var2) {
      return false;
   }

   @Override
   public boolean setType(IWildcardType var1, ETypeInfo var2) {
      return false;
   }

   @Override
   public void updateTypes(ETypeInfo var1) {
   }

   public amj q() {
      return this;
   }

   @Override
   public ICElement generateC(IERoutineContext var1, ICMethod var2) {
      throw new RuntimeException();
   }

   @Override
   public void q(and var1) {
      var1.append("[");
      var1.append(this.q + "");
      var1.append(":");
      var1.append(this.RF + "");
      var1.append("[");
   }

   public amj q(int var1) {
      if (this.q + var1 < 0) {
         throw new IllegalArgumentException();
      } else {
         return new amj(this.q + var1, this.RF + var1);
      }
   }
}
