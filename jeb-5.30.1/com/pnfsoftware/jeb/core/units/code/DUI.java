package com.pnfsoftware.jeb.core.units.code;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DUI {
   List def;
   List use;
   List defpot;
   List usepot;
   List spoiled;

   DUI(List var1, List var2, List var3, List var4, List var5) {
      this.def = var1;
      this.use = var2;
      this.defpot = var3;
      this.usepot = var4;
      this.spoiled = var5;
   }

   public Collection getDef() {
      return this.def == null ? Collections.emptyList() : Collections.unmodifiableList(this.def);
   }

   public Collection getUse() {
      return this.use == null ? Collections.emptyList() : Collections.unmodifiableList(this.use);
   }

   public Collection getDefPot() {
      return this.defpot == null ? Collections.emptyList() : Collections.unmodifiableList(this.defpot);
   }

   public Collection getUsePot() {
      return this.usepot == null ? Collections.emptyList() : Collections.unmodifiableList(this.usepot);
   }

   public Collection getSpoiled() {
      return this.spoiled == null ? Collections.emptyList() : Collections.unmodifiableList(this.spoiled);
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.def == null ? 0 : this.def.hashCode());
      var1 = 31 * var1 + (this.defpot == null ? 0 : this.defpot.hashCode());
      var1 = 31 * var1 + (this.spoiled == null ? 0 : this.spoiled.hashCode());
      var1 = 31 * var1 + (this.use == null ? 0 : this.use.hashCode());
      return 31 * var1 + (this.usepot == null ? 0 : this.usepot.hashCode());
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
         DUI var2 = (DUI)var1;
         if (this.def == null) {
            if (var2.def != null) {
               return false;
            }
         } else if (!this.def.equals(var2.def)) {
            return false;
         }

         if (this.defpot == null) {
            if (var2.defpot != null) {
               return false;
            }
         } else if (!this.defpot.equals(var2.defpot)) {
            return false;
         }

         if (this.spoiled == null) {
            if (var2.spoiled != null) {
               return false;
            }
         } else if (!this.spoiled.equals(var2.spoiled)) {
            return false;
         }

         if (this.use == null) {
            if (var2.use != null) {
               return false;
            }
         } else if (!this.use.equals(var2.use)) {
            return false;
         }

         if (this.usepot == null) {
            if (var2.usepot != null) {
               return false;
            }
         } else if (!this.usepot.equals(var2.usepot)) {
            return false;
         }

         return true;
      }
   }
}
