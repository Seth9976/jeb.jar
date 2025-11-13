package com.pnfsoftware.jeb.core.output.code.coordinates;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class MethodCoordinates implements ICodeCoordinates {
   @SerId(1)
   private int methodId;
   @SerId(2)
   private boolean endFlag = false;

   public MethodCoordinates(int var1) {
      this(var1, false);
   }

   public MethodCoordinates(int var1, boolean var2) {
      this.methodId = var1;
      this.endFlag = var2;
   }

   public int getMethodId() {
      return this.methodId;
   }

   public boolean isEndFlag() {
      return this.endFlag;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.endFlag ? 1231 : 1237);
      return 31 * var1 + this.methodId;
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
         MethodCoordinates var2 = (MethodCoordinates)var1;
         return this.endFlag != var2.endFlag ? false : this.methodId == var2.methodId;
      }
   }

   @Override
   public String toString() {
      return "method:" + this.methodId + (this.endFlag ? "(end)" : "");
   }
}
