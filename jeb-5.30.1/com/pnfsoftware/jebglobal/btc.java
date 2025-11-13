package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Map;

public class btc implements bsy {
   public btd q;
   public int RF;
   public int xK;
   public int Dw = -1;

   public btc(btd var1, int var2, int var3, int var4) {
      if (var1 != null && var2 >= 0 && var3 >= 0) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
         this.Dw = var4;
      } else {
         throw new RuntimeException();
      }
   }

   public btc(btd var1, int var2, int var3) {
      this(var1, var2, var3, -1);
   }

   public btc(int var1, int var2, int var3, btd var4) {
      this(var4, var1, var2, var3);
   }

   public btd Dw() {
      return this.q;
   }

   @Override
   public int q() {
      return this.RF;
   }

   @Override
   public int RF() {
      return this.xK;
   }

   @Override
   public int xK() {
      return this.Dw;
   }

   @Override
   public void q(Map var1) {
      this.RF = (Integer)var1.get(this.RF);
      this.xK = (Integer)var1.get(this.xK);
      if (this.Dw != -1) {
         this.Dw = (Integer)var1.get(this.Dw);
      }
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.q == null ? 0 : this.q.hashCode());
      var1 = 31 * var1 + this.RF;
      var1 = 31 * var1 + this.xK;
      return 31 * var1 + this.Dw;
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
         btc var2 = (btc)var1;
         if (this.q != var2.q) {
            return false;
         } else if (this.RF != var2.RF) {
            return false;
         } else {
            return this.xK != var2.xK ? false : this.Dw == var2.Dw;
         }
      }
   }

   @Override
   public String toString() {
      String var1 = Strings.ff("%s:%d->%d", this.q, this.RF, this.xK);
      if (this.Dw >= 0) {
         var1 = var1 + "=>" + this.Dw;
      }

      return var1;
   }
}
