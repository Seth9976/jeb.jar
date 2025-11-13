package com.pnfsoftware.jeb.core.properties.impl;

import com.pnfsoftware.jeb.core.properties.IPropertyDefinition;
import com.pnfsoftware.jeb.core.properties.IPropertyTypeInteger;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Map;
import java.util.WeakHashMap;

public final class PropertyTypeInteger implements IPropertyTypeInteger {
   private static Map map = new WeakHashMap();
   private int min;
   private int max;
   private Integer def;

   public static PropertyTypeInteger create(int var0, int var1, Integer var2) {
      if (var2 == null) {
         throw new RuntimeException("Default value cannot be null");
      } else if (var2 >= var0 && var2 <= var1) {
         PropertyTypeInteger var3 = new PropertyTypeInteger(var0, var1, var2);
         PropertyTypeInteger var4 = (PropertyTypeInteger)map.get(var3);
         if (var4 == null) {
            var4 = var3;
            map.put(var3, var3);
         }

         return var4;
      } else {
         throw new IllegalArgumentException("Default value is out of range");
      }
   }

   public static PropertyTypeInteger create() {
      return create(Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
   }

   public static PropertyTypeInteger create(Integer var0) {
      return create(Integer.MIN_VALUE, Integer.MAX_VALUE, var0);
   }

   public static PropertyTypeInteger createPositive(Integer var0) {
      return create(1, Integer.MAX_VALUE, var0);
   }

   public static PropertyTypeInteger createPositiveOrZero(Integer var0) {
      return create(0, Integer.MAX_VALUE, var0);
   }

   public static PropertyTypeInteger createNegative(Integer var0) {
      return create(Integer.MIN_VALUE, -1, var0);
   }

   public static PropertyTypeInteger createNegativeOrZero(Integer var0) {
      return create(Integer.MIN_VALUE, 0, var0);
   }

   private PropertyTypeInteger(int var1, int var2, Integer var3) {
      this.min = var1;
      this.max = var2;
      this.def = var3;
   }

   @Override
   public int getMin() {
      return this.min;
   }

   @Override
   public int getMax() {
      return this.max;
   }

   @Override
   public String getName() {
      return "Integer";
   }

   @Override
   public Integer getDefault() {
      return this.def;
   }

   @Override
   public boolean validate(Object var1) {
      try {
         int var2 = Integer.parseInt(var1.toString());
         return var2 >= this.min && var2 <= this.max;
      } catch (NumberFormatException var3) {
         return false;
      }
   }

   @Override
   public Object afterRead(IPropertyDefinition var1, Object var2) {
      if (var2 instanceof Integer) {
         return var2;
      } else {
         return var2 instanceof CharSequence ? Integer.parseInt(var2.toString()) : var2;
      }
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.def == null ? 0 : this.def.hashCode());
      var1 = 31 * var1 + this.max;
      return 31 * var1 + this.min;
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
         PropertyTypeInteger var2 = (PropertyTypeInteger)var1;
         if (this.def == null) {
            if (var2.def != null) {
               return false;
            }
         } else if (!this.def.equals(var2.def)) {
            return false;
         }

         return this.max != var2.max ? false : this.min == var2.min;
      }
   }

   @Override
   public String toString() {
      String var1 = this.getName() + "(" + this.getDefault() + ")";
      if (this.min != Integer.MIN_VALUE && this.max != Integer.MAX_VALUE) {
         var1 = var1 + Strings.ff("{%d-%d}", this.min, this.max);
      } else if (this.min != Integer.MIN_VALUE && this.max == Integer.MAX_VALUE) {
         var1 = var1 + Strings.ff("{%d+}", this.min);
      } else if (this.min == Integer.MIN_VALUE && this.max != Integer.MAX_VALUE) {
         var1 = var1 + Strings.ff("{%d-}", this.max);
      }

      return var1;
   }
}
