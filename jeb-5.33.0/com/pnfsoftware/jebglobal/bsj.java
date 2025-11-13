package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.util.format.Strings;

public class bsj {
   private long pC;
   private int A;
   private boolean kS;

   public bsj(long var1, int var3, boolean var4) {
      this.pC = var1;
      this.A = var3;
      this.kS = var4;
   }

   public bsj(long var1, int var3) {
      this.pC = var1;
      this.A = var3;
   }

   public long kS() {
      return this.pC;
   }

   public int wS() {
      return this.A;
   }

   public boolean UT() {
      return this.kS;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (int)(this.pC ^ this.pC >>> 32);
      var1 = 31 * var1 + (this.kS ? 1231 : 1237);
      return 31 * var1 + this.A;
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
         bsj var2 = (bsj)var1;
         if (this.pC != var2.pC) {
            return false;
         } else {
            return this.kS != var2.kS ? false : this.A == var2.A;
         }
      }
   }

   @Override
   public String toString() {
      return Strings.ff("0x%X:%s[%s]", this.pC, DUtil.formatVarId(this.A), this.kS ? "use" : "def");
   }
}
