package com.pnfsoftware.jeb.core.properties.impl;

import com.pnfsoftware.jeb.core.properties.IPropertyDefinition;
import com.pnfsoftware.jeb.core.properties.IPropertyTypeString;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Map;
import java.util.WeakHashMap;

public final class PropertyTypeString implements IPropertyTypeString {
   private static Map map = new WeakHashMap();
   private int min;
   private int max;
   private String def;

   public static PropertyTypeString create(int var0, int var1, String var2) {
      if (var2 == null) {
         throw new RuntimeException("Default value cannot be null");
      } else if (var2.length() < var0 || var2.length() > var1) {
         throw new IllegalArgumentException("Default value is out of range");
      } else if (var0 >= 0 && var0 <= var1) {
         PropertyTypeString var3 = new PropertyTypeString(var0, var1, var2);
         PropertyTypeString var4 = (PropertyTypeString)map.get(var3);
         if (var4 == null) {
            var4 = var3;
            map.put(var3, var3);
         }

         return var4;
      } else {
         throw new IllegalArgumentException("The range is invalid");
      }
   }

   public static PropertyTypeString create(String var0) {
      return create(0, Integer.MAX_VALUE, var0);
   }

   public static PropertyTypeString create() {
      return create(0, Integer.MAX_VALUE, "");
   }

   private PropertyTypeString(int var1, int var2, String var3) {
      this.min = var1;
      this.max = var2;
      this.def = var3;
   }

   @Override
   public String getName() {
      return "String";
   }

   @Override
   public int getMinLength() {
      return this.min;
   }

   @Override
   public int getMaxLength() {
      return this.max;
   }

   @Override
   public String getDefault() {
      return this.def;
   }

   @Override
   public boolean validate(Object var1) {
      String var2 = var1.toString();
      return var2.length() >= this.min && var2.length() <= this.max;
   }

   @Override
   public Object afterRead(IPropertyDefinition var1, Object var2) {
      if ((var1.getFlags() & 4) != 0) {
         var2 = Strings.urldecodeUTF8(var2.toString());
      }

      return var2 instanceof CharSequence ? var2.toString() : var2;
   }

   @Override
   public Object beforeWrite(IPropertyDefinition var1, Object var2) {
      if ((var1.getFlags() & 4) != 0) {
         var2 = Strings.urlencodeUTF8(var2.toString());
      }

      return var2;
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
         PropertyTypeString var2 = (PropertyTypeString)var1;
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
      return this.getName() + "(\"" + this.getDefault() + "\")";
   }
}
