package com.pnfsoftware.jeb.core.units.code.asm.type;

import com.pnfsoftware.jeb.util.format.Strings;

public class TypeLayoutInfo {
   public static final TypeLayoutInfo i1 = new TypeLayoutInfo(1, TypeCategory.INTEGRAL);
   public static final TypeLayoutInfo i2 = new TypeLayoutInfo(2, TypeCategory.INTEGRAL);
   public static final TypeLayoutInfo f1 = new TypeLayoutInfo(1, TypeCategory.FLOAT);
   public static final TypeLayoutInfo f2 = new TypeLayoutInfo(2, TypeCategory.FLOAT);
   public static final TypeLayoutInfo ptr = new TypeLayoutInfo(1, TypeCategory.POINTER);
   private int slotcount;
   private TypeCategory category;

   private TypeLayoutInfo(int var1, TypeCategory var2) {
      if (var1 <= 0) {
         var1 = 1;
      }

      this.slotcount = var1;
      this.category = var2;
   }

   public int getSlotcount() {
      return this.slotcount;
   }

   public TypeCategory getCategory() {
      return this.category;
   }

   public static TypeLayoutInfo i(int var0) {
      if (var0 == 1) {
         return i1;
      } else {
         return var0 == 2 ? i2 : new TypeLayoutInfo(var0, TypeCategory.INTEGRAL);
      }
   }

   public static TypeLayoutInfo f(int var0) {
      if (var0 == 1) {
         return f1;
      } else {
         return var0 == 2 ? f2 : new TypeLayoutInfo(var0, TypeCategory.FLOAT);
      }
   }

   public static TypeLayoutInfo p(int var0) {
      return var0 == 1 ? ptr : new TypeLayoutInfo(var0, TypeCategory.POINTER);
   }

   public static TypeLayoutInfo v(int var0) {
      return new TypeLayoutInfo(var0, TypeCategory.VECTOR);
   }

   public static TypeLayoutInfo c(int var0) {
      return new TypeLayoutInfo(var0, TypeCategory.COMPOSITE);
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.category == null ? 0 : this.category.hashCode());
      return 31 * var1 + this.slotcount;
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
         TypeLayoutInfo var2 = (TypeLayoutInfo)var1;
         return this.category != var2.category ? false : this.slotcount == var2.slotcount;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("%d:%s", this.slotcount, this.category);
   }

   public boolean isInteger() {
      return this.category == TypeCategory.INTEGRAL;
   }

   public boolean isFloat() {
      return this.category == TypeCategory.FLOAT;
   }

   public boolean isPointer() {
      return this.category == TypeCategory.POINTER;
   }

   public boolean isVector() {
      return this.category == TypeCategory.VECTOR;
   }

   public boolean isComposite() {
      return this.category == TypeCategory.COMPOSITE;
   }
}
