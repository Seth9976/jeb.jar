package com.pnfsoftware.jeb.core.units.code.asm.analyzer;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class ReferenceLocation {
   @SerId(1)
   private final Object location;

   private ReferenceLocation(long var1) {
      this.location = var1;
   }

   private ReferenceLocation(INativeMethodItem var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.location = var1;
      }
   }

   public static ReferenceLocation create(long var0) {
      return new ReferenceLocation(var0);
   }

   public static ReferenceLocation createFromExternalRoutine(INativeMethodItem var0) {
      Assert.a(var0.getData() == null);
      return new ReferenceLocation(var0);
   }

   public boolean isInternalAddress() {
      return this.location instanceof Long;
   }

   public Long getInternalAddress() {
      return this.location instanceof Long ? (Long)this.location : null;
   }

   public boolean isExternalMethod() {
      return this.location instanceof INativeMethodItem && ((INativeMethodItem)this.location).getData() == null;
   }

   public INativeMethodItem getExternalMethod() {
      return this.location instanceof INativeMethodItem ? (INativeMethodItem)this.location : null;
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (this.location == null ? 0 : this.location.hashCode());
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
         ReferenceLocation var2 = (ReferenceLocation)var1;
         if (this.location == null) {
            if (var2.location != null) {
               return false;
            }
         } else if (!this.location.equals(var2.location)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      if (this.location instanceof Long) {
         return Strings.ff("0x%X", (Long)this.location);
      } else {
         return this.location instanceof INativeMethodItem ? ((INativeMethodItem)this.location).getName() : this.location + "";
      }
   }
}
