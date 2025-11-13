package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Map;

public class alb implements aky {
   public int q;
   public int RF;
   public int xK;
   public alc Dw;
   public int Uv;

   public alb(int var1, int var2, int var3, alc var4, int var5) {
      if (var1 > 0 && var2 > 0) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
         this.Dw = var4;
         this.Uv = var5;
      } else {
         throw new RuntimeException();
      }
   }

   public alb(int var1, int var2, int var3, alc var4) {
      this(var1, var2, var3, var4, 0);
   }

   @Override
   public int q() {
      return this.q;
   }

   public int xK() {
      return this.RF;
   }

   @Override
   public int RF() {
      return this.xK;
   }

   public alc Dw() {
      return this.Dw;
   }

   @Override
   public void q(Map var1) {
      this.q = (Integer)var1.get(this.q);
      this.RF = (Integer)var1.get(this.RF);
      if (this.xK > 0) {
         this.xK = (Integer)var1.get(this.xK);
      }
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.Dw == null ? 0 : this.Dw.hashCode());
      var1 = 31 * var1 + this.q;
      var1 = 31 * var1 + this.RF;
      var1 = 31 * var1 + this.xK;
      return 31 * var1 + this.Uv;
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
         alb var2 = (alb)var1;
         if (this.Dw != var2.Dw) {
            return false;
         } else if (this.q != var2.q) {
            return false;
         } else if (this.RF != var2.RF) {
            return false;
         } else {
            return this.xK != var2.xK ? false : this.Uv == var2.Uv;
         }
      }
   }

   @Override
   public String toString() {
      String var1 = Strings.ff("%s:%d->%d->%d", this.Dw, this.q, this.RF, this.xK);
      if (this.Uv > 0) {
         var1 = var1 + "(" + this.Uv + ")";
      }

      return var1;
   }
}
