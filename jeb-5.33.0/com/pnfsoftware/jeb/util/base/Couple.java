package com.pnfsoftware.jeb.util.base;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collection;

@Ser
public class Couple {
   @SerId(1)
   Object a;
   @SerId(2)
   Object b;

   public Couple(Object var1, Object var2) {
      this.a = var1;
      this.b = var2;
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

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.a == null ? 0 : this.a.hashCode());
      return 31 * var1 + (this.b == null ? 0 : this.b.hashCode());
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
         Couple var2 = (Couple)var1;
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

         return true;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("(%s,%s)", this.a, this.b);
   }

   public static Collection getFirstElements(Collection var0) {
      ArrayList var1 = new ArrayList();

      for (Couple var3 : var0) {
         var1.add(var3.a);
      }

      return var1;
   }

   public static Collection getSecondElements(Collection var0) {
      ArrayList var1 = new ArrayList();

      for (Couple var3 : var0) {
         var1.add(var3.b);
      }

      return var1;
   }
}
