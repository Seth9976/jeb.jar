package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.util.format.Strings;

public class bww {
   private long q;
   private int RF;
   private boolean xK;

   public bww(long var1, int var3, boolean var4) {
      this.q = var1;
      this.RF = var3;
      this.xK = var4;
   }

   public bww(long var1, int var3) {
      this.q = var1;
      this.RF = var3;
   }

   public long Dw() {
      return this.q;
   }

   public int Uv() {
      return this.RF;
   }

   public boolean oW() {
      return this.xK;
   }

   public boolean gO() {
      return !this.xK;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (int)(this.q ^ this.q >>> 32);
      var1 = 31 * var1 + (this.xK ? 1231 : 1237);
      return 31 * var1 + this.RF;
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
         bww var2 = (bww)var1;
         if (this.q != var2.q) {
            return false;
         } else {
            return this.xK != var2.xK ? false : this.RF == var2.RF;
         }
      }
   }

   @Override
   public String toString() {
      return Strings.ff("0x%X:%s[%s]", this.q, DUtil.formatVarId(this.RF), this.xK ? "use" : "def");
   }
}
