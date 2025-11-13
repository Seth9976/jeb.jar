package com.pnfsoftware.jeb.core.output.code.coordinates;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class InstructionCoordinates implements ICodeCoordinates {
   @SerId(1)
   private int methodId;
   @SerId(2)
   private int offset;

   public InstructionCoordinates(int var1, int var2) {
      this.methodId = var1;
      this.offset = var2;
   }

   public int getMethodId() {
      return this.methodId;
   }

   public int getOffset() {
      return this.offset;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.methodId;
      return 31 * var1 + this.offset;
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
         InstructionCoordinates var2 = (InstructionCoordinates)var1;
         return this.methodId != var2.methodId ? false : this.offset == var2.offset;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("method:%d,offset:%Xh", this.methodId, this.offset);
   }
}
