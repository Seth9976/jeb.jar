package com.pnfsoftware.jeb.util.base;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Ser
public abstract class DynamicEnum {
   @SerId(1)
   protected final int id;
   @SerId(2)
   protected final String name;

   protected DynamicEnum(int var1, String var2) {
      if (var2 != null && var2.length() != 0) {
         this.id = var1;
         this.name = var2;
      } else {
         throw new IllegalArgumentException("Must have a non-empty name");
      }
   }

   public int id() {
      return this.id;
   }

   public String name() {
      return this.name;
   }

   public abstract int ordinal();

   @Override
   public int hashCode() {
      return this.id;
   }

   @Override
   public boolean equals(Object var1) {
      return var1 == this;
   }

   @Override
   public String toString() {
      return this.name;
   }

   protected static final int ordinal(Map var0, DynamicEnum var1) {
      int var2 = 0;

      for (DynamicEnum var4 : var0.values()) {
         if (var4 == var1) {
            return var2;
         }

         var2++;
      }

      return -1;
   }

   protected static final DynamicEnum valueOf(Map var0, int var1, DynamicEnum var2) {
      for (DynamicEnum var4 : var0.values()) {
         if (var4.id == var1) {
            return var4;
         }
      }

      return var2;
   }

   protected static final DynamicEnum valueOf(Map var0, String var1, DynamicEnum var2) {
      return (DynamicEnum)var0.getOrDefault(var1, var2);
   }

   protected static final Collection values(Map var0) {
      return Collections.unmodifiableCollection(var0.values());
   }

   protected static final void verifyAvailability(Map var0, int var1, String var2) {
      for (DynamicEnum var4 : var0.values()) {
         if (var4.id == var1 || var4.name.equals(var2)) {
            throw new IllegalArgumentException("Duplicate entry found!");
         }
      }
   }

   protected static final boolean isBuiltin(Map var0, int var1, DynamicEnum var2) {
      int var3 = new ArrayList(var0.keySet()).indexOf(var2.name);
      return var3 < var1;
   }

   protected static final synchronized DynamicEnum register(Map var0, DynamicEnum var1) {
      verifyAvailability(var0, var1.id, var1.name);
      var0.put(var1.name(), var1);
      return var1;
   }

   protected static final synchronized boolean unregister(Map var0, int var1, String var2) {
      DynamicEnum var3 = (DynamicEnum)var0.get(var2);
      return var3 != null && !isBuiltin(var0, var1, var3) ? var0.remove(var3.name) != null : false;
   }

   public boolean isCompatibleWith(DynamicEnum var1) {
      if (var1 == null) {
         return false;
      } else if (var1 == this) {
         return true;
      } else {
         return this.name.startsWith(var1.name + "_") ? true : var1.name.startsWith(this.name + "_");
      }
   }
}
