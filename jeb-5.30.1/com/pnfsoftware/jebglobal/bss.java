package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Map;

public class bss implements bsy {
   public bst q;
   public int RF;
   public int xK = -1;
   public boolean Dw;

   public bss(bst var1, int var2, int var3) {
      if (var1 != null && var2 >= 0) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
      } else {
         throw new RuntimeException();
      }
   }

   public bst Dw() {
      return this.q;
   }

   @Override
   public int q() {
      return this.RF;
   }

   @Override
   public int RF() {
      return -1;
   }

   @Override
   public int xK() {
      return this.xK;
   }

   @Override
   public void q(Map var1) {
      this.RF = (Integer)var1.get(this.RF);
      if (this.xK >= 0) {
         this.xK = (Integer)var1.get(this.xK);
      }
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.q == null ? 0 : this.q.hashCode());
      var1 = 31 * var1 + this.RF;
      return 31 * var1 + this.xK;
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
         bss var2 = (bss)var1;
         if (this.q != var2.q) {
            return false;
         } else {
            return this.RF != var2.RF ? false : this.xK == var2.xK;
         }
      }
   }

   @Override
   public String toString() {
      return Strings.ff("%s:%d=>%d", this.q, this.RF, this.xK);
   }
}
