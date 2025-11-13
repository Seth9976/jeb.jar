package com.pnfsoftware.jeb.core.properties.impl;

import com.pnfsoftware.jeb.core.properties.IPropertyDefinition;
import com.pnfsoftware.jeb.core.properties.IPropertyTypeBoolean;

public final class PropertyTypeBoolean implements IPropertyTypeBoolean {
   private static final PropertyTypeBoolean pFalse = new PropertyTypeBoolean(false);
   private static final PropertyTypeBoolean pTrue = new PropertyTypeBoolean(true);
   private Boolean def;

   public static PropertyTypeBoolean create(Boolean var0) {
      if (var0 == null) {
         throw new RuntimeException("Default value cannot be null");
      } else {
         return var0 ? pTrue : pFalse;
      }
   }

   public static PropertyTypeBoolean create() {
      return create(false);
   }

   private PropertyTypeBoolean(Boolean var1) {
      this.def = var1;
   }

   @Override
   public String getName() {
      return "Boolean";
   }

   @Override
   public Boolean getDefault() {
      return this.def;
   }

   @Override
   public boolean validate(Object var1) {
      try {
         Boolean.parseBoolean(var1.toString());
         return true;
      } catch (Exception var2) {
         return false;
      }
   }

   @Override
   public Object afterRead(IPropertyDefinition var1, Object var2) {
      if (var2 instanceof Boolean) {
         return var2;
      } else {
         return var2 instanceof CharSequence ? Boolean.parseBoolean(var2.toString()) : var2;
      }
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (this.def == null ? 0 : this.def.hashCode());
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
         PropertyTypeBoolean var2 = (PropertyTypeBoolean)var1;
         if (this.def == null) {
            if (var2.def != null) {
               return false;
            }
         } else if (!this.def.equals(var2.def)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      return this.getName() + "(" + this.getDefault() + ")";
   }
}
