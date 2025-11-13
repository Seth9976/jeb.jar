package com.pnfsoftware.jeb.util.base;

public class Wrapper {
   public static final Wrapper NULL = new Wrapper(null);
   private Object object;

   public static Wrapper wrap(Object var0) {
      return new Wrapper(var0);
   }

   private Wrapper(Object var1) {
      this.object = var1;
   }

   public Object get() {
      return this.object;
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (this.object == null ? 0 : this.object.hashCode());
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
         Wrapper var2 = (Wrapper)var1;
         if (this.object == null) {
            if (var2.object != null) {
               return false;
            }
         } else if (!this.object.equals(var2.object)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      return this.object == null ? "null" : this.object.toString();
   }
}
