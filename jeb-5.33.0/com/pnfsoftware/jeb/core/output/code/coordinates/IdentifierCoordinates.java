package com.pnfsoftware.jeb.core.output.code.coordinates;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class IdentifierCoordinates {
   @SerId(1)
   private ICodeCoordinates base;
   @SerId(2)
   private int varindex;

   public IdentifierCoordinates(ICodeCoordinates var1, int var2) {
      if (!(var1 instanceof InstructionCoordinates) && !(var1 instanceof MethodCoordinates)) {
         throw new IllegalArgumentException();
      } else {
         this.base = var1;
         this.varindex = var2;
      }
   }

   public ICodeCoordinates getBase() {
      return this.base;
   }

   public int getMethodIndex() {
      return this.base instanceof InstructionCoordinates ? ((InstructionCoordinates)this.base).getMethodId() : ((MethodCoordinates)this.base).getMethodId();
   }

   public int getMethodOffset() {
      return this.base instanceof InstructionCoordinates ? ((InstructionCoordinates)this.base).getOffset() : -1;
   }

   public int getVariableIndex() {
      return this.varindex;
   }

   public boolean isVirtual() {
      return this.varindex >= 65536;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.base == null ? 0 : this.base.hashCode());
      return 31 * var1 + this.varindex;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         IdentifierCoordinates var2 = (IdentifierCoordinates)var1;
         if (this.base == null) {
            if (var2.base != null) {
               return false;
            }
         } else if (!this.base.equals(var2.base)) {
            return false;
         }

         return this.varindex == var2.varindex;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("ident:[%s],%s%d", this.base, this.isVirtual() ? "x" : "", this.varindex);
   }
}
