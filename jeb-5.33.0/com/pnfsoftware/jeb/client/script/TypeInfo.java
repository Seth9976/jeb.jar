package com.pnfsoftware.jeb.client.script;

public class TypeInfo {
   String name;
   String simplename;

   TypeInfo(String var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.name = var1;
         int var2 = var1.lastIndexOf(46);
         if (var2 < 0) {
            this.simplename = var1;
         } else {
            this.simplename = var1.substring(var2 + 1);
         }
      }
   }

   public String getName() {
      return this.name;
   }

   public String getSimpleName() {
      return this.simplename;
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (this.name == null ? 0 : this.name.hashCode());
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
         TypeInfo var2 = (TypeInfo)var1;
         if (this.name == null) {
            if (var2.name != null) {
               return false;
            }
         } else if (!this.name.equals(var2.name)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      return this.name;
   }
}
