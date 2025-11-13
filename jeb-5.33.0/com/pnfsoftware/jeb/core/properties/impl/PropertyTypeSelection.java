package com.pnfsoftware.jeb.core.properties.impl;

import com.pnfsoftware.jeb.core.properties.IPropertyDefinition;
import com.pnfsoftware.jeb.core.properties.IPropertyTypeInteger;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PropertyTypeSelection implements IPropertyTypeInteger {
   List entries;
   PropertyTypeSelection.E def;
   private Set ids;

   PropertyTypeSelection(List var1, PropertyTypeSelection.E var2) {
      this.entries = var1;
      this.def = var2;
      this.ids = new HashSet(var1.size());

      for (PropertyTypeSelection.E var4 : var1) {
         if (!this.ids.add(var4.id)) {
            throw new RuntimeException();
         }
      }
   }

   @Override
   public String getName() {
      return "Selection";
   }

   public List getEntries() {
      return this.entries;
   }

   @Override
   public int getMin() {
      return Integer.MIN_VALUE;
   }

   @Override
   public int getMax() {
      return Integer.MAX_VALUE;
   }

   @Override
   public Integer getDefault() {
      return this.def == null ? 0 : this.def.id;
   }

   @Override
   public boolean validate(Object var1) {
      try {
         int var2 = Integer.parseInt(var1.toString());
         return this.ids.contains(var2);
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
   public String toString() {
      StringBuilder var1 = new StringBuilder(this.getName());
      Strings.ff(var1, "(%d)", this.getDefault());
      var1.append("{");
      int var2 = 0;

      for (PropertyTypeSelection.E var4 : this.getEntries()) {
         if (var2 > 0) {
            var1.append(", ");
         }

         Strings.ff(var1, "%s(%d)", var4.value, var4.id);
         var2++;
      }

      var1.append("}");
      return var1.toString();
   }

   public static class Builder {
      List entries = new ArrayList();
      PropertyTypeSelection.E def;

      public static PropertyTypeSelection.Builder create() {
         return new PropertyTypeSelection.Builder();
      }

      Builder() {
      }

      public PropertyTypeSelection.Builder addDefaultEntry(int var1, String var2, String var3) {
         PropertyTypeSelection.E var4 = this.add(var1, var2, var3);
         this.def = var4;
         return this;
      }

      public PropertyTypeSelection.Builder addEntry(int var1, String var2, String var3) {
         this.add(var1, var2, var3);
         return this;
      }

      private PropertyTypeSelection.E add(int var1, String var2, String var3) {
         if (var2 == null) {
            throw new IllegalArgumentException("Null value");
         } else {
            for (PropertyTypeSelection.E var5 : this.entries) {
               if (var5.id == var1 || var5.value.equals(var2)) {
                  throw new IllegalArgumentException("Duplicate entry: " + var5);
               }
            }

            PropertyTypeSelection.E var6 = new PropertyTypeSelection.E(var1, var2, var3);
            this.entries.add(var6);
            return var6;
         }
      }

      public PropertyTypeSelection.Builder setDefault(PropertyTypeSelection.E var1) {
         if (var1 != null && !this.entries.contains(var1)) {
            throw new IllegalArgumentException("Default entry must be registered first");
         } else {
            this.def = var1;
            return this;
         }
      }

      public PropertyTypeSelection.Builder setDefault(int var1) {
         for (PropertyTypeSelection.E var3 : this.entries) {
            if (var3.id == var1) {
               this.def = var3;
               return this;
            }
         }

         throw new IllegalArgumentException("Cannot find entry with id " + var1);
      }

      public PropertyTypeSelection build() {
         return new PropertyTypeSelection(this.entries, this.def);
      }
   }

   public static class E {
      int id;
      String value;
      String description;

      E(int var1, String var2, String var3) {
         this.id = var1;
         this.value = var2;
         this.description = var3;
      }

      public int getId() {
         return this.id;
      }

      public String getValue() {
         return this.value;
      }

      public String getDescription() {
         return this.description;
      }

      @Override
      public String toString() {
         return Strings.ff("%d:%s(%s)", this.id, this.value, this.description);
      }
   }
}
