package com.pnfsoftware.jeb.core.properties.impl;

import com.pnfsoftware.jeb.core.properties.IPropertyTypePath;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.WeakHashMap;

public class PropertyTypePath implements IPropertyTypePath {
   private static Map map = new WeakHashMap();
   private String def;

   public static PropertyTypePath create(String var0) {
      if (var0 == null) {
         throw new RuntimeException("Default value cannot be null");
      } else {
         PropertyTypePath var1 = new PropertyTypePath(var0);
         PropertyTypePath var2 = (PropertyTypePath)map.get(var1);
         if (var2 == null) {
            var2 = var1;
            map.put(var1, var1);
         }

         return var2;
      }
   }

   public static PropertyTypePath create() {
      return create("");
   }

   private PropertyTypePath(String var1) {
      this.def = var1;
   }

   @Override
   public String getName() {
      return "Path";
   }

   @Override
   public int getMinLength() {
      return 0;
   }

   @Override
   public int getMaxLength() {
      return Integer.MAX_VALUE;
   }

   @Override
   public String getDefault() {
      return this.def;
   }

   @Override
   public boolean validate(Object var1) {
      String var2 = var1.toString();

      try {
         new File(var2).getCanonicalPath();
         return true;
      } catch (IOException var3) {
         return false;
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
         PropertyTypePath var2 = (PropertyTypePath)var1;
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
      return this.getName() + "(\"" + this.getDefault() + "\")";
   }
}
