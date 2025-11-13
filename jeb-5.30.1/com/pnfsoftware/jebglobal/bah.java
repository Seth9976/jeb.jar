package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeFeature;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class bah extends bae {
   @SerId(1)
   private final String q;
   @SerId(2)
   private final boolean RF;
   @SerId(3)
   private final int xK;
   @SerId(4)
   private boolean Dw;

   public bah(String var1, boolean var2, int var3) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
   }

   @Override
   public String getType() {
      return "CalledRoutineName";
   }

   public String q() {
      return this.q;
   }

   public boolean RF() {
      return this.RF;
   }

   public int xK() {
      return this.xK;
   }

   public void q(boolean var1) {
      this.Dw = var1;
   }

   public boolean Dw() {
      return this.Dw;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append(this.getType());
      var1.append(" -> ");
      var1.append(this.q());
      Strings.ff(var1, "(#%d)", this.xK);
      if (this.RF) {
         var1.append("(I)");
      }

      if (this.Dw) {
         var1.append("(IMP)");
      }

      return var1.toString();
   }

   @Override
   public boolean match(INativeFeature var1) {
      return var1 instanceof bag ? var1.match(this) : this.equals(var1);
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.xK;
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
         bah var2 = (bah)var1;
         if (this.xK != var2.xK) {
            return false;
         } else {
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
   }

   @Override
   public INativeFeature deepCopy() {
      bah var1 = new bah(this.q, this.RF, this.xK);
      var1.q(this.Dw);
      return var1;
   }
}
