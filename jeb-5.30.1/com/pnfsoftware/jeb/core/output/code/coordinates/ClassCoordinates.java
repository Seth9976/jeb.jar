package com.pnfsoftware.jeb.core.output.code.coordinates;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class ClassCoordinates implements ICodeCoordinates {
   @SerId(1)
   private int classId;

   public ClassCoordinates(int var1) {
      this.classId = var1;
   }

   public int getClassId() {
      return this.classId;
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + this.classId;
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
         ClassCoordinates var2 = (ClassCoordinates)var1;
         return this.classId == var2.classId;
      }
   }

   @Override
   public String toString() {
      return "class:" + this.classId;
   }
}
