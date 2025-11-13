package com.pnfsoftware.jeb.util.base;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class Triple {
   @SerId(1)
   Object a;
   @SerId(2)
   Object b;
   @SerId(3)
   Object c;

   public Triple(Object var1, Object var2, Object var3) {
      this.a = var1;
      this.b = var2;
      this.c = var3;
   }

   public Object getFirst() {
      return this.a;
   }

   public void setFirst(Object var1) {
      this.a = var1;
   }

   public Object getSecond() {
      return this.b;
   }

   public void setSecond(Object var1) {
      this.b = var1;
   }

   public Object getThird() {
      return this.c;
   }

   public void setThird(Object var1) {
      this.c = var1;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.a == null ? 0 : this.a.hashCode());
      var1 = 31 * var1 + (this.b == null ? 0 : this.b.hashCode());
      return 31 * var1 + (this.c == null ? 0 : this.c.hashCode());
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
         Triple var2 = (Triple)var1;
         if (this.a == null) {
            if (var2.a != null) {
               return false;
            }
         } else if (!this.a.equals(var2.a)) {
            return false;
         }

         if (this.b == null) {
            if (var2.b != null) {
               return false;
            }
         } else if (!this.b.equals(var2.b)) {
            return false;
         }

         if (this.c == null) {
            if (var2.c != null) {
               return false;
            }
         } else if (!this.c.equals(var2.c)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("(%s,%s,%s)", this.a, this.b, this.c);
   }
}
