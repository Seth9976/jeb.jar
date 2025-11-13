package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeFeature;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.HashSet;
import java.util.Set;

@Ser
public class bag extends bae {
   @SerId(1)
   private final Set q;

   public bag(Set var1) {
      Assert.a(var1 != null && var1.size() != 0, "FeatureDisjunction need values");
      Class var2 = null;

      for (INativeFeature var4 : var1) {
         if (var2 == null) {
            var2 = var4.getClass();
         } else {
            Assert.a(var4.getClass() == var2, "Features in FeatureDisjunction need to be of same type");
         }

         Assert.a(!(var4 instanceof bag), "FeatureDisjunction containing FeatureDisjunction is disallowed");
      }

      this.q = var1;
   }

   public Set q() {
      return this.q;
   }

   @Override
   public String getType() {
      return "FeatureDisjunction";
   }

   @Override
   public boolean match(INativeFeature var1) {
      if (!(var1 instanceof bag)) {
         for (INativeFeature var9 : this.q) {
            if (var9.match(var1)) {
               return true;
            }
         }

         return false;
      } else if (this.q.size() != ((bag)var1).q().size()) {
         return false;
      } else {
         boolean var2 = true;

         for (INativeFeature var4 : this.q) {
            boolean var5 = false;

            for (INativeFeature var7 : ((bag)var1).q()) {
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
         bag var2 = (bag)var1;
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

   @Override
   public INativeFeature deepCopy() {
      HashSet var1 = new HashSet();

      for (INativeFeature var3 : this.q) {
         var1.add(var3.deepCopy());
      }

      return new bag(var1);
   }
}
