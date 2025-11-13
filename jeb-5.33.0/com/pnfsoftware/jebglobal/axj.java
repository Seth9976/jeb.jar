package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeFeature;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.HashSet;
import java.util.Set;

@Ser
public class axj extends axh {
   @SerId(1)
   private final Set pC;

   public axj(Set var1) {
      Assert.a(var1 != null && var1.size() != 0, "FeatureDisjunction need values");
      Class var2 = null;

      for (INativeFeature var4 : var1) {
         if (var2 == null) {
            var2 = var4.getClass();
         } else {
            Assert.a(var4.getClass() == var2, "Features in FeatureDisjunction need to be of same type");
         }

         Assert.a(!(var4 instanceof axj), "FeatureDisjunction containing FeatureDisjunction is disallowed");
      }

      this.pC = var1;
   }

   public Set pC() {
      return this.pC;
   }

   @Override
   public String getType() {
      return "FeatureDisjunction";
   }

   @Override
   public boolean match(INativeFeature var1) {
      if (!(var1 instanceof axj)) {
         for (INativeFeature var9 : this.pC) {
            if (var9.match(var1)) {
               return true;
            }
         }

         return false;
      } else if (this.pC.size() != ((axj)var1).pC().size()) {
         return false;
      } else {
         boolean var2 = true;

         for (INativeFeature var4 : this.pC) {
            boolean var5 = false;

            for (INativeFeature var7 : ((axj)var1).pC()) {
               if (var4.match(var7)) {
                  var5 = true;
                  break;
               }
            }

            if (!var5) {
               var2 = false;
               break;
            }
         }

         return var2;
      }
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (this.pC == null ? 0 : this.pC.hashCode());
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
         axj var2 = (axj)var1;
         if (this.pC == null) {
            if (var2.pC != null) {
               return false;
            }
         } else if (!this.pC.equals(var2.pC)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public INativeFeature deepCopy() {
      HashSet var1 = new HashSet();

      for (INativeFeature var3 : this.pC) {
         var1.add(var3.deepCopy());
      }

      return new axj(var1);
   }
}
