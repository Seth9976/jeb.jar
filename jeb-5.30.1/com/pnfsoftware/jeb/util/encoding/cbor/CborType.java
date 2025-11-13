package com.pnfsoftware.jeb.util.encoding.cbor;

public class CborType {
   private final int m_major;
   private final int m_additional;

   private CborType(int var1, int var2) {
      this.m_major = var1;
      this.m_additional = var2;
   }

   public static String getName(int var0) {
      switch (var0) {
         case 0:
            return "unsigned integer";
         case 1:
            return "negative integer";
         case 2:
            return "byte string";
         case 3:
            return "text string";
         case 4:
            return "array";
         case 5:
            return "map";
         case 6:
            return "tag";
         case 7:
            return "float/simple value";
         default:
            throw new IllegalArgumentException("Invalid major type: " + var0);
      }
   }

   public static CborType valueOf(int var0) {
      return new CborType((var0 & 0xFF) >>> 5, var0 & 31);
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         CborType var2 = (CborType)var1;
         return this.m_major == var2.m_major && this.m_additional == var2.m_additional;
      } else {
         return false;
      }
   }

   public int getAdditionalInfo() {
      return this.m_additional;
   }

   public int getMajorType() {
      return this.m_major;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.m_additional;
      return 31 * var1 + this.m_major;
   }

   public boolean isBreakAllowed() {
      return this.m_major == 4 || this.m_major == 2 || this.m_major == 5 || this.m_major == 3;
   }

   public boolean isEqualType(CborType var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Parameter cannot be null!");
      } else {
         return this.m_major == var1.m_major;
      }
   }

   public boolean isEqualType(int var1) {
      return this.m_major == (var1 & 0xFF) >>> 5;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append(getName(this.m_major)).append('(').append(this.m_additional).append(')');
      return var1.toString();
   }
}
