package com.pnfsoftware.jeb.core.units.code.asm.type;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Ser
public class CodeConstantManager {
   public static final CodeConstantManager EMPTY = new CodeConstantManager(1);
   @SerId(1)
   int status;
   @SerId(2)
   Map mapnames = new HashMap();
   @SerId(3)
   Map mapvalues = new HashMap();

   public CodeConstantManager() {
      this(0);
   }

   private CodeConstantManager(int var1) {
      this.status = var1;
   }

   public boolean isLocked() {
      return (this.status & 1) != 0;
   }

   public void unlock() {
      this.status &= -2;
   }

   private void verifyNotLocked() {
      if (this.isLocked()) {
         throw new IllegalStateException("The manager is in read-only mode");
      }
   }

   public boolean isLegalName(String var1) {
      return var1 != null && var1.length() > 0;
   }

   public boolean isLegalValue(Object var1) {
      return var1 instanceof Integer || var1 instanceof Long || var1 instanceof String;
   }

   public List getNamedConstantsByValue(Object var1) {
      ArrayList var2 = new ArrayList();

      for (String var4 : this.getNamesByValue(var1)) {
         var2.add(new CodeConstant(var4, var1));
      }

      if (var1 instanceof Integer) {
         long var7 = ((Integer)var1).intValue() & 4294967295L;

         for (String var6 : this.getNamesByValue(var7)) {
            var2.add(new CodeConstant(var6, var7));
         }
      } else if (var1 instanceof Long && (Long)var1 >= 0L) {
         int var8 = (int)((Long)var1).longValue();

         for (String var10 : this.getNamesByValue(var8)) {
            var2.add(new CodeConstant(var10, var8));
         }
      }

      return var2;
   }

   public Set getNamesByValue(Object var1) {
      if (!this.isLegalValue(var1)) {
         throw new IllegalArgumentException();
      } else {
         Set var2 = (Set)this.mapvalues.get(var1);
         return var2 != null ? var2 : Collections.emptySet();
      }
   }

   public Set getValuesByName(String var1) {
      if (!this.isLegalName(var1)) {
         throw new IllegalArgumentException();
      } else {
         Set var2 = (Set)this.mapnames.get(var1);
         return var2 != null ? var2 : Collections.emptySet();
      }
   }

   public void clear() {
      this.verifyNotLocked();
      this.mapnames.clear();
      this.mapvalues.clear();
   }

   public int size() {
      int var1 = 0;

      for (Set var3 : this.mapnames.values()) {
         var1 += var3.size();
      }

      return var1;
   }

   public void addConstant(String var1, Object var2) {
      this.verifyNotLocked();
      if (this.isLegalName(var1) && this.isLegalValue(var2)) {
         Object var3 = (Set)this.mapnames.get(var1);
         if (var3 == null) {
            var3 = new HashSet();
            this.mapnames.put(var1.intern(), var3);
         }

         var3.add(var2);
         Object var4 = (Set)this.mapvalues.get(var2);
         if (var4 == null) {
            var4 = new HashSet();
            this.mapvalues.put(var2, var4);
         }

         var4.add(var1.intern());
      } else {
         throw new IllegalArgumentException();
      }
   }

   public void removeConstant(String var1, Object var2) {
      this.verifyNotLocked();
      if (this.isLegalName(var1) && this.isLegalValue(var2)) {
         Set var3 = (Set)this.mapnames.get(var1);
         if (var3 != null) {
            var3.remove(var2);
            if (var3.isEmpty()) {
               this.mapnames.remove(var1);
            }
         }

         Set var4 = (Set)this.mapvalues.get(var2);
         if (var4 != null) {
            var4.remove(var1);
            if (var4.isEmpty()) {
               this.mapvalues.remove(var2);
            }
         }
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (this.mapnames == null ? 0 : this.mapnames.hashCode());
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
         CodeConstantManager var2 = (CodeConstantManager)var1;
         if (this.mapnames == null) {
            if (var2.mapnames != null) {
               return false;
            }
         } else if (!this.mapnames.equals(var2.mapnames)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      return this.mapnames.toString();
   }
}
