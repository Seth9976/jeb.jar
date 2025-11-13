package com.pnfsoftware.jeb.core.units.code.asm.sig.codeless;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Ser
public class Constraint {
   @SerId(1)
   private Set possibleFuncs;

   public Constraint(Collection var1) {
      this.setPossibleFuncs(new HashSet(var1));
   }

   public boolean isEmpty() {
      return this.getPossibleFuncs().isEmpty();
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (this.getPossibleFuncs() == null ? 0 : this.getPossibleFuncs().hashCode());
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
         Constraint var2 = (Constraint)var1;
         if (this.getPossibleFuncs() == null) {
            if (var2.getPossibleFuncs() != null) {
               return false;
            }
         } else if (!this.getPossibleFuncs().equals(var2.getPossibleFuncs())) {
            return false;
         }

         return true;
      }
   }

   public Set getPossibleFuncs() {
      return this.possibleFuncs;
   }

   public void setPossibleFuncs(Set var1) {
      this.possibleFuncs = var1;
   }
}
