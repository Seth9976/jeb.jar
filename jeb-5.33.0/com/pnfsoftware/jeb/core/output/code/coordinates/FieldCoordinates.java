package com.pnfsoftware.jeb.core.output.code.coordinates;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class FieldCoordinates implements ICodeCoordinates {
   @SerId(1)
   private int fieldId;

   public FieldCoordinates(int var1) {
      this.fieldId = var1;
   }

   public int getFieldId() {
      return this.fieldId;
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + this.fieldId;
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
         FieldCoordinates var2 = (FieldCoordinates)var1;
         return this.fieldId == var2.fieldId;
      }
   }

   @Override
   public String toString() {
      return "field:" + this.fieldId;
   }
}
