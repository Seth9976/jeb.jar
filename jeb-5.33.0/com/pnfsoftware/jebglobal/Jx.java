package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

public class Jx {
   public static final Jx pC = new Jx((byte)0, 0L, 0L);
   public byte A;
   public long kS;
   public long wS;
   public long UT;

   public static void pC(Jx var0) {
      if (!var0.pC()) {
         ul.pC(var0.A);
         if (var0.UT < 0L) {
            throw new yb("Illegal JDWPLocation index: " + var0.UT);
         }
      }
   }

   public Jx(byte var1, long var2, long var4, long var6) {
      this.A = var1;
      this.kS = var2;
      this.wS = var4;
      this.UT = var6;
   }

   public Jx(byte var1, long var2, long var4) {
      this(var1, var2, var4, 0L);
   }

   public boolean pC() {
      return this.equals(pC);
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (int)(this.kS ^ this.kS >>> 32);
      var1 = 31 * var1 + (int)(this.UT ^ this.UT >>> 32);
      var1 = 31 * var1 + (int)(this.wS ^ this.wS >>> 32);
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
         Jx var2 = (Jx)var1;
         if (this.kS != var2.kS) {
            return false;
         } else if (this.UT != var2.UT) {
            return false;
         } else {
            return this.wS != var2.wS ? false : this.A == var2.A;
         }
      }
   }

   @Override
   public String toString() {
      return this.pC() ? "NO_LOCATION" : Strings.ff("%s=%d.%d:%d", ul.pC(this.A), this.kS, this.wS, this.UT);
   }
}
