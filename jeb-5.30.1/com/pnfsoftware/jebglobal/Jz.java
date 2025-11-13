package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

public class Jz {
   public long q;
   public oG RF;

   public Jz(long var1, oG var3) {
      this.q = var1;
      this.RF = var3;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (int)(this.q ^ this.q >>> 32);
      return 31 * var1 + (this.RF == null ? 0 : this.RF.hashCode());
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
         Jz var2 = (Jz)var1;
         if (this.q != var2.q) {
            return false;
         } else {
            if (this.RF == null) {
               if (var2.RF != null) {
                  return false;
               }
            } else if (!this.RF.equals(var2.RF)) {
               return false;
            }

            return true;
         }
      }
   }

   @Override
   public String toString() {
      return Strings.ff("id=%d,loc=[%s]", this.q, this.RF);
   }

   public Jz.eo q() {
      Jz.eo var1 = new Jz.eo();
      var1.q = this.q;
      var1.RF = this.RF.xK;
      var1.xK = this.RF.Dw;
      return var1;
   }

   public static class eo {
      long q;
      long RF;
      long xK;

      private eo() {
      }

      @Override
      public int hashCode() {
         int var1 = 1;
         var1 = 31 * var1 + (int)(this.RF ^ this.RF >>> 32);
         var1 = 31 * var1 + (int)(this.q ^ this.q >>> 32);
         return 31 * var1 + (int)(this.xK ^ this.xK >>> 32);
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
            Jz.eo var2 = (Jz.eo)var1;
            if (this.RF != var2.RF) {
               return false;
            } else {
               return this.q != var2.q ? false : this.xK == var2.xK;
            }
         }
      }
   }
}
