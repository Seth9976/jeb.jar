package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Deprecated
@Ser
public class abm {
   @SerId(1)
   private Object q;

   private abm(long var1) {
      this.q = var1;
   }

   private abm(INativeItem var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.q = var1;
      }
   }

   public static abm q(long var0) {
      return new abm(var0);
   }

   public static abm q(INativeItem var0) {
      return new abm(var0);
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (this.q == null ? 0 : this.q.hashCode());
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
         abm var2 = (abm)var1;
         if (this.q == null) {
            if (var2.q != null) {
               return false;
            }
         } else if (!this.q.equals(var2.q)) {
            return false;
         }

         return true;
      }
   }

   public boolean q() {
      return this.q instanceof Long;
   }

   public long RF() {
      if (this.q instanceof Long) {
         return (Long)this.q;
      } else {
         throw new UnsupportedOperationException("Unable to retrieve address for target " + this.q);
      }
   }

   public boolean xK() {
      return this.q instanceof INativeItem;
   }

   public INativeItem Dw() {
      return (INativeItem)this.q;
   }

   @Override
   public String toString() {
      return this.q.toString();
   }
}
