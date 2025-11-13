package com.pnfsoftware.jeb.core.units.code.android.dex;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class DalvikParserError {
   @SerId(1)
   private DalvikParserErrorType errorType;
   @SerId(2)
   private int bytecodeOffset;

   public DalvikParserError(DalvikParserErrorType var1) {
      this(var1, -1);
   }

   public DalvikParserError(DalvikParserErrorType var1, int var2) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.errorType = var1;
         this.bytecodeOffset = var2;
      }
   }

   public DalvikParserErrorType getErrorType() {
      return this.errorType;
   }

   public int getBytecodeOffset() {
      return this.bytecodeOffset;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.bytecodeOffset;
      return 31 * var1 + (this.errorType == null ? 0 : this.errorType.hashCode());
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
         DalvikParserError var2 = (DalvikParserError)var1;
         return this.bytecodeOffset != var2.bytecodeOffset ? false : this.errorType == var2.errorType;
      }
   }

   @Override
   public String toString() {
      return this.bytecodeOffset < 0 ? this.errorType.toString() : Strings.ff("%s@0x%X", this.errorType, this.bytecodeOffset);
   }
}
